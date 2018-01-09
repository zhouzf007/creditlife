package com.entrobus.credit.base.controller;

import com.entrobus.credit.base.service.BsStaticsCacheService;
import com.entrobus.credit.base.service.BsStaticsService;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.pojo.base.BsStatics;
import com.entrobus.credit.vo.base.BsStaticVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return WebResult.ok();
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
    public WebResult add(@RequestBody @Validated BsStaticVo vo, BindingResult result) {
        if (result.hasErrors()) {
            String validationMsg = getValidationMsg(result);
            return WebResult.error(validationMsg);
        }
        BsStatics statics = new BsStatics();
        BeanUtils.copyProperties(vo, statics);
        int i = bsStaticsService.add(statics);
        if (i > 0) bsStaticsCacheService.cacheOrRefresh(statics.getId());
        return WebResult.ok("操作成功");
    }
    /**
     * 添加，并缓存
     *
     * @param
     * @return
     */
    @GetMapping("")
    public WebResult list(BsStaticVo vo) {
        //todo
        return WebResult.ok("操作成功");
    }

    private String getValidationMsg(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : result.getAllErrors()) {
            if (sb.length() > 0 ) sb.append(",");
            sb.append(error.getDefaultMessage());
        }
        return sb.toString();
    }

    /**
     * 获取同类型的
     *
     * @param codeType
     * @return
     */
    @GetMapping("/similar")
    public List<BsStaticVo> similar(@RequestParam String codeType) {
        List<BsStaticVo> list = null;
        list = bsStaticsCacheService.getOrCacheByType(codeType);
        return list;
    }

    /**
     * 修改，并缓存
     * 暂时这样
     *
     * @param vo
     * @return
     */
    @PutMapping("/{id}")
    public WebResult update(@PathVariable Long id, @RequestBody @Validated BsStaticVo vo, BindingResult result) {
        if (result.hasErrors()) {
            String validationMsg = getValidationMsg(result);
            return WebResult.error(validationMsg);
        }
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


}
