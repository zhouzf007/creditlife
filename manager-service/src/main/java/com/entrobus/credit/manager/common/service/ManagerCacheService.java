package com.entrobus.credit.manager.common.service;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.vo.base.BsStaticVo;
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
    /**
     *  根据codeType和codeValue查找
     * @param codeType
     * @param codeValue
     * @return
     */
//    @Override
    public<T> BsStaticVo getBsStatic(String codeType, T codeValue) {
        String key = Cachekey.BsStatics.TYPE_VALUE_ID + codeType + codeValue;
        String id = CacheService.getString(redisTemplate,key);
        if (id == null) return null;
        String idKey = Cachekey.BsStatics.ID_OBJ + id ;
        BsStaticVo cacheObj = CacheService.getCacheObj(redisTemplate, key, BsStaticVo.class);
        return cacheObj;
    }
    /**
     * 静态数据
     *  根据codeType和codeValue查找 codeName
     * @param codeType
     * @param codeValue
     * @return
     */
//    @Override
    public<T> String getCodeName(String codeType, T codeValue) {
        String key = Cachekey.BsStatics.TYPE_VALUE_NAME + codeType + codeValue;
        return CacheService.getString(redisTemplate,key);
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
