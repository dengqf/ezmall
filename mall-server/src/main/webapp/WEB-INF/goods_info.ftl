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
        <li>
            <a href="${context}/${firstCategory.no!}/1/goods_cate_list.html">${firstCategory.name!}</a>
        </li>
        <li>
            <a href="${context}/${secondCategory.no!}/1/goods_cate_list.html">${secondCategory.name!}</a>
        </li>
        <li>
            <a href="${context}/${thirdCategory.no!}/1/goods_cate_list.html">${thirdCategory.name!}</a>
        </li>
        <li class="active">
        ${goods.name!}
        </li>
    </ul>
    <div class="row">
        <form action="${context}/addToShopCart.html" class="" id="queryForm">
        <#--<div class="col-sm-5">-->
        <#--<img itemprop="image" src="${context}/${goods.goodsPicture!""}" alt="${goods.name!}" width="400"-->
        <#--height="400">-->
        <#--</div>-->
            <div class="col-sm-5 col-md-5">
                <img itemprop="image" src="${context}/${goods.goodsPicture!""}" alt="${goods.name!}">
            </div>
            <div class="col-sm-offset-0 col-sm-6 col-md-offset-0 col-md-6">
                <h3>${goods.name!}</h3>

                <p><a href="#">${goods.merchantCompany!"商城自营"}</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>商品编号：${goods.no!}</span></p>

                <p>品牌：<a href="${context}/${goods.brandNo!}/1/goods_brand_list.html">${goods.brandName!}</a></p>

                 <@m.priceInfo goods.marketPrice!-1 goods.sellPrice!0 goods.currencyType!"CNY" economy!-1/>
                <hr>
                <p>购买数量：

                    <a href="javascript:reduce('#J_Amount');"><strong>-</strong></a>
                    <input name="amount" id="J_Amount" type="text" maxlength="2" size="1" value="1"
                           onkeyup="modify('#J_Amount');"/>
                    <a href="javascript:add('#J_Amount')"><strong>+</strong></a>
                    (库存数量 10 件)

                <p class="help-inline" id="J_Tip"></p>
                <input id="nAmount" type="hidden" value="1000"/>
                </p>
                <p>
                    <input type="hidden" name="no" value="${goods.no!}">
                    <span class="btn  btn-primary" onclick="toBuyGoods()">立即购买</span>
                    <span class="btn  btn-primary" onclick="toAddShopCart()">加入购物车</span>
                    <a href="#" onclick="toAddFavorite()"><i class="glyphicon glyphicon-star">收藏商品</i></a>
                </p>
                <hr>
                <p>服务：7天退货</p>

                <p>支付：<img itemprop="image" src="${context}/img/alipay.png" width="20"
                           height="20">支付宝支付</p>
            </div>
        </form>
    </div>
    <br>
    <br>

    <div class="row">
        <div class="col-sm-12">
            <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                <li class="active"><a href="#home" data-toggle="tab">商品详情</a></li>
                <li class=""><a href="#dianping" data-toggle="tab">商品评论</a></li>
                <li class=""><a href="#rate" data-toggle="tab">免税店使用指南</a></li>
                <li class=""><a href="#getGoods" data-toggle="tab">商品领取方式</a></li>
                <li class=""><a href="#cancelOrder" data-toggle="tab">取消订购/退货</a></li>
            <#--<li class=""><a href="#techDesc" data-toggle="tab">技师简介</a></li>-->


            </ul>
            <div id="myTabContent" class="tab-content">
                <div class=" tab-pane fade active in " id="home">
                    <div class="row">
                        <div class="col-sm-8">
                            <table class="table table-bordered table-hover table-striped   small " id="gmsTable">
                                <tbody>
                                <#if props??>
                                    <#list props as item>
                                    <tr>
                                        <td class="col-sm-1">${item.propItemName!}</td>
                                        <td class="col-sm-5">${item.propItemValue!}</td>
                                    </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div>
                    ${goods.goodsDesc!""}
                    </div>
                </div>
                <div class="tab-pane fade" id="dianping">
                    <iframe width="100%" height="100%" id="mainframe" scrolling="no" frameborder="0"
                            onload="this.height=Math.max(window.screen.height ,
            Math.min(mainframe.document.documentElement.scrollHeight,mainframe.document.body.scrollHeight))"
                            border="0" name="mainframe" src="${context}/${goods.no!}/1/goodsCommentList.html"></iframe>


                </div>
                <div class="tab-pane fade" id="rate">
                    <img src="${context}/img/notice_rate.jpg"/>
                </div>
                <div class="tab-pane fade" id="getGoods">
                    <img src="${context}/img/notice_goods.jpg"/>
                </div>
                <div class="tab-pane fade" id="cancelOrder">
                    <img src="${context}/img/notice_cancle.jpg"/>
                </div>
                <div class="tab-pane fade" id="techDesc">

                    <img src="${context}/${merchant.userPicture!""}">

                    <div>
                    ${merchant.userDesc!"这个技师没有任何简介"}
                    </div>

                </div>
            </div>
        </div>
    </div>
    <br>

