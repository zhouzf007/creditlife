package com.entrobus.credit.controller;

import com.entrobus.credit.client.FileServiceClient;
import com.entrobus.credit.common.bean.FileUploadResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.util.FreemarkUtil;
import com.entrobus.credit.util.PDFUtil;
import com.entrobus.credit.vo.common.PdfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class CreditApiController {
    @Autowired
    private FileServiceClient fileServiceClient;

}
