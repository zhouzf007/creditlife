package com.entrobus.credit.controller;

import com.entrobus.credit.client.FileServiceClient;
import com.entrobus.credit.common.bean.FileUploadResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.util.PDFUtil;
import com.entrobus.credit.vo.common.PdfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private FileServiceClient fileServiceClient;


    @RequestMapping("/createPdf")
    public FileUploadResult createPdf(String templateName,String imageDiskPath,Map data){
        FileUploadResult uploadResult = null;
        PdfVo pdfVo = null;
        try {
            pdfVo = PDFUtil.generateToFile(templateName,imageDiskPath,data);
            uploadResult = fileServiceClient.uploadFile2FileServer(pdfVo.getFile());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConversionUtil.deletedirectory(pdfVo.getDirectory());
        }
        return uploadResult;
    }

    @ResponseBody
    @RequestMapping("/test")
    public FileUploadResult test(){
        Map<Object, Object> o=new HashMap<Object, Object>();
        //存入一个集合
        List<String> list = new ArrayList<String>();
        list.add("小明");
        list.add("张三");
        list.add("李四");
        o.put("name", "http://www.xdemo.org/");
        o.put("nameList", list);
        FileUploadResult uploadResult = createPdf("demo.ftl","pdf/img",o);
        return uploadResult;
    }

}
