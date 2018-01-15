package com.entrobus.credit.manager.common.client;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.vo.base.BsStaticVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 参数的注解在FeignClient中使必须的
 */
@FeignClient(name = "base-service", fallback = BsStaticsClient.BsStaticsClientFallback.class)
//@RequestMapping("/statics")
public interface BsStaticsClient {
    /**
     * 从缓存获取，如果缓存中没有，则刷新缓存
     * @param id
     * @return
     */
    @GetMapping("/statics/{id}")
    BsStaticVo getBsStatic(@PathVariable("id") Long id);

    /**
     * 获取同类型的
     *
     * @param codeType
     * @return
     */
    @GetMapping("/statics/similar")
    List<BsStaticVo> getByType(@RequestParam("codeType") String codeType);

    /**
     * 根据条件搜索
     *
     * @return
     */
    @GetMapping("/statics/search")
    List<BsStaticVo> search(@RequestParam(value = "codeType") String codeType,
                            @RequestParam(value = "codeValue") String codeValue,
                            @RequestParam(value = "codeName") String codeName,
                            @RequestParam(value = "ext") String ext,
                            @RequestParam(value = "param") String param
                            );

    /**
     * 跟据codeType和codeValue查询
     *
     * @param codeType
     * @return
     */
    @GetMapping("/statics/unique")
    BsStaticVo getByTypeAndValue(@RequestParam("codeType") String codeType, @RequestParam("codeValue") String codeValue);
    /**
     * 跟据codeType和codeValue查询CodeName
     *
     * @param codeType
     * @return
     */
    @GetMapping("/statics/name")
    String getCodeName(@RequestParam("codeType") String codeType, @RequestParam("codeValue") String codeValue);






    /**
     * 列表
     *
     * @param
     * @return
     */
    @GetMapping("/statics")
    WebResult list(@RequestParam(value = "codeType") String codeType, @RequestParam(value = "status")String status,
                   @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize);
    /**
     * 修改，并缓存
     * 暂时这样
     *
     * @param vo
     * @return
     */
    @PutMapping(value = "/statics/{id}",consumes = "application/json")
    WebResult update(@PathVariable("id") Long id, @RequestBody  BsStaticVo vo);

    /**
     * 删除，并删除缓存
     * 暂时这样
     *
     * @param id
     * @return
     */
    @DeleteMapping("/statics/{id}")
    WebResult del(@PathVariable("id") Long id);

    /**
     * 刷新缓存，
     *
     * @param codeType 如果非空，只刷新codeType对应数据的缓存，否则刷新全部
     * @return
     */
    @PostMapping("/statics/cache")
    WebResult cacheOrRefreshAll(@RequestParam(value = "codeType",required = false) String codeType);
    /**
     * 添加，并缓存
     *
     * @param vo
     * @return
     */
    @PostMapping(value = "/statics",consumes = "application/json")
    WebResult add(@RequestBody  BsStaticVo vo);

    /**
     * 批量删除，并删除缓存
     * 暂时这样
     *
     * @param ids
     * @return
     */
    @PostMapping("/statics/trashCan")
    WebResult batchDel(@RequestParam("ids") List<Long> ids) ;
    @Component
    class BsStaticsClientFallback implements   BsStaticsClient{
        private static final Logger LOGGER = LoggerFactory.getLogger(BsStaticsClient.BsStaticsClientFallback.class);

        /**
         * 从缓存获取，如果缓存中没有，则刷新缓存
         *
         * @param id
         * @return
         */
        @Override
        public BsStaticVo getBsStatic(Long id) {
            LOGGER.info("getBsStatic异常发生，进入fallback方法");
            return null;
        }

        /**
         * 获取同类型的
         *
         * @param codeType
         * @return
         */
        @Override
        public List<BsStaticVo> getByType(String codeType) {
            LOGGER.info("getByType异常发生，进入fallback方法");
            return null;
        }

        /**
         * 根据条件搜索
         *
         * @param codeType
         * @param codeValue
         * @param codeName
         * @param ext
         * @param param
         * @return
         */
        @Override
        public List<BsStaticVo> search(String codeType, String codeValue, String codeName, String ext, String param) {
            LOGGER.info("search异常发生，进入fallback方法");
            return null;
        }



        /**
         * 获取同类型的
         *
         * @param codeType
         * @param codeValue
         * @return
         */
        @Override
        public BsStaticVo getByTypeAndValue(String codeType, String codeValue) {
            LOGGER.info("getByTypeAndValue异常发生，进入fallback方法");
            return null;
        }

        /**
         * 跟据codeType和codeValue查询CodeName
         *
         * @param codeType
         * @param codeValue
         * @return
         */
        @Override
        public String getCodeName(String codeType, String codeValue) {
            LOGGER.info("getCodeName异常发生，进入fallback方法");
            return "";
        }

        /**
         * 列表
         *
         * @param codeType
         * @param status
         * @param pageNum
         * @param pageSize @return
         */
        @Override
        public WebResult list(String codeType, String status, Integer pageNum, Integer pageSize) {
            LOGGER.info("list异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }


        /**
         * 修改，并缓存
         * 暂时这样
         *
         * @param id
         * @param vo
         * @return
         */
        @Override
        public WebResult update(Long id, BsStaticVo vo) {
            LOGGER.info("update异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }

        /**
         * 删除，并删除缓存
         * 暂时这样
         *
         * @param id
         * @return
         */
        @Override
        public WebResult del(Long id) {
            LOGGER.info("del异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }

        /**
         * 刷新缓存，
         *
         * @param codeType 如果非空，只刷新codeType对应数据的缓存，否则刷新全部
         * @return
         */
        @Override
        public WebResult cacheOrRefreshAll(String codeType) {
            LOGGER.info("cacheOrRefreshAll异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }

        /**
         * 添加，并缓存
         *
         * @param vo
         * @return
         */
        @Override
        public WebResult add(BsStaticVo vo) {
            LOGGER.info("add异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }

        /**
         * 批量删除，并删除缓存
         * 暂时这样
         *
         * @param id
         * @return
         */
        @Override
        public WebResult batchDel(List<Long> id) {
            LOGGER.info("batchDel异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }
    }
}
