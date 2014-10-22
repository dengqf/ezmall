package com.ezmall.mapper;

import com.ezmall.model.CategoryPropRp;
import com.ezmall.model.CategoryPropRpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryPropRpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    int countByExample(CategoryPropRpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    int deleteByExample(CategoryPropRpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    int insert(CategoryPropRp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    int insertSelective(CategoryPropRp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    List<CategoryPropRp> selectByExample(CategoryPropRpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    CategoryPropRp selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    int updateByExampleSelective(@Param("record") CategoryPropRp record, @Param("example") CategoryPropRpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    int updateByExample(@Param("record") CategoryPropRp record, @Param("example") CategoryPropRpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    int updateByPrimaryKeySelective(CategoryPropRp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_category_prop_rp
     *
     * @mbggenerated Wed Jun 18 15:59:44 CST 2014
     */
    int updateByPrimaryKey(CategoryPropRp record);
}