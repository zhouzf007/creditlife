package com.entrobus.credit.manager.bank.service;

import com.entrobus.credit.pojo.manager.LoanInterestRate;
import com.entrobus.credit.pojo.manager.LoanInterestRateExample;

import java.util.List;

public interface LoanInterestRateService {
    int countByExample(LoanInterestRateExample example);

    LoanInterestRate selectByPrimaryKey(String id);

    List<LoanInterestRate> selectByExample(LoanInterestRateExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LoanInterestRate record);

    int updateByPrimaryKey(LoanInterestRate record);

    int deleteByExample(LoanInterestRateExample example);

    int updateByExampleSelective(LoanInterestRate record, LoanInterestRateExample example);

    int updateByExample(LoanInterestRate record, LoanInterestRateExample example);

    int insert(LoanInterestRate record);

    int insertSelective(LoanInterestRate record);

    int insertBatchSelective(List<LoanInterestRate> records);

}