package com.entrobus.credit.order.services.impl;

import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.AmountUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.order.client.ProductionClient;
import com.entrobus.credit.order.client.UserClient;
import com.entrobus.credit.order.dao.OrdersMapper;
import com.entrobus.credit.order.services.ContractService;
import com.entrobus.credit.order.services.OrderCacheService;
import com.entrobus.credit.order.services.OrdersService;
import com.entrobus.credit.pojo.order.Contract;
import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.order.OrdersExample;
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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductionClient productionClient;

    @Autowired
    private OrderCacheService cacheService;

    @Autowired
    private ContractService contractService;

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
        //@TODO 检验是否可以贷款
        //当前申请情况
        //信用报告信息
        Contract contract = new Contract();
        CreditReport creditReport = userClient.getCreditReport(userInfo.getId());
        if (creditReport != null) {
            order.setCreditReportId(creditReport.getId());
            order.setCreditScore(creditReport.getCreditScore());
            contract.setCreditReportId(creditReport.getId());
            contract.setCreditScore(creditReport.getCreditScore());
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
            return WebResult.fail("贷款产品不合法");
        }
        order.setLoanUsage(vo.getUsage());
        order.setApplyMoney(vo.getMoney());
        order.setApplyTime(new Date());

        //加入用户信息
        order.setRole(userInfo.getRole() + "");
        order.setAccount(userInfo.getDefualtAccount());

        //生成合同信息
        contract.setId(GUIDUtil.genRandomGUID());
        contract.setOrderId(order.getId());
        contract.setUserId(order.getUserId());
        contract.setSignature(vo.getSignature());
        contractService.insertSelective(contract);
        order.setContractId(contract.getId());
        this.insertSelective(order);
        Map rsMap = new HashMap<>();
        rsMap.put("applyNo", order.getApplyNo());
        return WebResult.ok(rsMap);
    }

    public WebResult getUserOrderList(Integer state, String orgId, Integer offset, Integer limit) throws Exception {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        if (state != null) {
            criteria.andStateEqualTo(state);
        }
        if (StringUtils.isNotEmpty(orgId)) {
            criteria.andOrgIdEqualTo(orgId);
        }
        example.setOrderByClause(" create_time desc ");
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
    public WebResult getOrderList(Integer state, String orgId, Integer offset, Integer limit) throws Exception {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        if (state != null) {
            criteria.andStateEqualTo(state);
        }
        if (StringUtils.isNotEmpty(orgId)) {
            criteria.andOrgIdEqualTo(orgId);
        }
        example.setOrderByClause(" create_time desc ");
        if (limit != null && offset != null) {
            example.setLimitStart(offset);
            example.setLimitEnd(limit);
        }
        PageHelper.startPage(offset, limit);
        List<Orders> list = this.selectByExample(example);
        List<OrderListVo> rsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Orders order = list.get(i);
            OrderListVo orderVo = new OrderListVo();
            CacheUserInfo userInfo = cacheService.getUserCacheByUid(order.getUserId());
            orderVo.setId(order.getId());
            orderVo.setUserName(userInfo.getRealName());
            orderVo.setApplyTime(order.getApplyTime());
            orderVo.setApplyNo(order.getApplyNo());
            orderVo.setState(order.getState());
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
    public List<UserOrdersVo> getUserOrderList(String id, Integer offset, Integer limit) {
        OrdersExample example = new OrdersExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO).
                andUserIdEqualTo(id);
        example.setOrderByClause(" apply_time desc ");
        List<Orders> list = ordersMapper.selectByExample(example);
        List<UserOrdersVo> rsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Orders order = list.get(i);
            UserOrdersVo vo = new UserOrdersVo();
            rsList.add(vo);
        }
        return rsList;
    }

}