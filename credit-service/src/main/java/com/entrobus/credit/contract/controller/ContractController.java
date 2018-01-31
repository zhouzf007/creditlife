package com.entrobus.credit.contract.controller;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.bean.FileUploadResult;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.contract.client.FileServiceClient;
import com.entrobus.credit.contract.services.ContractService;
import com.entrobus.credit.contract.util.PDFUtil;
import com.entrobus.credit.pojo.order.Contract;
import com.entrobus.credit.vo.common.PdfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private FileServiceClient fileServiceClient;

    @PostMapping(path = "/contract")
    public WebResult saveContract(String vo, Contract contract) {
        Map rs = JSON.parseObject(vo);
//        FileUploadResult uploadResult = createPdf("loan_contract.ftl", "pdf/img", rs);
//        if (uploadResult != null && uploadResult.isUploadSuccess()) {
//            contract.setContractUrl(uploadResult.getFileUrl());
//        }
        contractService.insertSelective(contract);
        return WebResult.ok();
    }

    public FileUploadResult createPdf(String templateName, String imageDiskPath, Map data) {
        FileUploadResult uploadResult = null;
        PdfVo pdfVo = null;
        try {
            pdfVo = PDFUtil.generateToFile(templateName, imageDiskPath, data);
            uploadResult = fileServiceClient.uploadFile2FileServer(pdfVo.getFile());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pdfVo != null) {
                ConversionUtil.deletedirectory(pdfVo.getDirectory());
            }
        }
        return uploadResult;
    }

}