package com.entrobus.credit.user.client;

import com.entrobus.credit.vo.base.BsStaticVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "base-service", fallback = BsStaticsClient.BsStaticsClientFallback.class)
@RequestMapping("/statics")
public interface BsStaticsClient {
    /**
     * 从缓存获取，如果缓存中没有，则刷新缓存
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    BsStaticVo getBsStatic(@PathVariable("id") Long id);

    /**
     * 获取同类型的
     *
     * @param codeType
     * @return
     */
    @GetMapping("/similar")
    List<BsStaticVo> getByType(@RequestParam("codeType") String codeType);

    /**
     * 根据条件搜索
     *
     * @return
     */
    @GetMapping("/search")
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
    @GetMapping("/unique")
    BsStaticVo getByTypeAndValue(@RequestParam("codeType") String codeType, @RequestParam("codeValue") String codeValue);
    /**
     * 跟据codeType和codeValue查询CodeName
     *
     * @param codeType
     * @return
     */
    @GetMapping("/name")
    String getCodeName(@RequestParam("codeType") String codeType, @RequestParam("codeValue") String codeValue);




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
    }
}
