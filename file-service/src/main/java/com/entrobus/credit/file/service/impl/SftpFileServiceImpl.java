package com.entrobus.credit.file.service.impl;

import com.entrobus.credit.file.util.FileUtil;
import com.entrobus.credit.file.bean.UploadResult;
import com.entrobus.credit.file.service.FileService;
import com.smartfast4j.jsch.SftpFileService;
import com.smartfast4j.jsch.bean.SftpUploadResult;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于Sftp上传实现
 * Created by gacl on 2017/10/16.
 */
public class SftpFileServiceImpl implements FileService {

    private static Logger logger = Logger.getLogger(SftpFileServiceImpl.class);

    /**
     * 文件上传后的存储方式
     */
    private static final String STORAG_TYPE = "SFTP";
    /**
     * Sftp连接客户端初始化用到的properties配置文件
     */
    private static final String SFTP_CONFIG_FILE_PATH = "sFtpConfig.properties";

    private SftpFileService sftpFileService;

    //使用volatile关键字保其可见性
    volatile private static FileService instance = null;

    /**
     * 获取实例
     * 单例模式是为确保一个类只有一个实例，并为整个系统提供一个全局访问点的一种模式方法。
     从概念中体现出了单例的一些特点：
     （1）、在任何情况下，单例类永远只有一个实例存在
     （2）、单例需要有能力为整个系统提供这一唯一实例
     * @return
     */
    public static FileService getInstance() {
        if(instance != null){//懒汉式
            String className = instance.getClass().getName();
            logger.debug("懒汉式：FileService实例已经存在，直接返回使用，该实例的hashCode="+instance.hashCode()+"，FileService使用的实现类是："+className);
        }else{
            synchronized (FileService.class) {
                if(instance == null){//二次检查
                    instance = new SftpFileServiceImpl();
                    String className = instance.getClass().getName();
                    logger.debug("FileService实例还未创建，创建该实例，该实例的hashCode="+instance.hashCode()+"，FileService使用的实现类是："+className);
                }else{
                    String className = instance.getClass().getName();
                    logger.debug("二次检查：FileService实例已经存在，直接返回使用，该实例的hashCode="+instance.hashCode()+"，FileService使用的实现类是："+className);
                }
            }
        }
        return instance;
    }

    private SftpFileServiceImpl(){
        sftpFileService = new SftpFileService(SFTP_CONFIG_FILE_PATH);
    }

    @Override
    public UploadResult uploadFile(String filePath) {
        return uploadFile(filePath,"");
    }

    @Override
    public UploadResult uploadFile(String filePath, String savePath) {
        File file = new File(filePath);
        return uploadFile(file,savePath);
    }

    @Override
    public UploadResult uploadFile(InputStream inputStream, String fileName) {
        return uploadFile(inputStream, fileName, "");
    }

    @Override
    public UploadResult uploadFile(MultipartFile multipartFile) {
        UploadResult uploadResult;
        String fileName = multipartFile.getOriginalFilename();// 文件名
        try {
            uploadResult = uploadFile(multipartFile.getBytes(),fileName);
        } catch (IOException e) {
            e.printStackTrace();
            uploadResult = new UploadResult();
            uploadResult.setOriginalFileName(fileName);
            uploadResult.setIsUploadSuccess(false);
        }
        return uploadResult;
    }

