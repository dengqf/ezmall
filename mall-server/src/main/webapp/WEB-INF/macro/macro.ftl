<#macro inputText labelName paramName paramValue>
<label for="${paramName}" class="col-sm-2 control-label"> ${labelName}
</label>

<div class="col-sm-2">
    <input class="form-control input-sm" name="${paramName}" id="${paramName}" value="${paramValue}" type="text">
</div>
</#macro>

<#macro inputText2 labelName paramName paramValue>
<label for="${paramName}" class="col-sm-1 control-label"> ${labelName}
</label>

<div class="col-sm-2">
    <input class="form-control input-sm" name="${paramName}" id="${paramName}" value="${paramValue}" type="text">
</div>
</#macro>

<#macro inputDate labelName startName startValue endName endValue>
<label class="col-sm-2 control-label">${labelName}
</label>
<div class="col-sm-4">
    <div class="row">
        <div class="col-sm-5">
            <input id="${startName}" type="text" name="${startName}" value="${startValue}"
                   onClick="WdatePicker()" class="form-control  input-sm"/>
        </div>
        <div class="col-sm-1 ">
            <label class="control-label">至</label>
        </div>
        <div class="col-sm-5 ">
            <input id="${endName}" type="text" name="${endName}" value="${endValue}"
                   onClick="WdatePicker()" class="form-control  input-sm"/>
        </div>
    </div>
</div>
</#macro>

<#macro inputDate2 labelName startName startValue endName endValue>
<label class="col-sm-1 control-label">${labelName}
</label>
<div class="col-sm-4">
    <div class="row">
        <div class="col-sm-5">
            <input id="${startName}" type="text" name="${startName}" value="${startValue}"
                   onClick="WdatePicker()" class="form-control  input-sm"/>
        </div>
        <div class="col-sm-1 ">
            <label class="control-label">至</label>
        </div>
        <div class="col-sm-5 ">
            <input id="${endName}" type="text" name="${endName}" value="${endValue}"
                   onClick="WdatePicker()" class="form-control  input-sm"/>
        </div>
    </div>
</div>
</#macro>

<#--带分页的TABLE-->
<#macro tableWithPage formId>
<table class="table table-bordered  table-hover table-striped small " id="gmsTable">
    <#nested>
