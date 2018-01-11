package com.entrobus.credit.base.service;

import com.entrobus.credit.pojo.base.BsStatics;
import com.entrobus.credit.pojo.base.BsStaticsExample;
import com.entrobus.credit.vo.base.BsStaticVo;

import java.util.List;

public interface BsStaticsService {
    int countByExample(BsStaticsExample example);

    BsStatics selectByPrimaryKey(Long id);

    List<BsStatics> selectByExample(BsStaticsExample example);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BsStatics record);

    int updateByPrimaryKey(BsStatics record);

    int deleteByExample(BsStaticsExample example);

    int updateByExampleSelective(BsStatics record, BsStaticsExample example);

    int updateByExample(BsStatics record, BsStaticsExample example);

    int insert(BsStatics record);

    int insertSelective(BsStatics record);

    List<BsStatics> getByCodeType(String codeType);

    BsStatics getByTypeAndValue(String codeType, String codeValue);

    List<BsStatics> getByAll();

    List<BsStaticVo> getBsStaticVo(BsStaticVo vo);

    List<BsStatics> getByVo(BsStaticVo vo);

    int add(BsStatics statics);

    int saveUpdate(BsStatics statics);

    int logicDel(Long id);

    int batchLogicDel(List<Long> idList);
}