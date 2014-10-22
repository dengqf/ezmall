package com.ezmall.apiImpl;

import com.ezmall.api.IGoodsBaseApi;
import com.ezmall.dto.GoodsDto;
import com.ezmall.dto.ResultDto;
import com.ezmall.model.*;
import com.ezmall.service.*;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-10
 * Time: 下午11:35
 * To change this template use File | Settings | File Templates.
 */
@Service
public class GoodsBaseApiImpl implements IGoodsBaseApi {

    @Autowired
    IGoodsService goodsService;
    @Autowired
    IGoodsPriceService goodsPriceService;

    @Override
    public ResultDto importGoods(GoodsDto dto) {
        return goodsService.importGoods(dto);
    }

    @Override
    public String insertGoods(Map<String, String> goodsMap, Map<String, String> propMap) {
        GoodsDto dto = new GoodsDto();
        String brandName = goodsMap.get("brandName");
        String brandIndex = goodsMap.get("brandIndex"); //品牌英文索引
        String brandEnglishName = goodsMap.get("brandEnglishName"); //品牌英文名
        String structName = goodsMap.get("structName");  //分类结构名称，3及
        String goodsName = goodsMap.get("goodsName");  //商品名称
        //  String goodsIndex = goodsMap.get("goodsIndex");//商品英文索引
        String goodsDesc = goodsMap.get("goodsDesc");   //商品描述
        String marketPrice = goodsMap.get("marketPrice");  //市场价格
        String sellPrice = goodsMap.get("sellPrice");  //销售价格
        String thirdNo = goodsMap.get("thirdNo");  //第三方货品NO
        String thirdLink = goodsMap.get("thirdLink");  //第三方货品URL
        String thirdDomain = goodsMap.get("thirdDomain");  //第三方货品URL
        String currencyType = goodsMap.get("currencyType");
        String country = goodsMap.get("country"); //国家
        String countryIndex = goodsMap.get("countryIndex"); //国家
        String city = goodsMap.get("city"); //城市
        String mall = goodsMap.get("mall"); //商城

        Assert.hasText(brandName, "品牌名称不能为空");
        Assert.hasText(brandIndex, "品牌首字母不能为空");
        Assert.hasText(goodsName, "商品名称不能为空");
        Assert.hasText(structName, "商品分类不能为空");
        Assert.hasText(thirdNo, "第三方商品编号不能为空");
        Assert.hasText(thirdDomain, "第三方DOMAIN不能为空");
        Assert.hasText(country, "国家不能为空");
        Assert.hasText(countryIndex, "国家首字母不能为空");
        Assert.hasText(city, "城市不能为空");
        Assert.hasText(mall, "商场名称不能为空");
        Assert.hasText(currencyType, "货币类型不能为空");
        Assert.hasText(sellPrice, "销售价格不能为空");
        Assert.hasText(marketPrice, "市场价格不能为空");

        /**
         * 如果已经存在则不插入相同的，并比较价格
         */
        if (StringUtils.isNotBlank(thirdNo) && StringUtils.isNotBlank(thirdDomain)) {
            Goods old = goodsService.isGoodImport(thirdNo, thirdDomain);
            if (old != null) {
                //如果价格发生了变化，则修改价格
                Double price = Double.parseDouble(sellPrice);
                if (!old.getSellPrice().equals(price)) {
                    GoodsPrice goodsPrice = new GoodsPrice();
                    goodsPrice.setPrice(price);
                    goodsPrice.setUsername("API");
                    goodsPrice.setGoodsNo(old.getNo());
                    goodsPrice.setId(UUIDUtil.getUUID());
                    goodsPrice.setCreateDate(new Date());
                    goodsPriceService.insert(goodsPrice);
                    old.setSellPrice(price);
                    goodsService.save(old);
                    return "商品已经存在,并更新价格.";
                }

                return "商品已经存在.";
            }
        }


        Goods goods = new Goods();
        goods.setName(goodsName);
        goods.setGoodsDesc(goodsDesc);
        goods.setThirdNo(thirdNo);
        goods.setThirdLink(thirdLink);
        goods.setThirdDomain(thirdDomain);
        goods.setCurrencyType(currencyType);
        goods.setMarketPrice(Double.parseDouble(marketPrice));
        goods.setSellPrice(Double.parseDouble(sellPrice));
        goods.setCountryIndex(countryIndex);
        goods.setCountry(country);
        goods.setMall(mall);
        goods.setCity(city);


        dto.setGoods(goods);
        dto.setBrandEnglishName(brandEnglishName);
        dto.setBrandName(brandName);
        dto.setBrandIndex(brandIndex);
        dto.setPropMap(propMap);
        dto.setStructName(structName);

        return importGoods(dto).getMessage();
    }

    @Override
    public boolean isGoodExists(String thirdNo, String thridDomain) {
        return goodsService.isGoodImport(thirdNo, thridDomain) != null;
    }

    @Override
    public String insertGoodsPrice(String thirdNo, String thirdDomain, Double price) {
        Goods goods = goodsService.isGoodImport(thirdNo, thirdDomain);
        if (goods != null) {
            if (goods.getSellPrice().equals(price)) {
                return "商品价格无变动,第三方货品号:" + thirdNo + ",第三方网站:" + thirdDomain;
            }
            GoodsPrice goodsPrice = new GoodsPrice();
            goodsPrice.setId(UUIDUtil.getUUID());
            goodsPrice.setGoodsNo(goods.getNo());
            goodsPrice.setPrice(price);
            goodsPrice.setUsername("API");
            goodsPrice.setCreateDate(new Date());
            goodsPriceService.insert(goodsPrice);
            return CommonConstrant.SUCCESS;
        } else {
            return "找不到对应的商品,第三方货品号:" + thirdNo + ",第三方网站:" + thirdDomain;
        }

    }

    @Override
    public String getStr(String str) {
        System.out.print("接收的参数为:" + str);
        return str;
    }
}
