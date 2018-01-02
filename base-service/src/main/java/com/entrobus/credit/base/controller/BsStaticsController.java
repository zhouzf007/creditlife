package com.entrobus.credit.base.controller;

import com.entrobus.credit.base.service.BsStaticsCacheService;
import com.entrobus.credit.base.service.BsStaticsService;
import com.entrobus.credit.pojo.base.BsStatics;
import com.entrobus.credit.vo.BsStaticVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statics")
public class BsStaticsController {
    @Autowired
    private BsStaticsService bsStaticsService;
    @Autowired
    private BsStaticsCacheService bsStaticsCacheService;

    /**
     * 刷新缓存，
     * @param codeType 如果非空，只刷新codeType对应数据的缓存，否则刷新全部
     * @return
     */
    @PostMapping("/cache")
    public int cacheOrRefreshAll(@RequestParam(required = false) String codeType){
        if (StringUtils.isNotBlank(codeType)){
            return bsStaticsCacheService.cacheOrRefreshByType(codeType);
        }
        return bsStaticsCacheService.cacheOrRefreshAll();
    }
    /**
     * 刷新指定数据缓存，
     * @param id
     * @return
     */
    @PostMapping("/cache/{id}")
    public BsStaticVo cacheOrRefresh(@PathVariable Long id){
        return bsStaticsCacheService.cacheOrRefresh(id);
    }
    /**
     * 从缓存获取，如果缓存中没有，则刷新缓存
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BsStaticVo getBsStatic(@PathVariable Long id){
        BsStaticVo vo = bsStaticsCacheService.getById(id);
        if (vo == null){
            vo = bsStaticsCacheService.cacheOrRefresh(id);
        }
        return vo;
    }

    /**
     * 添加，并缓存
     * @param vo
     * @return
     */
    @PostMapping("")
    public int add(@RequestBody BsStaticVo vo){
        if (StringUtils.isBlank(vo.getCodeType())
                || StringUtils.isBlank(vo.getCodeValue())
                || StringUtils.isBlank(vo.getCodeName())
                || StringUtils.isBlank(vo.getCodeDesc())){
            return 0;
        }
        BsStatics statics = new BsStatics();
        BeanUtils.copyProperties(vo,statics);
        int i = bsStaticsService.add(statics);
        if (i > 0) bsStaticsCacheService.cacheOrRefresh(statics.getId());
        return i;
    }
    /**
     * 获取列表
     * @param codeType
     * @return
     */
    @GetMapping("")
    public List<BsStaticVo> getList(@RequestParam(required = false) String codeType){
        List<BsStaticVo> list = null;
        if (StringUtils.isNotBlank(codeType)){
            list = bsStaticsCacheService.getByType(codeType);
            if (list == null) {
                bsStaticsCacheService.cacheOrRefreshByType(codeType);
                list = bsStaticsCacheService.getByType(codeType);
            }
        }else {
            List<BsStatics> staticsList = bsStaticsService.getByAll();
            list = staticsList.stream().map(statics -> {
                BsStaticVo vo = new BsStaticVo();
                BeanUtils.copyProperties(statics,vo);
                return vo;
            }).collect(Collectors.toList());
        }
        return list;
    }
    /**
     * 修改，并缓存
     * 暂时这样
     * @param vo
     * @return
     */
    @PutMapping("/{id}")
    public int update(@PathVariable Long id,@RequestBody BsStaticVo vo){
        if ( StringUtils.isBlank(vo.getCodeType())
                || StringUtils.isBlank(vo.getCodeValue())
                || StringUtils.isBlank(vo.getCodeName())
                || StringUtils.isBlank(vo.getCodeDesc())){
            return 0;
        }
        BsStatics statics = new BsStatics();
        BeanUtils.copyProperties(vo,statics);
        statics.setId(id);
        int i = bsStaticsService.updateByPrimaryKeySelective(statics);
        if (i > 0) bsStaticsCacheService.cacheOrRefresh(statics.getId());
        return i;
    }
    /**
     * 删除，并删除缓存
     * 暂时这样
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public int del(@PathVariable Long id){

        int i = bsStaticsService.logicDel(id);
        if (i > 0) bsStaticsCacheService.cacheOrRefresh(id);
        return i;
    }



}
