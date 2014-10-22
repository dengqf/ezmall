<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/mall_nav.ftl"/>
<@m.mallNavLevel2  '密码修改'/>

    <div class="row">
        <div class="col-sm-2">

           <@m.memberMenu/>
        </div>
        <div class="col-sm-10">
            <form class="form-horizontal" action="" id="signupForm">

                <fieldset>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label for="password" class="col-sm-3 control-label">旧密码：</label>

                                <div class="col-sm-5">
                                    <input class="form-control" name="password" id="password"
                                           placeholder="请输入当前用户密码"
                                           type="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="newPassword" class="col-sm-3 control-label">新密码：</label>

                                <div class="col-sm-5">
                                    <input class="form-control" id="newPassword" name="newPassword"
                                           placeholder="请输入需要更改的密码"
                                           type="password">

                                </div>
                            </div>
                            <div class="form-group">
                                <label for="newPassword2" class="col-sm-3 control-label">确认新密码：</label>

                                <div class="col-sm-5">
                                    <input class="form-control" id="newPassword2" name="newPassword2"
                                           placeholder="请确认新密码"
                                           type="password">

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-8 ">
                                <@m.btnReturn/>
                                    <button class="btn btn-primary">修改</button>


                                </div>
                            </div>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
    </div>
<#include "../include/footer.ftl"/>
</div>
<script>
    $().ready(function () {
        $("#menu_password").attr("class", "list-group-item active");

        $("#signupForm").validate({
            errorClass: "text-danger",
            rules: {

                password: {
                    required: true,
                    minlength: 6
                },
                newPassword: {
                    required: true,
                    minlength: 6
                },
                newPassword2: {
                    required: true,
                    equalTo: "#newPassword"
                }

            },

            messages: {

                password: {
                    required: "请输入旧密码",
                    minlength: "密码长度不能小于6个字 符"
                },
                newPassword: {
                    required: "请输入新密码",
                    minlength: "密码长度不能小于6个字 符"
                },
                newPassword2: {
                    required: "新密码不能为空",
                    minlength: "密码长度不能小于6个字 符",
                    equalTo: "两次输入密码不一致不一致"
                }


            },
            submitHandler: function () {
                updatePassword();
            }

        })
    });

    function updatePassword() {
        $.ajax({
            type: "post",
            url: "${context}/member/updatePassword.html",
            data: $("#signupForm").serialize(),
            success: function (messageVo) {
                var obj = JSON.parse(messageVo);
                if (obj.success == true) {
                    alert("修改成功");
                } else {
                    alert(obj.message);
                }
            }
        });
    }
</script>
<script src="${context}/js/jquery.validate.min.js" type="text/javascript"></script>
</body>
</html>
