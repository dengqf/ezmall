<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel2  '用户管理'/>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/admin/adminManager.html" method="post"
                  id="queryForm">
                <fieldset>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                            <@m.inputText '用户名称:' 'username' admin.username!""/>
                            <@m.inputText '商家名称:' 'company' admin.company!""/>
                                <label for="type" class="col-sm-2 control-label">用户类型:
                                </label>

                                <div class="col-sm-2">
                                    <select class="form-control input-sm" id="type" name="type">
                                        <option value="">--请选择--</option>
                                        <option value="1" <#if admin.type??&& admin.type=="1" >selected=""</#if>>超级管理员
                                        </option>
                                        <option value="2" <#if admin.type??&& admin.type=="2" >selected=""</#if>>系统管理员
                                        </option>
                                        <option value="3" <#if admin.type??&& admin.type=="3" >selected=""</#if>>店铺技师
                                        </option>

                                    </select>
                                </div>
                            </div>


                            <div class="form-group ">
                                <div class="col-sm-offset-8 col-sm-4 pull-right">
                                <#--<button class="btn btn-default">Cancel</button>-->
                                    <button class="btn btn-primary">搜索</button>
                                    <a class="btn btn-primary"
                                       href="${context}/gms/admin/toEditAdmin.html">新建</a>
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
                    <th>用户名称</th>
                    <th>商家名称</th>
                    <th class="col-sm-1">手机号码</th>
                    <th>联系人</th>
                    <th class="col-sm-1">用户类型</th>
                    <th>操作</th>

                </tr>
                </thead>
                <tbody>
                    <#if  (pageData.data)?? >
                        <#list pageData.data as item >
                        <tr>
                            <td>
                            ${item.username!}
                            </td>
                            <td>${item.company!}</td>
                            <td>${item.mobile!}</td>
                            <td>${item.linkman!}</td>
                            <td>
                                <#if item.type=="1">
                                    超级管理员
                                <#elseif item.type=="2">
                                    系统管理员
                                <#else>
                                    店铺技师
                                </#if>
                            </td>
                            <td>
                                <a href="${context}/gms/admin/toEditAdmin.html?id=${item.id!}" class="btn btn-primary btn-sm">修改</a>
                            </td>

                        </tr>
                        </#list>
                    <#else>
                    <tr>
                        <td colspan="6">没有相关记录！</td>
                    </tr>
                    </#if>
                </tbody>
            </@m.tableWithPage>
        </#if>
        </div>


    </div>
<#include "../include/footer.ftl"/>
</div>
</body>
</html>
