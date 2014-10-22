<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>

<#if item.id??>
    <@m.breadNavLevel3  context+'/gms/prop/toPropItemManager.html' '属性管理' '编辑属性'/>
<#else>
    <@m.breadNavLevel3  context+'/gms/prop/toPropItemManager.html' '属性管理' '新建属性'/>
</#if>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="#" method="post"
                  id="queryForm">
                <fieldset>
                    <div class="row">
                        <div class="col-sm-12">
                        <#if item.id??>

                        <#else>
                            <div class="form-group">
                                <@m.categorySelect "" "" ""/>

                            </div>
                        </#if>


                            <div class="form-group">
                            <@m.inputText '属性名称:' 'name' item.name!/>
                            <#--<@m.inputText '属性默认值:' 'defaultValue' item.defaultValue!/>-->

                                <label for="itemType" class="col-sm-2 control-label">属性类型:
                                </label>

                                <div class="col-sm-2">
                                    <select class="form-control input-sm" id="itemType" name="itemType">
                                        <option value="">--请选择--</option>
                                        <option value="0"
                                                <#if item.itemType??&&item.itemType==0 >selected="true" </#if>>文本输入
                                        </option>
                                        <option value="1"
                                                <#if item.itemType??&&item.itemType==1 >selected="true" </#if>>单选框
                                        </option>
                                        <option value="2"
                                                <#if item.itemType??&&item.itemType==2 >selected="true" </#if>>多选框
                                        </option>

                                    </select>
                                </div>


                            </div>
                            <div class="form-group ">
                                <label for="defaultValue" class="col-sm-2 control-label">属性默认值:</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="8" id="defaultValue" name="defaultValue">${item.defaultValue!}</textarea>
                                    <span class="help-block">属性A|属性B(中间用|分开)</span>
                                </div>
                            </div>
                            <div class="form-group ">
                                <input type="hidden" name="id" value="${item.id!}" id="id">

                                <div class="col-sm-offset-8 col-sm-4 pull-right">
                                <@m.btnReturn/>
                                    <span class="btn btn-primary"
                                          href="${context}/gms/goods/toGoodsEdit.html" onclick="createProp()">确定</span>
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
    function createProp() {
        var thirdNo = $("#thirdCategory").val();
        var name = $("#name").val();
        var id = $("#id").val();
        if (id == "") {
            if (thirdNo == "") {
                alert("属性只能关联第三级分类");
                return;
            }
        }
        if (name.length < 2 || name.length > 8) {
            alert("属性名称长度为2-8位");
            return;
        }

        var itemType = $("#itemType").val();
        if (itemType == "") {
            alert("请选择属性项类型");
            return;
        }
        $.ajax({
            type: "post",
            url: "${context}/gms/prop/propItemEdit.html",
            data: $("#queryForm").serialize(),
            success: function (messageVo) {
                var obj = JSON.parse(messageVo);
                if (obj.success == true) {
                    alert("属性创建成功");
//                    $("#id").val(obj.id);
                <#--window.location.href = "${context}/gms/prop/toPropItemManager.html";-->
                } else {
                    alert(obj.message);
                }
            }
        });
    }
</script>
</body>
</html>
