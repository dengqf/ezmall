<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/head.ftl"/>
    <script src="${context}/js/jHtmlArea-0.8.min.js"></script>
    <link href="${context}/css/jHtmlArea.css" rel="stylesheet">
</head>
<body>
<div class="container">
<#include "../include/nav.ftl"/>
<#--<@m.breadNavLevel2  '商品编辑'/>-->
<@m.breadNavLevel3  context+'/gms/goods/toGoodsManager.html' '商品管理' '商品编辑'/>


    <div class="row">

        <div class="col-sm-12">
            <form class="form-horizontal" action="${context}/gms/goods/goodsEdit.html" enctype="multipart/form-data"
                  id="queryForm" method="post">
                <fieldset>
                <#--<legend>添加信息（<@m.formMust/> 为必填项）</legend>-->
                    <div class="form-group">
                    <@m.inputText '商品名称:' 'name' goods.name!""/>

                        <span class="help-block"><#if goods.no??>商品编号:${goods.no!},</#if>所属分类:${category.structName!}</span>

                    </div>
                    <div class="form-group">
                        <label for="pic" class="col-sm-2 control-label">商品图片:</label>
                    <#if goods.goodsPicture??>
                        <div class="col-sm-3">
                            <img src="${context}/${goods.goodsPicture!""}" alt="" width="150" height="150">
                        </div>
                    </#if>
                        <div class="col-sm-7">
                            <input class="input-file" name="pic" id="pic" type="file">
                            <span class="help-block">请选择正方形图像,大小不超过2M,选择文件后，要点击“确定”按钮</span>

                        </div>
                    </div>
                    <div class="form-group">
                    <#--<@m.brands brands goods.brandNo!/>-->
                        <label for="brandNo" class="col-sm-2 control-label">店铺:
                        </label>
                        <div class="col-sm-2">
                            <select class="form-control input-sm" id="brandNo" name="brandNo">
                                <option value="" >--请选择--</option>
                                <#list brands as item>
                                    <option value="${item.no!}"  <#if (item.no!)==(goods.brandNo!)>selected="true" </#if> >${item.name!}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <#--<div class="form-group">-->
                    <#--<@m.categorySelect "${vo.firstCategory!}" "${vo.secondCategory!}" "${vo.thirdCategory!}"/>-->
                    <#--</div>-->
                    <div class="form-group">
                    <@m.inputText '市场价格:' 'marketPrice' goods.marketPrice!/>
                    <@m.inputText '销售价格:' 'sellPrice' goods.sellPrice!/>
                    <@m.inputText '成本价格:' 'costPrice' goods.costPrice!/>
                    </div>
                    <div class="form-group">
                    <@m.currencyTypeSelect  goods.currencyType!/>
                    <@m.inputText '销售国家:' 'country' goods.country!/>
                    <@m.inputText '销售城市:' 'city' goods.city!/>
                    </div>
                    <div class="form-group">
                    <@m.inputText '款号:' 'styleNo' goods.styleNo!/>
                    <@m.inputText '规格名称:' 'specName' goods.specName!/>
                    <@m.inputText '款色编码:' 'supplierCode' goods.supplierCode!/>
                    </div>
                    <div class="form-group">
                    <@m.inputText '销售单位:' 'unit' goods.unit!/>
                    <@m.inputText '商品年份:' 'year' goods.year!/>
                    <@m.inputText '商品积分:' 'giveScore' goods.giveScore!0/>

                    </div>
                    <div class="form-group">
                    <@m.inputText '商品运费:' 'expressPrice' goods.expressPrice!0/>

                    <#--<@m.inputText '第三方货品号:' 'thirdNo' goods.thirdNo!/>-->


                    </div>
                    <#--<div class="form-group">-->
                        <#--<label for="thirdLink" class="col-sm-2 control-label">第三方货品链接:</label>-->

                        <#--<div class="col-sm-10">-->
                            <#--<input class="form-control" id="thirdLink" name="thirdLink"-->
                                   <#--placeholder="例:http://item.jd.com/1094111.html" type="text"-->
                                   <#--value="${goods.thirdLink!}">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="thirdPicture" class="col-sm-2 control-label">第三方图片链接:</label>-->

                        <#--<div class="col-sm-10">-->
                            <#--<input class="form-control" id="thirdPicture" name="thirdPicture"-->
                                   <#--placeholder="例:http://www.jd.com/bigimage.aspx?id=1094111" type="text"-->
                                   <#--value="${goods.thirdLink!}">-->
                        <#--</div>-->

                    <#--</div>-->
                    <div class="form-group">
                        <label for="goodsDesc" class="col-sm-2 control-label">商品描述:</label>

                        <div class="col-sm-10">
                            <textarea class="form-control" rows="10" id="goodsDesc" placeholder="输入商品描述"
                                      name="goodsDesc">${goods.goodsDesc!""}</textarea>
                        <#--<span class="help-block">A longer block of help text that breaks onto a new line and may extend beyond one line.</span>-->
                        </div>
                    </div>


                </fieldset>

                <h4>添加商品属性:</h4>
                <hr>
                <div class="row ">
                    <div id="rewrite_box"  class="col-sm-12 "  style="display:none;" >
                    <#list propItemList as propItem>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">${propItem.name}:</label>

                            <div class="col-sm-10 rewrite" >
                                    <input class="form-control" id="${propItem.no!}"   style="display:none;"  name="propNameCheck" type="text"
                                       title="${propItem.name!}"
                                       value="<#assign x="" /><#list relations as rp><#if rp.propItemNo==propItem.no&&rp.propItemValue??> <#assign x=rp.propItemValue!"" /></#if></#list><#if x=="">${propItem.defaultValue!}<#else>${x!}</#if>">

                                <input  id="${propItem.no!}_1" name="propName" type="hidden"
                                       value="<#list relations as rp><#if rp.propItemNo==propItem.no>${rp.propItemValue!}</#if></#list>">

                                <input  id="${propItem.no!}_2" name="defaultValue" type="hidden"
                                        value="${propItem.defaultValue!}">
                                <input  id="${propItem.no!}_3" name="itemType" type="hidden"
                                        value="${propItem.itemType!"0"}">
                            </div>

                        </div>
                    </#list>
                    <#if propItemList?size<1 >

                        <a class="btn  btn-link" href="${context}/gms/category/toAddProp.html?no=${goods.categoryNo!}" >商品分类下无商品属性,点击添加</a>

                    </#if>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10 col-sm-offset-2">
                        <input type="hidden" name="id" id="id" value="${goods.id!}">
                        <input type="hidden" name="categoryNo" id="categoryNo" value="${category.no!}">
                    <@m.btnReturn/>
                        <span class="btn btn-primary" onclick="editGoods()">确定</span>
                    </div>
                </div>
            </form>
        </div>
    </div>
