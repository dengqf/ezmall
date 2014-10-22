<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
    <script src="${context}/js/jquery-1.4.2.min.js"></script>

    <script type="text/javascript">
        $(function(){
            var content="${context}";
            var myztree = function(){
                var $row = $(this).parent().parent();
                var nextlv = parseInt($row.attr("lv")) + 1;
                var nn = $(this).parent().parent().next();
                if($row.attr("isopen") == "0" && $row.attr("loding")=="0"){


                    $.ajax({
                        type: "POST",
                        url: "${context}/gms/category/categoryListByParentNo.html",
                        data: {
                            "parentNo": $row.attr("title")
                        },
                        dataType: "json",
                        success: function (messageVo) {
                            if (messageVo.success == true) {
                                var oHtml = [];
                                $.each(messageVo.obj, function (index, content) {
                                  var onof = '<i class="zhanwei"></i>';
                                    if(content.level != "3"){
                                        onof = '<i class="onoff j_onoff"></i>';

                                    oHtml.push('<li loding="0" isopen="0" class="lv'+nextlv+'" lv="'+nextlv+'" title="'+content.no+'"><div class="col1">'+onof+'<span>'+content.name+'</span></div><div class="col2">'+content.struct+'</div>' +
                                            '<div class="col3">'+content.structName+'</div>' +
                                            '<div class="col4">' +
                                            '<span class="btn btn-sm btn-danger" onclick="delObjectById('+'\''+content.id+'\''+')">删除</span>' +
                                             '</div></li>')
                                    }else{
                                        oHtml.push('<li loding="0" isopen="0" class="lv'+nextlv+'" lv="'+nextlv+'" title="'+content.no+'"><div class="col1">'+onof+'<span>'+content.name+'</span></div><div class="col2">'+content.struct+'</div>' +
                                                '<div class="col3">'+content.structName+'</div>' +
                                                '<div class="col4">' +
                                                '<span class="btn btn-sm btn-danger" onclick="delObjectById('+'\''+content.id+'\''+')">删除</span>&nbsp;&nbsp;' +
                                                '<a href="${context}/gms/category/toAddProp.html?no='+content.no+'" class="btn btn-sm btn-primary">属性关联</a>&nbsp;&nbsp;' +
                                                '<a href="${context}/gms/category/toAddBrand.html?no='+content.no+'" class="btn btn-sm btn-primary">品牌关联</a>' +
                                                '</div></li>')
                                    }

                                });
                                $row.after(oHtml.join(""));
                                $row.attr("loding","0");

                            } else {

                                alert(messageVo.message);

                            }
                        }
                    });

                    $row.addClass("open");

                    $row.attr("isopen","1");

                }else if($row.attr("isopen") == "1" && $row.attr("loding")=="0"){
                    $row.removeClass("open");
                    $row.attr("isopen","0");
                    rmchildlist();

                }

                function rmchildlist(){
                    if(nn.attr("lv") - $row.attr("lv") > 0){
                        xx = nn.next();
                        nn.remove();
                        nn =xx
                        rmchildlist();
                    }
                }
            }
            $(".j_onoff").live("click",myztree);
        })
    </script>
    <style type="text/css">
        ul.tretable {list-style:none;padding:0;}
        ul.tretable{border-top:1px solid #ddd;border-left:1px solid #ddd;width:100%;}
        ul.tretable li{border-bottom:1px solid #ddd;border-right:1px solid #ddd;height:32px;padding-left:10px;line-height:32px;}
        ul.tretable .col1,.col2,.col3,.col4{border-right:1px solid #ddd;width:230px;height:32px;float:left;width:25%;}
        ul.tretable .col4{border-right:none;}
        ul.tretable .field{background:#F5F5F5;font-weight:bold;}
        ul.tretable .onoff{width:9px;height:9px;background:url(../../img/onoff.jpg) no-repeat;display:block;cursor:pointer;margin:12px 12px;overflow:hidden;_display:inline;}
        ul.tretable .onoff{float:left;}
        ul.tretable .span{float:left;}
        ul.tretable .zhanwei{width:9px;height:9px;display:block;overflow:hidden;margin:15px;float:left;}
        ul.tretable .lv1 .j_onoff{margin-left:0px;}
        ul.tretable .lv2 .j_onoff{margin-left:15px;}
        ul.tretable .lv3 .j_onoff{margin-left:30px;}
        ul.tretable .lv4 .j_onoff{margin-left:45px;}
        ul.tretable .lv5 .j_onoff{margin-left:60px;}
        ul.tretable .lv6 .j_onoff{margin-left:75px;}
        ul.tretable .lv7 .j_onoff{margin-left:90px;}
        ul.tretable .lv8 .j_onoff{margin-left:105px;}
        ul.tretable .lv9 .j_onoff{margin-left:120px;}
        ul.tretable .lv1 .zhanwei{margin-left:0px;}
        ul.tretable .lv2 .zhanwei{margin-left:15px;}
        ul.tretable .lv3 .zhanwei{margin-left:30px;}
        ul.tretable .lv4 .zhanwei{margin-left:45px;}
        ul.tretable .lv5 .zhanwei{margin-left:60px;}
        ul.tretable .lv6 .zhanwei{margin-left:75px;}
        ul.tretable .lv7 .zhanwei{margin-left:90px;}
        ul.tretable .lv8 .zhanwei{margin-left:105px;}
        ul.tretable .lv9 .zhanwei{margin-left:120px;}
        ul.tretable .close .j_onoff{background-position:0 0;}
        ul.tretable .open .j_onoff{background-position:0 -9px;}

    </style>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel2  '分类管理'/>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/brand/brandManager.html" method="post"
                  id="queryForm">
                <fieldset>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group ">
                                <div class="col-sm-offset-10 col-sm-2 pull-right">
                                <#--<button class="btn btn-default">Cancel</button>-->

                                    <a class="btn btn-primary"
                                       href="${context}/gms/category/toEditCategory.html">新建分类</a>
                                </div>
                            </div>
                        </div>
                    </div>

                </fieldset>
            </form>
            <ul class="tretable">
                <li class="field"><div class="col1">分类名称</div><div class="col2">分类结构</div><div class="col3">结构名称</div><div class="col4">操作</div></li>
           <#if list??>
            <#list list as item>
                <li lv="1" loding="0" class="lv1" isopen="0" title="${item.no!}">
                    <div class="col1"><i class="onoff j_onoff"></i>${item.name!}</div>
                    <div class="col2">${item.struct!}</div>
                    <div class="col3">${item.structName!}</div>
                    <div class="col4"> <span class="btn btn-sm btn-danger" onclick="delObjectById('${item.id!}')">删除</span>
                        <#if item.level=="3">
                            <a class="btn btn-sm btn-primary"
                               href="${context}/gms/category/toAddProp.html?no=${item.no!}">属性关联</a>
                            <a class="btn btn-sm btn-primary"
                               href="${context}/gms/category/toAddBrand.html?no=${item.no!}">品牌关联</a></td>
                        </#if></div>
                </li>
            </#list>
           </#if>

            </ul>

        </div>


    </div>
<#include "../include/footer.ftl"/>
</div>
<script>
    function openCategory(no) {
        $.ajax({
            type: "POST",
            url: "${context}/gms/category/categoryListByParentNo.html",
            data: {
                "parentNo": no
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {

                    $.each(messageVo.obj, function (index, content) {

                        document.getElementById(no).innerHTML =
                                "<tr><td>" + content.name + "</td><td>" + content.no + "</td><td>" + content.struct + "</td><td>" + content.structName + "</td>"
                                        + "<td>" + content.level + "</td><td><a href='#'>删除</a></td></tr>";

                    });

                } else {
                    alert(messageVo.message);

                }
            }
        });
    }
    function delObjectById(id) {
        $.ajax({
            type: "POST",
            url: "/ezmall/gms/category/delCategory.html",
            data: {
                "id": id
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    alert("删除成功");
                    window.location.reload();
                } else {
                    alert(messageVo.message);
                }
            }
        });
    }
    function delCate(id) {
        alert(id);
        $.ajax({
            type: "POST",
            url: "${context}/gms/category/delCategory.html",
            data: {
                "id": id
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    alert("删除成功");
                    $("#" + id).attr("class", "hidden");
                } else {

                    alert(messageVo.message);

                }
            }
        });
    }
</script>
</body>
</html>
