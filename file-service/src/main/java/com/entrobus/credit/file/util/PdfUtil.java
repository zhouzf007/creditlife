package com.entrobus.credit.file.util;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;


public class PdfUtil {
    private static final int N = 3;

    public static void main(String[] args)
    {
//        String[] files = {"C:\\pdf\\1.pdf", "C:\\pdf\\2.pdf"};
//        String savepath = "C:\\pdf\\temp.pdf";
//        mergePdfFiles(files, savepath);

//        partitionPdfFile("http://fdfs-test.newseax.com/group1/M00/03/20/CmhqV1pqjbqAYiqMAA5fG023nYw877.pdf");
//        createPDF();
        chaImgPDF("C:\\pdf\\temp\\hello.pdf","C:\\pdf\\temp\\hello2.pdf");
    }

    public static void createPDF(){
        // 创建一个文档对象
        Document doc = new Document();
        Image jpeg;
        try {
            // 定义输出位置并把文档对象装入输出对象中
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\pdf\\temp\\hello.pdf"));
            // 打开文档对象
            doc.open();
            // 加入文字“Hello World”
//            doc.add(new Paragraph("HelloWorld"));
            jpeg = Image.getInstance("C:\\pdf\\100200.png");
            //图片居中
            jpeg.setAlignment(Image.ALIGN_CENTER);
            doc.add(jpeg);
            // 关闭文档对象，释放资源
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void chaImgPDF(String fileUrl,String savepath){


        try {
            // 创建一个文档对象
            Document document = new Document(new PdfReader(fileUrl).getPageSize(1));
//            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savepath));
            document.open();
            Image jpeg;
//            document.newPage();
            jpeg = Image.getInstance("C:\\pdf\\tu.png");
            jpeg.setAlignment(Image.ALIGN_CENTER);
            document.add(jpeg);
//            copy.add(jpeg);
            // 关闭文档对象，释放资源
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergePdfFiles(String[] files, String savepath)
    {
        try
        {
            Document document = new Document(new PdfReader(files[0]).getPageSize(1));

            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savepath));

            document.open();

            for(int i=0; i<files.length; i++)
            {
                PdfReader reader = new PdfReader(files[i]);

                int n = reader.getNumberOfPages();

                for(int j=1; j<=n; j++)
                {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }

            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch(DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void partitionPdfFile(String filepath)
    {
        Document document = null;
        PdfCopy copy = null;

        try
        {
            PdfReader reader = new PdfReader(filepath);

            int n = reader.getNumberOfPages();

            if(n < N)
            {
                System.out.println("The document does not have " + N + " pages to partition !");
                return;
            }

            int size = n/N;
            String staticpath = filepath.substring(0, filepath.lastIndexOf("\\")+1);
            String savepath = null;
            ArrayList<String> savepaths = new ArrayList<String>();
            for(int i=1; i<=N; i++)
            {
                if(i < 10)
                {
                    savepath = filepath.substring(filepath.lastIndexOf("\\")+1, filepath.length()-4);
                    savepath = staticpath + savepath + "0" + i + ".pdf";
                    savepaths.add(savepath);
                }
                else
                {
                    savepath = filepath.substring(filepath.lastIndexOf("\\")+1, filepath.length()-4);
                    savepath = staticpath + savepath + i + ".pdf";
                    savepaths.add(savepath);
                }
            }

            for(int i=0; i<N-1; i++)
            {
                document = new Document(reader.getPageSize(1));
                copy = new PdfCopy(document, new FileOutputStream(savepaths.get(i)));
                document.open();
                for(int j=size*i+1; j<=size*(i+1); j++)
                {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
                document.close();
            }


            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savepaths.get(N-1)));
            document.open();
            for(int j=size*(N-1)+1; j<=n; j++)
            {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch(DocumentException e) {
            e.printStackTrace();
        }
    }

    //写PDF文件
    public static void write_pdf(String filepath)
    {
        try{
            //设置pdf文件输出流
//            Document document=new Document(PageSize.A4);
//            document.setMargins(50, 50, 100, 50);      //pdf的4个页边距
//            Rectangle pageRect=document.getPageSize();
//            PdfWriter.getInstance(document, new FileOutputStream(filepath));
//            //创建汉字字体
//            BaseFont bfSong = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
//            Font fontSong = new Font(bfSong, 10, Font.NORMAL);
//
//            //添加一个图片，设置图片的位置，大小
//            try {
//                Watermark watermark = new Watermark(Image.getInstance("test.jpg"), pageRect.left()+50,pageRect.top()-85);
//                watermark.scalePercent(50);
//                document.add(watermark);
//            }catch(Exception e) {
//                System.err.println("找不到图片");
//            }
//
//            //增加页头信息
//            HeaderFooter header = new HeaderFooter(new Phrase("Java实例一百例",fontSong), false);   //设置页眉文字
//            header.setBorder(2);       //设置边框
//            header.setAlignment(Element.ALIGN_RIGHT);   //设置对齐方式
//            document.setHeader(header);
//
//            //增加页脚信息
//            HeaderFooter footer = new HeaderFooter(new Phrase("第 ",fontSong),new Phrase(" 页",fontSong));  //设置页脚文字
//            footer.setAlignment(Element.ALIGN_CENTER);   //设置对齐方式
//            footer.setBorder(1);   //设置边框
//            document.setFooter(footer);
//
//            // 打开文档
//            document.open();
//            //添加表格
//            Table table = new Table(4);  //设置Table列数
//            table.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);  //表格垂直对齐方式
//            table.setBorder(Rectangle.NO_BORDER);   //设置边框
//            int hws[] = {10, 20, 10, 20};   //按比例分配单元格宽度
//            table.setWidths(hws);
//            table.setWidth(100);    //设置表格占据的宽度比例
//            //表头信息
//            Cell cellmain = new Cell(new Phrase("用户信息",new Font(bfSong, 10, Font.BOLD,new Color(0,0,255)))); //创建单元格
//            cellmain.setHorizontalAlignment(Element.ALIGN_CENTER);  //设置水平对齐方式
//            cellmain.setColspan(4);   //设置占据列数
//            cellmain.setBorder(Rectangle.NO_BORDER); //设置边框
//            cellmain.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));   //设置背景色
//            table.addCell(cellmain);   //添加单元格
//            //分表头信息
//            Cell cellleft= new Cell(new Phrase("收货人信息",new Font(bfSong, 10, Font.ITALIC,new Color(0,0,255))));//创建单元格
//            cellleft.setColspan(2); //设置占据列数
//            cellleft.setHorizontalAlignment(Element.ALIGN_CENTER); //设置水平对齐方式
//            table.addCell(cellleft);
//            Cell cellright= new Cell(new Phrase("订货人信息",new Font(bfSong, 10, Font.ITALIC,new Color(0,0,255))));
//            cellright.setColspan(2);//设置占据列数
//            cellright.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平对齐方式
//            table.addCell(cellright);
//
//            //收货和订货人信息，表体内容
//            table.addCell(new Phrase("姓名",fontSong));  //添加一个单元格
//            table.addCell(new Phrase("张三",fontSong));  //添加一个单元格
//            table.addCell(new Phrase("姓名",fontSong));  //添加一个单元格
//            table.addCell(new Phrase("李四",fontSong));  //添加一个单元格
//
//            table.addCell(new Phrase("电话",fontSong));  //添加一个单元格
//            table.addCell(new Phrase("23456789",fontSong));  //添加一个单元格
//            table.addCell(new Phrase("电话",fontSong));  //添加一个单元格
//            table.addCell(new Phrase("9876543",fontSong));  //添加一个单元格
//
//            //将表格添加到文本中
//            document.add(table);
//            //关闭文本，释放资源
//            document.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }


}
