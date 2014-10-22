<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel2  '错误信息'/>


    <div class="row">

        <div class="col-sm-offset-4 col-sm-8">
            <p class="text-danger">${error!""}</p>

        </div>
        <div class="col-sm-offset-10 col-sm-2 ">

        <#if url??>
            <button class="btn  btn-default "
                    onclick="window.location=('${context}/${url}')"> 返回
            </button>
        <#else>
            <span class="btn btn-default "
                  onclick="javascript:history.go(-1);">返回</span>
        </#if>
        </div>
    </div>
<#include "../include/footer.ftl"/>
</div>

</body>
</html>
