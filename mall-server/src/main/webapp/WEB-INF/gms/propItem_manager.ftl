<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel2  '属性管理'/>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/prop/propItemManager.html" method="post"
                  id="queryForm">
                <fieldset>
                    <div class="row">
                        <div class="col-sm-12">

                            <div class="form-group">
                            <@m.categorySelect "${vo.firstCategory!}" "${vo.secondCategory!}" "${vo.thirdCategory!}"/>

                            </div>

                            <div class="form-group ">
                                <div class="col-sm-offset-10 col-sm-2 pull-right">
                                <#--<button class="btn btn-default">Cancel</button>-->
                                    <button class="btn btn-primary">搜索</button>
                                    <a class="btn btn-primary"
                                       href="${context}/gms/prop/toPropItemEdit.html">新建</a>
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
                    <th class="col-sm-2">属性名称</th>
                    <th>默认值</th>
                    <th class="col-sm-1">属性类型</th>

                    <th class="col-sm-2">操作</th>

                </tr>
                </thead>
                <tbody>
                    <#if  (pageData.data)?? >
                        <#list pageData.data as item >
                        <tr id="${item.id!}">
                            <td> ${item.name!}</td>
                            <td> ${item.defaultValue!}</td>
                            <td>
                                <#if item.itemType??>
                                    <#if item.itemType==0>
                                        文本
                                    <#elseif item.itemType==1>
                                        单选
                                    <#elseif item.itemType==2>
                                        多选
                                    <#else>
                                        文本
                                    </#if>
                                <#else>
                                    文本
                                </#if>
                            </td>

                            <td>
                                <@m.delById item.id "${context}/gms/prop/delPropItem.html" />
                                <a class="btn btn-primary btn-sm"
                                   href="${context}/gms/prop/toPropItemEdit.html?id=${item.id!}">编辑</a>
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


    </div>
<#include "../include/footer.ftl"/>
</div>
</body>
</html>
