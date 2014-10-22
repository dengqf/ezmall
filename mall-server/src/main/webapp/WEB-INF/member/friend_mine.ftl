<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>

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
                    <th class="col-sm-3">好友用户名</th>
                    <th class="col-sm-5">备注</th>
                    <th class="col-sm-2">状态</th>
                    <th class="col-sm-2">操作</th>


                </tr>
                </thead>
                <tbody>
                    <#if  (pageData.data)?? >
                        <#list pageData.data as item >
                        <tr id="${item.id!}">
                            <td>${item.friendUsername!}</td>
                            <td>${item.remark!}</td>
                            <td id="${item.id!}_status"><@m.friendStatusText item.status!/></td>

                            <td>
                                <div class="btn-group btn-group-sm">
                                    <button type="button" class="btn btn-primary">管理列表</button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown"><span
                                            class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li><@m.delLinkById item.id context+"/member/delFriend.html"/></li>
                                        <li><a href="#" onclick="getFriend('${item.friendUsername}')">查看用户资料</a></li>

                                        <li><a href="#"
                                               onclick="blockFriend('${item.memberUsername}','${item.friendUsername}','${item.id!}')">加入黑名单</a>
                                        </li>

                                        <li class="divider"></li>
                                    </ul>
                                </div>
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


    <script>




        function blockFriend(memberId, friendId, id) {
            $.ajax({
                type: "POST",
                url: "${context}/member/blockFriend.html",
                data: {
                    "memberUsername": memberId,
                    "friendUsername": friendId

                },
                dataType: "json",
                success: function (messageVo) {
                    if (messageVo.success) {
                        alert("操作成功");
                        document.getElementById(id + "_status").innerHTML = "黑名单";
                    } else {
                        alert(messageVo.message);
                    }
                }
            });
        }


    </script>
    <@m.viewFriend/>
</body>
</html>
