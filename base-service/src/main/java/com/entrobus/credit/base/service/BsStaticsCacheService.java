package com.entrobus.credit.base.service;

import com.entrobus.credit.vo.user.BsStaticVo;

import java.util.List;

public interface BsStaticsCacheService {
    /**
     *  根据codeType和codeValue查找
     * @param codeType
     * @param codeValue
     * @return
     */
    BsStaticVo getByTypeAndValue(String codeType,String codeValue);

    BsStaticVo getOrCache(String codeType, String codeValue);

    /**
     *   根据id查找
     * @param id
     * @return
     */
    BsStaticVo getById(Long id);
    /**
     *   根据id查找,如果没有，则从数据库更新至缓存
     * @param id
     * @return
     */
    BsStaticVo getOrCacheById(Long id);

    /**
     * 根据codeType从缓存查找
     * @param codeType
     * @return
     */
    List<BsStaticVo> getByType(String codeType);
    /**
     * 根据codeType从缓存查找,如果缓存中没有，则刷新缓存
     * @param codeType
     * @return
     */
    List<BsStaticVo> getOrCacheByType(String codeType);

    /**
     * 清除所有缓存
     * @return
     */
    int clearCache();
    /**
     * 更新所有缓存
     * @return
     */
    List<BsStaticVo> cacheOrRefreshAll();
    /**
     * 根据id更新缓存,如果不存在或已停用，则从缓存删除
     * @param id
     * @return
     */
    BsStaticVo cacheOrRefresh(Long id);
    /**
     * 根据codeType更新缓存,如果不存在或已停用，则从缓存删除
     * @param codeType
     * @return
     */
    List<BsStaticVo> cacheOrRefreshByType(String codeType);
}
