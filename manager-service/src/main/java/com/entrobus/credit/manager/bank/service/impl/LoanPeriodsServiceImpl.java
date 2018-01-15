package com.entrobus.credit.manager.bank.service.impl;

import com.entrobus.credit.manager.bank.service.LoanPeriodsService;
import com.entrobus.credit.manager.dao.LoanPeriodsMapper;
import com.entrobus.credit.pojo.manager.LoanPeriods;
import com.entrobus.credit.pojo.manager.LoanPeriodsExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanPeriodsServiceImpl implements LoanPeriodsService {
    @Autowired
    private LoanPeriodsMapper loanPeriodsMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoanPeriodsServiceImpl.class);

    public int countByExample(LoanPeriodsExample example) {
        int count = this.loanPeriodsMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public LoanPeriods selectByPrimaryKey(String id) {
        return this.loanPeriodsMapper.selectByPrimaryKey(id);
    }

    public List<LoanPeriods> selectByExample(LoanPeriodsExample example) {
        return this.loanPeriodsMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.loanPeriodsMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(LoanPeriods record) {
        return this.loanPeriodsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(LoanPeriods record) {
        return this.loanPeriodsMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(LoanPeriodsExample example) {
        return this.loanPeriodsMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(LoanPeriods record, LoanPeriodsExample example) {
        return this.loanPeriodsMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(LoanPeriods record, LoanPeriodsExample example) {
        return this.loanPeriodsMapper.updateByExample(record, example);
    }

    public int insert(LoanPeriods record) {
        return this.loanPeriodsMapper.insert(record);
    }

    public int insertSelective(LoanPeriods record) {
        return this.loanPeriodsMapper.insertSelective(record);
    }
}