<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel3  context+'/gms/order/toOrderManager.html' '订单管理' '订单详情'/>

    <div class="row">
        <div class="col-sm-12">

                <h4 class="text-center text-danger">订单编号:${vo.order.no!}-<@m.orderStatusText vo.order.status!/></h4>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">收货信息</h3>
                    </div>
                    <div class="panel-body">
                        <p>收货人:${vo.order.linkman!}&nbsp;&nbsp;&nbsp;&nbsp;收货地址:${vo.order.address!}&nbsp;&nbsp;&nbsp;&nbsp;联系电话:${vo.order.mobile!}</p>


                    </div>

                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">支付信息</h3>
                    </div>
                    <div class="panel-body">
                    <#if vo.order.payment??&& vo.order.payment=="0">
                        <p><input type="radio" name="payment" value="0" disabled="">货到付款</p>
                    <#else>
                        <p><input type="radio" name="payment" value="1" checked="true">网上支付</p>
                    </#if>
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
                                     <span class="pull-right">购买商品 <class="text-danger">${vo.order.totalAmount!}</label>
                                         件,
                        订单金额(含运费):<span>¥</span><span style=" color:#ff5500;"><label
                                                 style="color:#ff5500;font-size:14px; font-weight:bold;">${vo.order.totalPrice+vo.order.totalExpressPrice!}</label></span>

                        </span>
                                </td>
                            </tr>
                            </tbody>

                        </table>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">订单管理</h3>
                    </div>
                    <div class="panel-body">

                        <form class="form-horizontal" action="${context}/gms/order/orderEdit.html" method="post"
                              id="queryForm">
                            <fieldset>
                                <div class="row">
                                    <div class="col-sm-12">


                                        <div class="form-group">
                                        <@m.inputText '快递单号:' 'expressCompany' vo.order.expressCompany!/>
                                        <@m.inputText '快递公司:' 'expressOrderNo' vo.order.expressOrderNo!/>
                                           <@m.merchantOrderStatusSelect vo.order.status!/>
                                        </div>
                                        <div class="form-group ">
                                            <input type="hidden" name="id" id="id" value="${vo.order.id!}">

                                            <div class="col-sm-offset-8 col-sm-4 text-right">
                                            <@m.btnReturn/>
                                                <span class="btn btn-primary" onclick="saveOrder()">保存</span>

                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </fieldset>
                        </form>
                    </div>

                </div>

        </div>
    </div>
<#include "../include/footer.ftl"/>
</div>
<script>
    function saveOrder() {
        var status = $("#status").val();
        var expressCompany = $("#expressCompany").val();
        var expressOrderNo = $("#expressOrderNo").val();

        if (status == "") {
            alert("请选择订单状态");
            return
        }
        //发货
        if (status == "1") {
            if (expressCompany == "" || expressOrderNo == "") {
                alert("发货状态下，请填写快递单号及快递公司");
                return
            }
        }

        $.ajax({
            type: "post",
            url: "${context}/gms/order/orderEdit.html",
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
</script>
</body>
</html>
