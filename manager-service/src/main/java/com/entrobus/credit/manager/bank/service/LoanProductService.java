package com.entrobus.credit.manager.bank.service;

import com.entrobus.credit.pojo.manager.LoanProduct;
import com.entrobus.credit.pojo.manager.LoanProductExample;

import java.util.List;

public interface LoanProductService {
    int countByExample(LoanProductExample example);

    LoanProduct selectByPrimaryKey(String id);

    List<LoanProduct> selectByExample(LoanProductExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LoanProduct record);

    int updateByPrimaryKey(LoanProduct record);

    int deleteByExample(LoanProductExample example);

    int updateByExampleSelective(LoanProduct record, LoanProductExample example);

    int updateByExample(LoanProduct record, LoanProductExample example);

    int insert(LoanProduct record);

    int insertSelective(LoanProduct record);
}