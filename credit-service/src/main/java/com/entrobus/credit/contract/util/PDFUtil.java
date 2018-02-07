package com.entrobus.credit.contract.util;

import com.entrobus.credit.common.util.CloseableUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.vo.common.PdfVo;
import com.lowagie.text.DocumentException;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

/**
 * Created by cwh on 2018/1/29.
 */
public class PDFUtil {
    private final static Logger logger = LoggerFactory.getLogger(PDFUtil.class);

    /**
     * 生成PDF文件
     *
     * @param templateName  模板名称
     * @param imageDiskPath 图片存放目录
     * @param data          freemark模板填充所需数据
     * @throws Exception
     */
    public static PdfVo generateToFile(String templateName, String imageDiskPath, Map data) throws Exception {
        String directory = null;
        String pdfName = null;
        String mpdf="";
        OutputStream out = null;
        try {
            directory = getTempDirectory();
            //生成的PDF文件名称
            pdfName = directory + GUIDUtil.genRandomGUID() + System.currentTimeMillis() + ".pdf";
            out = new FileOutputStream(pdfName);
            //生成html文件模板
            String html = PDFHelper.getPdfContent(templateName, data, directory);
            ITextRenderer render = PDFHelper.getRender();
            render.setDocument(html);
            if (imageDiskPath != null && !imageDiskPath.equals("")) {
                //new File(basePath).toURI().toURL().toString()
                render.getSharedContext().setBaseURL("file:/" + imageDiskPath);
            }
            mpdf=html.substring(0,html.lastIndexOf("/")+1)+pdfName;
            logger.info("modifypdfName:"+mpdf.replace("file:",""));
            render.layout();
            render.createPDF(out);
            render.finishPDF();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            CloseableUtil.close(out);
        }
        PdfVo vo = new PdfVo();
        vo.setDirectory(mpdf.replace("file:",""));
        vo.setPdfName(pdfName);
//        File pf=new File(mpdf.replace("file:",""));
//        logger.info("TotalSpace"+pf.length());
//        vo.setFile(pf);
        return vo;
    }


    /**
     * 获取临时文件地址
     *
     * @return
     */
    private static String getTempDirectory() {
//        URL resourceUrl = clazz.getClassLoader().getResource("image_temp");
        URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource("pdf_temp");
        if(resourceUrl==null){
            File dir = new File(Thread.currentThread().getContextClassLoader().getResource("").getFile()+"/pdf_temp");
            if(dir.mkdir()){
                resourceUrl = Thread.currentThread().getContextClassLoader().getResource("pdf_temp");
            }
        }
        if (resourceUrl==null) return "";
        String directory = resourceUrl.getFile();//偶尔会空指针，待优化
//        String directory = "/tmp";
        if (!directory.endsWith(File.separator)) {
            directory += File.separator;
        }
        return directory;
    }
}
