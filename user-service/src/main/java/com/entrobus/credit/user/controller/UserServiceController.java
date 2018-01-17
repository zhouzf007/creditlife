package com.entrobus.credit.user.controller;

import com.entrobus.credit.user.client.MsgClient;
import com.entrobus.credit.user.client.ServiceBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class UserServiceController {

    @Value("${name:unknown}")
    private String name;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    MsgClient msgClient;

//    @Autowired

//    @GetMapping(path = "/current")
//    public Principal getCurrentAccount(Principal principal) {
//        return principal;
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public WebResult getUserById(@PathVariable("id") String id) {
//        WebResult result = new WebResult();
//        UserInfoCache userCache = userCacheService.getUserCache(id);
//        Map rs = new HashMap<>();
//        rs.put("data", userCache);
//        return result.ok(rs);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
//    public WebResult addUser(@PathVariable("id") String id, @ModelAttribute UserInfoCache user) {
//        WebResult result = new WebResult();
//        user.setId(id);
//        user.setName(name);
//        userCacheService.setUserCache(user);
//        msgClient.sendMessage(user.getMobile(), "welcome");
//        return result.ok();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    WebResult deleteUser(@PathVariable("id") String id) {
//        WebResult result = new WebResult();
//        UserInfoCache userCache = userCacheService.getUserCache(id);
//        userCacheService.removeUserCache(id);
//        msgClient.sendMessage(userCache.getMobile(), "good bye");
//        return result.ok();
//    }
//
//    @GetMapping(value = "/test")
//    WebResult test() {
//        String test = serviceBClient.test();
//        return WebResult.ok(test);
//    }
//
//
//    @Autowired
//    MsgPublishChannel msgChannel;
//
//    @RequestMapping(method = RequestMethod.POST, path = "/sendMsg")
//    public void sendCode(@RequestBody Map<String, Object> msg) {
//        Message<Map<String, Object>> msgs = MessageBuilder.withPayload(msg).build();
//        msgChannel.sendMsg().send(msgs);
//    }
//
//    @Autowired
//    private UserCacheService userCacheService;

}