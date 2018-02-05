package com.entrobus.credit.contract.controller;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.bean.FileUploadResult;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.contract.client.FileServiceClient;
import com.entrobus.credit.contract.services.ContractService;
import com.entrobus.credit.contract.services.impl.ContractServiceImpl;
import com.entrobus.credit.contract.util.PDFUtil;
import com.entrobus.credit.pojo.order.Contract;
import com.entrobus.credit.vo.common.PdfVo;
import com.entrobus.credit.vo.contract.ContractFillVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ContractController {
    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    @Autowired
    private FileServiceClient fileServiceClient;

    @GetMapping(value = "/contract")
    Contract getContract(@RequestParam("id") String id) {
        return contractService.selectByPrimaryKey(id);
    }

    @PostMapping(path = "/contract", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Contract saveContract(@RequestBody ContractFillVo vo) {
        Map v = JSON.parseObject(JSON.toJSONString(vo));
        FileUploadResult uploadResult = createPdf("loan_contract.ftl", "pdf/img", v);
        if (uploadResult != null && uploadResult.isUploadSuccess()) {
            Contract contract = new Contract();
            contract.setId(GUIDUtil.genRandomGUID());
            contract.setOrderId(vo.getOrderId());
            contract.setSignature(vo.getSignature());
            contract.setUserId(vo.getUserId());
            contract.setContractNo(vo.getContractNumber());
            contract.setContractUrl(uploadResult.getFileUrl());
            contractService.insertSelective(contract);
            return contract;
        }
        return null;
    }

    public FileUploadResult createPdf(String templateName, String imageDiskPath, Map data) {
        FileUploadResult uploadResult = null;
        PdfVo pdfVo = null;
        try {
            pdfVo = PDFUtil.generateToFile(templateName, imageDiskPath, data);
            File file = new File(pdfVo.getDirectory());
            InputStream in = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(pdfVo.getPdfName(), in);
            logger.info("IS EMPTY:" + multipartFile.isEmpty());
            logger.info(" getSize:" + multipartFile.getSize());
            logger.info(" getContentType:" + multipartFile.getContentType());
            uploadResult = fileServiceClient.postUploadFile(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pdfVo != null) {
//                ConversionUtil.de(pdfVo.getDirectory());
            }
        }
        return uploadResult;
    }


    @ResponseBody
    @RequestMapping("/test")
    public FileUploadResult test2() throws IOException {
        InputStream in = new FileInputStream("D:\\123.txt");
        MultipartFile multipartFile = new MockMultipartFile("123.text", in);
        long size = multipartFile.getSize();
        FileUploadResult uploadResult = fileServiceClient.postUploadFile(multipartFile);
        return uploadResult;
    }
}
