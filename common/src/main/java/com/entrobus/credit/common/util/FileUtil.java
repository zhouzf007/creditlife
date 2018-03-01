package com.entrobus.credit.common.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件工具类
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    /**
     * 默认文件夹名称
     */
    public static String DEFAULT_TEMP_DIR = "temp";
    /**
     * 如果文件存在，则强行删除
     * @see org.apache.commons.io.FileUtils
     * @param fileUri
     */
    public static void forceDelete(String fileUri) {
        if (fileUri == null || "".equals(fileUri) || File.separator.equals(fileUri)) return;
        try {
            FileUtils.forceDelete(new File(fileUri));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取临时文件地址
     * 在项目当前目录下
     *@param child 自定义的临时文件夹名称
     * @return
     */
    public static String getTempDirectory(String child) throws FileNotFoundException {
//      //获取跟目录
        String projectPath = getProjectDirectory();
        if(StringUtils.isEmpty(child) ) child = DEFAULT_TEMP_DIR;
        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(projectPath,child);
        if(!upload.exists()) upload.mkdirs();
        String tempPath = upload.getAbsolutePath();
        logger.info("upload url:"+ tempPath);
        //在开发测试模式时，得到的地址为：{项目跟目录}/target/static/images/upload/
        //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/
        return tempPath;
    }

    /**
     * 获取项目所在目录
     * @return
     * @throws FileNotFoundException
     */
    public static String getProjectDirectory() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        String projectPath = path.getAbsolutePath();
        logger.info("path:"+ projectPath);
        return projectPath;
    }
}
