package com.entrobus.credit.user.services.impl;

import com.alibaba.fastjson.JSONArray;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.common.util.HttpClientUtil;
import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.order.CreditReportExample;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.user.client.BsStaticsClient;
import com.entrobus.credit.user.dao.CreditReportMapper;
import com.entrobus.credit.user.services.CreditReportService;
import com.entrobus.credit.user.services.UserInfoService;
import com.entrobus.credit.vo.user.CacheUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CreditReportServiceImpl implements CreditReportService {

    @Autowired
    private CreditReportMapper creditReportMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private BsStaticsClient bsStaticsClient;

    private static final Logger logger = LoggerFactory.getLogger(CreditReportServiceImpl.class);

    public int countByExample(CreditReportExample example) {
        int count = this.creditReportMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CreditReport selectByPrimaryKey(String id) {
        return this.creditReportMapper.selectByPrimaryKey(id);
    }

    public List<CreditReport> selectByExample(CreditReportExample example) {
        return this.creditReportMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.creditReportMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CreditReport record) {
        record.setUpdateTime(new Date());
        return this.creditReportMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CreditReport record) {
        record.setUpdateTime(new Date());
        return this.creditReportMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(CreditReportExample example) {
        return this.creditReportMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(CreditReport record, CreditReportExample example) {
        record.setUpdateTime(new Date());
        return this.creditReportMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(CreditReport record, CreditReportExample example) {
        record.setUpdateTime(new Date());
        return this.creditReportMapper.updateByExample(record, example);
    }

    public int insert(CreditReport record) {
        defaultValue(record);
        return this.creditReportMapper.insert(record);
    }

    public int insertSelective(CreditReport record) {
        defaultValue(record);
        return this.creditReportMapper.insertSelective(record);
    }

    protected void defaultValue(CreditReport record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
    }

    @Override
    public CreditReport getCreditReportByUid(CacheUserInfo loginUser) {
        CreditReportExample example = new CreditReportExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO).andUserIdEqualTo(loginUser.getId());
        example.setOrderByClause(" create_time desc ");
        List<CreditReport> list = selectByExample(example);
        if (!list.isEmpty()) {
            CreditReport creditReport = list.get(0);
            long time = new Date().getTime() - creditReport.getCreateTime().getTime();
            if(time/(1000*60*60*24) < 30){
                return creditReport;
            }
        }
        if(StringUtils.isNotBlank(loginUser.getIdCard())){
            //@TODO 获取信用报告
            String url = bsStaticsClient.getCodeName(Constants.CODE_TYPE.THREE_URL, "CREDIT_REPORT");
            String json = HttpClientUtil.doGet(url + loginUser.getIdCard());
            if(StringUtils.isNotBlank(json)){
                Map map = (Map) JSONArray.parse(json);
                if(map != null){
                    String msg = (String) map.get("msg");
                    if(StringUtils.isNotBlank(msg) && msg.equals("success")){
                        Map rmap = (Map) map.get("result");
                        if(rmap != null){
                            Double creditScore = null;
                            try {
                                creditScore = ((BigDecimal) rmap.get("score")).doubleValue();
                            } catch (Exception e) {
                                creditScore = 0d;
                            }
                            String reportUrl = (String) rmap.get("url_report");
                            Long quota = null;
                            try {
                                quota = ((Integer) rmap.get("max_loan")).longValue();
                            } catch (Exception e) {
                                quota = 0l;
                            }
                            CreditReport cr=new CreditReport();
                            cr.setUserId(loginUser.getId());
                            cr.setId(GUIDUtil.genRandomGUID());
                            cr.setCreditScore(creditScore);
                            cr.setQuota(quota);
                            cr.setReportUrl(reportUrl);
                            cr.setCreateTime(new Date());
                            this.insertSelective(cr);
                            UserInfo userInfo = new UserInfo();
                            userInfo.setId(loginUser.getId());
                            userInfo.setCreditScore(creditScore);
                            userInfo.setQuota(quota);
                            userInfo.setUpdateTime(new Date());
                            userInfo.setUpdateOperator(userInfo.getId());
                            userInfoService.updateByPrimaryKeySelective(userInfo);
                            userInfoService.initUserCache(userInfo);
                            return cr;
                        }
                    }
                }
            }
        }
        return null;
    }
}