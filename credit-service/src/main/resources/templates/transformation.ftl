<html>
<head>
<#--针对不同客户端或用途使用不同头部，因为转换PDF（打印体）和h5头部信息即样式不同-->
    <#if headType?? && headType == "h5">
    <#--针对移动端使用h5头部，需传headType == h5-->
        <#include "head/head_h5.ftl">
    <#else >
    <#--默认使用打印体头部，用于转换PDF-->
        <#include "head/head_pdf.ftl">
    </#if>
</head>

<body>
<div class="page_body">
    ${content!}
</div>
</body>
</html>