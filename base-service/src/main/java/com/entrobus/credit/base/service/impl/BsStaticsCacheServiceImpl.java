package com.entrobus.credit.base.service.impl;

import com.entrobus.credit.base.service.BsStaticsCacheService;
import com.entrobus.credit.base.service.BsStaticsService;
import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.pojo.base.BsStatics;
import com.entrobus.credit.vo.user.BsStaticVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class BsStaticsCacheServiceImpl implements BsStaticsCacheService {
    @Autowired
    private BsStaticsService bsStaticsService;
    @Autowired
    private RedisTemplate redisTemplate;
//    @Override
    public BsStaticVo cacheObj(BsStatics statics){
        if (statics == null) return null;
        String key = getKey(statics.getId());
        BsStaticVo vo = new BsStaticVo();
        BeanUtils.copyProperties(statics,vo);
        CacheService.setCacheObj(redisTemplate, key,vo);
        return vo;
    }

//    @Override
    public BsStaticVo cacheObjByTypeAndValue(BsStatics statics) {
        BsStaticVo vo = cacheObj(statics);
        if (vo == null){
            return null;
        }
        String key = getKey(statics.getCodeType(), statics.getCodeValue());
        CacheService.setCacheObj(redisTemplate, key,getByType(String.valueOf(statics.getId())));
        return vo;
      
    }

    private String getKey(String codeType, String codeValue) {
        return Cachekey.BsStatics.TYPE_VALUE_ID + codeType + codeValue;
    }
    private String getKey(String id) {
        return Cachekey.BsStatics.ID_OBJ + id ;
    }
    private String getKey(Long id) {
        return getKey(String.valueOf(id));
    }
    private String getListKey(String codeType) {
        return Cachekey.BsStatics.TYPE_LIST + codeType ;
    }

    /**
     * 将集合中符合codeType的缓存
     * @param list
     * @param codeType
     * @return
     */
//    @Override
    public int cacheObjByType(List<BsStatics> list, String codeType) {
        if (list == null) return 0;
       
        List<String> idList = new ArrayList<>();
        for (BsStatics statics : list) {
            if(statics == null) continue;
            //跳过codeType不同的
            if (!Objects.equals(statics.getCodeType(),codeType)) continue;
            //跳过已删除的
            //跳过已停用的
            String strId = String.valueOf(statics.getId());
            if (!isNormal(statics)) {
                delObj(strId);//如果已停用或者已删除，则删除
                continue;
            }
            //缓存对象，如果没有缓存成功，跳过
            BsStaticVo vo = cacheObjByTypeAndValue(statics);
            if (vo == null) continue;

            idList.add(strId);
        }
        cacheIdListByType(codeType, idList);
        return idList.size();
    }

    /**
     * 根据codeType将id集合缓存
     * @param codeType
     * @param idList
     */
    private void cacheIdListByType(String codeType, List<String> idList) {
        String listKey = getListKey(codeType);
        CacheService.setCacheObj(redisTemplate,listKey,idList);
    }
    /**
     * 删除缓存
     * @param statics
     * @return
     */
    public int delObj(BsStatics statics){
        return delObj(statics.getId());
    }
    /**
     * 删除缓存
     * @param id
     * @return
     */
    public int delObj(Long id){
        return delObj(String.valueOf(id));
    }

    /**
     * 删除缓存
     * @param id
     * @return
     */
    public int delObj(String id){
        String key = getKey(id);
        boolean b = CacheService.delete(redisTemplate, key);
        return b ? 1 : 0;
    }

    /**
     *  根据codeType和codeValue查找
     * @param codeType
     * @param codeValue
     * @return
     */
    @Override
    public BsStaticVo getByTypeAndValue(String codeType, String codeValue) {
        String key = getKey(codeType, codeValue);
        String id = CacheService.getString(redisTemplate,key);
        if (id == null) return null;
        BsStaticVo statics = getById(id);
        return statics;
    }
    /**
     *   根据id查找
     * @param id
     * @return
     */
    private BsStaticVo getById(String id) {
        String key = getKey(id);
        BsStaticVo cacheObj = CacheService.getCacheObj(redisTemplate, key, BsStaticVo.class);
        return cacheObj;
    }

    /**
     *   根据id查找
     * @param id
     * @return
     */
    @Override
    public BsStaticVo getById(Long id) {
        return getById(String.valueOf(id));
    }

    /**
     * 根据codeType从缓存查找
     * @param codeType
     * @return
     */
    @Override
    public List<BsStaticVo> getByType(String codeType) {
        String key = getListKey(codeType);
        List<String> idList = CacheService.getCacheObj(redisTemplate,key,ArrayList.class);
        if (idList == null) return null;
        List<BsStaticVo> list = new ArrayList<>();
        for (String id : idList) {
            BsStaticVo statics = getById(id);
            if (statics != null) list.add(statics);
        }
        return list;
    }


    /**
     * 清除所有缓存
     * @return
     */
    @Override
    public int clearCache(){
        int n = 0;
        n += CacheService.deleteByKeyPrefix(redisTemplate,Cachekey.BsStatics.ID_OBJ);
        n += CacheService.deleteByKeyPrefix(redisTemplate,Cachekey.BsStatics.TYPE_VALUE_ID);
        n += CacheService.deleteByKeyPrefix(redisTemplate,Cachekey.BsStatics.TYPE_LIST);
        return n;
    }

    /**
     * 更新所有缓存
     * @return
     */
    @Override
    public int cacheOrRefreshAll(){
        //情况缓存
        clearCache();
        //从数据库查找所有数据
        List<BsStatics> list = bsStaticsService.getByAll();
        if (list.isEmpty()) return 0;
        //根据codeType分组
        Map<String,List<BsStatics>> groupMap = list.stream()
                .filter(this::isNormal)
                .collect(Collectors.groupingBy(BsStatics::getCodeType));
        if (groupMap.isEmpty()) return 0;
        int n = 0;
        //根据分组缓存
        for (String codeType : groupMap.keySet()) {
            List<BsStatics> listByType = groupMap.get(codeType);
            n += cacheObjByType(listByType,codeType);
        }
        return n;
    }

    /**
     * 根据id更新缓存,如果不存在或已停用，则从缓存删除
     * @param id
     * @return
     */
    @Override
    public BsStaticVo cacheOrRefresh(Long id){
        if (id == null) return null;
        //根据id从数据库查找
        BsStatics statics = bsStaticsService.selectByPrimaryKey(id);
        //如果不可用，删除
        if (!isNormal(statics)) delObj(id);
        //缓存对象，id
        BsStaticVo vo = cacheObjByTypeAndValue(statics);
        String codeType = statics.getCodeType();
        List<BsStatics> listByType = bsStaticsService.getByCodeType(codeType);
        List<String > idList = listByType.stream()
                .filter(this::isNormal)
                .map(s -> String.valueOf(s.getId()))
                .collect(Collectors.toList());
        //缓存id集合
        cacheIdListByType(codeType,idList);
        return vo;
    }

    /**
     * 判断是否是可用的，非null、未删除、状态正常
     * @param statics
     * @return
     */
    protected boolean isNormal(BsStatics statics) {
        return statics != null && Objects.equals(statics.getDeleteFlag(), Constants.DeleteFlag.NO)
                && Objects.equals(statics.getStatus(),Constants.Status.NORMAL);
    }

    /**
     * 根据codeType更新缓存,如果不存在或已停用，则从缓存删除
     * @param codeType
     * @return
     */
    @Override
    public int cacheOrRefreshByType(String codeType){
        if (codeType == null) return 0;
        List<BsStatics> listByType = bsStaticsService.getByCodeType(codeType);
        return cacheObjByType(listByType,codeType);

    }
}


