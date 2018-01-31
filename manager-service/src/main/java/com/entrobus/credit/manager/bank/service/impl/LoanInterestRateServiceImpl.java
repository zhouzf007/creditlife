package com.entrobus.credit.manager.bank.service.impl;

import com.entrobus.credit.manager.bank.service.LoanInterestRateService;
import com.entrobus.credit.manager.dao.LoanInterestRateMapper;
import com.entrobus.credit.pojo.manager.LoanInterestRate;
import com.entrobus.credit.pojo.manager.LoanInterestRateExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LoanInterestRateServiceImpl implements LoanInterestRateService {
    @Autowired
    private LoanInterestRateMapper loanInterestRateMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoanInterestRateServiceImpl.class);

    public int countByExample(LoanInterestRateExample example) {
        int count = this.loanInterestRateMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public LoanInterestRate selectByPrimaryKey(String id) {
        return this.loanInterestRateMapper.selectByPrimaryKey(id);
    }

    public List<LoanInterestRate> selectByExample(LoanInterestRateExample example) {
        return this.loanInterestRateMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.loanInterestRateMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(LoanInterestRate record) {
        return this.loanInterestRateMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(LoanInterestRate record) {
        return this.loanInterestRateMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(LoanInterestRateExample example) {
        return this.loanInterestRateMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(LoanInterestRate record, LoanInterestRateExample example) {
        return this.loanInterestRateMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(LoanInterestRate record, LoanInterestRateExample example) {
        return this.loanInterestRateMapper.updateByExample(record, example);
    }

    public int insert(LoanInterestRate record) {
        return this.loanInterestRateMapper.insert(record);
    }

    public int insertSelective(LoanInterestRate record) {
        return this.loanInterestRateMapper.insertSelective(record);
    }

    @Override
    public int insertBatchSelective(List<LoanInterestRate> records) {
        return this.loanInterestRateMapper.insertBatchSelective(records);
    }
}