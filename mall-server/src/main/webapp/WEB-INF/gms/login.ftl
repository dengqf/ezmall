<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel2  '管理员登录'/>

    <div class="row">
        <div class="col-lg-offset-3 col-lg-8">
            <form class="form-horizontal" action="${context}/gms/login.html" method="post">

                <fieldset>
                <#--<legend>登陆</legend>-->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <label for="username" class="col-lg-2 control-label">用户名：</label>

                                <div class="col-lg-4">
                                    <input class="form-control" name="username" id="username" placeholder="请输入登录用户名"
                                           type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-lg-2 control-label">密码：</label>

                                <div class="col-lg-4">
                                    <input class="form-control" id="password" name="password" placeholder="请输入登录密码"
                                           type="password">

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button class="btn btn-default">取消</button>
                                    <button type="submit" class="btn btn-primary">确定</button>
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

</body>
</html>
