package com.ezmall.service;

import com.ezmall.model.GoodsPrice;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-8-9
 * Time: 下午5:00
 * To change this template use File | Settings | File Templates.
 */
public interface IGoodsPriceService {

      Integer insert(GoodsPrice goodsPrice);

      Integer save(GoodsPrice goodsPrice);

    /**
     * 根据商品编号得到商品变动的价格
     * @param goodsNo 商品编号
     * @param size  显示个数
     * @return
     */
      List<GoodsPrice> getGoodsPricesByGoodsNo(String goodsNo,Integer size);

}