<#include "../include/footer.ftl"/>
</div>
<script>
    var proflag=false;
    $('#goodsDesc').htmlarea();
    function editGoods() {
        if ($("#name").val().length > 100 || $("#name").val().length < 2) {
            alert("商品名称为2-64位字符");
            return;
        }
        if ($("#brandNo").val() == "") {
            alert("请选择店铺");
            return;
        }
        if ($("#thirdCategory").val() == "") {
            alert("商品的分类必须为第三级分类");
            return;
        }
        var marketPrice=$("#marketPrice").val();
        if (!marketPrice.match(/^(([1-9]\d*)|0)(\.\d{1,2})?$/)) {
            alert('市场价格必须为数字');
            return;
        }
        if(marketPrice<=0){
            alert('市场价格必须>0');
            return;
        }
        if (!$("#sellPrice").val().match(/^(([1-9]\d*)|0)(\.\d{1,2})?$/)) {
            alert('销售价格必须为数字');
            return;
        }
        if (!$("#costPrice").val().match(/^(([1-9]\d*)|0)(\.\d{1,2})?$/)) {
            alert('成本价格必须为数字');
            return;
        }
        if ($("#currencyType").val() == "") {
            alert("请选择货币类型");
            return;
        }
	
          $($("input[name='propNameCheck']")).each(
        //$($("input[name='propName']")).each(
                function () {
//                        alert( $(this).attr('title')) ;
                    var no = $(this).attr('id');
                    var name = $(this).attr('title');
                    var propValue = $(this).val();
                    if (propValue != "") {
                        $("#" + no + "_1").val(name + "#*#" + no + "#*#" + propValue);
			proflag=true;
                    } else {
                       // $("#" + no + "_1").val("");
		       alert("所有属性都要选填");
		       proflag=false;
		       return;
                    }


                }

        );
	if(proflag)
          document.getElementById("queryForm").submit();
    }