</table>
<script>
    var formId = "${formId}";
    var totalRows = '${pageData.rowCount}';
    var pageSize = ${pageData.pageSize};
    /**
     *检查是否含有财务千分位分隔符
     *当totalRows>1000时默认格式会加财务千分位逗号
     *例如11,628，在js当做字符串处理
     */
    function ck(txt) {
        if (txt.indexOf(',') > -1) {
            return true;
        }
        return false;
    }

    /**
     *以逗号进行字符串分割
     *返回去掉逗号的字符串
     *例如11,628->11628
     */
    function split(datastr) {
        var arr = new Array();
        var str = "";
        arr = datastr.split(",");
        for (i = 0; i < arr.length; i++) {
            str += arr[i];
        }
        return str;
    }
    /**
     *如果totalRows>=1000,则去除财务分隔符逗号
     *否则转换为数字类型
     */
    if (ck(totalRows)) {
        totalRows = split(totalRows);
    } else {
        totalRows = Number(totalRows);
    }

    function queryPage(pageNo, size) {
        var pageForm = (formId && formId != "") ? document.getElementById(formId) : document.forms[0];
        if (size >= 10) {
            var artionUrl = pageForm.getAttribute("action");
            var pageInput = document.createElement("input");
            pageInput.setAttribute("type", "hidden");
            pageInput.setAttribute("name", "pageSize");
            pageInput.setAttribute("value", size);
            pageForm.appendChild(pageInput);

        } else {
            if (isNaN(pageSize)) {
                alert('每页条数只能为数字');
                return;
            }
            var totalPage = Math.ceil(totalRows / pageSize);
            var toPage = pageNo;
            if (pageNo == 0) {
                toPage = document.getElementById("query.page").value;
                if (isNaN(toPage) || toPage <= 0) {
                    alert("请输入大于0的整数.");
                    return;
                }

                if (toPage > totalPage) {
                    alert("没有当前页数");
                    return;
                }
            }
            //校验是跳转页是否在记录有效范围内
            //取大于等于总页数的值
            if (toPage > totalPage) {
                alert("已经到最后一页");
                return;
            }
            var arr = pageForm.elements;
            var flag = false;
            for (var i = 0, j = arr.length; i < j; i++) {
                if (arr[i].getAttribute("name") == "query.page") {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                var artionUrl = pageForm.getAttribute("action");
                // 				if(artionUrl.indexOf("?")>0){
                // 					artionUrl = artionUrl + "&page="+toPage;
                // 				}else{
                // 					artionUrl = artionUrl + "?page="+toPage;
                // 				}
                // 				pageForm.setAttribute("action",artionUrl);

                var pageInput = document.createElement("input");
                pageInput.setAttribute("type", "hidden");
                pageInput.setAttribute("name", "page");
                pageInput.setAttribute("value", toPage);
                pageForm.appendChild(pageInput);

                var pageInput1 = document.createElement("input");
                pageInput1.setAttribute("type", "hidden");
                pageInput1.setAttribute("name", "pageSize");
                pageInput1.setAttribute("value", pageSize);
                pageForm.appendChild(pageInput1);
            }
        }
        pageForm.submit();
    }

</script>
<div>
    <#setting number_format="0">
    <#if pageData?? && pageData.data?? && (pageData.rowCount > 0)>
    <@onlyPageInfo/>
</#if>
</div>
</#macro>

<#--不带分页TABLE-->
<#macro table>
<table class="table table-bordered  table-hover table-striped small " id="gmsTable">
    <#nested>
</table>
</#macro>

<#macro  onlyPageInfo>
<ul class="pagination">

    <#if (pageData.pageCount > 1) >
        <#if (pageData.hasPrevious == false) >
            <li class="prev disabled"><a href="#">上一页</a></li>
        <#else>
            <li><a href="javascript:queryPage(${pageData.pageNo-1})" class="prev" title="上一页">上一页</a></li>
        </#if>

        <#if (pageData.pageCount < 10) >
            <#list 1..(pageData.pageCount) as row>

                <#if pageData.pageNo == row >
                    <!-- 选中时的样式 -->
                    <li class="disabled"><a href="javascript:queryPage(${row})" class="curr">${row}</a></li>
                <#else>
                    <li><a href="javascript:queryPage(${row})">${row}</a></li>
                </#if>
            </#list>

        <#elseif ((pageData.pageCount - pageData.pageNo) < 5) >
            <#list 1..9 as row>
                <#if ((pageData.pageCount - pageData.pageNo) == (9- row)) >
                    <!-- 选中时的样式 -->
                    <li class="disabled"><a href="javascript:queryPage(${pageData.pageCount - 9 + row})"
                                            class="curr"> ${pageData.pageCount  - 9 + row}</a></li>
                <#else>
                    <li>
                        <a href="javascript:queryPage(${pageData.pageCount - 9 + row})"> ${pageData.pageCount  - 9 + row}</a>
                    </li>
                    <!-- 默认的样式 -->
                </#if>
            </#list>

        <#else>
            <#list 1..10 as row>
                <#if (row == 8) >
                    <li><a> ...</a></li>
                <#elseif (row == 9) >
                    <li><a href="javascript:queryPage(${pageData.pageCount -1 })"> ${pageData.pageCount -1}</a>
                    </li>
                <#elseif (row == 10) >
                    <li><a href="javascript:queryPage(${pageData.pageCount})"> ${pageData.pageCount}</a></li>
                <#else>

                    <#if (pageData.pageNo < 5) >

                        <#if (pageData.pageNo == row) >
                            <!-- 选中时的样式 -->
                            <li class="disabled"><a href="javascript:queryPage(${row});"
                                                    class="curr"> ${row}</a></li>
                        <#else>
                            <li><a href="javascript:queryPage(${row});"> ${row}</a></li>
                            <!-- 默认的样式 -->
                        </#if>

                    <#else>

                        <#if (row == 4) >
                            <!-- 选中时的样式 -->
                            <li class="disabled"><a href="javascript:queryPage(${pageData.pageNo-4+row})"
                                                    class="curr"> ${pageData.pageNo-4+row}</a></li>
                        <#else>
                            <!-- 默认的样式 -->
                            <li>
                                <a href="javascript:queryPage(${pageData.pageNo-4+row})"> ${pageData.pageNo-4+row}</a>
                            </li>
                        </#if>

                    </#if>
                </#if>
            </#list>
        </#if>
        <#if (pageData.hasNext == false) >
            <li class="next disabled"><a href="#">下一页</a></li>
        <#else>
            <li><a href="javascript:queryPage(${pageData.pageNo+1})" class="next" title="下一页">下一页</a></li>
        </#if>  &nbsp;&nbsp;
        <li>
            <input class="" size="2" id="query.page" style="width: 30px;" name="query.page" type="text"
                   maxlength="5"
                   value="${pageData.pageNo}" class="pagenum" onkeyup="value=value.replace(/[^\d]/g,'')"/>
            &nbsp;&nbsp;
            <button class="btn btn-primary" onClick="queryPage(0);">跳转</button>

        </li>
    </#if>

    <#if pageData ?? && pageData.rowCount ??>
        <li>&nbsp;&nbsp;&nbsp;&nbsp;总记录数:</li>
        <li class="text-danger">${pageData.rowCount}</li>


    </#if>
</ul>
</#macro>
<#--导航条-->
<#macro breadNavLevel2 name>
<ul class="breadcrumb">
    <li>
        <a href="${context}/gms/index.html">首页</a>
    </li>
    <li class="active">
    ${name}
    </li>
</ul>
</#macro>



<#--导航条-->
<#macro breadNavLevel3  link linkName name>
<ul class="breadcrumb">
    <li>
        <a href="${context}/gms/index.html">首页</a>
    </li>
    <li>
        <a href="${link}">${linkName}</a>
    </li>
    <li class="active">
    ${name}
    </li>
</ul>
</#macro>

<#--商城导航条-->
<#macro mallNavLevel2 name>
<ul class="breadcrumb">
    <li>
        <a href="${context}/index.html">首页</a>
    </li>
    <li class="active">
    ${name}
    </li>
</ul>
</#macro>

<#--商城导航条-->
<#macro mallNavLevel3 link linkName name>
<ul class="breadcrumb">
    <li>
        <a href="${context}/index.html">首页</a>
    </li>
    <li>
        <a href="${link}">${linkName}</a>
    </li>
    <li class="active">
    ${name}
    </li>
</ul>
</#macro>


<#macro formMust>
<span style="color: red">&nbsp;*&nbsp;</span>
</#macro>

<#macro btnReturn>
<#--<button class="btn btn-default" onclick="history.go(-1);">返回</button>-->
<span class="btn btn-default" onclick="history.go(-1);">返回</span>
</#macro>

<#macro btnReg>
<#--<button class="btn btn-default" onclick="history.go(-1);">返回</button>-->
<a href="${context}/toEditMember.html" class="btn btn-warning">注册</a>
</#macro>

<#macro loginMember>
<form class="form-horizontal" action="${context}/gms/login.html" id="loginForm">

    <fieldset>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label for="username" class="col-sm-3 control-label">用户名：</label>

                    <div class="col-sm-5">
                        <input class="form-control" name="username" id="username"
                               placeholder="请输入登录用户名"
                               type="text">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">密码：</label>

                    <div class="col-sm-5">
                        <input class="form-control" id="password" name="password"
                               placeholder="请输入登录密码"
                               type="password">

                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-7 col-sm-offset-2">

                    <#--<button type="submit" class="btn btn-primary">取消</button>-->
                        <span class="btn btn-primary" onclick="loginMember()">确定</span>
                        <@btnReg/>

                    </div>
                </div>
            </div>
        </div>

    </fieldset>
</form>


</#macro>


<#macro reportCategory categoryList resultList>
<label for="structNames" class="col-sm-1 control-label">分类:
</label>

<div class="col-sm-2">

    <select name="structNames" id="structNames" class="multiselect" multiple="multiple" size="5" title="点击选择分类">
        <option value=""></option>
        <#list categoryList as category>
            <option value="${category.structName!}" <#list resultList as obj><#if (obj==category.structName)>selected="true"</#if></#list>>${category.catName}</option>
        </#list>
    </select>
</div>

</#macro>

<#macro brands list result>
<label for="brandNo" class="col-sm-2 control-label">品牌:
</label>

    <@brandOption list result/>
</#macro>

<#macro brands2 list result>
<label for="brandNo" class="col-sm-1 control-label">品牌:
</label>
    <@brandOption list result/>
</#macro>

<#macro brandOption list result>
<div class="col-sm-2">
    <select class="form-control input-sm" id="brandNo" name="brandNo">
        <option value="">--请选择--</option>
    </select>
</div>
<script>
    $(function () {

        $.ajax({
            type: "POST",
            url: "${context}/gms/brand/allBrand.html",
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    $.each(messageVo.obj, function (index, content) {
                        $("#brandNo").append("<option value='" + content.no + "'>" + content.name + "</option>");
                    });
                    $("#brandNo ").val(${result});
                } else {
                    alert(messageVo.message);
                    //  document.getElementById("btnSubmit").innerHTML = "保存";

                }
            }
        });
    })
