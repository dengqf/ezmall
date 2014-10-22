package com.ezmall.service.impl;

import com.ezmall.mapper.GoodsMapper;
import com.ezmall.model.Area;
import com.ezmall.model.GoodsExample;
import com.ezmall.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-8-3
 * Time: 下午9:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public List<Area> getCountryList(String index) {
       return goodsMapper.getCountryList(index);
    }

    @Override
    public List<Area> getCityList(String country) {
        return goodsMapper.getCityList(country);
    }

    @Override
    public List<Area> getMallList(String country, String city) {
        return goodsMapper.getMallList(country,city);
    }
}
