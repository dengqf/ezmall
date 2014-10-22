<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/mall_nav.ftl"/>
<@m.mallNavLevel2  '我的订单'/>

    <div class="row">
        <div class="col-sm-2">

        <@m.memberMenu/>
        </div>
        <div class="col-sm-10">
        <#if pageData??>
            <@m.tableWithPage 'queryForm'>
                <thead>
                <tr>
                    <th class="col-sm-2">购买时间</th>
                    <th class="col-sm-7">订单详情</th>
                    <th class="col-sm-1">状态</th>
                    <th class="col-sm-2">操作</th>

                </tr>
                </thead>
                <tbody>
                    <#if  (pageData.data)?? >
                        <#list pageData.data as item >
                        <tr id="${item.order.id!}">
                            <td>${(item.order.createDate?string("yyyy-MM-dd"))!}</td>
                            <td>
                                <div>

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <ul class="list-inline">
                                                <#list item.subOrders as sub>
                                                    <li class="col-sm-1">
                                                        <a href="${context}/${sub.goodsNo!}/goods.html" target="_blank"
                                                           title="${sub.goodsName!}"><img
                                                                src="${context}/${sub.goodsPicture!}" width="55"
                                                                height="55"/></a></li>


                                                </#list>
                                            </ul>
                                        </div>
                                    </div>
                                    <p>订单金额:${item.order.totalPrice!}元,运费:${item.order.totalExpressPrice!}
                                        元,合计:${item.order.totalPrice+item.order.totalExpressPrice}</p>

                                </div>
                            </td>
                            <td>
                                <@m.orderStatusText item.order.status!/>
                            </td>
                            <td>
                            <p> <#if item.order.payFlag=="N"><a class="btn btn-sm btn-danger"
                                                                href="${context}/member/paySubmit.html?id=${item.order.id!}">立即支付</a><#else>
                                <span class="btn btn-sm btn-success">已经支付</span>  </#if><p>

                                <p><a href="${context}/member/orderInfo.html?id=${item.order.id!}">查看订单</a></p>

                                <p><#if "0"==item.order.status><a href="#" onclick="cancleOrder('${item.order.id!}')">取消订单</a> <#else>
                                    &nbsp;&nbsp;</#if></p>

                                <p><#if "0"==item.order.status><a href="#" onclick="sendFriend('${item.order.no!}')">转发好友</a> <#else>
                                    &nbsp;&nbsp;</#if></p>

                                <p> 订单编号:${item.order.no!} </p>
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
<script>
    function cancleOrder(id) {
        $.ajax({
            type: "POST",
            url: "${context}/member/cancelOrder.html",
            data: {
                "id": id
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {

                    $("#" + id).attr("class", "hidden");

                } else {
                    alert(messageVo.message);

                }
            }
        });
    }
    function sendFriend(no) {

        $.ajax({
            type: "POST",
            url: "${context}/member/checkOrderCanSend.html",
            data: {
                "no": no
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    //转页

                window.location.href = "${context}/member/sendOrderToFriend.html?orderNo="+no;

                } else {
                    alert(messageVo.message);

                }
            }
        });
    }
    $(function () {
        $("#menu_order").attr("class", "list-group-item active");
    })
</script>
</body>
</html>