</script>
</#macro>

<#macro categorySelect firstNo secondNo thirdNo>
<label for="firstCategory" class="col-sm-2 control-label">一级分类:
</label>
<div class="col-sm-2">
    <select class="form-control input-sm" id="firstCategory" name="firstCategory">
        <option value="">--请选择--</option>
    </select>
</div>
<label for="secondCategory" class="col-sm-2 control-label">二级分类:
</label>
<div class="col-sm-2">
    <select class="form-control input-sm" id="secondCategory" name="secondCategory">
        <option value="">--请选择--</option>
    </select>
</div>
<label for="thirdCategory" class="col-sm-2 control-label">三级分类:
</label>
<div class="col-sm-2">
    <select class="form-control input-sm" id="thirdCategory" name="thirdCategory">
        <option value="">--请选择--</option>
    </select>
</div>
<script>
    $(function () {

        $.ajax({
            type: "POST",
            url: "${context}/gms/category/categoryListByParentNo.html",
            data: {
                "parentNo": "0000"
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    $.each(messageVo.obj, function (index, content) {
                        $("#firstCategory").append("<option value='" + content.no + "'>" + content.name + "</option>");
                    });
                    $("#firstCategory").val("${firstNo!}");
                } else {
                    alert(messageVo.message);

                }
            }
        });

    <#--加载第一级-->
        <#if firstNo??&&firstNo!="">

            $.ajax({
                type: "POST",
                url: "${context}/gms/category/categoryListByParentNo.html",
                data: {
                    "parentNo": "${firstNo}"
                },
                dataType: "json",
                success: function (messageVo) {
                    if (messageVo.success == true) {
                        $.each(messageVo.obj, function (index, content) {
                            $("#secondCategory").append("<option value='" + content.no + "'>" + content.name + "</option>");
                        });
                        $("#secondCategory").val("${secondNo!}");
                    } else {
                        alert(messageVo.message);

                    }
                }
            });

        <#--判断二级分类是否存在-->
            <#if secondNo??&&secondNo!="">


                $.ajax({
                    type: "POST",
                    url: "${context}/gms/category/categoryListByParentNo.html",
                    data: {
                        "parentNo": "${secondNo}"
                    },
                    dataType: "json",
                    success: function (messageVo) {
                        if (messageVo.success == true) {
                            $.each(messageVo.obj, function (index, content) {
                                $("#thirdCategory").append("<option value='" + content.no + "'>" + content.name + "</option>");
                            });
                            $("#thirdCategory").val("${thirdNo!}");
                        } else {
                            alert(messageVo.message);

                        }
                    }
                });

            <#--$("#thirdCategory").val("${thirdNo!}");-->

            </#if>
        </#if>
//          alert("");


    });
    function chooseCate(a, b, c) {

    }
    $("#firstCategory").change(function () {
        var firstNo = $("#firstCategory").val();
        //清楚二级分类的OPTION
        cleanSecondCategory();
        if (firstNo != "") {
            //取得二级分类
            $.ajax({
                type: "POST",
                url: "${context}/gms/category/categoryListByParentNo.html",
                data: {
                    "parentNo": firstNo
                },
                dataType: "json",
                success: function (messageVo) {
                    if (messageVo.success == true) {
                        $.each(messageVo.obj, function (index, content) {
                            $("#secondCategory").append("<option value='" + content.no + "'>" + content.name + "</option>");
                        });

                    } else {
                        alert(messageVo.message);

                    }
                }
            });
        } else {
//            alert("null--");
        }
    });

    $("#secondCategory").change(function () {
        var parentNo = $("#secondCategory").val();
        //清楚二级分类的OPTION
        cleanThirdCategory();
        if (parentNo != "") {
            //取得二级分类
            $.ajax({
                type: "POST",
                url: "${context}/gms/category/categoryListByParentNo.html",
                data: {
                    "parentNo": parentNo
                },
                dataType: "json",
                success: function (messageVo) {
                    if (messageVo.success == true) {
                        $.each(messageVo.obj, function (index, content) {
                            $("#thirdCategory").append("<option value='" + content.no + "'>" + content.name + "</option>");
                        });

                    } else {
                        alert(messageVo.message);

                    }
                }
            });
        } else {
//            alert("null--");
        }
    });


    function cleanSecondCategory() {
        document.getElementById("secondCategory").innerHTML = "<option value=''>--请选择--</option>";
        document.getElementById("thirdCategory").innerHTML = "<option value=''>--请选择--</option>";
    }
    function cleanThirdCategory() {
        document.getElementById("thirdCategory").innerHTML = "<option value=''>--请选择--</option>";
    }
