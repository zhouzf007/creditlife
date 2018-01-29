package com.entrobus.credit.util;

import com.entrobus.credit.common.util.GUIDUtil;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;

/**
 * Created by cwh on 2018/1/29.
 */
public class PDFHelper {

    public static ITextRenderer getRender() throws DocumentException, IOException {
        ITextRenderer render = new ITextRenderer();
        String path = getPath();
        //添加字体，以支持中文
        //解决中文问题（模版页面test3.html配套加上
        //<style mce_bogus="1" type="text/css">
        //body {font-family: SimSun; background:none;margin-left: auto;margin-right: auto;}
        //</style>）
        render.getFontResolver().addFont(path + "pdf/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        render.getFontResolver().addFont(path + "pdf/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        return render;
    }

    /**
     * 获取要写入PDF的内容
     * @param templateName 模板名称
     * @param dataMap freemark模板填充所需数据
     * @param directory 临时文件目录
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String getPdfContent(String templateName, Map dataMap,String directory) throws  IOException, TemplateException {
        return useTemplate(templateName,dataMap,directory);
    }

    //使用freemarker得到html内容
    public static String useTemplate(String templateName, Map dataMap,String directory) throws IOException, TemplateException {
        //创建freemark模板
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(PDFHelper.class, "/templates/");//这里是指放在classes下
        Template template = configuration.getTemplate(templateName);
        //生成html文件
        String htmlName = directory + GUIDUtil.genRandomGUID() + System.currentTimeMillis() + ".html";
        // 将模板和数据模型合并生成文件
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlName), "UTF-8"));
        template.process(dataMap, writer);
        // 关闭流
        writer.flush();
        writer.close();
        return new File(htmlName).toURI().toURL().toString();
    }

    /**
     * 获取类路径
     * @return
     */
    public static String getPath(){
        return PDFHelper.class.getResource("/").getPath().substring(1);
    }

}
