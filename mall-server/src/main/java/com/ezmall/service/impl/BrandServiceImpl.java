/*
 * BrandServiceImpl.java 
 * Version: 2.01   2014-6-11
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.service.impl;

import com.ezmall.mapper.BrandMapper;
import com.ezmall.mapper.CategoryBrandRpMapper;
import com.ezmall.model.Brand;
import com.ezmall.model.BrandExample;
import com.ezmall.model.CategoryBrandRp;
import com.ezmall.model.CategoryBrandRpExample;
import com.ezmall.service.IBrandService;
import com.ezmall.service.ICategoryService;
import com.ezmall.utils.CommonUtil;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryBrandRpMapper categoryBrandRpMapper;
    @Autowired
    ICategoryService categoryService;

    @Override
    public Integer insert(Brand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public Integer save(Brand brand) {
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    @Transactional
    public Integer delBrand(Brand brand) {
        CategoryBrandRpExample example = new CategoryBrandRpExample();
        example.or().andBrandNoEqualTo(brand.getNo());
        categoryBrandRpMapper.deleteByExample(example);
        return brandMapper.deleteByPrimaryKey(brand.getId());
    }


    @Override
    public Brand getBrandById(String id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public Brand getBrandByNo(String no) {
        BrandExample example = new BrandExample();
        example.or().andNoEqualTo(no);
        return CommonUtil.getFirstObject(brandMapper.selectByExample(example));
    }

    @Override
    public Brand getBrandByName(String name) {
        BrandExample example = new BrandExample();
        example.or().andNameEqualTo(name);
        return CommonUtil.getFirstObject(brandMapper.selectByExample(example));
    }

    @Override
    public Brand getBrandByEnglishName(String englishName) {
        BrandExample example = new BrandExample();
        example.or().andEnglishNameEqualTo(englishName);
        return CommonUtil.getFirstObject(brandMapper.selectByExample(example));
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandMapper.selectByExample(null);
    }

    @Override
    public List<Brand> getBrandsByCategoryNo(String categoryNo) {
        List<Brand> brands = null;
        List<CategoryBrandRp> rps = categoryService.getCategoryBrandRpsByCategoryNo(categoryNo);
        List<String> brandNos = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(rps)) {
            for (CategoryBrandRp obj : rps) {
                brandNos.add(obj.getBrandNo());
            }
        }
        if (CollectionUtils.isNotEmpty(brandNos)) {
            BrandExample example = new BrandExample();
            example.or().andNoIn(brandNos);
            brands = brandMapper.selectByExample(example);
        } else {
            brands = new ArrayList<Brand>();
        }
        return brands;
    }

    @Override
    public List<Brand> getBrandsByIndex(String index) {
        BrandExample example = new BrandExample();
        example.or().andBrandIndexEqualTo(index);
        return brandMapper.selectByExample(example);
    }

    @Override
    public PageData<Brand> getBrandPageData(Brand brand, Query query) {
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.or();
        if (StringUtils.isNotBlank(brand.getName())) {
            criteria.andNameLike("%" + brand.getName() + "%");
        }
        if (StringUtils.isNotBlank(brand.getEnglishName())) {
            criteria.andEnglishNameLike("%" + brand.getEnglishName() + "%");
        }
        Integer rowsCount = brandMapper.countByExample(example);
        PageData<Brand> pageData = null;
        if (rowsCount < 1) {
            pageData = new PageData<Brand>(1, 0);
        } else {
            pageData = new PageData<Brand>(query.getPage(), query.getPageSize(), rowsCount);

            pageData.setData(brandMapper.selectByExample(example, CommonUtil.getRowBounds(query)));
        }

        return pageData;
    }
}
