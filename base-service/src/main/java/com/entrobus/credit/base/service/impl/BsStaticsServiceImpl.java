package com.entrobus.credit.base.service.impl;

import com.entrobus.credit.base.dao.BsStaticsMapper;
import com.entrobus.credit.base.service.BsStaticsService;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.pojo.base.BsStatics;
import com.entrobus.credit.pojo.base.BsStaticsExample;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BsStaticsServiceImpl implements BsStaticsService {
    @Autowired
    private BsStaticsMapper bsStaticsMapper;

    private static final Logger logger = LoggerFactory.getLogger(BsStaticsServiceImpl.class);

    public int countByExample(BsStaticsExample example) {
        int count = this.bsStaticsMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public BsStatics selectByPrimaryKey(Long id) {
        return this.bsStaticsMapper.selectByPrimaryKey(id);
    }

    public List<BsStatics> selectByExample(BsStaticsExample example) {
        return this.bsStaticsMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.bsStaticsMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BsStatics record) {
        updateValue(record);
        return this.bsStaticsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BsStatics record) {
        updateValue(record);
        return this.bsStaticsMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(BsStaticsExample example) {
        return this.bsStaticsMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(BsStatics record, BsStaticsExample example) {
        updateValue(record);
        return this.bsStaticsMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(BsStatics record, BsStaticsExample example) {

        updateValue(record);
        return this.bsStaticsMapper.updateByExample(record, example);
    }

    private void updateValue(BsStatics record) {
        if (record.getUpdateTime() == null) {
            Date updateTime = new Date();
            record.setUpdateTime(updateTime);
        }
    }

    public int insert(BsStatics record) {
        defaultValue(record);
        return this.bsStaticsMapper.insert(record);
    }

    public int insertSelective(BsStatics record) {
        defaultValue(record);
        return this.bsStaticsMapper.insertSelective(record);
    }

    private void defaultValue(BsStatics record) {
        Date now = new Date();
        if (record.getCreateTime() == null) record.setCreateTime(now);
        if (record.getUpdateTime() == null) record.setUpdateTime(now);
        if (record.getDeleteFlag() == null) record.setDeleteFlag(Constants.DELETE_FLAG.NO);
    }
}