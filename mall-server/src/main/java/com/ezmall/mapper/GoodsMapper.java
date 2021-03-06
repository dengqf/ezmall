package com.ezmall.mapper;

import com.ezmall.model.Area;
import com.ezmall.model.Goods;
import com.ezmall.model.GoodsExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    int countByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    int deleteByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    int insert(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    int insertSelective(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    List<Goods> selectByExample(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    Goods selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    int updateByPrimaryKeySelective(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_info
     *
     * @mbggenerated Wed Jun 18 23:19:05 CST 2014
     */
    int updateByPrimaryKey(Goods record);

    List<Area> getCountryList(String index);

    List<Area> getAllCountryList();

    List<Area> getCityList(String country);

    List<Area> getMallList(@Param("country")String country, @Param("city")String city);
}