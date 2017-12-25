package cn.zhangxd.auth.services;

import cn.zhangxd.auth.pojo.Authorities;
import cn.zhangxd.auth.pojo.AuthoritiesExample;
import cn.zhangxd.auth.pojo.Users;
import cn.zhangxd.auth.pojo.UsersExample;
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
