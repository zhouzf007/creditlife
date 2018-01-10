package com.entrobus.credit.manager.bank.service;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.bean.PartiesExt;
import com.entrobus.credit.pojo.manager.Parties;
import com.entrobus.credit.pojo.manager.PartiesExample;
import java.util.List;

public interface PartiesService {
    int countByExample(PartiesExample example);

    Parties selectByPrimaryKey(String id);

    List<Parties> selectByExample(PartiesExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Parties record);

    int updateByPrimaryKey(Parties record);

    int deleteByExample(PartiesExample example);

    int updateByExampleSelective(Parties record, PartiesExample example);

    int updateByExample(Parties record, PartiesExample example);

    int insert(Parties record);

    int insertSelective(Parties record);

    WebResult add(PartiesExt partiesExt);

    /**
     * 资金方是否已经存在 是=true
     * @param name
     * @return
     */
    boolean checkName(String name);

    /**
     * 手机号是否已经被绑定 是=true
     * @param mobile
     * @return
     */
    boolean checkContractMobile(String mobile);
}