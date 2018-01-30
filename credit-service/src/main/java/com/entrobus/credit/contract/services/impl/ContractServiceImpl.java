package com.entrobus.credit.contract.services.impl;

import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.contract.dao.ContractMapper;
import com.entrobus.credit.contract.services.ContractService;
import com.entrobus.credit.pojo.order.Contract;
import com.entrobus.credit.pojo.order.ContractExample;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        record.setUpdateTime(new Date());
        return this.contractMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Contract record) {
        record.setUpdateTime(new Date());
        return this.contractMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(ContractExample example) {
        return this.contractMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Contract record, ContractExample example) {
        record.setUpdateTime(new Date());
        return this.contractMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Contract record, ContractExample example) {
        record.setUpdateTime(new Date());
        return this.contractMapper.updateByExample(record, example);
    }

    public int insert(Contract record) {
        defaultValue(record);
        return this.contractMapper.insert(record);
    }

    public int insertSelective(Contract record) {
        defaultValue(record);
        return this.contractMapper.insertSelective(record);
    }

    protected void defaultValue(Contract record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
    }
}