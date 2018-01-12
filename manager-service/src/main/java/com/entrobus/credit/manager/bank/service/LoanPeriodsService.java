package com.entrobus.credit.manager.bank.service;

import com.entrobus.credit.pojo.manager.LoanPeriods;
import com.entrobus.credit.pojo.manager.LoanPeriodsExample;

import java.util.List;

public interface LoanPeriodsService {
    int countByExample(LoanPeriodsExample example);

    LoanPeriods selectByPrimaryKey(String id);

    List<LoanPeriods> selectByExample(LoanPeriodsExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LoanPeriods record);

    int updateByPrimaryKey(LoanPeriods record);

    int deleteByExample(LoanPeriodsExample example);

    int updateByExampleSelective(LoanPeriods record, LoanPeriodsExample example);

    int updateByExample(LoanPeriods record, LoanPeriodsExample example);

    int insert(LoanPeriods record);

    int insertSelective(LoanPeriods record);
}