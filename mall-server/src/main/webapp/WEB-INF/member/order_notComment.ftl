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
                    <th class="col-sm-8">商品信息</th>
                    <th class="col-sm-2">操作</th>

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
                                <span class="btn btn-sm btn-primary"
                                      onclick="showComment('${item.orderSubNo!}','${item.goodsName}','${context}/${item.goodsPicture!}')">添加评论</span>
                            </td>

                        </tr>

                        </#list>

                    <#else>
                    <tr>
                        <td colspan="3">没有相关记录！</td>
                    </tr>
                    </#if>
                </tbody>
            </@m.tableWithPage>
        </#if>

        </div>
    </div>


    <div id="commentModal" class="modal fade">
        <div id="dialogWidth" class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">请添加你的评论</h4>
                </div>

                <div id="msgText" class="modal-body">
                    <div class="row">
                        <div class="col-sm-3">
                            <img id="goodsPicture" src="" width="80" height="80"/>

                            <p id="goodsName"></p>
                        </div>
                        <div class="col-sm-9">
                            <div>
                                <textarea class="form-control" rows="4" id="comment" placeholder="您的评论对我们很重要(5-128字)"
                                          name="comment"></textarea>
                            </div>
                            <div class="exemple">
                                <span>商品评分:</span><span id="showScore" style="color: red"></span>

                                <div class="basic" id="basicScore" data-average="0" data-id="1"></div>
                            </div>
                            <input type="hidden" id="score" value="-1">
                            <input type="hidden" id="subOrderNo" value="">
                        </div>
                    <#--https://github.com/alpixel/jRating-->
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary btn-sm" onclick="addComment()">发布评论</button>
                </div>

            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>


    <script>
        function showComment(subOrderNo, goodsName, imgUrl) {
            $("#commentModal").modal('show');
            $("#goodsPicture").attr("src", imgUrl);
            document.getElementById("goodsName").innerHTML = goodsName;
            $("#subOrderNo").val(subOrderNo);
            $("#score").val(-1);

            document.getElementById("showScore").innerHTML = "";
//            $(".basic").attr("class","basic");

        }

        function addComment() {
            var no = $("#subOrderNo").val();
            var score = $("#score").val();
            var comment = $("#comment").val();
            if (score < 1) {
                alert("请给商品评分");
                return;
            }

            if (comment.length > 128 || comment.length < 5) {
                alert("评论字数为5-128，请重新输入");
                return;
            }
            $.ajax({
                type: "POST",
                url: "${context}/member/addGoodsComment.html",
                data: {
                    "score": score,
                    "orderSubNo": no,
                    "comment": comment
                },
                dataType: "json",
                success: function (messageVo) {
                    if (messageVo.success == true) {
                        alert("评论添加成功");
                        $("#commentModal").modal('hide');
                        $("#" + no).attr("class", "hidden");

                    } else {
                        alert(messageVo.message);

                    }
                }
            });

        }

        $(document).ready(function () {


            // you can rate 3 times ! After, jRating will be disabled
            $(".basic").jRating({
                canRateAgain: true,
                nbRates: 999,
                onClick: function (element, rate) {
                    $("#score").val(rate);
                    document.getElementById("showScore").innerHTML = rate + '分';
                }
            });

            // get the clicked rate !
//            $(".basic").jRating({
//                onClick : function(element,rate) {
//                    alert(rate);
//                }
//            });
        });
    </script>
</body>
</html>
