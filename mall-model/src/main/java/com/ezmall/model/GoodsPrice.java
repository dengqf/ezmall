package com.ezmall.model;

import java.util.Date;

public class GoodsPrice {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_price.ID
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_price.GOODS_NO
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    private String goodsNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_price.PRICE
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    private Double price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_price.USERNAME
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_price.CREATE_DATE
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_price.ID
     *
     * @return the value of tbl_goods_price.ID
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_price.ID
     *
     * @param id the value for tbl_goods_price.ID
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_price.GOODS_NO
     *
     * @return the value of tbl_goods_price.GOODS_NO
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    public String getGoodsNo() {
        return goodsNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_price.GOODS_NO
     *
     * @param goodsNo the value for tbl_goods_price.GOODS_NO
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_price.PRICE
     *
     * @return the value of tbl_goods_price.PRICE
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_price.PRICE
     *
     * @param price the value for tbl_goods_price.PRICE
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_price.USERNAME
     *
     * @return the value of tbl_goods_price.USERNAME
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_price.USERNAME
     *
     * @param username the value for tbl_goods_price.USERNAME
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_price.CREATE_DATE
     *
     * @return the value of tbl_goods_price.CREATE_DATE
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_price.CREATE_DATE
     *
     * @param createDate the value for tbl_goods_price.CREATE_DATE
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}