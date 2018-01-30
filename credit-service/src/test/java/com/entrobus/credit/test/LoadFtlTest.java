package com.entrobus.credit.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;

public class LoadFtlTest {
    @Test
    public void loadText() throws IOException {
        //读取xml文件
        String basePath = "D:/idea_project/creditlife/credit-service/src/main/resources/templates";//文件目录
        BufferedReader in = null;
        Writer out =  null;
        try {
            in =new BufferedReader( new InputStreamReader(new FileInputStream(basePath+"/test.txt")));
            StringBuffer sb = new StringBuffer();
            String line = null;


            while ((line = in.readLine()) != null) {
                //以前在这出现乱码问题，后来在这设置了编码格式
                if (StringUtils.isNotBlank(line)) {
                    sb.append("<p class=\"main_text\">").append(line).append("</p>");
                }else {
                    sb.append(line);
                }
                sb.append("\n");
            }
            FileOutputStream os = new FileOutputStream(basePath+"/test2.txt");
            out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            out.write(sb.toString());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }finally {
            if (in != null) {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
