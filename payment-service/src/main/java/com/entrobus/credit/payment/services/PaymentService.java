package com.entrobus.credit.payment.services;

import com.entrobus.credit.pojo.payment.Payment;
import com.entrobus.credit.pojo.payment.PaymentExample;
import java.util.List;

public interface PaymentService {
    int countByExample(PaymentExample example);

    Payment selectByPrimaryKey(String id);

    List<Payment> selectByExample(PaymentExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);

    int deleteByExample(PaymentExample example);

    int updateByExampleSelective(Payment record, PaymentExample example);

    int updateByExample(Payment record, PaymentExample example);

    int insert(Payment record);

    int insertSelective(Payment record);
}