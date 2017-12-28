package com.entrobus.credit.common.test;

import com.entrobus.credit.common.file.FastdfsFileServiceImpl;
import com.entrobus.credit.common.file.FileService;
import com.entrobus.credit.common.file.FileUtil;
import com.entrobus.credit.common.file.bean.UploadResult;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gacl on 2017/10/16.
 */
public class FileServiceTest {

    private static Logger logger = Logger.getLogger(FileServiceTest.class);

    private static FileService fileService;

    /**
     * 针对所有测试，只执行一次，且必须为static void
     */
    @BeforeClass
    public static void init() {
        fileService = FastdfsFileServiceImpl.getInstance();
    }

    @Test
    public void testUploadFile1(){
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\测试数据\\Cg2BwVmv-oCAPA91AAJ6fgJZatg503.jpg");
            UploadResult uploadResult = fileService.uploadFile(fileInputStream, "Cg2BwVmv-oCAPA91AAJ6fgJZatg503.jpg");
            logger.debug("uploadResult="+uploadResult);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void testUploadFile2(){
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\测试数据\\4.png");
            UploadResult uploadResult = fileService.uploadFile(fileInputStream, "4.png","gaclXDP");
            logger.debug("uploadResult="+uploadResult);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void testUploadFile3(){
        try {
            File file = new File("D:/测试数据/me.jpg");
            UploadResult uploadResult = fileService.uploadFile(file, "gacl/test");
            logger.debug("uploadResult="+uploadResult);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void testUploadFile4(){
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\测试数据\\4.png");
            UploadResult uploadResult = fileService.uploadFile(FileUtil.input2ByteArray(fileInputStream), "4.png");
            logger.debug("uploadResult="+uploadResult);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void testUploadFile5(){
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\测试数据\\Cg2BwVmv-oCAPA91AAJ6fgJZatg503.jpg");
            UploadResult uploadResult = fileService.uploadFile(FileUtil.input2ByteArray(fileInputStream), "Cg2BwVmv-oCAPA91AAJ6fgJZatg503.jpg","gacl/test");
            logger.debug("uploadResult="+uploadResult);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void testUploadFile6(){
        try {
            UploadResult uploadResult = fileService.uploadFile("D:\\测试数据\\4.png");
            logger.debug("uploadResult="+uploadResult);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void testUploadFile7(){
        try {
            UploadResult uploadResult = fileService.uploadFile("D:/测试数据/Cg2BwVmv-oCAPA91AAJ6fgJZatg503.jpg","/gacl/test");
            logger.debug("uploadResult="+uploadResult);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void testUploadNetworkFile1(){
        try {
            UploadResult uploadResult = fileService.uploadNetworkFile("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508751925&di=d23ac8ad28fb2046936f47f991b7e2ca&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.gwchina.cn%2Fuploadfile%2F2016%2F0804%2F20160804015824783.jpg");
            logger.debug("uploadResult="+uploadResult);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void testUploadNetworkFile2(){
        try {
            UploadResult uploadResult = fileService.uploadNetworkFile("http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLJ9jRgnPbNfJGZThlDNNvNd8ulqGhQO34tL82PZEVWGzG4OHIWtM2xazcAnjpZzZ3xLfeEic4cvIg/0","png");
            logger.debug("uploadResult="+uploadResult);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    /**
     * UploadResult{originalFileName='Cg2BwVmv-oCAPA91AAJ6fgJZatg503.jpg', newFileName='null', savePath='group1/M00/00/00/CgEBH1nkbKKAdmxaAAJ6fgJZatg377.jpg', fileUrl='http://10.1.1.31/group1/M00/00/00/CgEBH1nkbKKAdmxaAAJ6fgJZatg377.jpg', fileSuffix='jpg', storageType='FastDFS', isUploadSuccess=true, descDetail='null', useTime=41, extFieldMap={storagePath=M00/00/00/CgEBH1nkbKKAdmxaAAJ6fgJZatg377.jpg, group=group1}}
     */
    @Test
    public void testDeleteFile() {
        int result = fileService.deleteFile("group1", "M00/00/00/CgEBH1nkcAqAAjOrAAJ6fgJZatg097.jpg");
        logger.debug("result=" + result);
    }

    @Test
    public void testBatchDeleteFile(){
        List<String> fileNameList = new ArrayList<>();
        //http://10.1.1.31/uploadFilesManager/gacl/test/3F5DA97DE423DAD861AEC9CD33769FC7.jpg
        fileNameList.add("M00/00/00/CgEBH1nkdoSADTnqAAJalAjxev4398.png");
        //http://10.1.1.31/uploadFilesManager/gacl/test/A7C167DB9EFFC1E2E80A09E7F8AB6F36.jpg
        fileNameList.add("M00/00/00/CgEBH1nkdrSAOwnFAADZ-kjjdmI189.jpg");
        //fileService.deleteFile("/mydata/app1/uploadFilesManager","9C6A4F38E6166A6CF0EB36BD326D1D0B.png");
        Map<String,Integer> resultMap = fileService.batchDeleteFile("group1",fileNameList);
        logger.debug("resultMap="+resultMap);
    }

    @Test
    public void testDownloadFile1(){
        byte[] fileByteArr = fileService.downloadFile("group1","M00/00/00/CgEBH1nlj6uAPCbaAAJalAjxev4358.png");
        if(fileByteArr != null){
            try {
                FileUtil.saveFile("D:/测试数据/wechatHeadImg.jpg", fileByteArr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testDownloadFile2(){
        fileService.downloadFile("group1","M00/00/00/CgEBH1nlkP6AAVJ7AADZ-kjjdmI533.jpg","D:/测试数据/百度.jpg");
    }

    @Test
    public void testDownloadFile3(){
        byte[] fileByteArr = fileService.downloadFile("http://10.1.1.31/group1/M00/00/00/CgEBH1nlm_uAZEArAAJXLU8X3Ko766.png");
        if(fileByteArr != null){
            try {
                FileUtil.saveFile("D:/测试数据/head2.png",fileByteArr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
