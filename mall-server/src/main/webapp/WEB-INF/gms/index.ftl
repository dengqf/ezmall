<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel2  '首页'/>
    <div class="row">

        <div class="col-sm-12">
            <ul class="list-inline">
                <li class="col-sm-3">
                    <a href="${context}/gms/goods/toGoodsManager.html">
                        <img src="${context}/img/u0.png">
                    </a>

                    <div class="caption">
                        <p><b>商品管理</b></p>
                    </div>

                </li>
                <li class="col-sm-3">
                    <a href="${context}/gms/goods/toGoodsCreate.html">
                        <img src="${context}/img/u2.png">
                    </a>

                    <div class="caption">
                        <p><b>商品发布</b></p>
                    </div>

                </li>
                <li class="col-sm-3">
                    <a href="${context}/gms/order/toOrderManager.html">
                        <img src="${context}/img/u4.png">
                    </a>

                    <div class="caption">
                        <p><b>订单查询</b></p>
                    </div>

                </li>
                <li class="col-sm-3">
                    <a href="${context}/gms/admin/toAdminUpdate.html">
                        <img src="${context}/img/u6.png">
                    </a>

                    <div class="caption">
                        <p><b>资料修改</b></p>
                    </div>

                </li>

            </ul>
        </div>
    </div>
<#include "../include/footer.ftl"/>
</div>
</body>
</html>
