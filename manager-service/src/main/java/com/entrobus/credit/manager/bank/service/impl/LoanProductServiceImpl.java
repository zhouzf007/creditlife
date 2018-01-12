package com.entrobus.credit.manager.bank.service.impl;

import com.entrobus.credit.manager.bank.service.LoanProductService;
import com.entrobus.credit.manager.dao.LoanProductMapper;
import com.entrobus.credit.pojo.manager.LoanProduct;
import com.entrobus.credit.pojo.manager.LoanProductExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanProductServiceImpl implements LoanProductService {
    @Autowired
    private LoanProductMapper loanProductMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoanProductServiceImpl.class);

    public int countByExample(LoanProductExample example) {
        int count = this.loanProductMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public LoanProduct selectByPrimaryKey(String id) {
        return this.loanProductMapper.selectByPrimaryKey(id);
    }

    public List<LoanProduct> selectByExample(LoanProductExample example) {
        return this.loanProductMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.loanProductMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(LoanProduct record) {
        return this.loanProductMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(LoanProduct record) {
        return this.loanProductMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(LoanProductExample example) {
        return this.loanProductMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(LoanProduct record, LoanProductExample example) {
        return this.loanProductMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(LoanProduct record, LoanProductExample example) {
        return this.loanProductMapper.updateByExample(record, example);
    }

    public int insert(LoanProduct record) {
        return this.loanProductMapper.insert(record);
    }

    public int insertSelective(LoanProduct record) {
        return this.loanProductMapper.insertSelective(record);
    }
}