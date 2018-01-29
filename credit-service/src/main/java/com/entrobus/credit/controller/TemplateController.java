package com.entrobus.credit.controller;

import com.entrobus.credit.util.FreemarkUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/template")
public class TemplateController {
    @RequestMapping("/test")
    public void test(String name, HttpServletResponse response){
        Map map = new HashMap();
        map.put("fullName",name);
        map.put("cellphone","12345678910");
        map.put("idCard","4521553269482156132");
        map.put("money","6000,000");
        map.put("term","12个月");
        map.put("interestStartDay","现在");
        map.put("repaymentMethod","按月付息");
        map.put("payee","收款人");//资金方
        map.put("payeeAccount","4654654968656");//资金方
        map.put("annualInterestRate","年化利率");
        map.put("payeeFullName","贷款人全称");

        //设置头部文件生成格式为微软word
        response.reset();
        try {
            response.setContentType("application/msword");
            response.setHeader("Content-disposition", "attachment; filename=" + new String("借款合同.doc".getBytes("GB2312"), "ISO8859-1"));
            OutputStream os = response.getOutputStream();
            FreemarkUtil.createWord(os,map,"loan_contract.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
