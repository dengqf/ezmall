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
                        <th class="col-sm-1">选择</th>
                        <th>订单编号</th>
                        <th>好友用户名</th>
                        <th>转发时间</th>
                        <th>状态</th>
                        <th class="col-sm-2">操作</th>

                    </tr>
                    </thead>
                    <tbody>
                        <#if  (pageData.data)?? >
                            <#list pageData.data as item >
                            <tr id="${item.id!}">
                                <td><input type="checkbox" id="orderNo" name="orderNo" title="${item.orderNo!}"
                                           value="${item.orderNo!}"></td>
                                <td>${item.orderNo!}</td>
                                <td><a href="#" onclick="getFriend('${item.memberName!}')"> ${item.memberName!}</a></td>
                                <td>${(item.createDate?string("yyyy-MM-dd"))!}</td>
                                <td><@m.friendOrderStatusText item.status!/></td>

                                <td>
                                    <span class="btn btn-sm btn-primary"
                                          onclick="doFriendOrder('${item.id!}',1)">同意</span>
                                    <span class="btn btn-sm btn-danger"
                                          onclick="doFriendOrder('${item.id!}',-1)">拒绝</span>
                                </td>

                            </tr>

                            </#list>
                        <tr>
                            <td colspan="6">
                                <label class="checkbox inline">
                                    <input value="" type="checkbox" id="checkAll"> 全选
                                    <span class="btn btn-sm btn-danger" onclick="changeStatus('1')">生成采购单</span>
                                </label>




                            </td>
                        </tr>
                        <#else>
                        <tr>
                            <td colspan="6">没有相关记录！</td>
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

        $("#friendOrder_toMe").attr("class", "list-group-item active");
    });
    $("#checkAll").click(function () {

        $('input[type=checkbox]').prop('checked', $(this).prop('checked'));
    });
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
