package com.ezmall.mapper;

import com.ezmall.model.SubPurchase;
import com.ezmall.model.SubPurchaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubPurchaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    int countByExample(SubPurchaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    int deleteByExample(SubPurchaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    int insert(SubPurchase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    int insertSelective(SubPurchase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    List<SubPurchase> selectByExample(SubPurchaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    SubPurchase selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    int updateByExampleSelective(@Param("record") SubPurchase record, @Param("example") SubPurchaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    int updateByExample(@Param("record") SubPurchase record, @Param("example") SubPurchaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    int updateByPrimaryKeySelective(SubPurchase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_purchase_sub
     *
     * @mbggenerated Sun Aug 03 21:19:15 CST 2014
     */
    int updateByPrimaryKey(SubPurchase record);
}