package com.entrobus.credit.payment.services;

import com.entrobus.credit.pojo.payment.RepaymentInstance;
import com.entrobus.credit.pojo.payment.RepaymentInstanceExample;
import java.util.List;

public interface RepaymentInstanceService {
    int countByExample(RepaymentInstanceExample example);

    RepaymentInstance selectByPrimaryKey(String id);

    List<RepaymentInstance> selectByExample(RepaymentInstanceExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepaymentInstance record);

    int updateByPrimaryKey(RepaymentInstance record);

    int deleteByExample(RepaymentInstanceExample example);

    int updateByExampleSelective(RepaymentInstance record, RepaymentInstanceExample example);

    int updateByExample(RepaymentInstance record, RepaymentInstanceExample example);

    int insert(RepaymentInstance record);

    int insertSelective(RepaymentInstance record);
}