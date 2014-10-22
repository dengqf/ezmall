<div class="navbar navbar-default">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target=".navbar-responsive-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#"><strong>管理平台</strong></a>
    </div>
    <div class="navbar-collapse collapse navbar-responsive-collapse">

    <@shiro.user>
        <ul class="nav navbar-nav">
            <@shiro.hasRole name="system">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        基础数据管理 <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="${context}/gms/brand/toBrandManager.html">品牌管理</a></li>

                        <li><a href="${context}/gms/category/toCategoryManager.html">分类管理</a></li>
                        <li><a href="${context}/gms/prop/toPropItemManager.html">属性管理</a></li>

                    </ul>
                </li>
            </@shiro.hasRole>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    商品管理 <b class="caret"></b></a>
                <ul class="dropdown-menu">


                    <li><a href="${context}/gms/goods/toGoodsCreate.html">商品发布</a></li>
                    <li><a href="${context}/gms/goods/toGoodsManager.html">商品管理</a></li>

                </ul>
            </li>


            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    订单管理 <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="${context}/gms/order/toOrderManager.html">订单查询</a></li>


                </ul>
            </li>
            <@shiro.hasRole name="admin">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        管理中心<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="${context}/gms/admin/toEditAdmin.html">创建用户</a></li>
                        <li><a href="${context}/gms/admin/toAdminManager.html">用户管理</a></li>


                    </ul>
                </li>
            </@shiro.hasRole>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    报表 <b class="caret"></b></a>
                <ul class="dropdown-menu">

                    <li><a href="#"
                           onclick="linkIframe('${context}/gms/purchase/report/toPurchaseCommodityReport.html')">商品采购汇总</a>
                    </li>
                    <li><a href="#"
                           onclick="linkIframe('${context}/gms/purchase/report/toPurchaseSupplierReport.html')">供应商采购汇总</a>
                    </li>
                    <li><a href="#"
                           onclick="linkIframe('${context}/gms/purchase/report/toPurchaseCompleteReport.html')">采购完工汇总</a>
                    </li>

                </ul>
            </li>


        </ul>

        <ul class="nav navbar-nav navbar-right">

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">  <@shiro.principal/><b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="${context}/gms/admin/toAdminUpdate.html">资料修改</a></li>
                    <li><a href="${context}/gms/admin/toUpdatePassword.html">密码修改</a></li>
                    <li class="divider"></li>
                    <li><a href="${context}/gms/logOut.html">退出</a></li>
                </ul>
            </li>

        <#--<li id="nav_login"><a href="#">登录</a></li>-->

        </ul>
    </@shiro.user>
    </div>
    <!-- /.nav-collapse -->
</div>