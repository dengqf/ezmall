/*
 * ShopCartVo.java 
 * Version: 2.01   2014-6-25
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.vo;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-25
 */
public class ShopCartVo {

    private String no;   //编号，暂时为商品，以后修改为货品
    private String goodsName;
    private String goodsNo; //冗余，为以后做准备
    private String goodsPicture;
    private String currencyType;
    private Double sellPrice;
    private Double priceToCNY;//折算成RMB
    private Integer amount;//购买数量
    private String specName;//购买的商品规格名称
    private String size;//购买的商品尺码
    private String status;//购买的商品状态

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getPriceToCNY() {
        return priceToCNY;
    }

    public void setPriceToCNY(Double priceToCNY) {
        this.priceToCNY = priceToCNY;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }
}
