package com.ezmall.mapper;

import com.ezmall.model.GoodsPrice;
import com.ezmall.model.GoodsPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsPriceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    int countByExample(GoodsPriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    int deleteByExample(GoodsPriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    int insert(GoodsPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    int insertSelective(GoodsPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    List<GoodsPrice> selectByExample(GoodsPriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    GoodsPrice selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    int updateByExampleSelective(@Param("record") GoodsPrice record, @Param("example") GoodsPriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    int updateByExample(@Param("record") GoodsPrice record, @Param("example") GoodsPriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    int updateByPrimaryKeySelective(GoodsPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_price
     *
     * @mbggenerated Sat Aug 09 17:00:20 CST 2014
     */
    int updateByPrimaryKey(GoodsPrice record);
}