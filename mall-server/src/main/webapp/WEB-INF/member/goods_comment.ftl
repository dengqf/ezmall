<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/mall_nav.ftl"/>
<@m.mallNavLevel2  '商品点评/晒单'/>
    <form class="form-horizontal" action="${context}/member/favoriteList.html" method="post"
          id="queryForm">
        <div class="row">
            <div class="col-sm-2">

            <@m.memberMenu/>
            </div>
            <div class="col-sm-10">
                <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                    <li class="active"><a href="#home" data-toggle="tab">未点评商品</a></li>
                    <li><a href="#profile" data-toggle="tab">已点评商品</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade active in" id="home">

                        <iframe width="100%" id="mainframe" scrolling="no" frameborder="0"
                                onload="this.height=Math.max(window.screen.height - 320,
            Math.min(mainframe.document.documentElement.scrollHeight,mainframe.document.body.scrollHeight))"
                                border="0" name="mainframe" src="${context}/member/notCommentSubOrder.html"></iframe>
                    </div>
                    <div class="tab-pane fade" id="profile">

                        <iframe width="100%" id="mainframe2" scrolling="no" frameborder="0"
                                onload="this.height=Math.max(window.screen.height - 320,
            Math.min(mainframe.document.documentElement.scrollHeight,mainframe.document.body.scrollHeight))"
                                border="0" name="mainframe" src="${context}/member/commentList.html"></iframe>
                    </div>

                </div>
            </div>
        </div>
    <#include "../include/footer.ftl"/>
</div>
<script>
    $(function () {
        $("#menu_comment").attr("class", "list-group-item active");
    })
</script>
</body>
</html>
