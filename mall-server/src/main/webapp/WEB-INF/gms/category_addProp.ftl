<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
    <script src="${context}/js/jquery.tagsinput.min.js"></script>
    <link href="${context}/css/jquery.tagsinput.css" rel="stylesheet">
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>

<@m.breadNavLevel3  context+'/gms/category/toCategoryManager.html' '分类管理' '属性关联'/>
    <form class="form-horizontal" action="${context}/gms/category/addProp.html" method="post"
          id="queryForm">

        <div class="row">
            <div class="col-sm-12">

                <div class="row">
                    <div class="col-sm-12">
                        <p>分类名称：${category.name!}  </p>

                        <p>分类结构名：${category.structName!} </p>
                    </div>
                    <div class="col-sm-12">
                        <p>分类关联属性:&nbsp;&nbsp;&nbsp;&nbsp;<span id="checkedIds"></span>
                        </p>

                        <p><input name="tags" id="tags" value=""/></p>
                    </div>
                    <div class="col-sm-offset-10 col-sm-2">
                        <span class="btn btn-primary" onclick="editPropGroup()">保存</span>
                    <@m.btnReturn/>
                    </div>

                </div>


            </div>


        </div>
        <h4>选取属性:</h4>
        <hr>
        <div class="row ">
            <div class="col-sm-12 ">
                <ul class="list-inline ">
                <#list propItems as propItem>
                    <li class="col-sm-2">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="propitemNo" title="${propItem.name!}"
                                       value="${propItem.no!}"
                                       onclick="showProps()"
                                    <#if relations??>
                                        <#list relations as item>
                                            <#if item.propitemNo==propItem.no>
                                       checked="true"
                                            </#if>
                                        </#list>
                                    </#if>
                                        > ${propItem.name!}
                            </label>
                        </div>
                    </li>
                </#list>
                </ul>
            </div>
        </div>
        <input type="hidden" value="${category.no!}" name="no">
    </form>
    <script>
        function showProps() {
            var checkedProps = $('input[name="propitemNo"]:checked');
            if (checkedProps.size() <= 0) {
                $("#checkedIds").html('');
                $('#tags').importTags('');
                return false;
            }
            // 获取父窗口
            var propNos = [];
            $($("input[name='propitemNo']:checked")).each(
                    function () {
                        propNos[propNos.length] = $(this).attr('title');

                    }

            );
            var strs = propNos.join(',');
            $('#tags').importTags(strs);
        }


        function editPropGroup() {

            var checkedProps = $('input[name="propitemNo"]:checked');
            if (checkedProps.size() <= 0) {
                alert("至少需要选择一个属性")
                return;
            }

            $.ajax({
                type: "post",
                url: "${context}/gms/category/addProp.html",
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

        $(function () {
            showProps()

        });

        $('#tags').tagsInput({
            width: 'auto',
//        height:'80px',
            onRemoveTag: function (tag) {
//            console.log('remover'+'"'+tag+'"')
//            alert(tag);
                $($("input[name='propitemNo']:checked")).each(
                        function () {
                            var val = $(this).attr('title');
                            if (val == tag) {
                                $(this).attr("checked", false);
                            }
//

                        }

                );
            },

            interactive: false
        });
    </script>
<#include "../include/footer.ftl"/>
</div>

</body>
</html>
