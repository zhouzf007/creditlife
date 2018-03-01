package com.entrobus.credit.contract.util;

import com.entrobus.credit.common.util.CloseableUtil;
import com.entrobus.credit.common.util.FileUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.vo.common.PdfVo;
import com.lowagie.text.DocumentException;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
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
        PdfVo vo = new PdfVo();
        OutputStream out = null;
        try {
            String directory = getTempDirectory() + File.separator;

            //生成的PDF文件名称
//            String pdfName = directory + GUIDUtil.genRandomGUID() + System.currentTimeMillis() + ".pdf";
            String pdfName = String.format("%s%d.pdf",  GUIDUtil.genRandomGUID(),System.currentTimeMillis());
            String pdfURI = directory + pdfName ;
            out = new FileOutputStream(pdfURI);
            //生成html文件模板
            String html = PDFHelper.getPdfContent(templateName, data, directory);

            ITextRenderer render = PDFHelper.getRender();
            render.setDocument(new File(html).toURI().toURL().toString());
            if (imageDiskPath != null && !imageDiskPath.equals("")) {
                //new File(basePath).toURI().toURL().toString()
                render.getSharedContext().setBaseURL("file:/" + imageDiskPath);
            }
            logger.info("modifypdfName:"+pdfURI.replace("file:",""));
            render.layout();
            render.createPDF(out);
            render.finishPDF();

            vo.setDirectory(directory);
            vo.setPdfName(pdfName);
            vo.setPdfURI(pdfURI);
            vo.setHtmlURI(html);
            vo.setHtmlName(html.substring(html.lastIndexOf(File.separator) + 1));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            CloseableUtil.close(out);
        }
        return vo;
    }

//
//    /**
//     * 获取临时文件地址
//     *
//     * @return
//     */
//    private static String getTempDirectory() {
////        URL resourceUrl = clazz.getClassLoader().getResource("image_temp");
//        URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource("pdf_temp");
//        if(resourceUrl==null){
//            File dir = new File(Thread.currentThread().getContextClassLoader().getResource("").getFile()+"/pdf_temp");
//            if(dir.mkdir()){
//                resourceUrl = Thread.currentThread().getContextClassLoader().getResource("pdf_temp");
//            }
//        }
//        if (resourceUrl==null) return "";
//        String directory = resourceUrl.getFile();//偶尔会空指针，待优化
////        String directory = "/tmp";
//        if (!directory.endsWith(File.separator)) {
//            directory += File.separator;
//        }
//        return directory;
//    }
    /**
     * 获取临时文件地址
     *
     * @return
     */
    private static String getTempDirectory() throws FileNotFoundException {
        return FileUtil.getTempDirectory("pdf_tem/");
    }
}
