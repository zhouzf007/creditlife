package com.entrobus.credit.order.services.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.order.dao.CreditReportMapper;
import com.entrobus.credit.order.services.CreditReportService;
import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.order.CreditReportExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditReportServiceImpl implements CreditReportService {
    @Autowired
    private CreditReportMapper creditReportMapper;

    private static final Logger logger = LoggerFactory.getLogger(CreditReportServiceImpl.class);

    public int countByExample(CreditReportExample example) {
        int count = this.creditReportMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CreditReport selectByPrimaryKey(String id) {
        return this.creditReportMapper.selectByPrimaryKey(id);
    }

    public List<CreditReport> selectByExample(CreditReportExample example) {
        return this.creditReportMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.creditReportMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CreditReport record) {
        return this.creditReportMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CreditReport record) {
        return this.creditReportMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(CreditReportExample example) {
        return this.creditReportMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(CreditReport record, CreditReportExample example) {
        return this.creditReportMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(CreditReport record, CreditReportExample example) {
        return this.creditReportMapper.updateByExample(record, example);
    }

    public int insert(CreditReport record) {
        return this.creditReportMapper.insert(record);
    }

    public int insertSelective(CreditReport record) {
        return this.creditReportMapper.insertSelective(record);
    }

    @Override
    public CreditReport getCreditReportByUid(String userId) {
        CreditReportExample example = new CreditReportExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO).andUserIdEqualTo(userId);
        example.setOrderByClause(" create_time desc ");
        List<CreditReport> list = selectByExample(example);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}