package com.entrobus.credit.file.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 针对上传文件返回结果的JavaBean
 */
public class UploadResult implements Serializable {

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
    /**
     * 描述信息
     */
    private String descDetail;

    /**
     * 上传使用时间
     */
    private Long useTime;

    /**
     * 扩展字段Map
     */
    private Map<String,Object> extFieldMap = new HashMap<>();

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

    public void setIsUploadSuccess(boolean isUploadSuccess) {
        this.isUploadSuccess = isUploadSuccess;
    }

    public String getDescDetail() {
        return descDetail;
    }

    public void setDescDetail(String descDetail) {
        this.descDetail = descDetail;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public Map<String, Object> getExtFieldMap() {
        return extFieldMap;
    }

    public void setExtFieldMap(Map<String, Object> extFieldMap) {
        this.extFieldMap = extFieldMap;
    }

    @Override
    public String toString() {
        return "UploadResult{" +
                "originalFileName='" + originalFileName + '\'' +
                ", newFileName='" + newFileName + '\'' +
                ", savePath='" + savePath + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileSuffix='" + fileSuffix + '\'' +
                ", storageType='" + storageType + '\'' +
                ", isUploadSuccess=" + isUploadSuccess +
                ", descDetail='" + descDetail + '\'' +
                ", useTime=" + useTime +
                ", extFieldMap=" + extFieldMap +
                '}';
    }
}
