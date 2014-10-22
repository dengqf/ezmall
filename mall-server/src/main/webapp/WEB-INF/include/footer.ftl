<hr class="featurette-divider" id="divider" >
<div class="footer" >
    <div class="row">
        <div class="col-lg-12">
            <ul class="list-inline small text-center">
                <li><a href="#" >关于我们</a></li>
                <li>|</li>
                <li><a href="#" >联系我们</a></li>

                <li>|</li>
                <li><a href="#" >网站资讯</a></li>

            </ul>
        </div>
    </div>
    <#--<div class="row">-->
        <#--<div class="col-lg-12">-->
            <#--<p class="text-center">-->
                <#--<small>Copyright &copy;2011-2014 Ezmall Technology Co., Ltd. 粤ICP备09070608号-4</small>-->
            <#--</p>-->
        <#--</div>-->
    <#--</div>-->
</div>
<script>
    $(function () {
       var windows_height=$(window).height();
       var body_height=$(document.body).height();
        var  height=windows_height-body_height-50
//        alert(body_height+"|"+windows_height+"!"+height);
        if(height>0){
            $("#divider").attr("style","margin-top:"+height+"px")

        }

    });
</script>






