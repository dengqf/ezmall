<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<@m.breadNavLevel2  '订单管理'/>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/order/orderManager.html" method="post"
                  id="queryForm">
                <fieldset>
                    <div class="row">
                        <div class="col-sm-12">


                            <div class="form-group">
                            <@m.inputText2 '下单用户:' 'userName' vo.userName!""/>
                            <@m.inputText2 '订单编号:' 'orderNo' vo.orderNo!""/>
                             <@shiro.hasRole name="system">
                                <@m.inputText2 '所属商家:' 'merchantName' vo.merchantName!""/>
                            </@shiro.hasRole>
                            </div>
                            <div class="form-group">
                            <@m.orderStatusSelect vo.orderStatus!""/>
                            <@m.inputDate2 '下单日期:' 'startCreateDate' vo.startCreateDate!""  'endCreateDate' vo.endCreateDate!""/>
                                <div class="col-sm-2 ">
                                    <button class="btn btn-primary">搜索</button>
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
                    <th class="col-sm-1">订单编号</th>
                    <th class="col-sm-7">订单详情</th>
                    <th class="col-sm-2">订单金额</th>
                    <th class="col-sm-1">状态</th>
                    <th class="col-sm-1">操作</th>

                </tr>
                </thead>
                <tbody>
                    <#if  (pageData.data)?? >
                        <#list pageData.data as item >
                        <tr id="${item.order.id!}">
                            <td>
                              ${item.order.no!} </td>
                            <td>


                                <div class="row">
                                  <div class="col-sm-12">
                                      <ul class="list-inline">
                                          <#list item.subOrders as sub>
                                              <li class="col-sm-1">
                                                  <a href="${context}/${sub.goodsNo!}/goods.html" target="_blank"
                                                     title="${sub.goodsName!}"><img
                                                          src="${context}/${sub.goodsPicture!}" width="55"
                                                          height="55"/></a></li>


                                          </#list>
                                      </ul>
                                  </div>
                                </div>
                         <p>下单日期:${(item.order.createDate?string("yyyy-MM-dd"))!}</p>
                            </td>
                            <td>
                                <p>订单金额:${item.order.totalPrice!}元</p>
                                <p>运费合计:${item.order.totalExpressPrice!}元</p>
                                <p>费用合计:${item.order.totalPrice+item.order.totalExpressPrice}元</p>

                            </td>
                            <td>
                                <@m.orderStatusText item.order.status!/>
                            </td>
                            <td>
                               <p><a class="btn btn-sm btn-primary" href="${context}/gms/order/toOrderEdit.html?id=${item.order.id!}">订单管理</span></a>


                            </td>

                        </tr>

                        </#list>

                    <#else>
                    <tr>
                        <td colspan="5">没有相关记录！</td>
                    </tr>
                    </#if>
                </tbody>
            </@m.tableWithPage>
        </#if>
        </div>


    </div>
<#include "../include/footer.ftl"/>
</div>
<script language="javascript" type="text/javascript" src="${context}/js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>
