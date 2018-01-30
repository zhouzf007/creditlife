package com.entrobus.credit.contract.services;

import com.entrobus.credit.pojo.order.Contract;
import com.entrobus.credit.pojo.order.ContractExample;

import java.util.List;

public interface ContractService {
    int countByExample(ContractExample example);

    Contract selectByPrimaryKey(String id);

    List<Contract> selectByExample(ContractExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);

    int deleteByExample(ContractExample example);

    int updateByExampleSelective(Contract record, ContractExample example);

    int updateByExample(Contract record, ContractExample example);

    int insert(Contract record);

    int insertSelective(Contract record);
}