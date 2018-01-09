package com.entrobus.credit.order.services.impl;

import com.entrobus.credit.order.dao.ContractMapper;
import com.entrobus.credit.order.services.ContractService;
import com.entrobus.credit.pojo.order.Contract;
import com.entrobus.credit.pojo.order.ContractExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractMapper contractMapper;

    private static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

    public int countByExample(ContractExample example) {
        int count = this.contractMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Contract selectByPrimaryKey(String id) {
        return this.contractMapper.selectByPrimaryKey(id);
    }

    public List<Contract> selectByExample(ContractExample example) {
        return this.contractMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.contractMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Contract record) {
        return this.contractMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Contract record) {
        return this.contractMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(ContractExample example) {
        return this.contractMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Contract record, ContractExample example) {
        return this.contractMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Contract record, ContractExample example) {
        return this.contractMapper.updateByExample(record, example);
    }

    public int insert(Contract record) {
        return this.contractMapper.insert(record);
    }

    public int insertSelective(Contract record) {
        return this.contractMapper.insertSelective(record);
    }
}