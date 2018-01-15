package com.entrobus.credit.file.controller;

import com.entrobus.credit.common.bean.FileUploadResult;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.file.bean.UploadResult;
import com.entrobus.credit.file.service.FileService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class FileServiceController {

    //日志打印
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FileService fileService;

    /**
     * 上传单个文件（测试通过）
     *
     * @return
     */
    @RequestMapping("/uploadFile")
    public WebResult uploadFile(@RequestParam(value = "file", required = false) MultipartFile file) {
        UploadResult uploadResult = fileService.uploadFile(file);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("uploadResult", uploadResult);
        return WebResult.ok(dataMap);
    }

    /**
     * 上传多文件
     *
     * @return
     */
    @RequestMapping("/uploadFiles")
    public WebResult uploadFiles(HttpServletRequest request) {
        Map<String, Object> dataMap = new HashMap<>();
        CommonsMultipartResolver mReso = new CommonsMultipartResolver(request.getServletContext());
        if (mReso.isMultipart(request)) {
            MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
            //获取上传文件
            List<MultipartFile> multipartFiles = mreq.getFiles("files");
            //移除multipartFiles集合中文件size等于0的那些文件
            Iterator<MultipartFile> it = multipartFiles.iterator();
            while (it.hasNext()) {
                MultipartFile tmpFile = it.next();
                if (tmpFile.getSize() == 0) {
                    it.remove();
                }
            }

            List<MultipartFile> multipartFileList = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                try {
                    multipartFileList.add(multipartFile);
                } catch (Exception e) {
                }
            }
            //保存上传文件
            List<UploadResult> uploadFileList = fileService.uploadFile(multipartFileList);
            dataMap.put("uploadResult", uploadFileList);
        }
        return WebResult.ok(dataMap);
    }

    /**
     * 上传网络文件
     * @param fileUrl 文件URL
     * @return FileUploadResult 文件上传结果
     */
    @RequestMapping("/uploadNetworkFile")
    public FileUploadResult uploadNetworkFile(String fileUrl){
        FileUploadResult fileUploadResult = new FileUploadResult();
        try {
            UploadResult uploadResult = fileService.uploadNetworkFile(fileUrl);
            BeanUtils.copyProperties(fileUploadResult,uploadResult);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            fileUploadResult.setUploadSuccess(false);
        }
        return fileUploadResult;
    }

    /**
     * 上传网络文件
     * @param fileUrl 文件URL
     * @param fileExt 文件后缀
     * @return FileUploadResult 文件上传结果
     */
    @RequestMapping("/uploadNetworkFile2")
    public FileUploadResult uploadNetworkFile2(String fileUrl,String fileExt){
        FileUploadResult fileUploadResult = new FileUploadResult();
        try {
            UploadResult uploadResult = fileService.uploadNetworkFile(fileUrl,fileExt);
            BeanUtils.copyProperties(fileUploadResult,uploadResult);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            fileUploadResult.setUploadSuccess(false);
        }
        return fileUploadResult;
    }
}