</script>
<script>
	$(document).ready(function(){	
		$("div.rewrite").each(function(i){
			var n=$(this).children("input").eq(3).val();
			var arr=$(this).children("input").eq(2).val().split("|");
			var old=$(this).children("input").eq(1).val().split("|");			
			var _name=$(this).children("input").eq(1).attr("name")+i+"_r";
			var odd_even=arr.length%2;
			var _html='<table width="100%">';
			switch(n)
			{
				case "1":
					if(odd_even==0)
					{	
						for(var i=0 ;i<arr.length;i+=2)
						{
							_html=_html+'<tr>';
							var state1=compare(old,arr[i]);
							if (state1)
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="radio" name="'+_name+'" value="'+arr[i]+'" checked/></td><td style="padding-top: 9px;width:42%">'+arr[i]+'</td>';
							}
							else
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="radio" name="'+_name+'" value="'+arr[i]+'"/></td><td style="padding-top: 9px;width:42%">'+arr[i]+'</td>'
							}
							var state2=compare(old,arr[i+1]);
							if (state2)
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="radio" name="'+_name+'" value="'+arr[i+1]+'" checked/></td><td style="padding-top: 9px;width:42%">'+arr[i+1]+'</td>';
							}
							else
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="radio" name="'+_name+'" value="'+arr[i+1]+'"/></td><td style="padding-top: 9px;width:42%">'+arr[i+1]+'</td>'
							}	
							_html=_html+'</tr>';
						}
						_html=_html+'</table>';
					}
					else
					{  
						for(var i=0 ;i<arr.length-1;i+=2)
						{
							_html=_html+'<tr>';
							var state1=compare(old,arr[i]);
							if (state1)
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="radio" name="'+_name+'" value="'+arr[i]+'" checked/></td><td style="padding-top: 9px;width:42%">'+arr[i]+'</td>';
							}
							else
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="radio" name="'+_name+'" value="'+arr[i]+'"/></td><td style="padding-top: 9px;width:42%">'+arr[i]+'</td>'
							}
							var state2=compare(old,arr[i+1]);
							if (state2)
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="radio" name="'+_name+'" value="'+arr[i+1]+'" checked/></td><td style="padding-top: 9px;width:42%">'+arr[i+1]+'</td>';
							}
							else
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="radio" name="'+_name+'" value="'+arr[i+1]+'"/></td><td style="padding-top: 9px;width:42%">'+arr[i+1]+'</td>'
							}
							_html=_html+'</tr>';
						}
						var state1=compare(old,arr[arr.length-1]);
						if (state1)
						{
							_html=_html+'<tr><td style="padding-top: 9px;width:8%"><input type="radio" name="'+_name+'" value="'+arr[arr.length-1]+'" checked/></td><td style="padding-top: 9px;width:42%">'+arr[arr.length-1]+'</td>';
						}
						else
						{
							_html=_html+'<tr><td style="padding-top: 9px;width:8%"><input type="radio" name="'+_name+'" value="'+arr[arr.length-1]+'"/></td><td style="padding-top: 9px;width:42%">'+arr[arr.length-1]+'</td>'
						}
						_html=_html+'<td style="padding-top: 9px;width:8%">&nbsp;</td><tr></table>';
					}
					break;
				case "2":
					if(odd_even==0)
					{	
						_html=_html+'<tr><td style="padding-top: 9px;width:8%"><input type="checkbox"  class="cboxchecked" /></td><td style="padding-top: 9px;width:42%">全选/全不选</td><td style="padding-top: 9px;width:8%">&nbsp;</td><td style="padding-top: 9px;width:42%">&nbsp;</td></tr>'
						for(var i=0 ;i<arr.length;i+=2)
						{
							_html=_html+'<tr>';
							var state1=compare(old,arr[i]);
							if (state1)
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="checkbox" name="'+_name+'" value="'+arr[i]+'" checked/></td><td style="padding-top: 9px;width:42%">'+arr[i]+'</td>';
							}
							else
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="checkbox" name="'+_name+'" value="'+arr[i]+'"/></td><td style="padding-top: 9px;width:42%">'+arr[i]+'</td>'
							}
							var state2=compare(old,arr[i+1]);
							if (state2)
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="checkbox" name="'+_name+'" value="'+arr[i+1]+'" checked/></td><td style="padding-top: 9px;width:42%">'+arr[i+1]+'</td>';
							}
							else
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="checkbox" name="'+_name+'" value="'+arr[i+1]+'"/></td><td style="padding-top: 9px;width:42%">'+arr[i+1]+'</td>'
							}	
							_html=_html+'</tr>';
						}
						_html=_html+'</table>';
					}
					else
					{  
						_html=_html+'<tr><td style="padding-top: 9px;width:8%"><input type="checkbox"  class="cboxchecked" /></td><td style="padding-top: 9px;width:42%">全选/全不选</td><td style="padding-top: 9px;width:8%"></td><td style="padding-top: 9px;width:42%"></td></tr>';
					
						for(var i=0 ;i<arr.length-1;i+=2)
						{
							_html=_html+'<tr>';
							var state1=compare(old,arr[i]);
							if (state1)
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="checkbox" name="'+_name+'" value="'+arr[i]+'" checked/></td><td style="padding-top: 9px;width:42%">'+arr[i]+'</td>';
							}
							else
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="checkbox" name="'+_name+'" value="'+arr[i]+'"/></td><td style="padding-top: 9px;width:42%">'+arr[i]+'</td>'
							}
							var state2=compare(old,arr[i+1]);
							if (state2)
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="checkbox" name="'+_name+'" value="'+arr[i+1]+'" checked/></td><td style="padding-top: 9px;width:42%">'+arr[i+1]+'</td>';
							}
							else
							{
								_html=_html+'<td style="padding-top: 9px;width:8%"><input type="checkbox" name="'+_name+'" value="'+arr[i+1]+'"/></td><td style="padding-top: 9px;width:42%">'+arr[i+1]+'</td>'
							}
							_html=_html+'</tr>';
						}
						var state1=compare(old,arr[arr.length-1]);
						if (state1)
						{
							_html=_html+'<tr><td style="padding-top: 9px;width:8%"><input type="checkbox" name="'+_name+'" value="'+arr[arr.length-1]+'" checked/></td><td style="padding-top: 9px;width:42%">'+arr[arr.length-1]+'</td>';
						}
						else
						{
							_html=_html+'<tr><td style="padding-top: 9px;width:8%"><input type="checkbox" name="'+_name+'" value="'+arr[arr.length-1]+'"/></td><td style="padding-top: 9px;width:42%">'+arr[arr.length-1]+'</td>'
						}
						_html=_html+'<td style="padding-top: 9px;width:8%"></td><td style="padding-top: 9px;width:42%"></td></tr></table>';
					}
					break;
				case "0":
					$(this).children("input").eq(0).show();
				break;
			}
			//$(this).children("input").hide();
			$(this).append(_html);
			$(this).children("table").find("input[type='radio']").click( function () {				
				var _val=$(this).val(); 
				$(this).parents("div.rewrite").children("input").eq(0).val(_val); 
				$(this).parents("div.rewrite").children("input").eq(1).val(_val); 
			}); 
			$(this).children("table").find("input[type='checkbox'][class!='cboxchecked']").click( function () {
			    if($(this).attr("checked")){
					$(this).attr('checked',false);
				}
				else{
					$(this).attr('checked',true);
					}
				var _val=new Array();
				$(this).parents("table").find("input[type='checkbox'][class!='cboxchecked']").each(function(){ 
					if($(this).attr("checked")){
						_val.push($(this).val());
					}
				})
				//alert(_val);
				var str=_val.join("|"); //alert( _val);
				$(this).parents("div.rewrite").children("input").eq(0).val(str); 
				$(this).parents("div.rewrite").children("input").eq(1).val(str); 
			});	
			$(this).children("table").find("input[class='cboxchecked']").click( function () {
				var bischecked=$(this).is(':checked');
				var _iptobj=$(this).parents("table").find("input[type='checkbox'][class!='cboxchecked']");
				   for (var i =0; i<_iptobj.length; i++)
					{
					 _iptobj.get(i).checked = bischecked;
					} 
				bischecked?_iptobj.attr('checked',true):_iptobj.attr('checked',false);
				var _val=new Array();
				$(this).parents("table").find("input[type='checkbox'][class!='cboxchecked']").each(function(){ 
					if($(this).attr("checked")){
						_val.push($(this).val());
					}
				})
				//alert(_val);
				var str=_val.join("|"); //alert( _val);
				$(this).parents("div.rewrite").children("input").eq(0).val(str); 
				$(this).parents("div.rewrite").children("input").eq(1).val(str); 
			});	
			function compare(arr,data)
			{
				for(var i=0;i<arr.length;i++)
				{
					if(arr[i]==data)
					{
						arr.splice(i,1);
						return true;

					}
				}
				return false;
			}

		});
		$("#rewrite_box").show();
	});
</script>
</body>
</html>
