package com.entrobus.credit.manager.common.service;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by cwh on 2018/1/10.
 */
@Service
public class ManagerCacheService  {
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 获取当前登录的系统用户
     * @return
     */
    public SysLoginUserInfo getCurrLoginUser() {
        String token = getRequest().getParameter("token");
//        String token = getRequest().getHeader("token");
        SysLoginUserInfo loginUser = CacheService.getCacheObj(redisTemplate,token,SysLoginUserInfo.class);
        return loginUser;
    }

    public void saveLoginUserInfo(String token,SysLoginUserInfo sysLoginUserInfo){
        CacheService.setCacheObj(redisTemplate,token,sysLoginUserInfo);
        CacheService.setString(redisTemplate,sysLoginUserInfo.getId()+"",token);
    }

    public void logout(){
        String token = getRequest().getParameter("token");
        SysLoginUserInfo loginUser = CacheService.getCacheObj(redisTemplate,token,SysLoginUserInfo.class);
        CacheService.delete(redisTemplate,loginUser.getId()+"");
        CacheService.delete(redisTemplate,token);
    }

    public void batchLogout(List<Long> idList){
        for(Long id:idList){
            String token = CacheService.getString(redisTemplate,id.toString());
            if(StringUtils.isNotEmpty(token)){
                CacheService.delete(redisTemplate,token);
                CacheService.delete(redisTemplate,id.toString());
            }
        }
    }

    /**
     * 获取当前请求对象
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            throw new RuntimeException("获取request失败", e);
        }
    }

}
