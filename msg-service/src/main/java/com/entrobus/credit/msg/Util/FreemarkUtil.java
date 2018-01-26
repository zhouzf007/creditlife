package com.entrobus.credit.msg.Util;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
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
}