</script>
</#macro>

<#macro goodsStatusSelect  result>
<label for="status" class="col-sm-2 control-label">状态:
</label>

<div class="col-sm-2">
    <select class="form-control input-sm" id="status" name="status">
        <option value="">--请选择--</option>

        <option value="0" <#if "0"==result>selected="true" </#if> >新建</option>
        <option value="1" <#if "1"==result>selected="true" </#if> >上架</option>
        <option value="2" <#if "2"==result>selected="true" </#if> >下架</option>

    </select>
</div>
</#macro>

<#macro goodsStatusText  result>
    <#if result=="0">
    新建
    <#elseif result=="1">
    上架
    <#elseif result=="2">
    下架
    <#else>
    其他
    </#if>
</#macro>

<#macro currencyTypeSelect result>
<label for="currencyType" class="col-sm-2 control-label">货币类型:
</label>

<div class="col-sm-2">
    <select class="form-control input-sm" id="currencyType" name="currencyType">
        <option value="">--请选择--</option>

        <option value="CNY" <#if "CNY"==result>selected="true" </#if> >人民币</option>
        <option value="USD" <#if "USD"==result>selected="true" </#if> >美元</option>
        <option value="GBP" <#if "GBP"==result>selected="true" </#if> >英镑</option>
        <option value="KER" <#if "KER"==result>selected="true" </#if> >韩元</option>
        <option value="JPY" <#if "JPY"==result>selected="true" </#if> >日元</option>
        <option value="HKD" <#if "HKD"==result>selected="true" </#if> >港币</option>
        <option value="EUR" <#if "EUR"==result>selected="true" </#if> >欧元</option>

    </select>
