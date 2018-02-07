package com.entrobus.credit.contract.client;

import com.entrobus.credit.common.bean.FileUploadResult;
import com.entrobus.credit.contract.config.FeignMultipartSupportConfig;
import com.entrobus.credit.contract.config.MultipartFileHttpMessageConverter;
import feign.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * 调用文件上传服务
 */
@FeignClient(name = "file-service", fallback = FileServiceClient.FileServiceClientHystric.class)
public interface FileServiceClient {

    /**
     * 上传网络文件
     *
     * @param fileUrl 文件URL
     * @return FileUploadResult 文件上传结果
     */
    @RequestMapping("/uploadNetworkFile")
    FileUploadResult uploadNetworkFile(@RequestParam(value = "fileUrl") String fileUrl);

    /**
     *
     * @return uploadFileStream 文件上传结果
     */
    @RequestMapping(value = "/postUploadFile",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @Headers("Content-Type: multipart/form-data")
    @Deprecated//暂时不可用
    FileUploadResult postUploadFile(@RequestPart("file") MultipartFile file);

    /**
     * 上传网络文件
     *
     * @param fileUrl 文件URL
     * @param fileExt 文件后缀
     * @return FileUploadResult 文件上传结果
     */
    @RequestMapping("/uploadNetworkFile2")
    FileUploadResult uploadNetworkFile2(@RequestParam(value = "fileUrl") String fileUrl, @RequestParam(value = "fileExt") String fileExt);

    /**
     * 上传文件到文件服务器
     *
     * @param file
     * @return
     */
    @RequestMapping("/uploadFile2FileServer")
    FileUploadResult uploadFile2FileServer(@RequestParam(value = "file") File file);

    /**
     *
     * @return uploadFileStream 文件上传结果
     */
    @PostMapping(value = "/uploadFileStream",consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Deprecated
    FileUploadResult uploadFileStream(@RequestBody InputStream inputStream,@RequestParam("fileName") String fileName);
    /**
     * 文件上传客户端断路器
     */
    @Component
    class FileServiceClientHystric implements FileServiceClient {

        private static final Logger logger = LoggerFactory.getLogger(FileServiceClientHystric.class);

        @Override
        public FileUploadResult uploadNetworkFile(String fileUrl) {
            logger.info("调用文件服务上传网络文件出错，进入fallback方法");
            return null;
        }

        @Override
        public FileUploadResult postUploadFile(MultipartFile file) {
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

        @Override
        public FileUploadResult uploadFileStream(InputStream inputStream, String fileName) {
            return null;
        }
    }

}
