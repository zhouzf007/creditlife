package com.entrobus.credit.base.service;

import com.entrobus.credit.pojo.base.BsStatics;
import com.entrobus.credit.pojo.base.BsStaticsExample;
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
}