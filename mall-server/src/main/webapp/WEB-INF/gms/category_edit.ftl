<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel3  context+'/gms/category/toCategoryManager.html' '分类管理' '新建分类'/>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/goods/goodsManager.html" method="post"
                  id="queryForm">
                <fieldset>
                    <div class="row">
                        <div class="col-sm-12">

                            <div class="form-group">
                            <@m.categorySelect "" "" ""/>

                            </div>
                            <div class="form-group">
                            <@m.inputText '分类名称:' 'name' ""/>
                                <span class="help-block">*选择上级分类,如未选择，则为顶级分类</span>
                            </div>
                            <div class="form-group ">
                                <div class="col-sm-offset-8 col-sm-4 pull-right">
                                <#--<button class="btn btn-default">Cancel</button>-->
                                <@m.btnReturn/>
                                    <span class="btn btn-primary"
                                          href="${context}/gms/goods/toGoodsEdit.html" onclick="createCate()">创建分类</span>
                                </div>
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
    function createCate() {
        var name = $("#name").val();
        var firstNo = $("#firstCategory").val();
        var secondNo = $("#secondCategory").val();
        var thirdNo = $("#thirdCategory").val();
        if (name == "" || name.length > 8) {
            alert("分类名称长度为1-8位");
            return;
        }
        if (thirdNo != "") {
            alert("目前分类不支持4及分类");
            return;
        }
        $.ajax({
            type: "post",
            url: "${context}/gms/category/editCategory.html",
            data: $("#queryForm").serialize(),
            success: function (messageVo) {
                var obj = JSON.parse(messageVo);
                if (obj.success == true) {
                    alert("分类创建成功");
                    window.location.reload();
                    <#--window.location.href = "${context}/gms/category/toCategoryManager.html";-->
                } else {
                    alert(obj.message);
                }
            }
        });
    }
</script>
</body>
</html>
