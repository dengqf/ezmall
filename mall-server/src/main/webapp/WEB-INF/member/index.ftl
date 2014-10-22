<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/mall_nav.ftl"/>
<@m.mallNavLevel2  '用户首页'/>

    <div class="row">
        <div class="col-sm-offset-1 col-sm-11">
            <ul class="list-inline">
                <li class="col-sm-3">
                    <a href="${context}/member/favoriteList.html">
                        <img src="${context}/img/u0.png">
                    </a>

                    <div class="caption">
                        <p><b>我的收藏</b></p>
                    </div>

                </li>
                <li class="col-sm-3">
                    <a href="${context}/member/orderList.html">
                        <img src="${context}/img/u2.png">
                    </a>

                    <div class="caption">
                        <p><b>我的订单</b></p>
                    </div>

                </li>
                <li class="col-sm-3">
                    <a href="${context}/member/toUpdatePassword.html">
                        <img src="${context}/img/u4.png">
                    </a>

                    <div class="caption">
                        <p><b>密码修改</b></p>
                    </div>

                </li>
                <li class="col-sm-3">
                    <a href="${context}/toEditMember.html?id=${EZMALL_MEMBER.id!}">
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
