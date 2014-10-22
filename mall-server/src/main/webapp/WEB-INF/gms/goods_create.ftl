<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>

</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>

<@m.breadNavLevel3  context+'/gms/goods/toGoodsManager.html' '商品管理' '商品发布'/>
    <form class="form-horizontal" action="${context}/gms/goods/toGoodsEdit.html" method="post"
          id="queryForm">
            <div class="row">
            <div class="col-sm-12">

                <div class="row">
                    <div class="form-group">
                    <@m.categorySelect "" "" ""/>
                    </div>
                    <div class="col-sm-offset-10 col-sm-2">
                    <@m.btnReturn/>
                        <span class="btn btn-primary" onclick="chooseCate()">下一步</span>

                    </div>

                </div>


            </div>


        </div>

    </form>
    <script>
        function chooseCate() {

            if ($("#thirdCategory").val() == "") {
                alert("商品的分类必须为第三级分类");
                return;
            }
            document.getElementById("queryForm").submit();

        }


    </script>
<#include "../include/footer.ftl"/>
</div>

</body>
</html>
