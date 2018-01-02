
package com.entrobus.credit.common.util;

import freemarker.core.ParseException;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import java.io.*;
import java.util.Map;
import freemarker.template.*;

/**
 * Freemarker工具类
 *
 */
public class FreemarkerUtil {

    private static final Logger logger = Logger.getLogger(FreemarkerUtil.class);

    private static Configuration cfg;

    /**
     * 初始化
     * @param cfg
     */
    public static void init(Configuration cfg) {
        FreemarkerUtil.cfg = cfg;
    }

    /**
     * 生成HTML
     * @param root
     * @param templatePath
     * @param htmlPath
     */
    public static String createHTML(Map root, String templatePath, String htmlPath) {
        Template template = null;
        File htmlFile = null;
        Writer out = null;
        try {
            htmlPath = htmlPath.replaceAll("\\\\", "/");
            htmlFile = new File(htmlPath);
            out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
            template = cfg.getTemplate(templatePath);
            template.process(root, out);
            return htmlPath;
        } catch (MalformedTemplateNameException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(out);
        }

    }

    /**
     * @param root
     * @param templatePath
     * @return
     */
    public static String process(Map root, String templatePath) {
        String flag = null;
        Writer out = null;
        Template template = null;
        try {
            out = new StringWriter();
            template = cfg.getTemplate(templatePath);
            template.process(root, out);
            flag = out.toString();
        } catch (MalformedTemplateNameException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(out);
        }
        return flag;
    }

    /**
     * 通过模板构造短信内容
     * @param templateName 模板名称
     * @param templateParamContent 模板参数内容
     * @return 构建好的内容
     */
    public static String getTemplateContent(String templateName,Map<String,String> templateParamContent){
        String content="";
        try {
            //通过指定模板名获取FreeMarker模板实例
            Template tpl=cfg.getTemplate(templateName);
            //FreeMarker通过Map传递动态数据
            //解析模板并替换动态数据，最终username将替换模板文件中的${username}标签。
            if(templateParamContent!=null){
                content= FreeMarkerTemplateUtils.processTemplateIntoString(tpl, templateParamContent);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return content;
    }


    /**
     * 获取freemaker模板文件
     * @param templateName
     * @return
     */
    public static Template getTemplateFile(String templateName){
        Template tpl = null;
        try{
            tpl=cfg.getTemplate(templateName);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
        }
        return tpl;
    }

    /**
     * 根据freemaker模板获取内容
     * @param template
     * @param templateParamContent
     * @return
     */
    public static String getTemplateContent(Template template,Map<String,String> templateParamContent){
        String content = null;
        try {
            content = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateParamContent);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return content;
    }
}
