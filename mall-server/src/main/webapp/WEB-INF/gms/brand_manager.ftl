<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel2  '品牌管理'/>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/brand/brandManager.html" method="post"
                  id="queryForm">
                <fieldset>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                            <@m.inputText '品牌中文名:' 'name' brand.name!""/>
                            <@m.inputText '品牌英文名:' 'englishName' brand.englishName!""/>

                            </div>


                            <div class="form-group ">
                                <div class="col-sm-offset-8 col-sm-4 pull-right">
                                <#--<button class="btn btn-default">Cancel</button>-->
                                    <button class="btn btn-primary" onclick="doSearch()">搜索</button>
                                    <a class="btn btn-primary"
                                       href="${context}/gms/brand/toBrandEdit.html">新建</a>
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
                    <th>品牌名</th>
                    <th>品牌编号</th>
                    <th>品牌英文名</th>
                    <th>品牌字母</th>
                    <th>操作</th>

                </tr>
                </thead>
                <tbody>
                    <#if  (pageData.data)?? >
                        <#list pageData.data as item >
                        <tr id="${item.id!}">
                            <td>
                                <a href="${context}/gms/brand/toBrandEdit.html?id=${item.id!}">${item.name!}</a>
                            </td>
                            <td>${item.no!}</td>
                            <td>${item.englishName!}</td>
                            <td>${item.brandIndex!}</td>
                            <td><a class="btn btn-primary btn-sm" href="${context}/gms/brand/toBrandEdit.html?id=${item.id!}" >修改</a>
                                <@m.delById item.id "${context}/gms/brand/delBrand.html" />
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


    </div>
<#include "../include/footer.ftl"/>
</div>
</body>
</html>
