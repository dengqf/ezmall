<!DOCTYPE html>
<html lang="en">
<head>
<#include "./include/head.ftl"/>
</head>
<body>
<div class="container">

<#include "./include/mall_nav.ftl"/>
<#if member.id??>
    <@m.mallNavLevel2  '资料修改'/>
<#else>
    <@m.mallNavLevel2  '用户注册'/>
</#if>



    <div class="row">
        <#if member.id??>
          <div class="col-sm-2">
              <@m.memberMenu/>
          </div>
        </#if>
        <div class="col-sm-10">
            <form class="form-horizontal" action="${context}/editMember.html" id="queryForm" name="queryForm"
                  method="post">
                <fieldset>


                <#if !member.id??>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>

                        <div class="col-sm-5">
                            <input placeholder="3 - 15 个半角字符，支持英文、数字" class="form-control" id="username"
                                   value="${member.username!}"
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
                        <label class="col-sm-2 control-label">联系人姓名</label>

                        <div class="col-sm-5">
                            <input placeholder="请填写真实姓名，2 - 15 个字符，支持中英文" class="form-control" id="linkman"
                                   value="${member.linkman!}"
                                   name="linkman" type="text">


                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系人邮箱</label>

                        <div class="col-sm-5">
                            <input placeholder="请填写常用邮箱" class="form-control" id="email" value="${member.email!}"
                                   name="email"
                                   type="text">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">手机号码</label>

                        <div class="col-sm-5">
                            <input placeholder="例如:13245678000" class="form-control" id="mobile"
                                   value="${member.mobile!}"
                                   name="mobile" type="text">


                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">送货地址</label>

                        <div class="col-sm-5">
                            <input placeholder="不超过60个字符（30个汉字）" class="form-control" id="address"
                                   value="${member.address!}"
                                   name="address" type="text">


                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10 col-sm-offset-2">
                            <input type="hidden" name="id" id="id" value="${member.id!}">
                        <@m.btnReturn/>
                            <button class="btn btn-primary">确定</button>
                        </div>
                    </div>
                </fieldset>
            </form>

        </div>


    </div>
<#include "./include/footer.ftl"/>
</div>
<script language="javascript">

    <#if member.id??>
    $().ready(function () {
                $("#menu_update").attr("class", "list-group-item active");

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
                        editMember();
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
                        editMember();
                    }

                })
            }
    );
    </#if>

    function editMember() {
        $.ajax({
            type: "post",
            url: "${context}/editMember.html",
            data: $("#queryForm").serialize(),
            success: function (messageVo) {
                var obj = JSON.parse(messageVo);
                if (obj.success == true) {
                    if ($("#id").val() == "") {
                        window.location.href = "${context}/member/index.html";
                    }else{
                        alert("修改成功");
                    }

                } else {
                    alert(obj.message);
                }
            }
        });
    }
</script>

<script type="text/javascript" src="${context}/js/jquery.validate.min.js"></script>
</body>
</html>
