<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel2  '商品管理'/>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/goods/goodsManager.html" method="post"
                  id="queryForm">
                <fieldset>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                            <@m.inputText '商品名称:' 'goodsName' vo.goodsName!""/>
                            <@m.brands brands vo.brandNo!/>
                            <@m.goodsStatusSelect  vo.status!/>

                            </div>
                            <div class="form-group">
                            <@m.categorySelect "${vo.firstCategory!}" "${vo.secondCategory!}" "${vo.thirdCategory!}"/>

                            </div>

                            <div class="form-group ">
                                <div class="col-sm-offset-8 col-sm-4 pull-right">
                                <#--<button class="btn btn-default">Cancel</button>-->
                                    <button class="btn btn-primary">搜索</button>
                                    <a class="btn btn-primary"
                                       href="${context}/gms/goods/toGoodsCreate.html">新建</a>
                                </div>
                            </div>
                        </div>
                    </div>

                </fieldset>
            </form>
        <#if pageData??>
            <@m.tableWithPage 'queryForm'>
                <thead>
                <tr>
                    <th>选择</th>
                    <th class="col-sm-4">商品名称</th>
                    <th>商品编号</th>
                    <th>品牌</th>
                    <th class="col-sm-2">分类结构</th>
                <#--<th>货币类型</th>-->
                <#--<th>市场价</th>-->
                    <th>销售价</th>
                    <th class="col-sm-1">状态</th>
                    <th class="col-sm-1">操作</th>

                </tr>
                </thead>
                <tbody>
                    <#if  (pageData.data)?? >
                        <#list pageData.data as item >
                        <tr id="${item.id!}">
                            <td><input type="checkbox" id="goodsNo" name="goodsNo" title="${item.status!}"
                                       value="${item.no!}"></td>
                            <td>
                                <p> ${item.name!}</p>

                                <p>所属商家:${item.merchantCompany!"商城自营"}</p>

                            </td>
                            <td>${item.no!}</td>
                            <td>${item.brandName!}</td>
                            <td>${item.categoryStructName!}</td>
                            <td>${item.currencyType!} ${item.sellPrice!0} 元</td>
                        <#--<td>${item.country!}</td>-->
                        <#--<td>${item.city!}</td>-->
                            <td id="${item.no!}_status"><@m.goodsStatusText item.status!/></td>
                            <td>
                            <#--<a class="btn btn-sm btn-primary"-->
                            <#--href="${context}/gms/goods/toGoodsEdit.html?id=${item.id!}">编辑</a>-->
                            <#--<a class="btn btn-sm btn-primary"-->
                            <#--href="${context}/gms/goods/toAddGoodsProp.html?no=${item.no!}">属性管理</a>-->
                            <#--<a class="btn btn-sm btn-primary"  target="_blank"-->
                            <#--href="${context}/${item.no!}/goods.html">预览</a>-->


                                <div class="btn-group btn-group-sm">
                                    <button type="button" class="btn btn-primary">管理</button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown"><span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li><a
                                                href="${context}/gms/goods/toGoodsEdit.html?id=${item.id!}">商品修改</a>
                                        </li>

                                        <li><a target="_blank"
                                               href="${context}/${item.no!}/goods.html">商品预览</a></li>
                                    <#--<li><a-->
                                    <#--href="${context}/gms/goods/toAddGoodsProp.html?no=${item.no!}">属性管理</a></li>-->
                                        <li> <@m.delLinkById item.id "${context}/gms/goods/delGoods.html" /></li>
                                        <li class="divider"></li>
                                    </ul>
                                </div>
                            </td>

                        </tr>

                        </#list>
                    <tr>
                        <td colspan="12">
                            <label class="checkbox inline">
                                <input value="" type="checkbox" id="checkAll"> 全选
                            </label>

                            <span class="btn btn-sm btn-danger" onclick="changeStatus('1')">批量上架</span>
                            <span class="btn btn-sm btn-danger" onclick="changeStatus('2')">批量下架</span>
                            <span class="btn btn-sm btn-danger" onclick="delGoodsList()">批量删除</span>

                        </td>
                    </tr>
                    <#else>
                    <tr>
                        <td colspan="8">没有相关记录！</td>
                    </tr>
                    </#if>
                </tbody>
            </@m.tableWithPage>
        </#if>
        </div>


    </div>
<#include "../include/footer.ftl"/>
</div>
<script>
    $("#checkAll").click(function () {

        $('input[type=checkbox]').prop('checked', $(this).prop('checked'));
    });

    function delGoodsList() {
        var checkedGoods = $('input[name="goodsNo"]:checked');
        if (checkedGoods.size() <= 0) {
            alert("至少需要选择一个商品")
            return;
        }

        var goodsNoList = [];

        $("input[name='goodsNo']:checked").each(function () {
            goodsNoList.push($(this).val());
        });

        $.ajax({
            type: "post",
            url: "${context}/gms/goods/delGoodsList.html",
            data: {
                "goodsNo": goodsNoList
            },
            success: function (messageVo) {
                var obj = JSON.parse(messageVo);
                if (obj.success == true) {
                    alert("删除成功");
                    window.location.reload();
                <#--window.location.href = "${context}/gms/goods/toGoodsManager.html";-->
                    //重新加载页面
                } else {
                    alert(obj.message);
                }
            }
        });
    }

    function changeStatus(status) {

        var checkedGoods = $('input[name="goodsNo"]:checked');
        if (checkedGoods.size() <= 0) {
            alert("至少需要选择一个商品")
            return;
        }

        var goodsNoList = [];
        var isSubmit = true;
        $("input[name='goodsNo']:checked").each(function () {
            if (status == $(this).attr('title')) {
                //状态有重复
                if (status == "1") {
                    alert("商品编号:" + $(this).val() + "的商品已经处于上架状态，请重新选择");
                    isSubmit = false;
                    return;
                } else {
                    alert("商品编号:" + $(this).val() + "的商品已经处于下架状态，请重新选择");
                    isSubmit = false;
                    return;
                }
            }

            goodsNoList.push($(this).val());
        });
        if (!isSubmit) {
            return;
        }
        $.ajax({
            type: "post",
            url: "${context}/gms/goods/changeGoodsStatus.html",
            data: {
                "status": status,
                "goodsNo": goodsNoList
            },
            success: function (messageVo) {
                var obj = JSON.parse(messageVo);
                if (obj.success == true) {
                    alert("修改成功");
                    $("input[name='goodsNo']:checked").each(function () {

                        var _id = $(this).val() + "_status";
//                        alert(_id);
                        if (status == "1") {
                            //修改为上架
                            document.getElementById(_id).innerHTML = "上架";

                        } else {
                            //修改为下架
                            document.getElementById(_id).innerHTML = "下架";
                        }
                        $(this).attr("title", status);
                    });
                } else {
                    alert(obj.message);
                }
            }
        });
    }
    $(function () {
        //TODO 以后修改JS逻辑
    <#--$("#firstCategory").val(${vo.firstCategory!});-->
    <#--$("#secondCategory").val(${vo.secondCategory!});-->
    <#--$("#thirdCategory").val(${vo.thirdCategory!""});-->
    })
</script>
</body>
</html>
