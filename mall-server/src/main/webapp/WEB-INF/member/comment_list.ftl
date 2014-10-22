<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
    <link rel="stylesheet" type="text/css" href="${context}/css/jRating.jquery.css" media="screen"/>
    <script type="text/javascript" src="${context}/js/jRating.jquery.js"></script>
</head>
<body>
<form class="form-horizontal" action="${context}/member/favoriteList.html" method="post"
      id="queryForm">
    <div class="row">
        <div class="col-sm-12">
        <#if pageData??>
            <@m.tableWithPage 'queryForm'>
                <thead>
                <tr>
                    <th class="col-sm-2">订单编号</th>
                    <th class="col-sm-4">商品信息</th>
                    <th class="col-sm-4">我的评论</th>
                    <th class="col-sm-2">我的评级</th>

                </tr>
                </thead>
                <tbody>
                    <#if  (pageData.data)?? >
                        <#list pageData.data as item >
                        <tr id="${item.orderSubNo!}">
                            <td>${item.orderNo!}</td>
                            <td><a href="${context}/${item.goodsNo!}/goods.html" target="_blank"><img
                                    src="${context}/${item.goodsPicture!}" width="45" height="45"/></a><a
                                    href="${context}/${item.goodsNo!}/goods.html"
                                    target="_blank">${item.goodsName!}</a>
                            </td>
                            <td>
                             ${item.comment!}

                            </td>
                            <td>
                                <span>商品评分:</span><span  style="color: red">${item.score!}分</span>

                                <div class="basic"  data-average="${item.score!0}" data-id="1"></div>
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





    <script>




        $(document).ready(function () {


            // you can rate 3 times ! After, jRating will be disabled
            $(".basic").jRating({
                isDisabled:true,
                nbRates: 0
            });

        });
    </script>
</body>
</html>
