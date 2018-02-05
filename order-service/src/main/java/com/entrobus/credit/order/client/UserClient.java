package com.entrobus.credit.order.client;

import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.vo.user.SearchUserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by zhouzf on 2017/12/28.
 */
@FeignClient(name = "user-service", fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @GetMapping(value = "/api/userCreditReport")
    CreditReport getUserCreditReport(@RequestParam("userId") String userId);

    @GetMapping(value = "/user/creditReport")
    CreditReport getCreditReport(@RequestParam("id") String id);


    CreditReport getCrediReport(@RequestParam("id") String id);
    /**
     * 搜索用户
     * @param key
     */
    @GetMapping(value = "/user/search")
    List<SearchUserInfoVo> searchUser(@RequestParam("key") String key);
    /**
     * 搜索用户ID
     * @param key
     */
    @GetMapping(value = "/user/searchUserIds")
     List<String> searchUserIds(@RequestParam("key") String key);
    @Component
    class UserClientFallback implements UserClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(UserClientFallback.class);

        @Override
        public CreditReport getUserCreditReport(String userId) {
            return null;
        }

        @Override
        public CreditReport getCreditReport(String id) {
            return null;
        }

        /**
         * 搜索用户
         *
         * @param key
         */
        @Override
        public List<SearchUserInfoVo> searchUser(String key) {
            return null;
        }

        /**
         * 搜索用户ID
         *
         * @param key
         */
        @Override
        public List<String> searchUserIds(String key) {
            return null;
        }

    }
}
