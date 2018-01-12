package com.entrobus.credit.order.services;

import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.order.CreditReportExample;

import java.util.List;

public interface CreditReportService {
    int countByExample(CreditReportExample example);

    CreditReport selectByPrimaryKey(String id);

    List<CreditReport> selectByExample(CreditReportExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditReport record);

    int updateByPrimaryKey(CreditReport record);

    int deleteByExample(CreditReportExample example);

    int updateByExampleSelective(CreditReport record, CreditReportExample example);

    int updateByExample(CreditReport record, CreditReportExample example);

    int insert(CreditReport record);

    int insertSelective(CreditReport record);
}