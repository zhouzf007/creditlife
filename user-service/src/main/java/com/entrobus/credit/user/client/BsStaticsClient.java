package com.entrobus.credit.user.client;

import com.entrobus.credit.vo.BsStaticVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "base-service", fallback = MsgClient.MsgClientFallback.class)
@RequestMapping("/statics")
public interface BsStaticsClient {
    /**
     * 从缓存获取，如果缓存中没有，则刷新缓存
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    BsStaticVo getBsStatic(@PathVariable Long id);
}
