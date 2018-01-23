package com.entrobus.credit.manager.bank.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.manager.bank.service.LoanInterestRateService;
import com.entrobus.credit.manager.bank.service.LoanProductService;
import com.entrobus.credit.manager.bank.service.OrganizationService;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.service.ManagerCacheService;
import com.entrobus.credit.manager.dao.LoanProductMapper;
import com.entrobus.credit.pojo.manager.*;
import com.entrobus.credit.vo.loan.LoanConfigureVo;
import com.entrobus.credit.vo.loan.LoanPeriodsRateVo;
import com.entrobus.credit.vo.loan.LoanProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LoanProductServiceImpl implements LoanProductService {
    @Autowired
    private LoanProductMapper loanProductMapper;
    @Autowired
    private ManagerCacheService managerCacheService;
    @Autowired
    private LoanInterestRateService loanInterestRateService;
    @Autowired
    private OrganizationService organizationService;

    private static final Logger logger = LoggerFactory.getLogger(LoanProductServiceImpl.class);

    public int countByExample(LoanProductExample example) {
        int count = this.loanProductMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public LoanProduct selectByPrimaryKey(String id) {
        return this.loanProductMapper.selectByPrimaryKey(id);
    }

    public List<LoanProduct> selectByExample(LoanProductExample example) {
        return this.loanProductMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.loanProductMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(LoanProduct record) {
        return this.loanProductMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(LoanProduct record) {
        return this.loanProductMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(LoanProductExample example) {
        return this.loanProductMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(LoanProduct record, LoanProductExample example) {
        return this.loanProductMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(LoanProduct record, LoanProductExample example) {
        return this.loanProductMapper.updateByExample(record, example);
    }

    public int insert(LoanProduct record) {
        return this.loanProductMapper.insert(record);
    }

    public int insertSelective(LoanProduct record) {
        return this.loanProductMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public WebResult save(LoanConfigureVo loanConfigureVo) {
        try {
            SysLoginUserInfo userInfo = managerCacheService.getCurrLoginUser();
            JSONArray jsonArray = JSONArray.parseArray(loanConfigureVo.getStrLoanPeriodsRateVoList());
            List<LoanPeriodsRateVo> rateVoList = jsonArray.toJavaList(LoanPeriodsRateVo.class);
            //创建或更新loan_product
            if (ConversionUtil.isEmptyParameter(loanConfigureVo.getId())) {
                //新增
                LoanProduct loanProduct = createProduct(userInfo.getOrgId(), loanConfigureVo.getRemark(), userInfo.getId());
                Integer result = this.insertSelective(loanProduct);
                if (result > 0) {
                    insertRate(rateVoList, loanProduct.getId());
                }
            }
            if (ConversionUtil.isNotEmptyParameter(loanConfigureVo.getId())) {
                //编辑
                LoanProduct loanProduct = this.selectByPrimaryKey(loanConfigureVo.getId());
                loanProduct.setRemark(loanConfigureVo.getRemark());
                loanProduct.setOrgId(userInfo.getOrgId());
                loanProduct.setUpdateTime(new Date());
                loanProduct.setUpdateUser(userInfo.getId());
                Integer result = this.updateByPrimaryKeySelective(loanProduct);
                if (result > 0) {
                    //删除原有期数利率配置
                    LoanInterestRateExample rateExample = new LoanInterestRateExample();
                    rateExample.createCriteria().andLoanProductIdEqualTo(loanProduct.getId());
                    loanInterestRateService.deleteByExample(rateExample);
                    insertRate(rateVoList, loanProduct.getId());
                }
            }
            return WebResult.ok("设置成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResult.fail("设置失败");
    }

    @Override
    public WebResult detail() {
        SysLoginUserInfo userInfo = managerCacheService.getCurrLoginUser();
        LoanProductExample example = new LoanProductExample();
        example.createCriteria().andOrgIdEqualTo(userInfo.getOrgId())
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        example.setOrderByClause(" create_time desc ");
        List<LoanProduct> productList = this.selectByExample(example);
        Map<String, Object> dataMap = new HashMap<>();
        if (ConversionUtil.isEmptyParameter(productList)) {
            return WebResult.fail(101, "暂未设置贷款信息");
        }
        LoanProduct product = productList.get(0);
        dataMap.put("id", product.getId());
        dataMap.put("orgId", product.getOrgId());
        dataMap.put("remark", product.getRemark());
        //资金方
        Organization organization = organizationService.selectByPrimaryKey(product.getOrgId());
        dataMap.put("orgName", organization.getName());
        //期数利率
        LoanInterestRateExample rateExample = new LoanInterestRateExample();
        rateExample.createCriteria().andLoanProductIdEqualTo(product.getId())
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        rateExample.setOrderByClause(" create_time asc");
        List<LoanInterestRate> interestRateList = loanInterestRateService.selectByExample(rateExample);
        TreeSet<Integer> set = new TreeSet<>();
        TreeSet<Integer> repaymentTypes = new TreeSet<>();
        Map<String, String> rateMap = new HashMap<>();
        List<LoanPeriodsRateVo> rateList = new ArrayList<>();
        for (LoanInterestRate rate : interestRateList) {
            set.add(rate.getPeriods());
            repaymentTypes.add(rate.getRepaymentType());
            rateMap.put(rate.getPeriods() + "type" + rate.getRepaymentType(), ConversionUtil.rateToString(rate.getInterestRate()));
        }
        for (Integer periods : set) {
            LoanPeriodsRateVo vo = new LoanPeriodsRateVo();
            vo.setPeriods(periods);
            vo.setInterestCapitalRate(rateMap.get(periods + "type" + Constants.REPAYMENT_TYPE.INTEREST_CAPITAL));
            vo.setMonthEqualRate(rateMap.get(periods + "type" + Constants.REPAYMENT_TYPE.MONTH_EQUAL));
            rateList.add(vo);
        }
        dataMap.put("rateList", rateList);
        dataMap.put("repaymentTypes", repaymentTypes);
        return WebResult.ok(dataMap);
    }

    @Override
    public LoanProductVo getInfoById(String id) {
        LoanProduct product = this.selectByPrimaryKey(id);
        return getLoanProductInfo(product);
    }

    @Override
    public LoanProductVo getInfoByOrgId(String orgId) {
        LoanProductExample example = new LoanProductExample();
        example.createCriteria().andOrgIdEqualTo(orgId)
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        example.setOrderByClause(" create_time desc ");
        List<LoanProduct> productList = this.selectByExample(example);
        if (ConversionUtil.isEmptyParameter(productList)) {
            return null;
        }
        LoanProduct product = productList.get(0);
        return getLoanProductInfo(product);
    }

    @Override
    public boolean checkProd(String prodId, Integer repaymentType, Integer repaymentTerm, Long rate) {
        LoanInterestRateExample rateExample = new LoanInterestRateExample();
        rateExample.createCriteria().andLoanProductIdEqualTo(prodId).andInterestRateEqualTo(rate)
                .andPeriodsEqualTo(repaymentTerm).andRepaymentTypeEqualTo(repaymentType)
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        return loanInterestRateService.countByExample(rateExample) == 0 ? false : true;
    }

    private LoanProductVo getLoanProductInfo(LoanProduct product) {
        LoanProductVo loanProductVo = new LoanProductVo();
        loanProductVo.setId(product.getId());
        loanProductVo.setOrgId(product.getOrgId());
        loanProductVo.setLoanDateType(product.getLoanDateType());
        loanProductVo.setRemark(product.getRemark());
        //期数利率
        LoanInterestRateExample rateExample = new LoanInterestRateExample();
        rateExample.createCriteria().andLoanProductIdEqualTo(product.getId())
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        rateExample.setOrderByClause(" create_time asc");
        List<LoanInterestRate> interestRateList = loanInterestRateService.selectByExample(rateExample);
        TreeSet<Integer> set = new TreeSet<>();
        Map<String, String> rateMap = new HashMap<>();
        List<LoanPeriodsRateVo> rateList = new ArrayList<>();
        for (LoanInterestRate rate : interestRateList) {
            set.add(rate.getPeriods());
            rateMap.put(rate.getPeriods() + "type" + rate.getRepaymentType(), ConversionUtil.rateToString(rate.getInterestRate()));
        }
        for (Integer periods : set) {
            String interestCapitalRate = rateMap.get(periods + "type" + Constants.REPAYMENT_TYPE.INTEREST_CAPITAL);
            String monthEqualRate = rateMap.get(periods + "type" + Constants.REPAYMENT_TYPE.MONTH_EQUAL);
            List<Integer> repaymentTypeList = new ArrayList<>();
            LoanPeriodsRateVo vo = new LoanPeriodsRateVo();
            vo.setPeriods(periods);
            if (ConversionUtil.isNotEmptyParameter(interestCapitalRate)) {
                vo.setInterestCapitalRate(interestCapitalRate);
                repaymentTypeList.add(Constants.REPAYMENT_TYPE.INTEREST_CAPITAL);
            }
            if (ConversionUtil.isNotEmptyParameter(monthEqualRate)) {
                vo.setMonthEqualRate(monthEqualRate);
                repaymentTypeList.add(Constants.REPAYMENT_TYPE.MONTH_EQUAL);
            }
            vo.setRepaymentTypeList(repaymentTypeList);
            rateList.add(vo);
        }
        loanProductVo.setLoanPeriodsRateVoList(rateList);
        return loanProductVo;
    }

    /**
     * 创建贷款利率实体
     *
     * @param periods
     * @param repaymentType
     * @param interestRate
     * @return
     */
    private LoanInterestRate createRate(String productId, Integer periods, Integer repaymentType, String interestRate) {
        LoanInterestRate rate = new LoanInterestRate();
        rate.setId(GUIDUtil.genRandomGUID());
        rate.setLoanProductId(productId);
        rate.setPeriods(periods);
        rate.setRepaymentType(repaymentType);
        rate.setInterestRate(ConversionUtil.rateToLong(interestRate));
        rate.setCreateTime(new Date());
        rate.setUpdateTime(new Date());
        rate.setDeleteFlag(Constants.DELETE_FLAG.NO);
        return rate;
    }

    private LoanProduct createProduct(String orgId, String remark, Long sysUserId) {
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setId(GUIDUtil.genRandomGUID());
        loanProduct.setOrgId(orgId);
        loanProduct.setLoanDateType(0);
        loanProduct.setRemark(remark);
        loanProduct.setCreateTime(new Date());
        loanProduct.setUpdateTime(new Date());
        loanProduct.setCreateUser(sysUserId);
        loanProduct.setUpdateUser(sysUserId);
        loanProduct.setDeleteFlag(Constants.DELETE_FLAG.NO);
        return loanProduct;
    }

    /**
     * 保存贷款设置下的期数利率
     *
     * @param rateVoList
     * @param productId
     */
    private void insertRate(List<LoanPeriodsRateVo> rateVoList, String productId) {
        List<LoanInterestRate> interestRateList = new ArrayList<>();
        for (LoanPeriodsRateVo periodsRateVo : rateVoList) {
            if (ConversionUtil.isNotEmptyParameter(periodsRateVo.getInterestCapitalRate())) {
                interestRateList.add(createRate(productId, periodsRateVo.getPeriods(), Constants.REPAYMENT_TYPE.INTEREST_CAPITAL, periodsRateVo.getInterestCapitalRate()));
            }
            if (ConversionUtil.isNotEmptyParameter(periodsRateVo.getMonthEqualRate())) {
                interestRateList.add(createRate(productId, periodsRateVo.getPeriods(), Constants.REPAYMENT_TYPE.MONTH_EQUAL, periodsRateVo.getMonthEqualRate()));
            }
        }
        loanInterestRateService.insertBatchSelective(interestRateList);
    }
}