package com.entrobus.credit.common.util;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ImageUtil {
    /**
     * @return
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     */
    public static String getImageBase64(String imgFile) throws IOException {
        InputStream in = null;
        byte[] data = null;
        BufferedInputStream bis = null;
        BufferedReader br = null;
        try {
            in = HttpClientUtil.getInputStream(imgFile);
            data = IOUtils.toByteArray(in);
            // 加密
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(data);
            return encode.replaceAll("\r","").replaceAll("\n","");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally {
            CloseableUtil.close(in);
            CloseableUtil.close(bis);
        }
//        return null;

    }



    /**
     * @return
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     */
    public static String getImageBase64Src(String imgFile) throws IOException {
        int index = imgFile.lastIndexOf(".");
        String ext = "png";//后缀，图片格式
        if (index > -1 && index < imgFile.length() - 1) {
            ext = imgFile.substring(index+1);
        }
        String base64 = getImageBase64(imgFile);
        String src = String.format("data:image/%s;base64,%s", ext,base64);
        return src;

    }
}
