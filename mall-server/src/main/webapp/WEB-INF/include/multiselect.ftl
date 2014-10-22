<link href="${context}/css/jquery.multiselect.css" rel="stylesheet" type="text/css"/>
<#--<style type="text/css">-->
<#--body { font:12px Helvetical, Arial, sans-serif; padding-bottom:200px }-->
<#--h1 { margin-bottom:0 }-->
<#--h2 { font-size:14px; padding:0; margin:25px 0 5px 0 }-->
<#--p { margin:5px 0; padding:0 }-->
<#--a { color:#001371 }-->
<#--#back { background:#16254B; margin:5px 0 8px 0 }-->
<#--#back div { padding:10px; color:#fff; font-size:14px }-->
<#--#back a { color:#fff }-->

<#--#callback { color:green; font-weight:bold }-->

<#--/* sets a min width on the selects. you can also use the minWidth parameter */-->
<#--select {  }-->
<#--</style>-->

<#--<script src="${context}/js/jquery.js" type="text/javascript"></script>-->
<script src="${context}/js/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${context}/js/jquery.multiselect.min.js" type="text/javascript"></script>

<script type="text/javascript">
    $(function () {

        // notice how you can overwrite the defaults for instances
        $.fn.multiSelect.defaults.minWidth = 160;
//            $("select.multiselect").multiSelect();
//            $("#MySelectBox").multiSelect();
        $("#years").multiSelect();
        $("#structNames").multiSelect();
        $("#sexNos").multiSelect();
        $("#seasonNos").multiSelect();
//        $("#brands").multiSelect();
//        $("#queryForm").submit(function (e) {
//            var data = $(this).serialize();
//            alert(data.length ? data : 'Nothing to serialize; check a box or two');
//            e.preventDefault();
//        });
    });
</script>
