<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/mall_nav.ftl"/>
<@m.mallNavLevel3  context+'/member/orderList.html' '我的订单' '支付订单'/>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/member/createOrder.html" id="signupForm">
                <h4 class="text-center">订单（编号:<a href="${context}/member/orderInfo.html?id=${order.id!}" title="查看订单详情">${order.no!}</a>）已提交，请于<span
                        class="text-danger">24小时</span>内完成支付<span class="small" style="color: red">( 逾期订单将被取消 )</span>
                </h4>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">收货信息</h3>
                    </div>
                    <div class="panel-body">
                        <p>收货人:${order.linkman!}</p>

                        <p>收货地址:${order.address!}</p>

                        <p>联系电话:${order.mobile!}</p>
                    </div>

                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">应付金额：<span
                                class="text-danger"> ¥${order.totalPrice+order.totalExpressPrice}元</span></h3>
                    </div>
                    <div class="panel-body">
                        <p><input type="radio" name="payment" value="1" checked="true"><img itemprop="image"
                                                                                            src="${context}/img/alipay2.png"
                                                                                            width="180"></p>


                    </div>

                </div>
                <p class="pull-right">
                    <span class="btn btn-primary btn-lg" onclick="payOrder()">立即支付</span>
                </p>
            </form>
        </div>
    </div>
<#include "../include/footer.ftl"/>
</div>
<script>
    function payOrder() {
        alert("功能开发中");
    }
</script>
</body>
</html>
