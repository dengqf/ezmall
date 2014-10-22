<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>

</head>
<body>

<div class="row">
<div class="col-sm-12">
<#if pageData??>
    <@m.tableWithPage 'queryForm'>
        <thead>
        <tr>
            <th class="col-sm-5">用户名</th>
            <th class="col-sm-5">操作</th>


        </tr>
        </thead>
    <tbody>
        <#if  (pageData.data)?? >
            <#list pageData.data as item >
            <tr id="${item.id!}">
                <td>${item.memberUsername!}</td>

                <td>
                    <div class="btn-group btn-group-sm">
                        <button type="button" class="btn btn-primary">管理列表</button>
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><span
                                class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#" onclick="agreeFriend('${item.id!}')">同意并加为好友</a></li>
                            <li><a href="#" onclick="getFriend('${item.memberUsername}')">查看用户资料</a></li>

                            <li><a href="#" onclick="blockFriend('${item.friendUsername!}','${item.memberUsername!}','${item.id!}')">加入黑名单</a></li>
                            <li><a href="#" onclick="refuseFriend('${item.id!}')">拒绝</a></li>
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


<script>


    function agreeFriend(id) {
        $.ajax({
            type: "POST",
            url: "${context}/member/agreeFriend.html",
            data: {
                "id": id
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success) {
                    alert("操作成功");
                    $("#" + id).attr("class", "hidden");
                } else {
                    alert(messageVo.message);
                }
            }
        });
    }

    function refuseFriend(id) {
        $.ajax({
            type: "POST",
            url: "${context}/member/refuseFriend.html",
            data: {
                "id": id
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success) {
                    alert("操作成功");
                    $("#" + id).attr("class", "hidden");
                } else {
                    alert(messageVo.message);
                }
            }
        });
    }
    function blockFriend(memberId, friendId,id) {
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
                    $("#" + id).attr("class", "hidden");
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
