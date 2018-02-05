package com.entrobus.credit.order.services.impl;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.*;
import com.entrobus.credit.order.client.CreditClient;
import com.entrobus.credit.order.client.PaymentClient;
import com.entrobus.credit.order.client.ProductionClient;
import com.entrobus.credit.order.client.UserClient;
import com.entrobus.credit.order.dao.OrdersMapper;
import com.entrobus.credit.order.services.OrderCacheService;
import com.entrobus.credit.order.services.OrdersService;
import com.entrobus.credit.pojo.order.Contract;
import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.order.OrdersExample;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.entrobus.credit.vo.contract.ContractFillVo;
import com.entrobus.credit.vo.order.ApplyVo;
import com.entrobus.credit.vo.order.OrderListVo;
import com.entrobus.credit.vo.order.UserOrderListVo;
import com.entrobus.credit.vo.order.UserOrdersVo;
import com.entrobus.credit.vo.user.CacheUserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserClient userClient;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private ProductionClient productionClient;

    @Autowired
    private OrderCacheService cacheService;

    @Autowired
    private CreditClient creditClient;

    private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

    public int countByExample(OrdersExample example) {
        int count = this.ordersMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Orders selectByPrimaryKey(String id) {
        return this.ordersMapper.selectByPrimaryKey(id);
    }

    public List<Orders> selectByExample(OrdersExample example) {
        return this.ordersMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.ordersMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Orders record) {
        record.setUpdateTime(new Date());
        return this.ordersMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Orders record) {
        record.setUpdateTime(new Date());
        return this.ordersMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(OrdersExample example) {
        return this.ordersMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Orders record, OrdersExample example) {
        record.setUpdateTime(new Date());
        return this.ordersMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Orders record, OrdersExample example) {
        record.setUpdateTime(new Date());
        return this.ordersMapper.updateByExample(record, example);
    }

    public int insert(Orders record) {
        defaultValue(record);
        return this.ordersMapper.insert(record);
    }

    public int insertSelective(Orders record) {
        defaultValue(record);
        return this.ordersMapper.insertSelective(record);
    }

    protected void defaultValue(Orders record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
    }

    @Override
    public List<Orders> getUserOrders(String userId) {
        OrdersExample example = new OrdersExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO).
                andUserIdEqualTo(userId);
        return this.ordersMapper.selectByExample(example);
    }

    @Override
    public Orders getUserLastOrder(String userId) {
        OrdersExample example = new OrdersExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO).
                andUserIdEqualTo(userId);
        example.setOrderByClause(" create_time desc ");
        List<Orders> list = this.ordersMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


    @Override
    public WebResult applyOrder(@RequestBody ApplyVo vo, CacheUserInfo userInfo) {
        Orders order = new Orders();
        //1.当前角色是否可以贷款
        if (userInfo.getRole() == 0) {
            return WebResult.fail(WebResult.CODE_NO_PERMISSION, "目前该用户角色不可申请贷款");
        }
        //2.当前贷款状态
        Orders lastOrder = this.getUserLastOrder(userInfo.getId());
        if (lastOrder != null) {
            if (lastOrder.getState() == Constants.ORDER_STATE.AUIDT_PENGDING) {
                return WebResult.fail(WebResult.CODE_NO_PERMISSION, "您已提交申请，正在审核中，请耐心等候");
            }
            if (lastOrder.getState() == Constants.ORDER_STATE.PASS || lastOrder.getState() == Constants.ORDER_STATE.OVERDUE) {
                return WebResult.fail(WebResult.CODE_NO_PERMISSION, "您有贷款正在使用中，暂不支持重新申请");
            }
            if (lastOrder.getState() == Constants.ORDER_STATE.LOAN_PENGDING) {
                return WebResult.fail(WebResult.CODE_NO_PERMISSION, "您的申请已经审核通过，请耐心等待放款");
            }
        }
        //信用报告信息
        Contract contract = new Contract();
        CreditReport creditReport = userClient.getUserCreditReport(userInfo.getId());
        if (creditReport != null) {
            order.setCreditReportId(creditReport.getId());
            order.setCreditScore(creditReport.getCreditScore());
            contract.setCreditReportId(creditReport.getId());
            contract.setCreditScore(creditReport.getCreditScore());
            if (vo.getMoney() > creditReport.getQuota()) {
                return WebResult.fail(WebResult.CODE_NO_PERMISSION, "您申请的额度过高，请重新提交申请");
            }
        } else {
            return WebResult.fail("无法获取信用报告，暂时无法申请贷款");
        }
        order.setId(GUIDUtil.genRandomGUID());
        order.setState(Constants.ORDER_STATE.AUIDT_PENGDING);
        order.setSystemState(Constants.ORDER_STATE.AUIDT_PENGDING);
        order.setUserId(userInfo.getId());
        order.setProdId(vo.getProdId());
        String applyNo = cacheService.getOrderApplyNo();
        order.setApplyNo(applyNo);
        //产品合法性检验
        boolean check = productionClient.checkProd(vo.getProdId(), vo.getRepaymentType(), vo.getRepaymentTerm(), vo.getRate());
        //加入产品信息
        if (check) {
            order.setRepaymentTerm(vo.getRepaymentTerm());
            order.setRepaymentType(vo.getRepaymentType());
            order.setInterestRate(vo.getRate());
            order.setOrgId(vo.getOrgId());
        } else {
            return WebResult.fail(WebResult.CODE_NO_PERMISSION, "您申请的贷款产品不存在");
        }
        order.setLoanUsage(vo.getUsage());
        order.setApplyMoney(vo.getMoney() * 100);
        order.setActualMoney(vo.getMoney() * 100);
        order.setApplyTime(new Date());
        //加入用户信息
        order.setRole(userInfo.getRole());
        order.setAccount(userInfo.getDefualtAccount());
        //生成合同信息
        if (StringUtils.isNotEmpty(vo.getSignature())) {
            try {
                ContractFillVo contractvo = createContract(vo, order, userInfo);
                contract = creditClient.saveContract(contractvo);
                if (contract == null) {
                    contract = new Contract();
                    contract.setId(GUIDUtil.genRandomGUID());
                    contract.setOrderId(order.getId());
//                    return WebResult.fail(WebResult.CODE_OPERATION, "合同生产失败，请重试");
                }
            } catch (Exception e) {
//                return WebResult.fail(WebResult.CODE_OPERATION, "合同生产失败，请重试");
            }
        } else {
            return WebResult.fail(WebResult.CODE_BUSI_DISPERMIT, "无效签名");
        }
        order.setContractId(contract.getId());
        this.insertSelective(order);
        Map rsMap = new HashMap<>();
        rsMap.put("applyNo", order.getApplyNo());
        return WebResult.ok((Object) rsMap);
    }

    public WebResult getUserOrderList(List<Integer> states, String orgId, Integer offset, Integer limit) throws Exception {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        if (states != null && states.size() > 0) {
            criteria.andStateIn(states);
        }
        if (StringUtils.isNotEmpty(orgId)) {
            criteria.andOrgIdEqualTo(orgId);
        }
        example.setDistinct(true);
        example.setOrderByClause(" create_time desc ");
        if (offset != null && limit != null)
            PageHelper.startPage(offset, limit);
        List<Orders> list = this.selectByExample(example);
        List<UserOrderListVo> rsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Orders order = list.get(i);
            UserOrderListVo rsorderVo = new UserOrderListVo();
            rsorderVo.setMoney(AmountUtil.changeF2Y(order.getApplyMoney()));
            CacheUserInfo userInfo = cacheService.getUserCacheByUid(order.getUserId());
            rsorderVo.setId(order.getId());
            rsorderVo.setUserName(userInfo.getRealName());
            rsorderVo.setMobile(userInfo.getCellphone());
            rsorderVo.setScore(order.getCreditScore());
            rsorderVo.setUserId(order.getUserId());
            rsorderVo.setState(order.getState());
            rsorderVo.setStateName(cacheService.translate(Cachekey.Translation.ORDER_STATE + order.getState()));
            rsList.add(rsorderVo);
        }
        PageInfo pageInfo = new PageInfo<>(list);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("rows", rsList);
        dataMap.put("total", pageInfo.getTotal());
        return WebResult.ok(dataMap);
    }

    @Override
    public WebResult getOrderList(List<Integer> states, String orgId, Integer offset, Integer limit) throws Exception {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        if (states != null && states.size() > 0) {
            criteria.andStateIn(states);
        }
        if (StringUtils.isNotEmpty(orgId)) {
            criteria.andOrgIdEqualTo(orgId);
        }
        example.setOrderByClause(" create_time desc ");
        if (offset != null && limit != null)
            PageHelper.startPage(offset, limit);
        List<Orders> list = this.selectByExample(example);
        List<OrderListVo> rsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Orders order = list.get(i);
            OrderListVo orderVo = new OrderListVo();
            CacheUserInfo userInfo = cacheService.getUserCacheByUid(order.getUserId());
            orderVo.setId(order.getId());
            orderVo.setUserName(userInfo.getRealName());
            orderVo.setApplyTime(DateUtils.formatDate(order.getApplyTime()));
            orderVo.setApplyNo(order.getApplyNo());
            orderVo.setState(order.getState());
            orderVo.setMoney(PurseUtil.toYuanString(order.getApplyMoney()));
            orderVo.setStateName(cacheService.translate(Cachekey.Translation.ORDER_STATE + order.getState()));
            rsList.add(orderVo);
        }
        PageInfo pageInfo = new PageInfo<>(list);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("rows", rsList);
        dataMap.put("total", pageInfo.getTotal());
        return WebResult.ok(dataMap);
    }

    @Override
    public List<UserOrdersVo> getUserOrderList(String id, Integer offset, Integer limit) throws Exception {
        OrdersExample example = new OrdersExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO).
                andUserIdEqualTo(id);
        example.setOrderByClause(" apply_time desc ");
        List<Orders> list = ordersMapper.selectByExample(example);
        List<UserOrdersVo> rsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Orders order = list.get(i);
            UserOrdersVo vo = new UserOrdersVo();
            vo.setId(order.getId());
            Integer state = order.getSystemState() == Constants.ORDER_STATE.OVERDUE ? Constants.ORDER_STATE.OVERDUE : order.getState();
            vo.setState(state);
            vo.setStateName(cacheService.translate(Cachekey.Translation.ORDER_STATE + state));
            vo.setLoanTime(order.getLoanTime() == null ? order.getApplyTime() : order.getLoanTime());
            vo.setMoney(AmountUtil.changeF2Y(order.getActualMoney()));
            if (order.getState() == Constants.ORDER_STATE.PASS || order.getState() == Constants.ORDER_STATE.OVERDUE) {
                RepaymentPlan plan = paymentClient.getPresentRepaymentPlan(order.getId());
                if (plan != null) {
                    vo.setDueTime(plan.getPlanTime());
                    vo.setTerm(order.getRepaymentTerm()-plan.getCurrentId());
                    vo.setTotalTerm(order.getRepaymentTerm());
                    vo.setPrincipalAndInterest(AmountUtil.changeF2Y(plan.getInterest() + plan.getPrincipal()));
                    vo.setBalance(AmountUtil.changeF2Y(plan.getPenalty()));
                }
            } else if (order.getState() == Constants.ORDER_STATE.FINISHED) {
                vo.setTerm(order.getRepaymentTerm());
                vo.setTotalTerm(order.getRepaymentTerm());
                vo.setPrincipalAndInterest(AmountUtil.changeF2Y(order.getPrincipalInterest()));
                vo.setBalance("0");
            }
            rsList.add(vo);
        }
        return rsList;
    }

    private ContractFillVo createContract(ApplyVo vo, Orders order, CacheUserInfo userInfo) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", order.getId());
        map.put("userId", order.getUserId());
        map.put("creditReportId", order.getCreditReportId());
        map.put("signature", vo.getSignature());
        //
        map.put("contractNumber", order.getApplyNo());//合同编号，与银行管理后台显示的编号一致
        map.put("borrowerFullName", userInfo.getRealName());//借款人全名
        map.put("lenderFullName", "中国建设银行股份有限公司佛山分行");//贷款人全称，将来可配置，目前“中国建设银行股份有限公司佛山分行”

        map.put("borrowerCellphone", userInfo.getCellphone());//借款人手机号
        map.put("borrowerIdCard", userInfo.getIdCard());//借款人证件号（身份证）
        map.put("money", AmountUtil.changeF2Y(order.getApplyMoney()) + "元"); //借款金额
        map.put("capitalMoney", AmountUtil.change2Upper(Double.valueOf(order.getApplyMoney() / 100))); //中文大写金额，如：叁拾万元整
        map.put("term", order.getRepaymentTerm() + "个月");//借款期限
        map.put("interestStartDay", "自放款成功日起计收利息");//起息日
        map.put("repaymentMethod", "");//还款方式

        map.put("appointPayeeAccount", "中国建设银行  4524");//指定收款账户，借款人的银行卡所属银行和卡号最后四位，例如：中国建设银行 2678
        map.put("annualInterestRate", order.getInterestRate() / 100 + "%");//年化利率
        map.put("noOperationInvalidTime", "3个月");//借款额度审批通过之日起无操作失效时间

        map.put("borrowerAddress", "借款人住址");//借款人住址
        map.put("borrowerPostalAddress", "通讯地址");//借款人通讯地址
        map.put("borrowerPostalCode", "邮政编码");//借款人邮政编码
        map.put("borrowerCardBank", userInfo.getAccountBank());//借款人开卡银行
        map.put("borrowerCardId", userInfo.getDefualtAccount());//借款人银行卡号

        map.put("lenderName", userInfo.getAccountBank());//贷款人名称，暂填 中国建设银行
        map.put("lenderAddress", "广东省佛山市佛山大道南327号");//贷款人住址，将来可配置，目前“广东省佛山市佛山大道南327号”
        map.put("lenderPostalAddress", "广东省佛山市佛山大道南327号");//贷款人通讯地址，将来可配置，目前“广东省佛山市佛山大道南327号”
        map.put("lenderPostalCode", "528000");//贷款人通讯地址，将来可配置，目前“528000”
        map.put("lenderPhone", "0757-82781212");//贷款人联系电话，将来可配置，目前“0757-82781212”

        //这里跟通讯地址有什么区别暂时不知道，但是注明了 从个人信用报告接口中返回
        map.put("borrowerMailingAddress", "邮寄地址");//乙方（借款人）邮寄地址，从个人信用报告接口中返回

        map.put("loanValidityPeriodStart", DateUtils.formatDate(new Date(), "yyyy年MM月dd日"));//借款额度有效期开始日期，用户提交申请的当日日期
        map.put("loanValidityPeriodEnd", DateUtils.formatDate(DateUtils.addYears(new Date(), 1), "yyyy年MM月dd日"));//借款额度有效期结束日期，借款额度有效期开始日期 一年后

        //签名图片 使用base64编码，其中png是图片格式
