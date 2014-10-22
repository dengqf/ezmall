package com.ezmall.mapper;

import com.ezmall.model.Favorite;
import com.ezmall.model.FavoriteExample;

import java.util.List;

import com.ezmall.vo.FavoriteVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FavoriteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    int countByExample(FavoriteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    int deleteByExample(FavoriteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    int insert(Favorite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    int insertSelective(Favorite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    List<Favorite> selectByExample(FavoriteExample example);

    List<Favorite> selectByExample(FavoriteExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    Favorite selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    int updateByExampleSelective(@Param("record") Favorite record, @Param("example") FavoriteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    int updateByExample(@Param("record") Favorite record, @Param("example") FavoriteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    int updateByPrimaryKeySelective(Favorite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_favorite
     *
     * @mbggenerated Thu Jun 26 22:43:59 CST 2014
     */
    int updateByPrimaryKey(Favorite record);

    List<FavoriteVo> getMemberFavoriteVos(String username, RowBounds rowBounds);

    Integer countMemberFavoriteVos(String username);
}