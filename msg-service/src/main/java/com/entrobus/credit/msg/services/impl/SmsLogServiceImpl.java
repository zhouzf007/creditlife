package com.entrobus.credit.msg.services.impl;

import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.msg.dao.SmsLogMapper;
import com.entrobus.credit.msg.services.SmsLogService;
import com.entrobus.credit.pojo.msg.SmsLog;
import com.entrobus.credit.pojo.msg.SmsLogExample;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsLogServiceImpl implements SmsLogService {
    @Autowired
    private SmsLogMapper smsLogMapper;

    private static final Logger logger = LoggerFactory.getLogger(SmsLogServiceImpl.class);

    public int countByExample(SmsLogExample example) {
        int count = this.smsLogMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SmsLog selectByPrimaryKey(String id) {
        return this.smsLogMapper.selectByPrimaryKey(id);
    }

    public List<SmsLog> selectByExample(SmsLogExample example) {
        return this.smsLogMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.smsLogMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SmsLog record) {
        record.setUpdateTime(new Date());
        return this.smsLogMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SmsLog record) {
        record.setUpdateTime(new Date());
        return this.smsLogMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(SmsLogExample example) {
        return this.smsLogMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(SmsLog record, SmsLogExample example) {
        return this.smsLogMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(SmsLog record, SmsLogExample example) {
        return this.smsLogMapper.updateByExample(record, example);
    }

    public int insert(SmsLog record) {
        if (StringUtils.isEmpty(record.getId())){
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setCreateTime(new Date());
        return this.smsLogMapper.insert(record);
    }

    public int insertSelective(SmsLog record) {
        if (StringUtils.isEmpty(record.getId())){
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setCreateTime(new Date());
        return this.smsLogMapper.insertSelective(record);
    }

    @Override
    public int saveSmsLog(String mobile, Integer type, String title, String content,String result) {
        SmsLog log=new SmsLog();
        log.setTargets(mobile);
        log.setTitle(title);
        log.setContent(content);
        log.setPushType(type);
        log.setResult(result);
        return this.insertSelective(log);
    }
}