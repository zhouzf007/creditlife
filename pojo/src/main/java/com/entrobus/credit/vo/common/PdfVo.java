package com.entrobus.credit.vo.common;

import java.io.File;
import java.io.Serializable;

public class PdfVo implements Serializable {
    private String directory;//临时文件目录
    private String pdfName;//临时文件名称
    private String pdfURI;//临时文件地址
    private String htmlName;//临时html文件名称
    private String htmlURI;//临时html地址
    private File file;

    public String getPdfURI() {
        return pdfURI;
    }

    public void setPdfURI(String pdfURI) {
        this.pdfURI = pdfURI;
    }

    public String getHtmlURI() {
        return htmlURI;
    }

    public void setHtmlURI(String htmlURI) {
        this.htmlURI = htmlURI;
    }

    public String getHtmlName() {
        return htmlName;
    }

    public void setHtmlName(String htmlName) {
        this.htmlName = htmlName;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
