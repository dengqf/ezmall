package com.ezmall.model;

import java.util.Date;

public class Category {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_info.ID
     *
     * @mbggenerated Thu Jun 19 13:28:04 CST 2014
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_info.NO
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    private String no;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_info.NAME
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_info.STRUCT
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    private String struct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_info.STRUCT_NAME
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    private String structName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_info.LEVEL
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    private String level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_info.PARENT_NO
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    private String parentNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_info.UPDATE_DATE
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_info.CREATE_DATE
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_category_info.VALID_FLAG
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    private String validFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_info.ID
     *
     * @return the value of tbl_category_info.ID
     *
     * @mbggenerated Thu Jun 19 13:28:04 CST 2014
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_info.ID
     *
     * @param id the value for tbl_category_info.ID
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_info.NO
     *
     * @return the value of tbl_category_info.NO
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public String getNo() {
        return no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_info.NO
     *
     * @param no the value for tbl_category_info.NO
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_info.NAME
     *
     * @return the value of tbl_category_info.NAME
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_info.NAME
     *
     * @param name the value for tbl_category_info.NAME
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_info.STRUCT
     *
     * @return the value of tbl_category_info.STRUCT
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public String getStruct() {
        return struct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_info.STRUCT
     *
     * @param struct the value for tbl_category_info.STRUCT
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public void setStruct(String struct) {
        this.struct = struct == null ? null : struct.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_info.STRUCT_NAME
     *
     * @return the value of tbl_category_info.STRUCT_NAME
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public String getStructName() {
        return structName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_info.STRUCT_NAME
     *
     * @param structName the value for tbl_category_info.STRUCT_NAME
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public void setStructName(String structName) {
        this.structName = structName == null ? null : structName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_info.LEVEL
     *
     * @return the value of tbl_category_info.LEVEL
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public String getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_info.LEVEL
     *
     * @param level the value for tbl_category_info.LEVEL
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_info.PARENT_NO
     *
     * @return the value of tbl_category_info.PARENT_NO
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public String getParentNo() {
        return parentNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_info.PARENT_NO
     *
     * @param parentNo the value for tbl_category_info.PARENT_NO
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public void setParentNo(String parentNo) {
        this.parentNo = parentNo == null ? null : parentNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_info.UPDATE_DATE
     *
     * @return the value of tbl_category_info.UPDATE_DATE
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_info.UPDATE_DATE
     *
     * @param updateDate the value for tbl_category_info.UPDATE_DATE
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_info.CREATE_DATE
     *
     * @return the value of tbl_category_info.CREATE_DATE
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_info.CREATE_DATE
     *
     * @param createDate the value for tbl_category_info.CREATE_DATE
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_category_info.VALID_FLAG
     *
     * @return the value of tbl_category_info.VALID_FLAG
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public String getValidFlag() {
        return validFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_category_info.VALID_FLAG
     *
     * @param validFlag the value for tbl_category_info.VALID_FLAG
     *
     * @mbggenerated Thu Jun 19 13:28:05 CST 2014
     */
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag == null ? null : validFlag.trim();
    }
}