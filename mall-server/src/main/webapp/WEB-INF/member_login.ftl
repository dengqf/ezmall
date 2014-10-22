<!DOCTYPE html>
<html lang="en">
<head>
<#include "./include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "./include/mall_nav.ftl"/>
<@m.mallNavLevel2  '用户登录'/>

    <div class="row">
        <div class="col-sm-offset-1 col-sm-8">
        <@m.loginMember/>

        </div>
    </div>
<#include "./include/footer.ftl"/>
</div>
<script>
    function loginMember() {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username == "" || password == "") {
            alert("用户名或密码不能为空");
            return;
        }
        $.ajax({
            type: "POST",
            url: "${context}/loginMember.html",
            data: {
                "username": username,
                "password": password
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    //购物车提示已经存在
                    window.location.href = "${context}/member/index.html";

                } else {
                    alert(messageVo.message);

                }
            }
        });
    }
</script>
</body>
</html>
