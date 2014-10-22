<meta charset="utf-8">
<#if goods??>
<title>EZMALL网上商城-${goods.name!}</title>
<#else>
<title>EZMALL网上商城</title>
</#if>



<meta http-equiv="x-ua-compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords"
      content="优购货品管理系统"/>
<meta name="description" content="优购货品管理系统"/>
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<#--<link href="${context}/css/bootstrap.min.css" rel="stylesheet">-->
<#--<link href="${context}/css/flatly.css" rel="stylesheet">-->
<link href="${context}/css/ubuntu.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 8]>
<script src="${context}/js/ie8-responsive-file-warning.js"></script>

<![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
<script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->


<script src="${context}/js/jquery.js" type="text/javascript"></script>
<script src="${context}/js/bootstrap.min.js"></script>
<script src="${context}/js/ygdialog.js"></script>
<#assign spring=JspTaglibs["/WEB-INF/macro/tlds/spring.tld"]/>


