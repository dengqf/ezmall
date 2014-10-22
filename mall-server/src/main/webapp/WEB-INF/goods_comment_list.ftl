<!DOCTYPE html>
<html lang="en">
<head>
<#include "./include/head.ftl"/>
    <link rel="stylesheet" type="text/css" href="${context}/css/jRating.jquery.css" media="screen"/>
    <script type="text/javascript" src="${context}/js/jRating.jquery.js"></script>
</head>
<body>

<div class="row">
    <div class="col-sm-12">
    <#if pageData??&&pageData.data??>
        <#list pageData.data as item >
            <div class="panel panel-default">
                <div class="panel-body">
                    <p> ${item.comment!}</p>
                    <span>用户:${item.username?substring(0,3)+"**"} ,商品评分:</span><span style="color: red">${item.score!}
                    分</span>

                    <div class="basic" data-average="${item.score!0}" data-id="1"></div>

                </div>
            </div>
        </#list>
    <#else >
        该商品目前没有评论
    </#if>

        <div class="text-right"> <@m.onlyPageInfo/></div>
    </div>


    <script>
        function queryPage(no) {
            window.location.href = '${context}/${no!}/' + no + '/goodsCommentList.html';
        }

        $(document).ready(function () {
            $(".basic").jRating({
                bigStarsPath: '${context}/img/icons/stars.png', // path of the icon stars.png
                smallStarsPath: '${context}/img/icons/small.png', // path of the icon small.png
                isDisabled: true
            });

        });
    </script>
</body>
</html>
