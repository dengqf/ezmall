<!DOCTYPE html>
<html lang="en">
<head>
<#include "./include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "./include/mall_nav.ftl"/>
    <ul class="breadcrumb">
        <li>
            <a href="${context}/index.html">首页</a>
        </li>
    <#list parentList as item>
        <li>
            <a href="${context}/${item.no!}/1/goods_cate_list.html">${item.name!}</a>
        </li>
    </#list>

        <li class="active">
        ${category.name!}
        </li>
    </ul>

    <div class="row">
        <div class="col-sm-12">
            <ul class="list-inline">
            <#if pageData??>
                <#list pageData.data as item >
                    <li class="col-sm-3">
                        <a href="${context}/${item.no!}/goods.html">
                            <img src="${context}/${item.goodsPicture!}" width="240" height="240">
                        </a>

                        <div class="caption">
                            <#--<p>${item.name!}</p>-->
                                <p title="${item.name!}" class="text-view">${item.name!}</p>



                            <p>销售价:<span
                                    class="text-danger"><@m.currencyFlag item.currencyType!"0" item.sellPrice!0/></span>
                            </p>
                        </div>

                    </li>

                </#list>
            </#if>

            </ul>

        </div>



    </div>
    <div class="text-right"> <@m.onlyPageInfo/></div>
<#include "./include/footer.ftl"/>
</div>
<script>
    function queryPage(no) {
        window.location.href = '${context}/${category.no}/' + no + '/goods_cate_list.html';
    }
</script>
</body>
</html>
