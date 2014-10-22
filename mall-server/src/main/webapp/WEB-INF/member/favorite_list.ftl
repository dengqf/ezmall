<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/mall_nav.ftl"/>
<@m.mallNavLevel2  '我的收藏'/>
    <form class="form-horizontal" action="${context}/member/favoriteList.html" method="post"
          id="queryForm">
        <div class="row">
            <div class="col-sm-2">

            <@m.memberMenu/>
            </div>
            <div class="col-sm-10">
            <#if pageData??>
                <@m.tableWithPage 'queryForm'>
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th class="col-sm-5">商品信息</th>
                        <th>单价</th>
                        <th>收藏时间</th>

                        <th class="col-sm-2">操作</th>

                    </tr>
                    </thead>
                    <tbody>
                        <#if  (pageData.data)?? >
                            <#list pageData.data as item >
                            <tr id="${item.id!}">
                                <td><input type="checkbox" id="goodsNo" name="id"
                                           value="${item.id!}"></td>
                                <td><a href="${context}/${item.goodsNo!}/goods.html" target="_blank"><img
                                        src="${context}/${item.goodsPicture!}" width="45" height="45"/></a><a
                                        href="${context}/${item.goodsNo!}/goods.html"
                                        target="_blank">${item.goodsName!}</a>
                                </td>
                                <td><@m.currencyFlag item.currencyType!"0" item.sellPrice!0/></td>
                                <td>${(item.createDate?string("yyyy-MM-dd"))!}</td>

                                <td>
                                    <@m.delById item.id "${context}/member/delFavorite.html" />
                                    <span class="btn btn-primary btn-sm"
                                          onclick="addToShopCart('${item.goodsNo!}',1)">加入购物车</span>
                                </td>

                            </tr>

                            </#list>
                        <tr>
                            <td colspan="5">
                                <label class="checkbox inline">
                                    <input value="" type="checkbox" id="checkAll"> 全选

                                </label>
                                <span class="btn btn-primary" onclick="delFav()">取消收藏</span>
                                <@m.btnReturn/>

                            </td>
                        </tr>
                        <#else>
                        <tr>
                            <td colspan="5">没有相关记录！</td>
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

        $('input[name=id]').prop('checked', $(this).prop('checked'));

    });
    function delFav() {
        var checkedProps = $('input[name="id"]:checked');
        if (checkedProps.size() <= 0) {
            alert("至少需要选择一个商品");
            return;
        }
        var form=  document.getElementById("queryForm");
        var action=form.action;
        form.action="${context}/member/delFavoriteList.html"
        document.getElementById("queryForm").submit();
        form.action=action;
    }

    $(function(){
        $("#menu_favorite").attr("class", "list-group-item active");
    })
</script>

<@m.shopCart/>
</body>
</html>