</div>
</#macro>

<#--市场价，销售价，货币类型，折合RMB-->
<#macro priceInfo marketPrice sellPrice currencyType economy>
    <#local y = ((goods.marketPrice!0)-(goods.sellPrice!0))>
<p>市场价：<span class="text-muted"><small> ${currencyType} ${goods.marketPrice!0} 元</small></span></p>
<p> 优惠价：<span class="text-danger"><strong>${currencyType} ${goods.sellPrice!0} 元</strong></span><span
        class="text-muted">（${((goods.sellPrice!0)/(goods.marketPrice!1))*10}
    折　为您节省：${currencyType} ${y!0} 元 <#if currencyType!="0">
        ,折合人民币 :${economy}元</#if> ）</span>
</p>
</#macro>

<#--货币类型(0人民币，1美元，2英镑，3韩元，4日元，5港币，6欧元)-->
<#macro currencyType result>
    <#if result=="0">
    人民币
    <#elseif result=="1">
    美元
    <#elseif result=="2">
    英镑
    <#elseif result=="3">
    韩元
    <#elseif result=="4">
    日元
    <#elseif result=="5">
    港币
    <#elseif result=="6">
    欧元
    <#else>
    其他
    </#if>
</#macro>

<#--货币标记-->
<#macro currencyFlag type price>
    ${type!"CNY"} ${price!0} 元
