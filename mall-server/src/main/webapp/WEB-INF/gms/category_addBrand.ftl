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

<@m.breadNavLevel3  context+'/gms/category/toCategoryManager.html' '分类管理' '品牌关联'/>
    <form class="form-horizontal" action="${context}/gms/category/addBrand.html" method="post"
          id="queryForm">

        <div class="row">
            <div class="col-sm-12">

                <div class="row">
                    <div class="col-sm-12">
                        <p>分类名称：${category.name!}  </p>

                        <p>分类结构名：${category.structName!} </p>
                    </div>
                    <div class="col-sm-12">
                        <p>分类关联品牌:&nbsp;&nbsp;&nbsp;&nbsp;<span id="checkedIds"></span>
                        </p>

                        <p><input name="tags" id="tags" value=""/></p>
                    </div>
                    <div class="col-sm-offset-10 col-sm-2">
                        <span class="btn btn-primary" onclick="editBrandGroup()">保存</span>
                    <@m.btnReturn/>
                    </div>

                </div>


            </div>


        </div>
        <h4>选取品牌:</h4>
        <hr>
        <div class="row ">
            <div class="col-sm-12 ">
                <ul class="list-inline ">
                <#list brands as brand>
                    <li class="col-sm-2">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="brandNo" title="${brand.name!}"
                                       value="${brand.no!}"
                                       onclick="showBrands()"
                                    <#if relations??>
                                        <#list relations as item>
                                            <#if item.brandNo==brand.no>
                                       checked="true"
                                            </#if>
                                        </#list>
                                    </#if>
                                        > ${brand.name!}
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
        function showBrands() {
            var checkedBrands = $('input[name="brandNo"]:checked');
            if (checkedBrands.size() <= 0) {
                $("#checkedIds").html('');
                $('#tags').importTags('');
                return false;
            }
            // 获取父窗口
            var brandNos = [];
            $($("input[name='brandNo']:checked")).each(
                    function () {
                        brandNos[brandNos.length] = $(this).attr('title');

                    }

            );


            var brandNoStrs = brandNos.join(',');
            $('#tags').importTags(brandNoStrs);
//        $("#checkedIds").html(brandNoStrs);
        }


        function editBrandGroup() {

            var checkedBrands = $('input[name="brandNo"]:checked');
            if (checkedBrands.size() <= 0) {
                alert("至少需要选择一个品牌")
                return;
            }

            $.ajax({
                type: "post",
                url: "${context}/gms/category/addBrand.html",
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
            showBrands()

        });

        $('#tags').tagsInput({
            width: 'auto',
//        height:'80px',
            onRemoveTag: function (tag) {
//            console.log('remover'+'"'+tag+'"')
//            alert(tag);
                $($("input[name='brandNo']:checked")).each(
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
