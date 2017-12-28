package com.entrobus.credit.file.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.file.FileService;
import com.entrobus.credit.file.bean.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileServiceController {

    @Autowired
    private FileService fileService;

    /**
     * 上传单个文件
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


}
