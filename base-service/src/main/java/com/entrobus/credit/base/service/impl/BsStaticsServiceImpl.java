package com.entrobus.credit.base.service.impl;

import com.entrobus.credit.base.dao.BsStaticsMapper;
import com.entrobus.credit.base.service.BsStaticsService;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.pojo.base.BsStatics;
import com.entrobus.credit.pojo.base.BsStaticsExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.entrobus.credit.vo.base.BsStaticVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
        if (record.getDeleteFlag() == null) record.setDeleteFlag(Constants.DeleteFlag.NO);
        if (record.getSortId() == null) record.setSortId(100);
    }
    @Override
    public List<BsStatics> getByCodeType(String codeType){
        if (StringUtils.isBlank(codeType)) return new ArrayList<>();
        BsStaticsExample example = new BsStaticsExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DeleteFlag.NO).andCodeTypeEqualTo(codeType);
        return selectByExample(example);
    }
    @Override
    public BsStatics getByTypeAndValue(String codeType, String codeValue){
        BsStaticsExample example = new BsStaticsExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DeleteFlag.NO)
                .andCodeTypeEqualTo(codeType).andCodeValueEqualTo(codeValue);
        List<BsStatics> list = selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<BsStatics> getByAll(){
        BsStaticsExample example = new BsStaticsExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
        return selectByExample(example);
    }
    @Override
    public List<BsStaticVo> getBsStaticVo(BsStaticVo vo){
        List<BsStatics> list = getByVo(vo);
        List<BsStaticVo>  voList = list.stream().map(this::toBsStaticVo).collect(Collectors.toList());
        return voList;
    }
    @Override
    public List<BsStatics> getByVo(BsStaticVo vo) {
        BsStaticsExample example = new BsStaticsExample();
        BsStaticsExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
        if (StringUtils.isNotBlank(vo.getCodeType())){
            criteria.andCodeTypeEqualTo(vo.getCodeType());
        }
        if (StringUtils.isNotBlank(vo.getCodeValue())){
            criteria.andCodeValueEqualTo(vo.getCodeValue());
        }
        if (vo.getStatus() != null){
            criteria.andStatusEqualTo(vo.getStatus());
        }
        if (StringUtils.isNotBlank(vo.getCodeName())){
            criteria.andCodeNameEqualTo(vo.getCodeName());
        }
        if (StringUtils.isNotBlank(vo.getExt())){
            criteria.andExtEqualTo(vo.getExt());
        }
        if (StringUtils.isNotBlank(vo.getParam())){
            criteria.andParamEqualTo(vo.getParam());
        }
        return selectByExample(example);
    }

    private BsStaticVo toBsStaticVo(BsStatics statics) {
        BsStaticVo newVO = new BsStaticVo();
        BeanUtils.copyProperties(statics,newVO);
        return newVO;
    }

    @Override
    public int add(BsStatics statics){
        BsStatics oldStatics = getByTypeAndValue(statics.getCodeType(),statics.getCodeValue());
        if (oldStatics != null) return 0;
        return insertSelective(statics);
    }
    @Override
    public int saveUpdate(BsStatics statics){
        BsStatics oldStatics = getByTypeAndValue(statics.getCodeType(),statics.getCodeValue());
        if (oldStatics != null && !Objects.equals(oldStatics.getId(),statics.getId())) return 0;
        return updateByPrimaryKeySelective(statics);
    }
    @Override
    public int logicDel(Long id){

        BsStatics bsStatics = new BsStatics();
        bsStatics.setDeleteFlag(Constants.DeleteFlag.YES);
        bsStatics.setDeleteTime(new Date());

        BsStaticsExample example = new BsStaticsExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DeleteFlag.NO).andIdEqualTo(id);

        int n =  this.updateByExampleSelective(bsStatics, example);
        return n;
    }
    @Override
    public int batchLogicDel(List<Long> idList){
        if (ConversionUtil.isEmptyCollection(idList)){
            return 0;
        }
        BsStatics bsStatics = new BsStatics();
        bsStatics.setDeleteFlag(Constants.DeleteFlag.YES);
        bsStatics.setDeleteTime(new Date());
        BsStaticsExample example = new BsStaticsExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DeleteFlag.NO).andIdIn(idList);

        int n =  this.updateByExampleSelective(bsStatics, example);
        return n;
    }
}