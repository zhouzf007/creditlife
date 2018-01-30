# word文档填充动态数据转PDF
## 步骤
### 一、手动将word转换为hmtl文件。
这里推荐自己写html。使用工具直接word转html会产生很多多余的标签和样式，在转换PDF时会有很多问题。
可以参考测试类LoadFtlTest，把word中的文本使用工具类给纯文本统一添加“p”标签,然后再添加样式。
### 二、使用freemarker工具填充动态值
先对第一步得到的html文件添加freemarker标签和${name}语法占位。这里与普通freemarker模板一样使用。
然后就可以在代码中对模板进行填充，生成新的临时html文件。
### 三、使用itext转换PDF
将第二部生成的临时文件转换。
这里需要注意的是中文字体和文字自动换行的问题。
## 注意事项

### 1.关于中文字体
1.1 可以使用系统字体文件路径或下载字体文件
1.2 font-family的值不能用中文，并且区分大小写，如宋体必须是SimSun
```$xslt
        ITextRenderer render = new ITextRenderer();
        String path = getPath();//可以使用系统字体文件路径或下载字体文件
        //添加字体，以支持中文
        //解决中文问题（模版html页面 配套加上
        //font-family的值不能用中文，并且区分大小写，如宋体必须是SimSun
        //<style mce_bogus="1" type="text/css">
        //body {font-family: SimSun; background:none;margin-left: auto;margin-right: auto;}
        //</style>）
        render.getFontResolver().addFont(path + "pdf/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        render.getFontResolver().addFont(path + "pdf/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
```
### 2.设置为A4纸
在html模板中style添加如下样式
```$xslt
  @page {
            size: a4;
            margin:0;
        }
```
