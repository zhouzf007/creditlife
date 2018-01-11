package com.entrobus.credit.manager.base.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.client.BsStaticsClient;
import com.entrobus.credit.vo.base.BsStaticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 添加，并缓存
     *
     * @param vo
     * @return
     */
    @PostMapping("")
    public WebResult add( BsStaticVo vo){
        return bsStaticsClient.add(vo);
    }
    /**
     * 获取同类型的
     *
     * @param codeType
     * @return
     */
    @GetMapping("/similar")
    public List<BsStaticVo> getByType(@RequestParam String codeType) {
        return bsStaticsClient.getByType(codeType);
    }
    /**
     * 修改，并缓存
     * 暂时这样
     *
     * @param vo
     * @return
     */
    @PutMapping("/{id}")
    public WebResult update(@PathVariable Long id,   BsStaticVo vo){
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
     * 删除，并删除缓存
     * 暂时这样
     *
     * @param ids
     * @return
     */
    @PostMapping("/trashCan")
    public WebResult batchDel(@RequestParam("ids") List<Long> ids) {
        return bsStaticsClient.batchDel(ids);
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
