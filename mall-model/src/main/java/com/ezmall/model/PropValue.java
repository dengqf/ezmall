package com.ezmall.model;

import java.util.Date;

public class PropValue {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propvalue_info.ID
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propvalue_info.NO
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    private String no;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propvalue_info.NAME
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propvalue_info.ITEM_NO
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    private String itemNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_propvalue_info.CREATE_DATE
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propvalue_info.ID
     *
     * @return the value of tbl_propvalue_info.ID
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propvalue_info.ID
     *
     * @param id the value for tbl_propvalue_info.ID
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propvalue_info.NO
     *
     * @return the value of tbl_propvalue_info.NO
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    public String getNo() {
        return no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propvalue_info.NO
     *
     * @param no the value for tbl_propvalue_info.NO
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propvalue_info.NAME
     *
     * @return the value of tbl_propvalue_info.NAME
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propvalue_info.NAME
     *
     * @param name the value for tbl_propvalue_info.NAME
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propvalue_info.ITEM_NO
     *
     * @return the value of tbl_propvalue_info.ITEM_NO
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propvalue_info.ITEM_NO
     *
     * @param itemNo the value for tbl_propvalue_info.ITEM_NO
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_propvalue_info.CREATE_DATE
     *
     * @return the value of tbl_propvalue_info.CREATE_DATE
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_propvalue_info.CREATE_DATE
     *
     * @param createDate the value for tbl_propvalue_info.CREATE_DATE
     *
     * @mbggenerated Tue Jun 10 21:32:13 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}