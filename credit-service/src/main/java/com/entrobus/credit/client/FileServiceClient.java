package com.entrobus.credit.client;

import com.entrobus.credit.common.bean.FileUploadResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

/**
 * 调用文件上传服务
 */
@FeignClient(name = "file-service", fallback = FileServiceClient.FileServiceClientHystric.class)
public interface FileServiceClient {

    /**
     * 上传网络文件
     * @param fileUrl 文件URL
     * @return FileUploadResult 文件上传结果
     */
    @RequestMapping("/uploadNetworkFile")
    FileUploadResult uploadNetworkFile(@RequestParam(value = "fileUrl") String fileUrl);

    /**
     * 上传网络文件
     * @param fileUrl 文件URL
     * @param fileExt 文件后缀
     * @return FileUploadResult 文件上传结果
     */
    @RequestMapping("/uploadNetworkFile2")
    FileUploadResult uploadNetworkFile2(@RequestParam(value = "fileUrl") String fileUrl, @RequestParam(value = "fileExt") String fileExt);

    /**
     * 上传文件到文件服务器
     * @param file
     * @return
     */
    @RequestMapping("/uploadFile2FileServer")
    FileUploadResult uploadFile2FileServer(@RequestParam(value = "file") File file);


    /**
     * 文件上传客户端断路器
     */
    @Component
    class  FileServiceClientHystric implements FileServiceClient{

        private static final Logger logger = LoggerFactory.getLogger(FileServiceClientHystric.class);

        @Override
        public FileUploadResult uploadNetworkFile(String fileUrl) {
            logger.info("调用文件服务上传网络文件出错，进入fallback方法");
            return null;
        }

        @Override
        public FileUploadResult uploadNetworkFile2(String fileUrl, String fileExt) {
            logger.info("调用文件服务上传网络文件出错，进入fallback方法");
            return null;
        }

        @Override
        public FileUploadResult uploadFile2FileServer(File file) {
            logger.info("调用文件服务上传网络文件出错，进入fallback方法");
            return null;
        }
    }

}
