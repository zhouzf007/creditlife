package com.entrobus.credit.file.service.impl;


import com.entrobus.credit.file.util.FileUtil;
import com.entrobus.credit.file.bean.UploadResult;
import com.entrobus.credit.file.service.FileService;
import com.smartfast4j.fastdfs.FastdfsFileService;
import com.smartfast4j.fastdfs.bean.FastDFSUploadResult;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 基于Fastdfs上传实现
 * Created by gacl on 2017/10/16.
 */
public class FastdfsFileServiceImpl implements FileService {

    private static Logger logger = LoggerFactory.getLogger(FastdfsFileServiceImpl.class);

    /**
     * 文件上传后的存储方式
     */
    private static final String STORAG_TYPE = "FastDFS";

    /**
     * fastdfs连接客户端初始化用到的properties配置文件
     */
    private static final String FASTDFS_CLIENT_CONFIG_FILE_PATH = "fastdfs-client.properties";

    private FastdfsFileService fastdfsFileService;

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
                    instance = new FastdfsFileServiceImpl();
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

    private FastdfsFileServiceImpl(){
        fastdfsFileService = new FastdfsFileService(FASTDFS_CLIENT_CONFIG_FILE_PATH);
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
        FastDFSUploadResult fastDFSUploadResult = fastdfsFileService.uploadFile(inputStream,fileName);
        return createResult(fastDFSUploadResult);
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
        return uploadFile(inputStream,fileName);//fastDFS不支持自定义存储路径
    }

    @Override
    public List<UploadResult> uploadFile(Map<String, InputStream> inputStreamMap, String savePath) {
        List<UploadResult> uploadResultList = new ArrayList<>();
        List<FastDFSUploadResult> fastDFSUploadResults = fastdfsFileService.uploadFile(inputStreamMap);
        if(fastDFSUploadResults != null){
            for(FastDFSUploadResult fastDFSUploadResult:fastDFSUploadResults){
                uploadResultList.add(createResult(fastDFSUploadResult));
            }
        }
        return uploadResultList;
    }

    @Override
    public UploadResult uploadFile(File file, String savePath) {
        FastDFSUploadResult fastDFSUploadResult = fastdfsFileService.uploadFile(file);
        return createResult(fastDFSUploadResult);
    }

    @Override
    public UploadResult uploadFile(byte[] fileByte, String fileName) {
        FastDFSUploadResult fastDFSUploadResult = fastdfsFileService.uploadFile(fileByte,fileName);
        return createResult(fastDFSUploadResult);
    }

    @Override
    public UploadResult uploadFile(byte[] fileByte, String fileName, String savePath) {
        return uploadFile(fileByte,fileName);//fastDFS不支持自定义存储路径
    }

    @Override
    public UploadResult uploadNetworkFile(String fileUrl) throws MalformedURLException {
        return uploadNetworkFile(fileUrl,"");
    }

    @Override
    public UploadResult uploadNetworkFile(String fileUrl, String fileExt) throws MalformedURLException {
        FastDFSUploadResult fastDFSUploadResult = fastdfsFileService.uploadNetworkFile(fileUrl, fileExt);
        return createResult(fastDFSUploadResult);
    }

    @Override
    public int deleteFile(String directory, String fileName) {
        int result = fastdfsFileService.deleteFile(directory, fileName);
        logger.info("deleteFileResult："+result);
        return result;
    }

    @Override
    public Map<String,Integer> batchDeleteFile(String directory, List<String> lstFileName) {
        Map<String,Integer> resultMap = fastdfsFileService.batchDeleteFile(directory, lstFileName);
        logger.info("batchDeleteFileResult："+resultMap);
        return resultMap;
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
        return fastdfsFileService.downloadFile(directory, fileName);
    }

    @Override
    public void downloadFile(String directory, String fileName, String savePath) {
        byte[] fileByte = fastdfsFileService.downloadFile(directory, fileName);
        if(fileByte != null){
            try {
                FileUtil.saveFile(savePath,fileByte);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    /**
     * 构建上传返回结果
     * @param fastDFSUploadResult FastDFS上传结果
     * @return UploadResult
     */
    private UploadResult createResult(FastDFSUploadResult fastDFSUploadResult){
        UploadResult uploadResult = null;
        if(fastDFSUploadResult != null){
            uploadResult = new UploadResult();
            try {
                logger.debug("通过BeanUtils.copyProperties复制sftpUploadResult对象属性值到uploadResult对象");
                BeanUtils.copyProperties(uploadResult, fastDFSUploadResult);
                uploadResult.setSavePath(fastDFSUploadResult.getFileId());
                uploadResult.setIsUploadSuccess(fastDFSUploadResult.isUploadSuccess());
            } catch (Exception e) {
                logger.debug("通过BeanUtils.copyProperties复制sftpUploadResult对象属性值到uploadResult对象异常：" + e.getMessage(), e);
                logger.debug("手动构建uploadResult对象");
                uploadResult.setSavePath(fastDFSUploadResult.getFileId());
                uploadResult.setDescDetail(fastDFSUploadResult.getDescDetail());
                uploadResult.setExtFieldMap(fastDFSUploadResult.getExtFieldMap());
                uploadResult.setFileSuffix(fastDFSUploadResult.getFileSuffix());
                uploadResult.setFileUrl(fastDFSUploadResult.getFileUrl());
                uploadResult.setIsUploadSuccess(fastDFSUploadResult.isUploadSuccess());
                uploadResult.setNewFileName(fastDFSUploadResult.getNewFileName());
                uploadResult.setOriginalFileName(fastDFSUploadResult.getOriginalFileName());
                uploadResult.setStorageType(fastDFSUploadResult.getStorageType());
                uploadResult.setUseTime(fastDFSUploadResult.getUseTime());
            }
        }
        return uploadResult;
    }
}
