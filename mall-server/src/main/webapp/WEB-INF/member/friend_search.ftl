<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>


<div class="row">
    <div class="col-sm-12">
        <form class="form-horizontal" action="${context}/member/friendSearch.html" method="post"
              id="queryForm">
            <fieldset>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <@m.inputText '用户名:' 'username' member.username!""/>
                            <@m.inputText '手机号码:' 'mobile' member.mobile!""/>
                            <@m.inputText '邮箱地址:' 'email' member.email!""/>

                        </div>


                        <div class="form-group ">
                            <div class="col-sm-12 text-right">
                                <span class="btn btn-primary" onclick="doSearch()">搜索</span>

                            </div>
                        </div>
                    </div>
                </div>

            </fieldset>
        </form>
    <#if pageData??>
        <@m.tableWithPage 'queryForm'>
            <thead>
            <tr>
                <th>用户名</th>
                <th>手机号码</th>
                <th>EMAIL</th>
                <th>操作</th>

            </tr>
            </thead>
            <tbody>
                <#if  (pageData.data)?? >
                    <#list pageData.data as item >
                    <tr id="${item.id!}">
                        <td>
                        ${item.username!}
                        </td>
                        <td>${item.mobile?substring(0,7)+"****"}</td>
                        <td>${item.email!}</td>
                        <td><span class="btn btn-sm btn-primary" onclick="addFriend('${item.username!}')">申请加为好友</span>
                        </td>

                    </tr>
                    </#list>
                <#else>
                <tr>
                    <td colspan="10">没有相关记录！</td>
                </tr>
                </#if>
            </tbody>
        </@m.tableWithPage>
    </#if>
    </div>
    <script>
        function doSearch() {
            if ($("#username").val() == "" && $("#mobile").val() == "" && $("#email").val() == "") {
                alert("用户名、手机号码、电子邮箱至少选择一样");
                return
            }
            document.getElementById("queryForm").submit();
        }

        function addFriend(username){
            $.ajax({
                type: "POST",
                url: "${context}/member/addFriend.html",
                data: {
                    "username": username
                },
                dataType: "json",
                success: function (messageVo) {
                    if (messageVo.success) {
                       alert("您的申请已经发送给对方");
                    } else {
                       alert(messageVo.message);
                    }
                }
            });
        }
    </script>


</body>
</html>