//        map.put("borrowerAutograph", "data:image/png;base64," + "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");
        map.put("borrowerAutograph", ImageUtil.getImageBase64Src(vo.getSignature()));
        JSON.toJSONString(map);
        ContractFillVo contract = JSON.parseObject(JSON.toJSONString(map), ContractFillVo.class);
        return contract;
    }
//        rs.setAnnualInterestRate(vo.getRate()+"%");
//        rs.setAppointPayeeAccount();
//        rs.setBorrowerAddress();
//        rs.setBorrowerAutograph();
//        rs.setBorrowerCardBank();
//        rs.setBorrowerCardId();
//        rs.setBorrowerCellphone();
//        rs.setBorrowerFullName();
//        rs.setBorrowerIdCard();
//        rs.setBorrowerPostalAddress();
//        rs.setBorrowerMailingAddress();
//        rs.setCapitalMoney();
//        rs.setContractNumber();
//        rs.setInterestStartDay();
//        rs.setNoOperationInvalidTime();
//        rs.setTerm();
//        rs.setSignature();
//        rs.setRepaymentMethod();
//        rs.setMoney();
//        rs.setLoanValidityPeriodStart();
//        rs.setLoanValidityPeriodEnd();
//        rs.setLenderPostalCode();
//        rs.setLenderPostalAddress();
//        rs.setLenderPhone();
//        rs.setLenderName();
//        rs.setLenderFullName();
//        rs.setLenderAddress();
//        rs.setBorrowerPostalCode();
}