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

<@m.breadNavLevel2  '资料修改'/>




    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/admin/editAdmin.html" id="queryForm" name="queryForm"
                  enctype="multipart/form-data" method="post">
                <fieldset>


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
                            <input placeholder="请填写商家名称，2 - 32 个字符，支持中英文" class="form-control" id="company"
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
                        editAdmin();
                    }

                })
            }
    );


    function editAdmin() {
        document.getElementById("queryForm").submit();
    }
</script>

<script type="text/javascript" src="${context}/js/jquery.validate.min.js"></script>
</body>
</html>
