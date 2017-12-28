package com.entrobus.credit.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gacl on 2017/10/20.
 */
public class FileServiceFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(FileServiceFactory.class);

    private static class FileServiceFactoryHolder {

        private static Map<String, FileService> fileServiceMap;

        static {
            fileServiceMap = new HashMap<>();
            fileServiceMap.put("FASTDFS",FastdfsFileServiceImpl.getInstance());
        }
    }

    /**
     * 获取实例
     * @param fileServiceName 实例名称，取值（FASTDFS、QINIU、SFTP）
     * 单例模式是为确保一个类只有一个实例，并为整个系统提供一个全局访问点的一种模式方法。
     从概念中体现出了单例的一些特点：
     （1）、在任何情况下，单例类永远只有一个实例存在
     （2）、单例需要有能力为整个系统提供这一唯一实例
     * @return
     */
    public static FileService getInstance(String fileServiceName) {
        FileService fileService = FileServiceFactoryHolder.fileServiceMap.get(fileServiceName);

        if(fileService==null){
            //返回默认实例
            return FileServiceFactoryHolder.fileServiceMap.get("FASTDFS");
        }else{
            String className = fileService.getClass().getName();
            LOGGER.debug("FileService实例的hashCode="+fileService.hashCode()+"，FileService使用的实现类是："+className);
            return fileService;
        }
    }

    /**
     * 获取默认实例
     * 单例模式是为确保一个类只有一个实例，并为整个系统提供一个全局访问点的一种模式方法。
     从概念中体现出了单例的一些特点：
     （1）、在任何情况下，单例类永远只有一个实例存在
     （2）、单例需要有能力为整个系统提供这一唯一实例
     * @return
     */
    public static FileService getDefualInstance() {
        return getInstance("FASTDFS");
    }
}
