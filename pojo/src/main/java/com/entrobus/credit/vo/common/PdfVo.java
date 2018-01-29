package com.entrobus.credit.vo.common;

import java.io.File;
import java.io.Serializable;

public class PdfVo implements Serializable {
    private String directory;//临时文件地址
    private String pdfName;//临时文件名称
    private File file;

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
