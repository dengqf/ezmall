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





    String getStr(String str);

}
