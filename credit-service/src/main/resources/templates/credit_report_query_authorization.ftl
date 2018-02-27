<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="Content-Style-Type" content="text/css"></meta>
    <meta name="generator" content="Aspose.Words for .NET 15.1.0.0"></meta>
    <title></title>
    <style type="text/css">
        <#--页面属性，纸张大小、页边距-->
        @page {
            size: a4;
            margin: 3.18cm  2.54cm;
        }
        /*字体 宋体*/
        body {
            font-family: SimSun;
        }
        div.page_body{
            /*width: 210mm;*/
            /*margin: 3.18cm  2.54cm;*/
            padding: 0;
            border: 0;
        }
        /*行间距*/
        p{
            line-height: 1;
        }
        /*主标题样式*/
        .main_title {
            margin: 0;
            text-align: center;
            font-size: 12pt;
        }
        /*正文文本样式*/
        .main_text {
            margin: 0;
            text-indent: 24pt;
            font-size: 12pt;
        }
        /*表格中文本样式*/
        .td_text {
            margin: 0;
            orphans: 0;
            text-align: justify;
            widows: 0;
            font-size: 12pt;
            /*font-family: Arial;*/
        }
        /*重点、强调 文本样式*/
        .emphasize_text {
            font-size: 12pt;
            font-style: italic;
            font-weight: bold
        }
        /*填写文本样式（下划线）*/
        .fill_in_text {
            font-size: 12pt;
            text-decoration:underline;
        }
        /*条目文本样式*/
        .clauses_text {
            font-size: 12pt;
            font-weight: bold;
        }
        /*签名图片样式*/
        img.autograph{
            -aw-left-pos: 0pt;
            -aw-rel-hpos: column;
            -aw-rel-vpos: paragraph;
            -aw-top-pos: 0pt;
            -aw-wrap-type: inline;
        }
        /*签名区（上下分布）文本样式*/
        p.autograph_text{
            margin: 0pt;
            orphans: 0;
            text-align: justify;
            /*text-indent: 48pt;*/
            text-indent: 0;
            widows: 0;
        }
    </style>
</head>

<body>
<div class="page_body">
    <p class="main_title">个人信用报告查询授权书</p>
    <p class="main_text">鉴于本人向${lenderHeadquartersName!"  "}申请贷款，特授权如下：</p>
    <#--<p class="main_text">鉴于本人向中国建设银行股份有限公司申请贷款，特授权如下：</p>-->
    <p class="main_text">一、本人同意并不可撤销地授权：贵行按照国家相关规定采集并向金融信用信息基础数据库及其他依法成立的征信机构提供本人个人信息和包括信贷信息在内的信用信息(包含本人因未及时履行合同义务产生的不良信息）。</p>
    <p class="main_text">二、本人同意并不可撤销地授权：贵行可以根据国家有关规定，本人同意建设银行采集并将本人个人信息和信用信息(包括不良信息）提供给金融信用信息基础数据库及其他依法成立的征信机构，并同意${lenderName!"  "}通过金融信用信息基础数据库及其他依法成立的征信机构查询、打印、保存本人的信用状况等个人信息，查询获得的信息用于审核贷款申请、担保人资格、贷后管理、贷款相关的其他事项以及法律规定的其他用途。</p>
    <p class="main_text">三、本人授权贵行可通过碧桂园集团(包括碧桂园物业管理公司、碧桂园集团所属或与碧桂园集团合作的公司等，下同）查询本人及配偶在碧桂园集团所属楼盘的房产信息、车位信息、车辆信息及其他物业管理信息，同意碧桂园集团向贵行提供本人及配偶在碧桂园集团所属楼盘的房产信息、车位信息、车辆信息及其他物业管理等信息。</p>

    <p class="main_text">本人郑重声明如下：</p>
    <p class="main_text">一、本人已阅读本授权书等所有内容，本人对以上条款含义及相应的法律后果、可能出现的风险已全部通晓并充分理解，并同意签署和遵守。</p>
    <p class="main_text">二、本贷款申请属于夫妻共同债务，非个人债务，本人已就本笔贷款向配偶履行了告知义务并已经配偶同意。</p>
    <p class="main_text">三、本人确保向${lenderName!"  "}提供的所有申请资料均真实、准确、完整、有效，无论该笔贷款申请审批通过与否，本人均不要求${lenderName!"  "}退回本申请表及相关材料。</p>
    <p class="main_text">四、本人点击“同意”本协议的按钮或点击“立即注册”，即代表本人对此授权书已充分理解，并同意受本授权书的约束。</p>

    <p class="main_text">借款申请人签名：</p>
    <p class="td_text">
        <#if borrowerAutograph ??>
            <img  src="${borrowerAutograph!}" width="129" height="167" alt=""  class="autograph"/>
        <#else >
            <div class="autograph" style="width: 129px;height: 167px"></div>
        </#if>
    </p>
</div>
</body>
</html>