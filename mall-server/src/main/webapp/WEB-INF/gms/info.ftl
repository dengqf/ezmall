<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel2  '信息提示'/>


    <div class="row">

        <div class="col-sm-offset-4 col-sm-8 ">
            <p>${info!""}</p>

        </div>

        <div class="col-sm-offset-10 col-sm-2 ">
        <#if url??>
            <button class="btn  btn-success"
                    onclick="window.location=('${context}/${url}')"> 返回
            </button>
        <#else>
            <button class="btn  btn-success"
                    onclick="window.location=('${context}/gms/index.html')">返回
            </button>
        </#if>
        </div>
    </div>
<#include "../include/footer.ftl"/>
</div>


</body>
</html>
