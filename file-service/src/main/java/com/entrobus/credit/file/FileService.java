package com.entrobus.credit.file;

import com.entrobus.credit.file.bean.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

/**
 * 针对文件常用操作service
 * Created by gacl on 2017/10/13.
 */
public interface FileService {

    /**
     * 上传文件
     * @param filePath 文件所在的路径(绝对路径)
     * @return UploadResult 上传结果
     */
    UploadResult uploadFile(String filePath);

    /**
     * 上传文件
     * @param filePath 文件所在的路径(绝对路径)
     * @param savePath 文件上传后的保存目录
     * @return UploadResult 上传结果
     */
    UploadResult uploadFile(String filePath, String savePath);

    /**
     * 上传文件
     * @param inputStream 文件输入流
     * @param fileName 原始文件名(带后缀,如1.png,2.jpg)
     * @return UploadResult 上传结果
     */
    UploadResult uploadFile(InputStream inputStream, String fileName);

    /**
     * 上传文件
     * @param multipartFile
     * @return UploadResult 上传结果
     */
    UploadResult uploadFile(MultipartFile multipartFile);

    /**
     * 一次性上传多个文件
     * @param multipartFiles
     * @return
     */
    List<UploadResult> uploadFile(List<MultipartFile> multipartFiles);

    /**
     * 一次性上传多个文件
     * @param inputStreamMap 以原始文件名(带后缀,如1.png,2.jpg)为key的输入流Map
     * @return List<UploadResult> 上传结果集合
     */
    List<UploadResult> uploadFile(Map<String, InputStream> inputStreamMap);

    /**
     * 上传文件
     * @param inputStream 文件输入流
     * @param fileName 文件名(带后缀,如1.png,2.jpg)
     * @param savePath 文件上传后的保存目录
     * @return UploadResult 上传结果
     */
    UploadResult uploadFile(InputStream inputStream, String fileName, String savePath);

    /**
     * 一次性上传多个文件
     * @param inputStreamMap 以原始文件名(带后缀,如1.png,2.jpg)为key的输入流Map
     * @param savePath 文件上传后的保存目录
     * @return List<UploadResult> 上传结果集合
     */
    List<UploadResult> uploadFile(Map<String, InputStream> inputStreamMap, String savePath);

    /**
     * 上传文件
     * @param file 要上传的文件
     * @param savePath 文件上传后的保存目录
     * @return UploadResult 上传结果
     */
    UploadResult uploadFile(File file, String savePath);

    /**
     * 上传文件
     * @param fileByte 文件字节数组
     * @param fileName 文件名(带后缀,如1.png,2.jpg)
     * @return UploadResult 上传结果
     */
    UploadResult uploadFile(byte[] fileByte, String fileName);

    /**
     * 上传文件
     * @param fileByte 文件字节数组
     * @param fileName 文件名(带后缀,如1.png,2.jpg)
     * @param savePath 文件上传后的保存目录
     * @return UploadResult 上传结果
     */
    UploadResult uploadFile(byte[] fileByte, String fileName, String savePath);

    /**
     * 上传网络文件
     * @param fileUrl 文件URL
     * @return UploadResult 上传结果
     */
    UploadResult uploadNetworkFile(String fileUrl) throws MalformedURLException;

    /**
     * 上传网络文件
     * @param fileUrl 文件URL
     * @param fileExt 文件扩展名（不带.）
     * @return UploadResult 上传结果
     */
    UploadResult uploadNetworkFile(String fileUrl, String fileExt) throws MalformedURLException;

    /**
     * 删除文件
     * @param directory 文件所在目录
     * @param fileName 文件名
     * @return -1：删除失败,0：删除成功
     */
    int deleteFile(String directory, String fileName);

    /**
     * 批量删除文件
     * @param directory 文件所在目录
     * @param lstFileName 要删除的文件名集合
     * @return -1：删除失败,0：删除成功
     */
    Map<String,Integer> batchDeleteFile(String directory, List<String> lstFileName);

    /**
     * 下载文件
     * @param fileUrl 文件访问URL
     * @return 文件的字节数组
     */
    byte[] downloadFile(String fileUrl);

    /**
     * 下载文件
     * @param directory 文件所在目录
     * @param fileName 文件名
     * @return 文件的字节数组
     */
    byte[] downloadFile(String directory, String fileName);

    /**
     * 下载文件
     * @param directory 文件所在目录
     * @param fileName 文件名
     * @param savePath 保存路径
     */
    void downloadFile(String directory, String fileName, String savePath);

}