</#macro>

<#--货币汇率-->
<#macro currencyRate result>
    <#if result=="0">
    1
    <#elseif result=="1">
    6.24
    <#elseif result=="2">
    10.6
    <#elseif result=="3">
    0.006
    <#elseif result=="4">
    0.061
    <#elseif result=="5">
    0.8
    <#elseif result=="6">
    8.46
    <#else>
    1
    </#if>
</#macro>

<#macro delById id action>
<span class="btn btn-sm btn-danger" onclick="delObjectById('${id}')">删除</span>
<script>
    function delObjectById(id) {
        $.ajax({
            type: "POST",
            url: "${action}",
            data: {
                "id": id
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    alert("删除成功");
                    $("#" + id).attr("class", "hidden");
                } else {

                    alert(messageVo.message);

                }
            }
        });
    }
</script>
</#macro>


<#macro delLinkById id action>
<a heref="#" onclick="delObjectById('${id}')">删除</a>
<script>
    function delObjectById(id) {
        $.ajax({
            type: "POST",
            url: "${action}",
            data: {
                "id": id
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    alert("删除成功");
                    $("#" + id).attr("class", "hidden");
                } else {

                    alert(messageVo.message);

                }
            }
        });
    }
</script>
</#macro>

<#macro loginModal>
<div id="loginModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">


            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <div class="row">
                    <div class="col-sm-12">
                        <@loginMember/>
                        <input type="hidden" id="flag" value="">
                    </div>
                </div>
            </div>

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>
    function loginMember() {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username == "" || password == "") {
            alert("用户名或密码不能为空");
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

                if (messageVo.success) {
                    window.location.reload();
                } else {
                    alert(messageVo.message);
                }

            }
        });
    }
</script>
</#macro>

<#macro shopCart>
<div id="shopCartModal" class="modal fade">
    <div id="dialogWidth" class="modal-dialog">
        <div class="modal-content">


            <div id="msgText" class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4>商品已经添加到购物车</h4>

                <p id="shopCartMessage"></p>

                <p><a href="${context}/toShopCart.html" class="btn btn-primary ">去结算</a>
                    <button class="btn btn-link" data-dismiss="modal">继续购物</button>
                </p>
            </div>

        </div>
    </div>
</div>
<script>
    function addToShopCart(goodsNo, amount) {

        $.ajax({
            type: "POST",
            url: "${context}/addToShopCart.html",
            data: {
                "no": goodsNo,
                "amount": amount
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    //购物车提示
                    document.getElementById("shopCartMessage").innerHTML = messageVo.message;
                    $("#shopCartModal").modal('show');

                } else {

                    alert(messageVo.message);

                }
            }
        });

    }
</script>
</#macro>
<#macro viewFriend>
<div id="friendInfoModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">查看好友资料</h4>
            </div>
            <div class="modal-body">
                <p id="friendInfo">

                </p>


            </div>

        </div>
    </div>
</div>
<script>
    function getFriend(username) {
        $.ajax({
            type: "POST",
            url: "${context}/member/viewFriend.html",
            data: {
                "username": username
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success) {
                    var obj = messageVo.obj;
                    document.getElementById("friendInfo").innerHTML = "联系人名：" + messageVo.obj.linkman + "<BR>手机号码：" + obj.mobile + "<BR>" + "电子邮箱：" + obj.email;
                    $("#friendInfoModal").modal('show');
                } else {
                    alert(messageVo.message);
                }
            }
        });
    }
