package com.entrobus.credit.manager.bank.service;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.bean.OrganizationExt;
import com.entrobus.credit.pojo.manager.Organization;
import com.entrobus.credit.pojo.manager.OrganizationExample;

import java.util.List;

public interface OrganizationService {
    int countByExample(OrganizationExample example);

    Organization selectByPrimaryKey(String id);

    List<Organization> selectByExample(OrganizationExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    int deleteByExample(OrganizationExample example);

    int updateByExampleSelective(Organization record, OrganizationExample example);

    int updateByExample(Organization record, OrganizationExample example);

    int insert(Organization record);

    int insertSelective(Organization record);

    WebResult add(OrganizationExt organizationExt);

    WebResult edit(OrganizationExt organizationExt);

    WebResult delete(String id);

    /**
     * 资金方是否已经存在 是=true
     * @param name
     * @return
     */
    boolean checkName(String name);

    boolean checkName(String name,String id);

    /**
     * 手机号是否已经被绑定 是=true
     * @param mobile
     * @return
     */
    boolean checkContractMobile(String mobile);

    boolean checkContractMobile(String mobile,String id);
}