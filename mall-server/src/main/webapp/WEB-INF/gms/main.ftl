<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<#--iframe -->
    <iframe width="100%" id="mainframe" scrolling="no" frameborder="0"
            onload="this.height=Math.min(mainframe.document.documentElement.scrollHeight,mainframe.document.body.scrollHeight)"
            border="0" name="mainframe" src="${context}/gms/index.html"></iframe>

<#--iframe END -->
<#include "../include/footer.ftl"/>
<#include "../include/ygdialog.ftl"/>
</div>
<!-- /.modal -->
<script type="text/javascript">
    function linkIframe(url) {
        $('#mainframe').attr("src", url);
    }
</script>
</body>
</html>
