package com.ezmall.api;

import com.ezmall.dto.GoodsDto;
import com.ezmall.dto.ResultDto;
import com.ezmall.model.Brand;
import com.ezmall.model.Category;
import com.ezmall.model.Goods;
import com.ezmall.model.Product;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-10
 * Time: 下午9:34
 * To change this template use File | Settings | File Templates.
 */
public interface IGoodsBaseApi {


    ResultDto importGoods(GoodsDto dto);

    /**
     * <"brandName",String>
     * <"brandEnglishName",String>
     * <"structName",String>
     * <"goodsName",String> --商品名
     * <"goodsDesc",String> --商品描述
     * <"marketPrice",String> --市场价格
     * <"sellPrice",String> --销售价格
     * <"thirdNo",String> --第三方商品编号
     * <"currencyType",String> --货币类型 人民币 0 美元 1
     *
     *
     * @param goodsMap
     * @param propMap
     * @return
     */
    String insertGoods(Map<String, String> goodsMap, Map<String, String> propMap);

    /**
     * 判断抓取货品是否存在
     * @param thirdNo  第三方货品号
     * @param thirdDomain    第三方网站名
     * @return     存在返回TRUE，不存在返回FALSE
     */
     boolean isGoodExists(String thirdNo, String thirdDomain);


    /**
     *插入变动的价格
     * @param thirdNo  第三方货品号
     * @param thirdDomain    第三方网站名
     * @param price  价格
     * @return
     */
     String insertGoodsPrice(String thirdNo, String thirdDomain,Double price);



    String getStr(String str);

}
