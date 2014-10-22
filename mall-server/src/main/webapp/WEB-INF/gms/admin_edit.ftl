<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
    <script src="${context}/js/jHtmlArea-0.8.min.js"></script>
    <link href="${context}/css/jHtmlArea.css" rel="stylesheet">
</head>
<body>
<div class="container">

<#include "../include/nav.ftl"/>
<#if admin.id??>
    <@m.breadNavLevel2  '资料修改'/>
<#else>
    <@m.breadNavLevel2  '创建用户'/>
</#if>



    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/admin/editAdmin.html" id="queryForm" name="queryForm"
                  enctype="multipart/form-data" method="post">
                <fieldset>


                <#if !admin.id??>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>

                        <div class="col-sm-5">
                            <input placeholder="3 - 15 个半角字符，支持英文、数字" class="form-control" id="username"
                                   value="${admin.username!}"
                                   name="username"
                                   type="text">


                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">密码</label>

                        <div class="col-sm-5">
                            <input placeholder=" 6 - 16 位半角字符（英文、数字、下划线）组成，区分大小写" class="form-control" id="password"
                                   value="" name="password"
                                   type="password">


                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">确认密码</label>

                        <div class="col-sm-5">
                            <input placeholder=" 两次输入密码需一致" class="form-control" id="password2" value=""
                                   name="password2"
                                   type="password">

                        </div>
                    </div>
                </#if>

                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">用户类型:
                        </label>

                        <div class="col-sm-2">
                            <select class="form-control input-sm" id="type" name="type">
                                <option value="">--请选择--</option>
                                <option value="1" <#if admin.type??&& admin.type=="1" >selected=""</#if>>系统管理员</option>
                                <option value="2" <#if admin.type??&& admin.type=="2" >selected=""</#if>>集团管理员</option>
                                <option value="3" <#if admin.type??&& admin.type=="3" >selected=""</#if>>商家管理员</option>

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系人姓名</label>

                        <div class="col-sm-5">
                            <input placeholder="请填写真实姓名，2 - 15 个字符，支持中英文" class="form-control" id="linkman"
                                   value="${admin.linkman!}"
                                   name="linkman" type="text">


                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">公司名称</label>

                        <div class="col-sm-5">
                            <input placeholder="请填写公司名称，2 - 32 个字符，支持中英文" class="form-control" id="company"
                                   value="${admin.company!}"
                                   name="company" type="text">


                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系人邮箱</label>

                        <div class="col-sm-5">
                            <input placeholder="请填写常用邮箱" class="form-control" id="email" value="${admin.email!}"
                                   name="email"
                                   type="text">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">手机号码</label>

                        <div class="col-sm-5">
                            <input placeholder="例如:13245678000" class="form-control" id="mobile"
                                   value="${admin.mobile!}"
                                   name="mobile" type="text">


                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">座机号码</label>

                        <div class="col-sm-5">
                            <input placeholder="例如:010-5801XXX9" class="form-control" id="tel"
                                   value="${admin.tel!}"
                                   name="tel" type="text">


                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系地址</label>

                        <div class="col-sm-5">
                            <input placeholder="不超过60个字符（30个汉字）" class="form-control" id="address"
                                   value="${admin.address!}"
                                   name="address" type="text">


                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-10 col-sm-offset-2">
                            <input type="hidden" name="id" id="id" value="${admin.id!}">
                        <@m.btnReturn/>
                            <button class="btn btn-primary">确定</button>
                        </div>
                    </div>
                </fieldset>
            </form>

        </div>


    </div>
<#include "../include/footer.ftl"/>
</div>
<script language="javascript">
    $('#userDesc').htmlarea();
    <#if admin.id??>
    $().ready(function () {

                jQuery.validator.addMethod("regex", function (value, element, params) {
                    var exp = new RegExp(params); //实例化正则对象，参数为传入的正则表达式
                    return exp.test(value); //测试是否匹配
                });
                   var validator = $("#queryForm").validate({
                    debug: true,
                    errorClass: "text-danger",

                    rules: {

                        email: {
                            required: true,
                            email: true
                        },
                        type: {
                            required: true
                        },
                        linkman: {
                            regex: /^[\u4e00-\u9fa5a-zA-Z]{2,15}$/
                        },
                        mobile: {
                            regex: /^1[3-9]{1}[0-9]{9}$/
                        },
                        address: {
                            regex: /^[\u4e00-\u9fa5a-zA-Z0-9]{5,32}$/
                        }

                    },

                    messages: {
                        email: {
                            required: "请输入Email地址",
                            email: "请输入正确的email地址"
                        },
                        type: {
                            required: "请选择用户类型"
                        },
                        linkman: {
                            regex: "联系人名称须为2 - 15 个字符，支持中英文"
                        },
                        mobile: {
                            regex: "请输入正确的手机号码"

                        },
                        address: {
                            regex: "地址名称须为5 - 30 个字符，支持中英文"
                        }


                    },
                    submitHandler: function () {
                        editAdmin();
                    }

                })
            }
    );
    <#else>
    $().ready(function () {
                jQuery.validator.addMethod("regex", function (value, element, params) {
                    var exp = new RegExp(params); //实例化正则对象，参数为传入的正则表达式
                    return exp.test(value); //测试是否匹配
                });

                var validator = $("#queryForm").validate({
                    debug: true,
                    errorClass: "text-danger",

                    rules: {

                        username: {
                            regex: /^[a-zA-Z0-9]{3,15}$/
                        },
                        password: {
                            required: true,
                            minlength: 6,
                            maxlength: 15
                        },
                        password2: {
                            required: true,
                            equalTo: "#password"
                        },
                        type: {
                            required: true
                        },
                        email: {
                            required: true,
                            email: true
                        },
                        linkman: {
                            regex: /^[\u4e00-\u9fa5a-zA-Z]{2,15}$/
                        },
                        mobile: {
                            regex: /^1[3-9]{1}[0-9]{9}$/
                        },
                        address: {
                            regex: /^[\u4e00-\u9fa5a-zA-Z0-9]{5,32}$/
                        }

                    },

                    messages: {
                        username: {
                            regex: "用户为3 - 15 个半角字符，支持英文、数字"
                        },
                        password: {
                            required: "请输入密码",
                            minlength: "密码长度不能小于6个字符",
                            maxlength: "密码长度不能大于于15个字符"
                        },
                        password2: {
                            required: "请确认输入的密码",
                            equalTo: "两次输入密码不一致"

                        },
                        type: {
                            required: "请选择用户类型"
                        },
                        email: {
                            required: "请输入Email地址",
                            email: "请输入正确的email地址"
                        },
                        linkman: {
                            regex: "联系人名称须为2 - 15 个字符，支持中英文"
                        },
                        mobile: {
                            regex: "请输入正确的手机号码"

                        },
                        address: {
                            regex: "地址名称须为5 - 30 个字符，支持中英文"
                        }


                    },
                    submitHandler: function () {
                        editAdmin();
                    }

                })
            }
    );
    </#if>

    function editAdmin() {
        document.getElementById("queryForm").submit();
    }
</script>

<script type="text/javascript" src="${context}/js/jquery.validate.min.js"></script>
</body>
</html>
