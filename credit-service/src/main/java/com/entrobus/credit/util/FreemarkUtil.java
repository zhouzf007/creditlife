package com.entrobus.credit.util;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.WordUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

public class FreemarkUtil {

    public static String getTemplate(String templateName,Map model) throws IOException, TemplateException {
        Configuration configuration = new Configuration();

        TemplateLoader c1 = new ClassTemplateLoader(TemplateLoader.class,"/templates/");

        configuration.setTemplateLoader(c1);

        Template template = configuration.getTemplate(templateName);

        String txt = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);

        return txt;
    }

    public final static boolean createWord(OutputStream os, Map dataMap, String templateName) {
        try {
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            configuration.setClassForTemplateLoading(WordUtils.class, "/templates/");//这里是指放在classes下
            // 获取模板
            Template template = configuration.getTemplate(templateName);
            // 将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            template.process(dataMap, out);
            // 关闭流
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
