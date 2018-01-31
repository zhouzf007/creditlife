package com.entrobus.credit.contract.controller;

import com.entrobus.credit.common.bean.FileUploadResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.common.util.ImageUtil;
import com.entrobus.credit.contract.client.FileServiceClient;
import com.entrobus.credit.contract.util.FreemarkUtil;
import com.entrobus.credit.contract.util.PDFUtil;
import com.entrobus.credit.vo.common.PdfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private FileServiceClient fileServiceClient;
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
        //签名图片 使用base64编码，
        map.put("autograph","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");

        //设置头部文件生成格式为微软word
        response.reset();
        try {
            response.setContentType("application/msword");
            response.setHeader("Content-disposition", "attachment; filename=" + new String("借款合同.doc".getBytes("GB2312"), "ISO8859-1"));
            OutputStream os = response.getOutputStream();
            FreemarkUtil.write(os,map,"loan_contract.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/html")
    public void html(String name, HttpServletResponse response) {
        createTestMap(name);


    }

    private Map<String,Object> createTestMap(String name) {
        Map map = new HashMap();
        map.put("contractNumber", "123456789");//合同编号，与银行管理后台显示的编号一致
        map.put("borrowerFullName", name);//借款人全名
        map.put("lenderFullName", "中国建设银行股份有限公司佛山分行");//贷款人全称，将来可配置，目前“中国建设银行股份有限公司佛山分行”

        map.put("borrowerCellphone", "12345678910");//借款人手机号
        map.put("borrowerIdCard", "4521553269482156132");//借款人证件号（身份证）
        map.put("money", "6000,000 元"); //借款金额
        map.put("capitalMoney", "叁拾万元整"); //中文大写金额，如：叁拾万元整
        map.put("term", "12个月");//借款期限
        map.put("interestStartDay", "自您提款成功日起计收利息");//起息日
        map.put("repaymentMethod", "按月付息");//还款方式

        map.put("appointPayeeAccount", "中国建设银行  4524");//指定收款账户，借款人的银行卡所属银行和卡号最后四位，例如：中国建设银行 2678
        map.put("annualInterestRate", "6%");//年化利率
        map.put("noOperationInvalidTime", "3个月");//借款额度审批通过之日起无操作失效时间

        map.put("borrowerAddress", "借款人住址");//借款人住址
        map.put("borrowerPostalAddress", "通讯地址");//通讯地址
        map.put("borrowerPostalCode", "邮政编码");//通讯地址
        map.put("borrowerCardBank", "中国建设银行");//借款人开卡银行
        map.put("borrowerCardId", "66541646454165");//借款人银行卡号

        map.put("lenderName", "中国建设银行");//贷款人名称，暂填 中国建设银行
        map.put("lenderAddress", "广东省佛山市佛山大道南327号");//贷款人住址，将来可配置，目前“广东省佛山市佛山大道南327号”
        map.put("lenderPostalAddress", "广东省佛山市佛山大道南327号");//贷款人通讯地址，将来可配置，目前“广东省佛山市佛山大道南327号”
        map.put("lenderPostalCode", "528000");//贷款人通讯地址，将来可配置，目前“528000”
        map.put("lenderPhone", "0757-82781212");//贷款人联系电话，将来可配置，目前“0757-82781212”

        //这里跟通讯地址有什么区别暂时不知道，但是注明了 从个人信用报告接口中返回
        map.put("borrowerMailingAddress", "邮寄地址");//乙方（借款人）邮寄地址，从个人信用报告接口中返回

        map.put("loanValidityPeriodStart", "2018年3月24日");//借款额度有效期开始日期，用户提交申请的当日日期
        map.put("loanValidityPeriodEnd", "2019年3月24日");//借款额度有效期结束日期，借款额度有效期开始日期 一年后

        //签名图片 使用base64编码，其中png是图片格式
        map.put("borrowerAutograph", "data:image/png;base64,"+"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");
        try {
            map.put("borrowerAutograph", ImageUtil.getImageBase64Src("http://fdfs-test.newseax.com/group1/M00/03/23/CmhqV1pxNLuAekCzAAAe6LzLBXM649.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

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
//            ConversionUtil.deletedirectory(pdfVo.getDirectory());
        }
        return uploadResult;
    }

    @ResponseBody
    @RequestMapping("/test2")
    public FileUploadResult test2(){
        Map<String, Object> o= createTestMap("aa");
        //存入一个集合
//        List<String> list = new ArrayList<String>();
//        list.add("小明");
//        list.add("张三");
//        list.add("李四");
//        o.put("name", "http://www.xdemo.org/");
//        o.put("nameList", list);

        FileUploadResult uploadResult = createPdf("loan_contract.ftl","pdf/img",o);
        return uploadResult;
    }

}
