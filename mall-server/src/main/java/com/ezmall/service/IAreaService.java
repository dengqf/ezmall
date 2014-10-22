package com.ezmall.service;

import com.ezmall.model.Area;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-8-3
 * Time: 下午9:35
 * To change this template use File | Settings | File Templates.
 */
public interface IAreaService {
    /**
     * @param index 国家开头首字母
     * @return
     */
    List<Area> getCountryList(String index);

    /**
     * 根据国家得到城市列表
     *
     * @param country
     * @return
     */
    List<Area> getCityList(String country);

    /**
     * 根据国家和城市，得到商城列表
     * @param country
     * @param city
     * @return
     */
    List<Area> getMallList(String country, String city);
}
