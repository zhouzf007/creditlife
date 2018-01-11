package com.entrobus.credit.manager.base.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.client.BsStaticsClient;
import com.entrobus.credit.vo.base.BsStaticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bs/statics")
public class BsStaticsController {
    @Autowired
    private BsStaticsClient bsStaticsClient;
    /**
     * 列表
     *
     * @param
     * @return
     */
    @GetMapping("")
    public WebResult list( String codeType, String status, Integer pageNum, Integer pageSize){
        return bsStaticsClient.list(codeType,status,pageNum,pageSize);
    }
    /**
     * 修改，并缓存
     * 暂时这样
     *
     * @param vo
     * @return
     */
    @PutMapping("/{id}")
    public WebResult update(@PathVariable Long id, @RequestBody  BsStaticVo vo){
        return bsStaticsClient.update(id,vo);
    }

    /**
     * 删除，并删除缓存
     * 暂时这样
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public WebResult del(@PathVariable Long id){
        return bsStaticsClient.del(id);
    }

    /**
     * 刷新缓存，
     *
     * @param codeType 如果非空，只刷新codeType对应数据的缓存，否则刷新全部
     * @return
     */
    @PostMapping("/cache")
    public WebResult cacheOrRefreshAll( String codeType){
        return bsStaticsClient.cacheOrRefreshAll(codeType);
    }
}
