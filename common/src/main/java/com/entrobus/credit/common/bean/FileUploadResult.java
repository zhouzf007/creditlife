package com.entrobus.credit.common.bean;

import java.io.Serializable;

/**
 * 文件上传结果
 */
public class FileUploadResult implements Serializable {

    /**
     * 上传文件的原始文件名
     */
    private String originalFileName;

    /**
     * 重命名后的文件名
     */
    private String newFileName;

    /**
     * 文件保存路径
     */
    private String savePath;

    /**
     * 文件网络访问URL地址
     */
    private String fileUrl;

    /**
     * 文件后缀名
     */
    private String fileSuffix;

    /**
     * 存储方式
     */
    private String storageType;

    /**
     * 上传结果：true:成功
     *           false：失败
     *
     */
    private boolean isUploadSuccess;

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public boolean isUploadSuccess() {
        return isUploadSuccess;
    }

    public void setUploadSuccess(boolean uploadSuccess) {
        isUploadSuccess = uploadSuccess;
    }
}
