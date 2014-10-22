<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/mall_nav.ftl"/>
<@m.mallNavLevel2  '我的好友'/>
    <form class="form-horizontal" action="${context}/member/favoriteList.html" method="post"
          id="queryForm">
        <div class="row">
            <div class="col-sm-2">

            <@m.memberMenu/>
            </div>
            <div class="col-sm-10">
                <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                    <li class="active"><a href="#home" data-toggle="tab">好友列表</a></li>
                    <li><a href="#profile" data-toggle="tab">受邀请列表</a></li>
                    <li><a href="#search" data-toggle="tab">查找</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade active in" id="home">

                        <iframe width="100%" id="mainframe" scrolling="no" frameborder="0"
                                onload="this.height=Math.max(window.screen.height - 420,
            Math.min(mainframe.document.documentElement.scrollHeight,mainframe.document.body.scrollHeight))"
                                border="0" name="mainframe" src="${context}/member/myFriends.html"></iframe>
                    </div>
                    <div class="tab-pane fade" id="profile">

                        <iframe width="100%" id="mainframe2" scrolling="no" frameborder="0"
                                onload="this.height=Math.max(window.screen.height -420,
            Math.min(mainframe.document.documentElement.scrollHeight,mainframe.document.body.scrollHeight))"
                                border="0" name="mainframe" src="${context}/member/beInvited.html"></iframe>
                    </div>
                    <div class="tab-pane fade" id="search">
                        <iframe width="100%" id="mainframe3" scrolling="no" frameborder="0"
                                onload="this.height=Math.max(window.screen.height - 420,
            Math.min(mainframe.document.documentElement.scrollHeight,mainframe.document.body.scrollHeight))"
                                border="0" name="mainframe" src="${context}/member/toFriendSearch.html"></iframe>
                    </div>
                </div>
            </div>
        </div>
    <#include "../include/footer.ftl"/>
</div>
<script>
    $(function () {

        $("#menu_friend").attr("class", "list-group-item active");
    })
</script>
</body>
</html>
