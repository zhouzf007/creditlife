package com.entrobus.credit.contract.controller;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.bean.FileUploadResult;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.common.util.HttpClientUtil;
import com.entrobus.credit.contract.client.FileServiceClient;
import com.entrobus.credit.contract.services.ContractService;
import com.entrobus.credit.contract.util.PDFUtil;
import com.entrobus.credit.pojo.order.Contract;
import com.entrobus.credit.vo.common.PdfVo;
import com.entrobus.credit.vo.contract.ContractFillVo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.apache.http.entity.mime.content.FileBody;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ContractController {
    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    @Autowired
    private EurekaClient eurekaClient;

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
            //使用common工具包上传

            String fileServiceAddr = getFileServiceAddr();
            return HttpClientUtil.postFile(fileServiceAddr, file);
            //通过feign上传
//            InputStream in = new FileInputStream(file);
//            MultipartFile multipartFile = new MockMultipartFile(pdfVo.getPdfName(), in);
//            logger.info("IS EMPTY:" + multipartFile.isEmpty());
//            logger.info(" getSize:" + multipartFile.getSize());
//            logger.info(" getContentType:" + multipartFile.getContentType());
//            uploadResult = fileServiceClient.postUploadFile(multipartFile);
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
//        String BOUNDARY = "---------------------------123821742118716";
//        InputStream in = new FileInputStream("D:\\123.txt");
//        MultipartFile multipartFile = new MockMultipartFile("123.text","123.text", "multipart/form-data" ,in);
//        long size = multipartFile.getSize();
//        multipartFile.transferTo(new File("E:\\456.txt"));
//        FileUploadResource fileUploadResource = Feign.builder()
//                .encoder(new FormEncoder(new JacksonEncoder()))
//                .decoder(new JacksonDecoder())
//                .target(FileUploadResource.class, "http://localhost:8060/file");
//        FileUploadResult result= fileUploadResource.upload(new File("D:\\123.txt"));
//       FileUploadResult uploadResult = fileServiceClient.postUploadFile(multipartFile);
//        return uploadResult;
        String fileServiceAddr = getFileServiceAddr();
        return HttpClientUtil.postFile(fileServiceAddr, new File("D:\\123.txt"));
    }

    /**
     * 获取文件服务器可用的地址，由于feign上传文件有问题，暂时这样写
     *
     * @return
     */
    private String getFileServiceAddr() {
        Applications applications = eurekaClient.getApplications();
//        Application gateway = applications.getRegisteredApplications("gateway");//通过网关
        Application gateway = applications.getRegisteredApplications("file-service");//不经过网关
        List<InstanceInfo> instances = gateway.getInstances();
        if (instances != null && instances.size() > 0) {
            InstanceInfo info = instances.get(0);
//            String url = String.format("http://%s:%d/file/postUploadFile", info.getHostName(),info.getPort()) ;//通过网关
            String url = String.format("http://%s:%d/postUploadFile", info.getHostName(),info.getPort()) ;//不经过网关

            return url;
        }
        return null;
    }


}
