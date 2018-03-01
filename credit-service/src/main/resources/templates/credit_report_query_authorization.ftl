<html>
<head>
    <#--针对不同客户端或用途使用不同头部，因为转换PDF（打印体）和h5头部信息即样式不同-->
    <#if headType?? && headType == "h5">
    <#--针对移动端使用h5头部，需传headType == h5-->
        <#include "head/head_h5.ftl">
    <#else >
    <#--默认使用打印体头部，用于转换PDF-->
        <#include "head/head_print.ftl">
    </#if>
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
            <img  src="${borrowerAutograph!}" width="129pt" height="167pt" alt=""  class="autograph"/>
        <#else >
            <div class="autograph" style="width: 129pt;height: 167pt"></div>
        </#if>
    </p>
</div>
</body>
</html>