    @Override
    public List<UploadResult> uploadFile(List<MultipartFile> multipartFiles) {
        Map<String, InputStream> inputStreamMap = new HashMap<>();
        for(MultipartFile multipartFile:multipartFiles){
            try {
                inputStreamMap.put(multipartFile.getOriginalFilename(),multipartFile.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //一次性上传多个文件
        return uploadFile(inputStreamMap);
    }

    @Override
    public List<UploadResult> uploadFile(Map<String, InputStream> inputStreamMap) {
        return uploadFile(inputStreamMap,"");
    }

    @Override
    public UploadResult uploadFile(InputStream inputStream, String fileName, String savePath) {
        SftpUploadResult sftpUploadResult = sftpFileService.uploadFile(inputStream,fileName,savePath);
        return createResult(sftpUploadResult);
    }

    @Override
    public List<UploadResult> uploadFile(Map<String, InputStream> inputStreamMap, String savePath) {
        List<UploadResult> uploadResults = null;
        List<SftpUploadResult> sftpUploadResults = sftpFileService.uploadFile(inputStreamMap,savePath);
        if(sftpUploadResults != null){
            uploadResults = new ArrayList<>();
            for(SftpUploadResult sftpUploadResult:sftpUploadResults){
                uploadResults.add(createResult(sftpUploadResult));
            }
        }
        return uploadResults;
    }

    @Override
    public UploadResult uploadFile(File file, String savePath) {
        SftpUploadResult sftpUploadResult = sftpFileService.uploadFile(file,savePath);
        return createResult(sftpUploadResult);
    }

    @Override
    public UploadResult uploadFile(byte[] fileByte, String fileName) {
       return uploadFile(fileByte,fileName, "");
    }

    @Override
    public UploadResult uploadFile(byte[] fileByte, String fileName, String savePath) {
        SftpUploadResult sftpUploadResult = sftpFileService.uploadFile(fileByte,fileName,savePath);
        return createResult(sftpUploadResult);
    }

    @Override
    public UploadResult uploadNetworkFile(String fileUrl) throws MalformedURLException {
        return uploadNetworkFile(fileUrl,"");
    }

    @Override
    public UploadResult uploadNetworkFile(String fileUrl,String fileExt) throws MalformedURLException {
        SftpUploadResult sftpUploadResult = sftpFileService.uploadNetworkFile(fileUrl,fileExt);
        return createResult(sftpUploadResult);
    }

    @Override
    public int deleteFile(String directory, String fileName) {
        return sftpFileService.deleteFile(directory, fileName);
    }

    @Override
    public Map<String,Integer> batchDeleteFile(String directory, List<String> lstFileName) {
        return sftpFileService.batchDeleteFile(directory, lstFileName);
    }

    @Override
    public byte[] downloadFile(String fileUrl) {
        try {
            //下载网络文件
            return  FileUtil.downloadNetworkFile(fileUrl);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public byte[] downloadFile(String directory, String fileName) {
        return null;
    }

    @Override
    public void downloadFile(String directory, String fileName, String savePath) {
        sftpFileService.downloadFile(directory, fileName, savePath);
    }

    /**
     * 构建上传返回结果
     * @param sftpUploadResult SFTP上传结果
     * @return UploadResult
     */
    private UploadResult createResult(SftpUploadResult sftpUploadResult){
        UploadResult uploadResult = null;
        if(sftpUploadResult != null){
            uploadResult = new UploadResult();
            try {
                logger.debug("通过BeanUtils.copyProperties复制sftpUploadResult对象属性值到uploadResult对象");
                BeanUtils.copyProperties(uploadResult, sftpUploadResult);
                //BeanUtils.copyProperties对于boolean类型的字段赋值不成功
                uploadResult.setIsUploadSuccess(sftpUploadResult.isUploadSuccess());
            } catch (Exception e) {
                logger.debug("通过BeanUtils.copyProperties复制sftpUploadResult对象属性值到uploadResult对象异常：" + e.getMessage(), e);
                logger.debug("手动构建uploadResult对象");
                uploadResult.setSavePath(sftpUploadResult.getSavePath());
                uploadResult.setDescDetail(sftpUploadResult.getDescDetail());
                uploadResult.setExtFieldMap(sftpUploadResult.getExtFieldMap());
                uploadResult.setFileSuffix(sftpUploadResult.getFileSuffix());
                uploadResult.setFileUrl(sftpUploadResult.getFileUrl());
                uploadResult.setIsUploadSuccess(sftpUploadResult.isUploadSuccess());
                uploadResult.setNewFileName(sftpUploadResult.getNewFileName());
                uploadResult.setOriginalFileName(sftpUploadResult.getOriginalFileName());
                uploadResult.setStorageType(sftpUploadResult.getStorageType());
                uploadResult.setUseTime(sftpUploadResult.getUseTime());
            }
        }
        return uploadResult;
    }
}
