package com.entrobus.credit.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;

public class LoadFtlTest {
    /**
     * 将纯文本文件批量添加上html标签
     * @throws IOException
     */
    @Test
    public void loadText() throws IOException {
        //读取xml文件
        String basePath = "D:/idea_project/creditlife/credit-service/src/main/resources/templates";//文件目录
        BufferedReader in = null;
        Writer out =  null;
        try {
            //读取存文本文件
            in =new BufferedReader( new InputStreamReader(new FileInputStream(basePath+"/test.txt")));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                if (StringUtils.isNotBlank(line)) {
                    sb.append("<p class=\"main_text\">").append(line).append("</p>");
                }else {
                    sb.append(line);
                }
                sb.append("\n");
            }
            //将生成的内容写入文件
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