<#--<div class="row">-->
<#--<div class="col-sm-12">-->
<#--<p>-->
<#--<img src="${context}/img/notice_01.gif"/>-->
<#--</p>-->
<#--</div>-->
<#--</div>-->

<#include "./include/footer.ftl"/>
</div>
<script>
    var min = 1;
    function reg(x) {
        jQuery('#J_Tip').html("");
        jQuery('#J_Tip').hide();
        return new RegExp("^[1-9]\\d*$").test(x);
    }
    function amount(obj, mode) {
        var x = jQuery(obj).val();
        if (this.reg(parseInt(x))) {
            if (mode) {
                x++;
            } else {
                x--;
            }
        } else {
            jQuery('#J_Tip').html("<i class=\"ico\"></i>请输入正确的数量");
            jQuery('#J_Tip').show();
            jQuery(obj).val(1);
            jQuery(obj).focus();
        }
        return x;
    }
    function reduce(obj) {
        var x = this.amount(obj, false);
        if (parseInt(x) >= this.min) {
            jQuery(obj).val(x);
        } else {
            jQuery('#J_Tip').html("<i class=\"ico\"></i>商品购买数量最少为" + this.min);
            jQuery('#J_Tip').show();
            jQuery(obj).val(1);
            jQuery(obj).focus();
        }
    }
    function add(obj) {
        var x = this.amount(obj, true);
        var max = jQuery('#nAmount').val();
        if (parseInt(x) <= parseInt(max)) {
            jQuery(obj).val(x);
        } else {
            jQuery('#J_Tip').html("<i class=\"ico\"></i>您所填写的商品数量超过库存");
            jQuery('#J_Tip').show();
            jQuery(obj).val(max == 0 ? 1 : max);
            jQuery(obj).focus();
        }
    }
    function modify(obj) {
        var x = jQuery(obj).val();
        var max = jQuery('#nAmount').val();
        if (!this.reg(parseInt(x))) {
            jQuery(obj).val(1);
            jQuery(obj).focus();
            jQuery('#J_Tip').html("<i class=\"ico\"></i>请输入正确的数量");
            jQuery('#J_Tip').show();
            return;
        }
        var intx = parseInt(x);
        var intmax = parseInt(max);
        if (intx < this.min) {
            jQuery('#J_Tip').html("<i class=\"ico\"></i>商品购买年限最少为" + this.min);
            jQuery('#J_Tip').show();
            jQuery(obj).val(this.min);
            jQuery(obj).focus();
        } else if (intx > intmax) {
            jQuery('#J_Tip').html("<i class=\"ico\"></i>您所填写的商品数量超过库存");
            jQuery('#J_Tip').show();
            jQuery(obj).val(max == 0 ? 1 : max);
            jQuery(obj).focus();
        }
    }

    function toAddShopCart() {
        var amount = $("#J_Amount").val();
        if (amount < 1) {
            alert("购买数量至少为1件");
        }
        addToShopCart('${goods.no!}', amount);
    }


    function toBuyGoods() {
        var amount = $("#J_Amount").val();
        if (amount < 1) {
            alert("购买数量至少为1件");
        }
        $.ajax({
            type: "POST",
            url: "${context}/checkMemberExist.html",
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success) {
                    bugGoods();
                } else {
                    $("#loginModal").modal('show');
                    $("#flag").val("2");
                }
            }
        });


    }

    function bugGoods() {
        var form = document.getElementById("queryForm");
        form.action = "${context}/member/toOrderSubmit.html";
        form.submit();

    }

    <#--添加到收藏夹-->
    function toAddFavorite() {
        $.ajax({
            type: "POST",
            url: "${context}/checkMemberExist.html",
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success) {
                    addFavorite();
                } else {
                    $("#loginModal").modal('show');
                    $("#flag").val("1");
                }
            }
        });


    }

    function addFavorite() {
        $.ajax({
            type: "POST",
            url: "${context}/member/addFavoriteToMember.html",
            data: {
                "goodsNo": "${goods.no!}"
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {

                    alert("添加成功");

                } else {
                    alert(messageVo.message);

                }
            }
        });
    }


</script>



<@m.loginModal/>
<@m.shopCart/>
</body>
</html>
