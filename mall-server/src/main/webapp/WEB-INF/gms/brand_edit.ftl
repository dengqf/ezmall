<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<#if brand.id??>
    <@m.breadNavLevel3  context+'/gms/brand/toBrandManager.html' '品牌管理' '品牌编辑'/>
<#else>
    <@m.breadNavLevel3  context+'/gms/brand/toBrandManager.html' '品牌管理' '新建品牌'/>

</#if>

    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/brand/brandEdit.html" method="post"
                  id="queryForm">
                <fieldset>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                            <@m.inputText '品牌中文名:' 'name' brand.name!""/>
                            <@m.inputText '品牌英文名:' 'englishName' brand.englishName!""/>
                            <@m.inputText '品牌字母:' 'brandIndex' brand.brandIndex!""/>

                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-8 col-sm-4 text-right">
                                <#--<button class="btn btn-default">Cancel</button>-->
                                <@m.btnReturn/>
                                    <span class="btn btn-primary " onclick="saveBrand()" id="btnSubmit"><#if brand.id??>
                                        保存品牌<#else>新建品牌</#if></span>

                                </div>
                                <input type="hidden" id="id" name="id" value="${brand.id!}">
                            </div>

                        </div>
                    </div>

                </fieldset>
            </form>
        </div>


    </div>
<#include "../include/footer.ftl"/>
</div>
<script>
    function saveBrand() {
        var brandName = $("#name").val();
        var brandIndex = $("#brandIndex").val();
        if (!brandName || brandName == null) {
            alert('请填写品牌名称！');
            return;
        }
        if (!brandIndex || brandIndex == null) {
            alert('请填写品牌字母，为品牌英文开头字母！');
            return;
        }
        var reg= /^[a-zA-Z]$/;
        if(!brandIndex.match(reg)) {
            alert('品牌字母填写内容必须单个英文字母！');
            return;
        }
        $.ajax({
            type: "POST",
            url: "${context}/gms/brand/brandEdit.html",
            data: {
                "id": $("#id").val(),
                "englishName": $("#englishName").val(),
                "brandIndex": brandIndex.toUpperCase(),
                "name": brandName
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    alert(messageVo.message);
                    document.getElementById("btnSubmit").innerHTML = "保存";
                    $("#id").val(messageVo.id);
                } else {
                    alert(messageVo.message);
                    //  document.getElementById("btnSubmit").innerHTML = "保存";

                }
            }
        });

    }
</script>
</body>
</html>
