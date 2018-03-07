package com.entrobus.credit.contract.controller;

import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.AmountUtil;
import com.entrobus.credit.common.util.DateUtils;
import com.entrobus.credit.contract.services.CreditCacheService;
import com.entrobus.credit.contract.util.FreemarkUtil;
import com.entrobus.credit.vo.order.ApplyVo;
import com.entrobus.credit.vo.user.CacheUserInfo;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 合同等文件合成预览
 */
@Controller
@RequestMapping("/preview")
public class PreviewController {

    @Autowired
    private CreditCacheService cacheService;
    @PostMapping("/loan_contract")
    @ResponseBody
    public WebResult previewLoanContract(Map<String,Object> model,@Validated @RequestBody ApplyVo vo) throws Exception {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(vo.getToken());
        if (userInfo == null) return WebResult.fail(WebResult.CODE_NOT_LOGIN);
        //预览不需要显示编号
//        model.put("contractNumber", "123456789");//合同编号，与银行管理后台显示的编号一致
        model.put("borrowerFullName", userInfo.getRealName() == null ? "" : userInfo.getRealName());//借款人全名
        model.put("lenderFullName", "中国建设银行股份有限公司佛山分行");//贷款人全称，将来可配置，目前“中国建设银行股份有限公司佛山分行”

        model.put("borrowerCellphone", userInfo.getCellphone());//借款人手机号
        model.put("borrowerIdCard", userInfo.getIdCard());//借款人证件号（身份证）
        model.put("money", vo.getMoney() + "元"); //借款金额
        model.put("capitalMoney", String.format("%s整",AmountUtil.change2Upper(vo.getMoney()))); //中文大写金额，如：叁拾万元整
        model.put("term", vo.getRepaymentTerm() + "个月");//借款期限
        model.put("interestStartDay", "自您提款成功日起计收利息");//起息日
        model.put("repaymentMethod", cacheService.translate(Cachekey.Translation.REPAYMENT_TYPE + vo.getRepaymentType()));//还款方式

        String defualtAccount = userInfo.getDefualtAccount();
        defualtAccount = StringUtils.isBlank(defualtAccount) ? "" : defualtAccount;
        String accountBank = userInfo.getAccountBank();
        accountBank = accountBank == null ? "" : accountBank;
        String appointPayeeAccount = String.format("%s  %s",accountBank, defualtAccount.length() > 4 ? defualtAccount.substring(defualtAccount.length() - 4) : defualtAccount);
        //指定收款账户，借款人的银行卡所属银行和卡号最后四位，例如：中国建设银行 2678
        model.put("appointPayeeAccount", appointPayeeAccount);
        model.put("annualInterestRate", "年化利率" + vo.getRate() / 100 + "%");//年化利率
        model.put("noOperationInvalidTime", "3个月");//借款额度审批通过之日起无操作失效时间

        model.put("borrowerAddress", userInfo.getAddressHouse());//借款人住址，从个人信用报告接口中返回
        model.put("borrowerPostalAddress", userInfo.getAddressHouse());//借款人通讯地址，从个人信用报告接口中返回
//        model.put("borrowerPostalCode", "邮政编码");//借款人邮政编码 ,暂时没有

        model.put("borrowerCardBank", accountBank);//借款人开卡银行
        model.put("borrowerCardId", defualtAccount);//借款人银行卡号

        model.put("lenderName", accountBank);//贷款人名称，暂填 中国建设银行
        model.put("lenderHeadquartersName", "中国建设银行股份有限公司");//总公司名称，暂填 中国建设银行股份有限公司
        model.put("lenderAddress", "广东省佛山市佛山大道南327号");//贷款人住址，将来可配置，目前“广东省佛山市佛山大道南327号”
        model.put("lenderPostalAddress", "广东省佛山市佛山大道南327号");//贷款人通讯地址，将来可配置，目前“广东省佛山市佛山大道南327号”
        model.put("lenderPostalCode", "528000");//贷款人通讯地址，将来可配置，目前“528000”
        model.put("lenderPhone", "0757-82781212");//贷款人联系电话，将来可配置，目前“0757-82781212”

        //这里跟通讯地址有什么区别暂时不知道，但是注明了 从个人信用报告接口中返回
        //即 借款人住址,已合并
//        model.put("borrowerMailingAddress", "邮寄地址");//乙方（借款人）邮寄地址，即 借款人住址 ，从个人信用报告接口中返回

        model.put("loanValidityPeriodStart", DateUtils.formatDate(new Date(), "yyyy年MM月dd日"));//借款额度有效期开始日期，用户提交申请的当日日期
        model.put("loanValidityPeriodEnd", DateUtils.formatDate(DateUtils.addYears(new Date(), 1), "yyyy年MM月dd日"));//借款额度有效期结束日期，借款额度有效期开始日期 一年后

        //签名图片 使用base64编码，其中png是图片格式
//        model.put("borrowerAutograph", "data:image/png;base64," + "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");
//        model.put("borrowerAutograph", "data:image/png;base64,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");
//        model.put("borrowerAutograph", ImageUtil.getImageBase64Src(vo.getSignature()));
        //        针对移动端使用h5头部，需传headType == h5,缺省使用打印体
        model.put("headType","h5");

        String template = FreemarkUtil.getTemplate("loan_contract.ftl", model);
        Map<String, Object> data = new HashMap<>();
        data.put("template", template.replaceAll("[\r\n]",""));
        return WebResult.ok().data(data);
    }

    /**
     * 预览模板
     * @param model
     * @return
     */
    @GetMapping("/{ftlName}")
    public String previewCreditReportQueryAuthorization(Map<String,Object> model, @PathVariable("ftlName") String ftlName)  {
        model.put("lenderHeadquartersName", "中国建设银行股份有限公司");//总公司名称，暂填 中国建设银行股份有限公司
        model.put("lenderName", "中国建设银行");//贷款人名称，暂填 中国建设银行
//        针对移动端使用h5头部，需传headType == h5,缺省使用打印体
        model.put("headType","h5");
        return ftlName;
    }

    /**
     * 预览模板
     * @param model
     * @return
     */
    @PostMapping("/transformation")
    public String transformation(Map<String,Object> model, @RequestBody String content)  {
        StringBuffer sb = new StringBuffer();
        if (content != null){
            String[] split = content.replaceAll(" ","&#160;").split("\n");
            for (String line : split) {
                if (StringUtils.isNotBlank(line)) {
                    sb.append("\t<p class='main_text'>").append(line).append("</p>");
                }else {
                    sb.append(line);
                }
                sb.append("\n");
            }
        }

        model.put("content",sb.toString());
        return "transformation";
    }


}
