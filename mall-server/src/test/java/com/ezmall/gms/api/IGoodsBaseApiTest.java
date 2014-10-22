/*
 * IGoodsBaseApiTest.java 
 * Version: 2.01   2014-7-8
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.gms.api;

import com.ezmall.api.IGoodsBaseApi;
import com.ezmall.gms.BaseTestCase;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-7-8
 */
public class IGoodsBaseApiTest extends BaseTestCase {
    @Resource
    IGoodsBaseApi goodsBaseApi;

    public void testGetStr() {
//    String str=    goodsBaseApi.getStr("BALABALA");
//    assertNotNull(str);
        String s1 = "name#*#1101#*#13#-18#";
        String[] propInfo = StringUtils.splitByWholeSeparator(s1, "#*#");
        assertNotNull(propInfo);
    }

    public void testInsertGoods() {
//        String brandName = goodsMap.get("brandName");
//        String brandEnglishName = goodsMap.get("brandEnglishName");
//        String structName = goodsMap.get("structName");
//        String goodsName = goodsMap.get("goodsName");
//        String goodsDesc = goodsMap.get("goodsDesc");
//        String marketPrice = goodsMap.get("marketPrice");
//        String sellPrice = goodsMap.get("sellPrice");
//        String thirdNo = goodsMap.get("thirdNo");
//        String currencyType = goodsMap.get("currencyType");
        Map<String, String> goodsMap = new HashMap<String, String>();
        goodsMap.put("brandName", "耐克");
        goodsMap.put("brandEnglishName", "nike");
        goodsMap.put("brandIndex", "N");
        goodsMap.put("structName", "鞋子-男鞋-运动鞋");
        goodsMap.put("goodsName", "耐克2014跑步鞋");
        goodsMap.put("goodsIndex", "N");
        goodsMap.put("goodsDesc", "耐克2014跑步鞋是耐克新推出的一款高科技跑步鞋");
        goodsMap.put("marketPrice", "252");
        goodsMap.put("sellPrice", "70");
        goodsMap.put("thirdNo", "2014002");
        goodsMap.put("currencyType", "1");
        goodsMap.put("country", "美国");
        goodsMap.put("countryIndex", "U");
        goodsMap.put("city", "纽约");


        Map<String, String> propMap = new HashMap<String, String>();
        propMap.put("有效期", "13年");
        propMap.put("产地", "芬兰3");
        propMap.put("适用部位", "脸部2");
        propMap.put("成分", "天然植物");
        propMap.put("成分1", "天然植物1");
        propMap.put("成分2", "天然植物2");
        propMap.put("成分3", "天然植物3");
        propMap.put("成分4", "天然植物5");

        goodsBaseApi.insertGoods(goodsMap, propMap);
    }

    public void testStre() {
        String str = "va<br>\\r\\n<br>是在淡紫色基础上加入蝴蝶图案的吸油纸产品，可以吸收皮肤的油分，保持皮肤的洁净。 <br>\\r\\n<br> <br>\\r\\n<br> <br>\\r\\n<p><strong><font color=\\\"#ff0000\\\">*购买时请留意上述商品最最多只可购买20个。<\\/font><\\/strong><\\/p><br>\\r\\n<\\/dd>\\r\\n\\t\\t\\t<\\/dl>\"}";
        String[] searchList = new String[]{"\\r", "\\n", "\\t"};
        String[] replacementList = new String[]{"", "", ""};
        str = StringUtils.replaceEach(str, searchList, replacementList);
//      str= str.replaceAll("\\\\r","@") ;
        System.out.print(str);
    }
}
