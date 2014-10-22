package com.ezmall.service;

import com.ezmall.model.Brand;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-10
 * Time: 下午11:42
 * To change this template use File | Settings | File Templates.
 */
public interface IBrandService {

    Integer insert(Brand brand);

    Integer save(Brand brand);

    Integer delBrand(Brand brand);

    Brand getBrandById(String id);

    Brand getBrandByNo(String no);

    Brand getBrandByName(String name);

    Brand getBrandByEnglishName(String englishName);

    List<Brand> getAllBrands();

    List<Brand> getBrandsByCategoryNo(String categoryNo);

    /**
     * 根据品牌开头字母得到品牌列表
     * @param index
     * @return
     */
    List<Brand> getBrandsByIndex(String index);

    PageData<Brand> getBrandPageData(Brand brand,Query query);

}
