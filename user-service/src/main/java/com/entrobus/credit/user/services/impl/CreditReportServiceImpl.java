package com.entrobus.credit.user.services.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.order.CreditReportExample;
import com.entrobus.credit.user.dao.CreditReportMapper;
import com.entrobus.credit.user.services.CreditReportService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        record.setUpdateTime(new Date());
        return this.creditReportMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CreditReport record) {
        record.setUpdateTime(new Date());
        return this.creditReportMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(CreditReportExample example) {
        return this.creditReportMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(CreditReport record, CreditReportExample example) {
        record.setUpdateTime(new Date());
        return this.creditReportMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(CreditReport record, CreditReportExample example) {
        record.setUpdateTime(new Date());
        return this.creditReportMapper.updateByExample(record, example);
    }

    public int insert(CreditReport record) {
        defaultValue(record);
        return this.creditReportMapper.insert(record);
    }

    public int insertSelective(CreditReport record) {
        defaultValue(record);
        return this.creditReportMapper.insertSelective(record);
    }

    protected void defaultValue(CreditReport record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
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
        //@TODO 获取信用报告
        String url="";
        int score=10000;
        CreditReport cr=new CreditReport();
        cr.setUserId(userId);
        cr.setId(GUIDUtil.genRandomGUID());
        cr.setCreditScore(score);
        cr.setReportUrl(url);
        cr.setCreateTime(new Date());
        this.insertSelective(cr);
        return cr;
    }
}