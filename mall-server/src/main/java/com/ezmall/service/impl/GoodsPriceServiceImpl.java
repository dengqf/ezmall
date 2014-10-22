package com.ezmall.service.impl;

import com.ezmall.mapper.GoodsPriceMapper;
import com.ezmall.model.GoodsPrice;
import com.ezmall.model.GoodsPriceExample;
import com.ezmall.service.IGoodsPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-8-9
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
@Service
public class GoodsPriceServiceImpl implements IGoodsPriceService {
    @Autowired
    GoodsPriceMapper goodsPriceMapper;

    @Override
    public Integer insert(GoodsPrice goodsPrice) {
        return goodsPriceMapper.insertSelective(goodsPrice);
    }

    @Override
    public Integer save(GoodsPrice goodsPrice) {
        return goodsPriceMapper.updateByPrimaryKeySelective(goodsPrice);
    }

    @Override
    public List<GoodsPrice> getGoodsPricesByGoodsNo(String goodsNo, Integer size) {
        GoodsPriceExample example=new GoodsPriceExample();
        example.or().andGoodsNoEqualTo(goodsNo);
        example.setOrderByClause("CREATE_DATE ASC LIMIT "+size);
        return goodsPriceMapper.selectByExample(example);
    }
}
