package com.ezmall.vo;

import com.ezmall.model.Goods;
import com.ezmall.model.GoodsPropRp;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-8-10
 * Time: 下午12:39
 * To change this template use File | Settings | File Templates.
 */
public class GoodsVo {
    Goods goods;
    List<GoodsPropRp> goodsPropRps;

    public GoodsVo(Goods goods, List<GoodsPropRp> goodsPropRps) {
        this.goods = goods;
        this.goodsPropRps = goodsPropRps;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<GoodsPropRp> getGoodsPropRps() {
        return goodsPropRps;
    }

    public void setGoodsPropRps(List<GoodsPropRp> goodsPropRps) {
        this.goodsPropRps = goodsPropRps;
    }
}
