package com.entrobus.credit.user.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.file.FileService;
import com.entrobus.credit.common.file.bean.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gacl on 2017/12/28.
 */
@RestController
public class FileTestController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/uploadFile")
    public WebResult uploadFile(){
        UploadResult uploadResult = fileService.uploadFile("D:\\测试数据\\4.png");
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("uploadResult",uploadResult);
        return WebResult.ok(dataMap);
    }
}
