package com.entrobus.credit.auth.services.impl;

import com.entrobus.credit.auth.dao.AuthoritiesMapper;
import com.entrobus.credit.auth.dao.UsersMapper;
import com.entrobus.credit.auth.pojo.Authorities;
import com.entrobus.credit.auth.pojo.AuthoritiesExample;
import com.entrobus.credit.auth.pojo.Users;
import com.entrobus.credit.auth.pojo.UsersExample;
import com.entrobus.credit.auth.services.AuthoritesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouzf on 2authoritiesMapper17/11/3authoritiesMapper.
 */
@Service
public class AuthoritesServiceImpl implements AuthoritesService {
    
    @Autowired
    private AuthoritiesMapper authoritiesMapper;

    @Autowired
    private UsersMapper usersMapper;
    
    @Override
    public int countByExample(UsersExample example) {
        return usersMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UsersExample example) {
        return usersMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String username) {
        return usersMapper.deleteByPrimaryKey(username);
    }

    @Override
    public int insert(Users record) {
        return usersMapper.insert(record);
    }

    @Override
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    @Override
    public List<Users> selectByExample(UsersExample example) {
        return usersMapper.selectByExample(example);
    }

    @Override
    public Users selectByPrimaryKey(String username) {
        return usersMapper.selectByPrimaryKey(username);
    }

    @Override
    public int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example) {
        return usersMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(@Param("record") Users record, @Param("example") UsersExample example) {
        return usersMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return usersMapper.updateByPrimaryKey(record);
    }

    @Override
    public int countByExample(AuthoritiesExample example) {
        return authoritiesMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AuthoritiesExample example) {
        return authoritiesMapper.deleteByExample(example);
    }

    @Override
    public int insert(Authorities record) {
        return authoritiesMapper.insert(record);
    }

    @Override
    public int insertSelective(Authorities record) {
        return authoritiesMapper.insertSelective(record);
    }

    @Override
    public List<Authorities> selectByExample(AuthoritiesExample example) {
        return authoritiesMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(@Param("record") Authorities record, @Param("example") AuthoritiesExample example) {
        return authoritiesMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(@Param("record") Authorities record, @Param("example") AuthoritiesExample example) {
        return authoritiesMapper.updateByExample(record, example);
    }
}
