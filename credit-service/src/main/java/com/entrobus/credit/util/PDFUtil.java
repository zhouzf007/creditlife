package com.entrobus.credit.util;

import com.entrobus.credit.client.FileServiceClient;
import com.entrobus.credit.common.bean.FileUploadResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.vo.common.PdfVo;
import com.lowagie.text.DocumentException;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cwh on 2018/1/29.
 */
public class PDFUtil {

    /**
     * 生成PDF文件
     * @param templateName 模板名称
     * @param imageDiskPath 图片存放目录
     * @param data freemark模板填充所需数据
     * @throws Exception
     */
    public static PdfVo generateToFile(String templateName,String imageDiskPath,Map data) throws Exception {
        String directory = null;
        String pdfName = null;
        try {
            directory = getTempDirectory();
            //生成的PDF文件名称
            pdfName = directory + GUIDUtil.genRandomGUID()+System.currentTimeMillis()+".pdf";
            OutputStream out = new FileOutputStream(pdfName);
            //生成html文件模板
            String html = PDFHelper.getPdfContent(templateName, data,directory);
            ITextRenderer render = PDFHelper.getRender();
            render.setDocument(html);
            if(imageDiskPath!=null&&!imageDiskPath.equals("")){
                //new File(basePath).toURI().toURL().toString()
                render.getSharedContext().setBaseURL("file:/"+imageDiskPath);
            }
            render.layout();
            render.createPDF(out);
            render.finishPDF();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        PdfVo vo = new PdfVo();
        vo.setDirectory(directory);
        vo.setPdfName(pdfName);
        vo.setFile(new File(pdfName));
        return vo;
    }



    /**
     * 获取临时文件地址
     * @return
     */
    private static String getTempDirectory() {
        //URL resourceUrl = clazz.getClassLoader().getResource("image_temp");
        URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource("pdf_temp");
        if(resourceUrl==null){
            File dir = new File(Thread.currentThread().getContextClassLoader().getResource("").getFile()+"/pdf_temp");
            if(dir.mkdir()){
                resourceUrl = Thread.currentThread().getContextClassLoader().getResource("pdf_temp");
            }
        }
        String directory = resourceUrl.getFile();//偶尔会空指针，待优化
        if(!directory.endsWith(File.separator)){
            directory += File.separator;
        }
        return directory;
    }
}
