package com.entrobus.credit.file.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.file.service.FileService;
import com.entrobus.credit.file.bean.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class FileServiceController {

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
    @ResponseBody
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

}
