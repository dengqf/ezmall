/*
 * GoodsDto.java 
 * Version: 2.01   2014-6-11
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.dto;

import com.ezmall.model.Goods;
import com.ezmall.model.Product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
public class GoodsDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4459640620715794481L;
	private Goods goods;
    private String brandName;
    private String brandEnglishName;
    private String brandIndex;
    private String structName;
    private List<Product> products;
    private Map<String, String> propMap;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandEnglishName() {
        return brandEnglishName;
    }

    public void setBrandEnglishName(String brandEnglishName) {
        this.brandEnglishName = brandEnglishName;
    }

    public String getStructName() {
        return structName;
    }

    public void setStructName(String structName) {
        this.structName = structName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Map<String, String> getPropMap() {
        return propMap;
    }

    public void setPropMap(Map<String, String> propMap) {
        this.propMap = propMap;
    }

    public String getBrandIndex() {
        return brandIndex;
    }

    public void setBrandIndex(String brandIndex) {
        this.brandIndex = brandIndex;
    }
}
