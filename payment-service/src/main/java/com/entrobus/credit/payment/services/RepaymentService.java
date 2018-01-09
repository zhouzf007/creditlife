package com.entrobus.credit.payment.services;

import com.entrobus.credit.pojo.payment.Repayment;
import com.entrobus.credit.pojo.payment.RepaymentExample;
import java.util.List;

public interface RepaymentService {
    int countByExample(RepaymentExample example);

    Repayment selectByPrimaryKey(String id);

    List<Repayment> selectByExample(RepaymentExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Repayment record);

    int updateByPrimaryKey(Repayment record);

    int deleteByExample(RepaymentExample example);

    int updateByExampleSelective(Repayment record, RepaymentExample example);

    int updateByExample(Repayment record, RepaymentExample example);

    int insert(Repayment record);

    int insertSelective(Repayment record);
}