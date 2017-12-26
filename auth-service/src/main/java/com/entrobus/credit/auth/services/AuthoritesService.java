package com.entrobus.credit.auth.services;

import com.entrobus.credit.auth.pojo.Authorities;
import com.entrobus.credit.auth.pojo.AuthoritiesExample;
import com.entrobus.credit.auth.pojo.Users;
import com.entrobus.credit.auth.pojo.UsersExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhouzf on 2017/11/30.
 */
public interface AuthoritesService {

    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(String username);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);


    int countByExample(AuthoritiesExample example);

    int deleteByExample(AuthoritiesExample example);

    int insert(Authorities record);

    int insertSelective(Authorities record);

    List<Authorities> selectByExample(AuthoritiesExample example);

    int updateByExampleSelective(@Param("record") Authorities record, @Param("example") AuthoritiesExample example);

    int updateByExample(@Param("record") Authorities record, @Param("example") AuthoritiesExample example);
}
