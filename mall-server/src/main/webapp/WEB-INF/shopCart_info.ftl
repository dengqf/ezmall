<!DOCTYPE html>
<html lang="en">
<head>
<#include "./include/head.ftl"/>
    <script src="${context}/js/Calculation.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#checkAll").click(function () {

                $('input[name=newslist]').prop('checked', $(this).prop('checked'));
                GetCount();
            });


            // 所有复选(:checkbox)框点击事件
            $("input[name=newslist]").click(function () {
                if ($(this).attr("checked")) {
                    //$(this).next().css({ "background-color": "#3366cc", "color": "#ffffff" });
                } else {
                    // $(this).next().css({ "background-color": "#ffffff", "color": "#000000" });
                }
            });

            // 输出
            $("input[name=newslist]").click(function () {
                // $("#total2").html() = GetCount($(this));
                GetCount();
                //alert(conts);
            });
            //GetCount();
        });
        //******************
        function GetCount() {
            var conts = 0;
            var aa = 0;
            $($("input[name='newslist']:checked")).each(
                    function () {
                        aa += 1;
                        conts += parseFloat($(this).val());
                    }

            );

            $("#shuliang").text(aa);
            $("#zong1").html((conts).toFixed(2));

        }
    </script>
</head>
<body>
<div class="container">

<#include "./include/mall_nav.ftl"/>
<ul class="breadcrumb">
    <li>
        <a href="${context}/index.html">首页</a>
    </li>

    <li class="active">
        我的购物车
    </li>
</ul>

<form class="form-horizontal" action="${context}/member/toShopCartSubmit.html" id="queryForm">

<div class="row">
<div class="col-sm-12">
<table class="table   table-hover table-striped " id="gmsTable">
    <thead>
    <tr>
        <th><input value="" type="checkbox" id="checkAll"> 全选</th>
        <th class="col-sm-5">商品名称</th>
        <th>单价</th>
        <th>单价(¥)</th>
        <th>数量</th>
        <th>小计(¥)</th>
        <th>操作</th>
    </tr>

    </thead>
<#list goodsList as item>


    <tr id="tr_${item.no!}">
        <td><input type="checkbox" value="${(item.priceToCNY!)*(item.amount!1)}" name="newslist"
                   title="${item.no!}"
                   checked="true"
                   id="newslist_${item.no}"/>
            <input type="hidden" name="shopCart" id="no_${item.no!}" value="${item.no!}">
        </td>
        <td><a href="${context}/${item.goodsNo!}/goods.html" target="_blank"><img
                src="${context}/${item.goodsPicture!}" width="45" height="45"/></a><a
                href="${context}/${item.goodsNo!}/goods.html" target="_blank">${item.goodsName!}</a>
        </td>
        <td><@m.currencyFlag item.currencyType!"0" item.sellPrice!0/></td>
        <td class="text-danger">${item.priceToCNY!}</td>
        <td>
            <input id="min_${item.no!}" name="" style=" width:20px; height:18px;border:1px solid #ccc;"
                   type="button"
                   value="-"/>
            <input id="text_box_${item.no!}" name="amount" type="text" value="${item.amount!1}"
                   style=" width:30px; text-align:center; border:1px solid #ccc;"/>
            <input id="add_${item.no!}" name="" style=" width:20px; height:18px;border:1px solid #ccc;"
                   type="button"
                   value="+"/>
        </td>
        <td><label id="total_${item.no!}" class="text-danger">${item.priceToCNY!}</label></td>
        <td><a href="#" onclick="delShopCart('${item.no!}')">删除</a></td>
    </tr>

    <!---商品加减算总数---->
    <script type="text/javascript">
        $(function () {
            var t = $("#text_box_${item.no!}");
            var price =${item.priceToCNY!0}
                    $("#add_${item.no!}").click(function () {
                        t.val(parseInt(t.val()) + 1)
                        setTotal();
                        GetCount();
                    })
            $("#min_${item.no!}").click(function () {
                if (t.val() > 0) {
                    t.val(parseInt(t.val()) - 1)
                    setTotal();
                    GetCount();
                }
            })
            function setTotal() {

                $("#total_${item.no!}").html((parseFloat(t.val()) * price).toFixed(2));
                $("#newslist_${item.no!}").val(parseInt(t.val()) * price);
            }

            setTotal();
            GetCount();
        })
    </script>
</#list>
    <tr>
        <td colspan="12">
            <a href="${context}/index.html" class="btn  btn-info ">继续购物</a>
                            <span class="pull-right">
                        已选商品 <label id="shuliang" class="text-danger">0</label> 件,
                        合计(不含运费):<span>¥</span><span style=" color:#ff5500;"><label id="zong1"
                                                                                    style="color:#ff5500;font-size:14px; font-weight:bold;"></label></span>

                            &nbsp;&nbsp;&nbsp;&nbsp;<@m.btnReturn/><span class="btn  btn-primary " onclick="toOrderSubmit()">去结算</span>
                        </span>
        </td>
    </tr>
</table>


<!---总数---->
<script type="text/javascript">
    $(function () {
        $(".quanxun").click(function () {
            setTotal();
            //alert($(lens[0]).text());
        });
        function setTotal() {
            var len = $(".tot");
            var num = 0;
            for (var i = 0; i < len.length; i++) {
                num = parseInt(num) + parseInt($(len[i]).text());

            }
            //alert(len.length);
            $("#zong1").text(parseFloat(num).toFixed(2));
            $("#shuliang").text(len.length);
        }

        //setTotal();
    })

    function delShopCart(no) {
        $.ajax({
            type: "POST",
            url: "${context}/delShopCart.html",
            data: {
                "no": no
            },
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    alert("删除成功");
                    $("#tr_" + no).attr("class", "hidden");
                    $("#newslist_" + no).attr("name", "delShopCart");
                    GetCount();
                } else {

                    alert(messageVo.message);

                }
            }
        });
    }

    <#--准备创建订单-->
    function toOrderSubmit() {
        var checkedGoods = $('input[name="newslist"]:checked');
        if (checkedGoods.size() <= 0) {
            alert("至少需要选择一个商品");
            return;
        }
        //清空
        $($("input[name='newslist']")).each(
                function () {
//                        alert( $(this).attr('title')) ;
                    var no = $(this).attr('title');

                    $("#no_" + no).val("");
                }

        );
        //检查数字
        var checkCart = true;
        $($("input[name='newslist']:checked")).each(
                function () {
//                        alert( $(this).attr('title')) ;
                    var no = $(this).attr('title');
                    var amount = $("#text_box_" + no).val();
                    if (!amount.match(/^\d+$/)) {
                        checkCart = false;
                    }
                    if (amount < 1) {
                        checkCart = false;
                    }
                    $("#no_" + no).val(no + "_" + amount);
                }

        );
        if (!checkCart) {
            alert("请检查购买数量是否>0");
            return;
        }

    <#--检查用户是否登录-->
        $.ajax({
            type: "POST",
            url: "${context}/checkMemberExist.html",
            dataType: "json",
            success: function (messageVo) {
                if (messageVo.success == true) {
                    document.getElementById("queryForm").submit();

                } else {
                    $("#loginModal").modal('show');

                }
            }
        });


    }


</script>


</div>


</div>

</form>
<#include "./include/footer.ftl"/>

<@m.loginModal/>

</body>
</html>