</script>
</#macro>
<#macro memberMenu>
<div class="list-group">
    <a href="${context}/member/orderList.html" class="list-group-item " id="menu_order">
        我的订单
    </a>
    <a href="${context}/member/friendOrderFromMe.html" class="list-group-item" id="friendOrder_fromMe">转发的订单</a>
    <a href="${context}/member/friendOrderToMe.html" class="list-group-item" id="friendOrder_toMe">好友的订单</a>
    <a href="${context}/toShopCart.html" class="list-group-item">我的购物单</a>
    <a href="${context}/member/favoriteList.html" class="list-group-item" id="menu_favorite">我的收藏夹</a>
    <a href="${context}/member/friendInfo.html" class="list-group-item" id="menu_friend">我的好友</a>
    <a href="${context}/member/goodsComment.html" class="list-group-item" id="menu_comment">商品点评/晒单 </a>

    <a href="${context}/toEditMember.html?id=${EZMALL_MEMBER.id!}" class="list-group-item" id="menu_update">资料修改 </a>
    <a href="${context}/member/toUpdatePassword.html" class="list-group-item" id="menu_password">密码修改 </a>

</div>
</#macro>


<#macro orderStatusSelect  result>
<label for="orderStatus" class="col-sm-1 control-label">状态:
</label>

<div class="col-sm-2">
    <select class="form-control input-sm" id="orderStatus" name="orderStatus">
        <option value="">--请选择--</option>

        <option value="0" <#if "0"==result>selected="true" </#if> >未付款</option>
        <option value="1" <#if "1"==result>selected="true" </#if> >已付款</option>
        <option value="2" <#if "2"==result>selected="true" </#if> >已发货</option>
        <option value="3" <#if "3"==result>selected="true" </#if> >已收货</option>
        <option value="99" <#if "99"==result>selected="true" </#if> >已完成</option>
        <option value="-1" <#if "-1"==result>selected="true" </#if> >已撤单</option>
        <option value="-2" <#if "-2"==result>selected="true" </#if> >申请退款</option>
        <option value="-3" <#if "-3"==result>selected="true" </#if> >已退款</option>
        <option value="-4" <#if "-4"==result>selected="true" </#if> > 已关闭</option>
        <option value="-9" <#if "-9"==result>selected="true" </#if> > 已失效</option>

    </select>
</div>
</#macro>


<#macro merchantOrderStatusSelect  result>
<label class="col-sm-1 control-label">状态:
</label>

<div class="col-sm-2">
    <select class="form-control input-sm" id="status" name="status">
        <option value="">--请选择--</option>
        <option value="2" <#if "2"==result>selected="true" </#if> >已发货</option>
        <option value="3" <#if "3"==result>selected="true" </#if> >已收货</option>
        <option value="99" <#if "99"==result>selected="true" </#if> >已完成</option>
        <option value="-3" <#if "-3"==result>selected="true" </#if> >已退款</option>
        <option value="-4" <#if "-4"==result>selected="true" </#if> > 已关闭</option>

    </select>
</div>
</#macro>

<#macro orderStatusText  result>
    <#if result=="0">
    未付款
    <#elseif result=="1">
    已付款
    <#elseif result=="2">
    已发货
    <#elseif result=="3">
    已收货
    <#elseif result=="10">
    订单转发
    <#elseif result=="99">
    已完成
    <#elseif result=="-1">
    已撤单
    <#elseif result=="-2">
    申请退款
    <#elseif result=="-3">
    已退款
    <#elseif result=="-4">
    已关闭
    <#elseif result=="-9">
    已失效
    <#else>
    其他
    </#if>
</#macro>



<#macro  goodsNameView name>
    <#if name?length lt 16>
    ${name!}
    <#else>
    ${name?substring(0,16)+"……"}
    </#if>
</#macro>

<#--好友状态-->
<#macro friendStatusText  result>
    <#if result=="0">
    已申请
    <#elseif result=="1">
    已同意
    <#elseif result=="-1">
    已拒绝
    <#elseif result=="-2">
    黑名单
    <#else>
    其他
    </#if>
</#macro>

<#--好友订单状态-->
<#macro friendOrderStatusText  result>
    <#if result=="0">
    已申请
    <#elseif result=="1">
    已同意
    <#elseif result=="-1">
    已拒绝
    <#else>
    其他
    </#if>
</#macro>
<#macro br size>
    <#list 0..size as i>
    <br>
    </#list>
</#macro>






