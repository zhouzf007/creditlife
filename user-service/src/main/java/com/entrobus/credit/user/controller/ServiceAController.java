package com.entrobus.credit.user.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.RedisUtil;
import com.entrobus.credit.user.client.MsgClient;
import com.entrobus.credit.user.dao.UsersMapper;
import com.entrobus.credit.user.services.AService;
import com.entrobus.credit.user.services.UserCacheService;
import com.entrobus.credit.vo.user.UserInfoCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
public class ServiceAController {

    @Value("${name:unknown}")
    private String name;

    @Autowired
    private AService aService;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    MsgClient msgClient;

    @GetMapping(path = "/current")
    public Principal getCurrentAccount(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WebResult getUserById(@PathVariable String id) {
        WebResult result = new WebResult();
        UserInfoCache userCache = userCacheService.getUserCache(id);
        Map rs = new HashMap<>();
        rs.put("data", userCache);
        return result.ok(rs);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public WebResult addUser(@PathVariable String id, @ModelAttribute UserInfoCache user) {
        WebResult result = new WebResult();
        user.setId(id);
        userCacheService.setUserCache(user);
        msgClient.sendMessage(user.getMobile(), "welcome");
        return result.ok();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    WebResult deleteUser(@PathVariable String id) {
        WebResult result = new WebResult();
        UserInfoCache userCache = userCacheService.getUserCache(id);
        userCacheService.removeUserCache(id);
        msgClient.sendMessage(userCache.getMobile(), "good bye");
        return result.ok();
    }

}