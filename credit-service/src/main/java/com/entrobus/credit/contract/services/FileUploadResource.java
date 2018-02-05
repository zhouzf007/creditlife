package com.entrobus.credit.contract.services;

import com.entrobus.credit.common.bean.FileUploadResult;
import feign.Headers;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

public interface FileUploadResource {
    @RequestLine("POST /postUploadFile")
    @Headers("Content-Type: multipart/form-data")
    FileUploadResult upload(@RequestParam("file") File file);
}
