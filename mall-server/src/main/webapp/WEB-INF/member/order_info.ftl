<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/mall_nav.ftl"/>
<@m.mallNavLevel3  context+'/member/orderList.html' '我的订单' '订单详情'/>

    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/member/paySubmit.html" id="signupForm">

                 <h4 class="text-center text-danger">订单编号:${vo.order.no!}</h4>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">收货信息</h3>
                    </div>
                    <div class="panel-body">
                        <p>收货人:${vo.order.linkman!}</p>

                        <p>收货地址:${vo.order.address!}</p>

                        <p>联系电话:${vo.order.mobile!}</p>
                    </div>

                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">支付信息</h3>
                    </div>
                    <div class="panel-body">
                        <p><input type="radio" name="payment" value="0" disabled="">货到付款</p>

                        <p><input type="radio" name="payment" value="1" checked="true">网上支付</p>


                    </div>

                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">商品信息</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table   table-hover  " id="gmsTable">
                            <thead>
                            <tr>
                                <th class="col-sm-5">商品名称</th>
                                <th>单价</th>
                                <th>单价(¥)</th>
                                <th>数量</th>
                                <th>运费</th>
                                <th>小计(¥)</th>
                            </tr>

                            </thead>

                            <tbody>
                            <#list vo.subOrders as item>
                            <tr>
                                <td>
                                    <a href="${context}/${item.goodsNo!}/goods.html"
                                       target="_blank">  ${item.goodsName!}
                                </td>
                                <td>
                                    <@m.currencyFlag item.currencyType!"0" item.orderPrice!0/>
                                </td>
                                <td>
                                ${(item.orderPrice!0)*(item.rate!-1)}元
                                </td>
                                <td>
                                ${item.amount!}
                                </td>
                                <td>
                                ${item.expressPrice!-0.0}
                                </td>
                                <td>
                                ${(item.orderPrice*item.amount*item.rate)+item.expressPrice!0}元
                                </td>
                            </tr>
                            </#list>
                            <tr>
                                <td colspan="6">
                                     <input type="hidden" name="id" value="${vo.order.id!}">
                                     <span class="pull-right">已选商品 <class="text-danger">${vo.order.totalAmount!}</label> 件,
                        订单金额(含运费):<span>¥</span><span style=" color:#ff5500;"><label   style="color:#ff5500;font-size:14px; font-weight:bold;">${vo.order.totalPrice+vo.order.totalExpressPrice!}</label></span>

                         &nbsp;&nbsp;&nbsp;&nbsp;<@m.btnReturn/>  <#if vo.order.payFlag=="N"><button class="btn  btn-primary ">支付订单</button><#else><span class="btn btn-success ">已经支付</span>  </#if>

                        </span>
                                </td>
                            </tr>
                            </tbody>

                        </table>
                    </div>
                </div>
            </form>
        </div>
    </div>
<#include "../include/footer.ftl"/>
</div>

<script src="${context}/js/jquery.validate.min.js" type="text/javascript"></script>
</body>
</html>
