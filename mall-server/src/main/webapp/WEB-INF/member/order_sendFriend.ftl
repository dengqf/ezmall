<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/mall_nav.ftl"/>
<@m.mallNavLevel3  context+'/member/orderList.html' '我的订单' '选择好友'/>

    <div class="row">
        <div class="col-sm-2">

        <@m.memberMenu/>
        </div>
        <div class="col-sm-10">
            <form class="form-horizontal" action="${context}/member/sendOrderToFriend.html" method="post"
                  id="queryForm">
            <#if pageData??>
                <@m.tableWithPage 'queryForm'>
                    <thead>
                    <tr>
                        <th class="col-sm-3">好友用户名</th>


                        <th class="col-sm-9">操作</th>

                    </tr>
                    </thead>
                    <tbody>
                        <#if  (pageData.data)?? >
                            <#list pageData.data as item >
                            <tr id="${item.id!}">

                                <td>${item.friendUsername!}</td>

                                <td>
                                    <span class="btn btn-primary btn-sm"
                                          onclick="getFriend('${item.friendUsername}')">查看资料</span>
                                     <span class="btn btn-primary btn-sm"
                                           onclick="sendOrder('${item.friendUsername!}')">转发订单</span>
                                </td>

                            </tr>

                            </#list>

                        <#else>
                        <tr>
                            <td colspan="12">没有相关记录！</td>
                        </tr>
                        </#if>
                    </tbody>
                </@m.tableWithPage>
            </#if>
                <input type="hidden" name="orderNo" id="orderNo" value="${orderNo!}">
            </form>
        </div>
    </div>
<#include "../include/footer.ftl"/>
</div>
<script>
    function sendOrder(username) {
        //TODO
        var orderNo = $("#orderNo").val();
        $.ajax({
            type: "POST",
            url: "${context}/member/confirmOrderToFriend.html",
            data: {
                "username": username,
                "orderNo": orderNo

            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success) {
                    alert("订单转发成功");

                } else {
                    alert(messageVo.message);
                }
            }
        });
    }
</script>
<@m.viewFriend/>
</body>
</html>
