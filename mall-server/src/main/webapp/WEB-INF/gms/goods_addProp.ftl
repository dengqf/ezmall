<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>

</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>

<@m.breadNavLevel3  context+'/gms/goods/toGoodsManager.html' '商品管理' '编辑属性'/>
    <form class="form-horizontal" action="${context}/gms/goods/addGoodsProp.html" method="post"
          id="queryForm">

        <div class="row">
            <div class="col-sm-12">

                <div class="row">
                    <div class="col-sm-3">
                        <p>商品名称：${goods.name!}  </p>
                    </div>
                    <div class="col-sm-3">
                        <p>商品编号：${goods.no!} </p>
                    </div>
                    <div class="col-sm-3">
                        <p>分类结构：${goods.categoryStructName!} </p>
                    </div>
                    <div class="col-sm-offset-10 col-sm-2">
                        <span class="btn btn-primary" onclick="addGoodsProp()">保存</span>
                    <@m.btnReturn/>
                    </div>

                </div>


            </div>


        </div>
        <h4>添加商品属性:</h4>
        <hr>
        <div class="row ">
            <div class="col-sm-12 ">
            <#list propItemList as propItem>
                <div class="form-group">
                    <label class="col-sm-2 control-label">${propItem.name}:</label>

                    <div class="col-sm-4">
                    <#--<#list relations as rp>-->
                    <#--<#if rp.propItemNo==propItem.no>-->
                    <#--<input class="form-control" id="thirdLink" name="propName" type="text" title=""-->
                    <#--value="${rp.propItemValue!}">-->
                    <#--</#if>-->
                    <#--</#list>-->
                        <input class="form-control" id="${propItem.no!}" name="propNameCheck" type="text"
                               title="${propItem.name!}"
                               value="<#list relations as rp><#if rp.propItemNo==propItem.no>${rp.propItemValue!}<#else ></#if></#list>">

                        <input class="form-control" id="${propItem.no!}_1" name="propName" type="hidden"
                               value="<#list relations as rp><#if rp.propItemNo==propItem.no>${rp.propItemValue!}</#if></#list>">

                    </div>

                </div>
            </#list>
            <#if propItemList?size<1 >

            <a class="btn  btn-link" href="${context}/gms/category/toAddProp.html?no=${goods.categoryNo!}" >商品分类下无商品属性,点击添加</a>

            </#if>
            </div>
        </div>
        <input type="hidden" value="${goods.no!}" name="no">
    </form>
    <script>
        function addGoodsProp() {

            $($("input[name='propNameCheck']")).each(
                    function () {
//                        alert( $(this).attr('title')) ;
                        var no = $(this).attr('id');
                        var name = $(this).attr('title');
                        var propValue = $(this).val();
                        if (propValue != "") {
                            $("#" + no + "_1").val(name + "#*#" + no + "#*#" + propValue);
                        } else {
                            $("#" + no + "_1").val("");
                        }


                    }

            );
            $.ajax({
                type: "post",
                url: "${context}/gms/goods/addGoodsProp.html",
                data: $("#queryForm").serialize(),
                success: function (messageVo) {
                    var obj = JSON.parse(messageVo);
                    if (obj.success == true) {
                        alert("修改成功");

                    } else {
                        alert(obj.message);
                    }
                }
            });


        }


    </script>
<#include "../include/footer.ftl"/>
</div>

</body>
</html>
