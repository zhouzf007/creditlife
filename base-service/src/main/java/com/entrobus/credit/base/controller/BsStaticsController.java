package com.entrobus.credit.base.controller;

import com.entrobus.credit.base.bean.BsStaticsExt;
import com.entrobus.credit.base.service.BsStaticsCacheService;
import com.entrobus.credit.base.service.BsStaticsService;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.pojo.base.BsStatics;
import com.entrobus.credit.vo.base.BsStaticVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     *
     * @param codeType 如果非空，只刷新codeType对应数据的缓存，否则刷新全部
     * @return
     */
    @PostMapping("/cache")
    public WebResult cacheOrRefreshAll(@RequestParam(required = false) String codeType) {
        if (StringUtils.isNotBlank(codeType)) {
            bsStaticsCacheService.cacheOrRefreshByType(codeType);
        }else {
            bsStaticsCacheService.cacheOrRefreshAll();
        }
        return WebResult.ok("操作成功");
    }

    /**
     * 刷新指定数据缓存，
     *
     * @param id
     * @return
     */
    @PostMapping("/cache/{id}")
    public BsStaticVo cacheOrRefresh(@PathVariable Long id) {
        return bsStaticsCacheService.cacheOrRefresh(id);
    }

    /**
     * 从缓存获取，如果缓存中没有，则刷新缓存
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BsStaticVo getBsStatic(@PathVariable Long id) {
        BsStaticVo vo = bsStaticsCacheService.getOrCacheById(id);
        return vo;
    }

    /**
     * 添加，并缓存
     *
     * @param vo
     * @return
     */
    @PostMapping("")
    public WebResult add(@RequestBody @Validated BsStaticVo vo) {
        BsStatics statics = new BsStatics();
        BeanUtils.copyProperties(vo, statics);
        int i = bsStaticsService.add(statics);
        if (i > 0) bsStaticsCacheService.cacheOrRefresh(statics.getId());
        return WebResult.ok("操作成功");
    }
    /**
     * 列表
     *
     * @param
     * @return
     */
    @GetMapping("")
    public WebResult list( BsStaticVo vo, Integer pageNum,  Integer pageSize) {
        if (pageNum != null && pageSize != null)
            PageHelper.startPage(pageNum,pageSize);
        List<BsStatics> list = bsStaticsService.getByVo(vo);

        List<BsStaticsExt> extList = null;
        if (list.isEmpty()){
            extList = new ArrayList<>();
        } else {
            extList = list.stream().map(this::toBsStaticsExt).collect(Collectors.toList());
        }
        PageInfo<BsStatics> pageInfo = new PageInfo<>(list);
        return WebResult.ok("操作成功").put("total",pageInfo.getTotal()).put("rows",extList);
    }

    private BsStaticsExt toBsStaticsExt(BsStatics statics) {
        BsStaticsExt ext = new BsStaticsExt();
        BeanUtils.copyProperties(statics,ext);
        String typeName = bsStaticsCacheService.getOrCacheName(Constants.CODE_TYPE.CODE_TYPE, ext.getCodeType());
        ext.setTypeName(typeName);
        String statusName = bsStaticsCacheService.getOrCacheName(Constants.CODE_TYPE.STATUS, ext.getStatus()+"");
        ext.setStatusName(statusName);
        return ext;
    }


    /**
     * 获取同类型的
     *
     * @param codeType
     * @return
     */
    @GetMapping("/similar")
    public List<BsStaticVo> getByType(@RequestParam String codeType) {
        List<BsStaticVo> list = null;
        list = bsStaticsCacheService.getOrCacheByType(codeType);
        return list;
    }
    /**
     * 根据条件搜索
     *
     * @param vo
     * @return
     */
    @GetMapping("/search")
    public List<BsStaticVo> search( BsStaticVo vo) {
        List<BsStaticVo> list = null;
        list = bsStaticsService.getBsStaticVo(vo);
        return list;
    }
    /**
     * 获取同类型的
     *
     * @param codeType
     * @return
     */
    @GetMapping("/unique")
    public BsStaticVo getByTypeAndValue(@RequestParam String codeType,@RequestParam String codeValue) {
        BsStaticVo  bsStaticVo= bsStaticsCacheService.getOrCache(codeType,codeValue);
        return bsStaticVo;
    }
    /**
     * 获取同类型的
     *
     * @param codeType
     * @return
     */
    @GetMapping("/name")
    public String getCodeName(@RequestParam String codeType,@RequestParam String codeValue) {
        String name = bsStaticsCacheService.getOrCacheName(codeType, codeValue);
        return name;
    }



    /**
     * 修改，并缓存
     * 暂时这样
     *
     * @param vo
     * @return
     */
    @PutMapping("/{id}")
    public WebResult update(@PathVariable Long id, @RequestBody @Validated BsStaticVo vo) {
        BsStatics statics = new BsStatics();
        BeanUtils.copyProperties(vo, statics);
        statics.setId(id);
        int i = bsStaticsService.updateByPrimaryKeySelective(statics);
        if (i > 0) bsStaticsCacheService.cacheOrRefresh(statics.getId());
        return WebResult.ok("操作成功");
    }

    /**
     * 删除，并删除缓存
     * 暂时这样
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public WebResult del(@PathVariable Long id) {

        int i = bsStaticsService.logicDel(id);
        if (i > 0) bsStaticsCacheService.cacheOrRefresh(id);
        return WebResult.ok("操作成功");
    }
    /**
     * 批量删除，并删除缓存
     * 暂时这样
     *
     * @param ids
     * @return
     */
    @PostMapping("/trashCan")
    public WebResult trashCan(@RequestParam List<Long> ids) {
        int i = bsStaticsService.batchLogicDel(ids);
        if (i > 0) bsStaticsCacheService.cacheOrRefreshAll();
        return WebResult.ok("操作成功");
    }


}
