<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/mall_nav.ftl"/>
<@m.mallNavLevel2  '好友的订单'/>
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
                        <th>订单编号</th>
                        <th>转发的好友</th>
                        <th>转发时间</th>
                        <th>状态</th>
                        <th class="col-sm-2">操作</th>

                    </tr>
                    </thead>
                    <tbody>
                        <#if  (pageData.data)?? >
                            <#list pageData.data as item >
                            <tr id="${item.id!}">
                                <td>${item.orderNo!}</td>
                                <td><a href="#" onclick="getFriend('${item.friendName}')"> ${item.friendName!}</a></td>
                                <td>${(item.createDate?string("yyyy-MM-dd"))!}</td>
                                <td><@m.friendOrderStatusText item.status!/></td>

                                <td>
                                    <#if item.status!="1">

                                        <a href="${context}/member/sendOrderToFriend.html?orderNo=${item.orderNo!}" class="btn btn-sm btn-primary">转发</a>
                                        <@m.delById item.id context+"/member/delFriendOrder.html"/>
                                        <#--<span class="btn btn-sm btn-danger" onclick="doFriendOrder('${item.id!}',-1)">删除</span>-->
                                    </#if>
                                </td>

                            </tr>

                            </#list>

                        <#else>
                        <tr>
                            <td colspan="4">没有相关记录！</td>
                        </tr>
                        </#if>
                    </tbody>
                </@m.tableWithPage>
            </#if>

            </div>
        </div>
    <#include "../include/footer.ftl"/>
</div>
<@m.viewFriend/>

<script>
    $(function () {

        $("#friendOrder_fromMe").attr("class", "list-group-item active");
    }
    );

    function doFriendOrder(id, status) {
        $.ajax({
            type: "POST",
            url: "${context}/member/changeFriendOrderStatus.html",
            data: {
                "id": id,
                "status": status

            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success) {
                    alert("修改成功");
                    window.location.reload();
                } else {
                    alert(messageVo.message);
                }
            }
        });
    }
</script>
</body>
</html>
