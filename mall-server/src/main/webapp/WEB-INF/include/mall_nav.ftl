<div class="navbar navbar-default">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target=".navbar-responsive-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#"><strong>EZMALL商城</strong></a>
    </div>
    <div class="navbar-collapse collapse navbar-responsive-collapse">


        <ul class="nav navbar-nav">

            <#--<li class="dropdown">-->
                <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                    <#--商品管理 <b class="caret"></b></a>-->
                <#--<ul class="dropdown-menu">-->

                    <#--<li><a href="${context}/gms/goods/toGoodsEdit.html">商品发布</a></li>-->
                    <#--<li><a href="${context}/gms/goods/toGoodsManager.html">商品管理</a></li>-->
                    <#--<li><a href="${context}/gms/brand/toBrandManager.html">品牌管理</a></li>-->

                    <#--<li><a href="${context}/gms/category/toCategoryManager.html">分类管理</a></li>-->
                    <#--<li><a href="${context}/gms/prop/toPropItemManager.html">属性管理</a></li>-->

                <#--</ul>-->
            <#--</li>-->
            <#--<li class="dropdown">-->
                <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                    <#--订单管理 <b class="caret"></b></a>-->
                <#--<ul class="dropdown-menu">-->

                    <#--<li><a href="#"-->
                           <#--onclick="linkIframe('${context}/gms/purchase/purchase/toPurchaseManage.html')">采购订单</a></li>-->
                    <#--<li><a href="#"-->
                           <#--onclick="linkIframe('${context}/gms/purchase/returnApply/toReturnApplyManage.html')">采购退货</a>-->
                    <#--</li>-->
                    <#--<li><a href="#">采购召回</a></li>-->
                    <#--<li><a href="#" onclick="linkIframe('${context}/gms/purchase/purchase/toUpdateWarehouse.html')">采购入仓修改</a>-->
                    <#--</li>-->
                    <#--<li><a href="#" onclick="linkIframe('${context}/gms/purchase/purchase/purchasePosErrorList.html')">采购单同步失败</a>-->
                    <#--</li>-->
                <#--</ul>-->
            <#--</li>-->


            <#--<li class="dropdown">-->
                <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                    <#--用户管理 <b class="caret"></b></a>-->
                <#--<ul class="dropdown-menu">-->
                    <#--<li><a href="#"-->
                           <#--onclick="linkIframe('${context}/gms/purchase/shipment/toShipmentManager.html')">发货单</a></li>-->

                    <#--<li><a href="#" onclick="linkIframe('${context}/gms/purchase/shipment/toShipmentDiffManager.html')">发货差异单</a>-->
                    <#--</li>-->
                    <#--<li><a href="#" onclick="linkIframe('${context}/gms/purchase/shipment/toReturnDefectManager.html')">返回申请单</a>-->
                    <#--</li>-->

                    <#--<li><a href="#" onclick="linkIframe('${context}/gms/purchase/shipment/toShipmentSyncError.html')">发货单同步失败</a>-->
                    <#--</li>-->
                <#--</ul>-->
            <#--</li>-->
            <#--<li class="dropdown">-->
                <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                    <#--报表 <b class="caret"></b></a>-->
                <#--<ul class="dropdown-menu">-->

                    <#--<li><a href="#"-->
                           <#--onclick="linkIframe('${context}/gms/purchase/report/toPurchaseCommodityReport.html')">商品采购汇总</a>-->
                    <#--</li>-->
                    <#--<li><a href="#"-->
                           <#--onclick="linkIframe('${context}/gms/purchase/report/toPurchaseSupplierReport.html')">供应商采购汇总</a>-->
                    <#--</li>-->
                    <#--<li><a href="#"-->
                           <#--onclick="linkIframe('${context}/gms/purchase/report/toPurchaseCompleteReport.html')">采购完工汇总</a>-->
                    <#--</li>-->

                <#--</ul>-->
            <#--</li>-->

            <#--<li><a href="#">关于</a></li>-->
        </ul>

        <ul class="nav navbar-nav navbar-right">
        <#if EZMALL_MEMBER??>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"> ${EZMALL_MEMBER.linkman!"登陆"}<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li> <a href="${context}/member/index.html">快捷菜单</a></li>
                    <li> <a href="${context}/member/orderList.html">我的订单</a></li>
                    <li><a href="${context}/member/favoriteList.html">我的收藏夹</a></li>
                    <li><a href="${context}/member/goodsComment.html">商品点评/晒单</a></li>
                    <li><a href="${context}/toEditMember.html?id=${EZMALL_MEMBER.id!}">资料修改</a></li>
                    <li><a href="${context}/member/toUpdatePassword.html">密码修改</a></li>
                    <li class="divider"></li>
                    <li><a href="${context}/logOutMember.html">退出</a></li>
                </ul>
            </li>

        <#else>
            <li><a href="${context}/toLoginMember.html">登录</a></li>
            <li><a href="${context}/toEditMember.html">注册</a></li>
        </#if>

        </ul>

    </div>
    <!-- /.nav-collapse -->
</div>