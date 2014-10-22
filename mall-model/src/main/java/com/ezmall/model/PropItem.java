package com.ezmall.model;

import java.util.Date;

public class PropItem {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propitem_info.ID
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propitem_info.NO
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    private String no;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propitem_info.NAME
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propitem_info.ITEM_TYPE
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    private Integer itemType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propitem_info.DEFAULT_VALUE
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    private String defaultValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propitem_info.CREATE_DATE
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propitem_info.ID
     *
     * @return the value of tbl_propitem_info.ID
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propitem_info.ID
     *
     * @param id the value for tbl_propitem_info.ID
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propitem_info.NO
     *
     * @return the value of tbl_propitem_info.NO
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public String getNo() {
        return no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propitem_info.NO
     *
     * @param no the value for tbl_propitem_info.NO
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propitem_info.NAME
     *
     * @return the value of tbl_propitem_info.NAME
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propitem_info.NAME
     *
     * @param name the value for tbl_propitem_info.NAME
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propitem_info.ITEM_TYPE
     *
     * @return the value of tbl_propitem_info.ITEM_TYPE
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public Integer getItemType() {
        return itemType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propitem_info.ITEM_TYPE
     *
     * @param itemType the value for tbl_propitem_info.ITEM_TYPE
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propitem_info.DEFAULT_VALUE
     *
     * @return the value of tbl_propitem_info.DEFAULT_VALUE
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propitem_info.DEFAULT_VALUE
     *
     * @param defaultValue the value for tbl_propitem_info.DEFAULT_VALUE
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propitem_info.CREATE_DATE
     *
     * @return the value of tbl_propitem_info.CREATE_DATE
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propitem_info.CREATE_DATE
     *
     * @param createDate the value for tbl_propitem_info.CREATE_DATE
     *
     * @mbggenerated Thu Jul 10 22:53:20 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}