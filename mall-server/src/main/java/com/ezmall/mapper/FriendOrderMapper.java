package com.ezmall.mapper;

import com.ezmall.model.FriendOrder;
import com.ezmall.model.FriendOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FriendOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    int countByExample(FriendOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    int deleteByExample(FriendOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    int insert(FriendOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    int insertSelective(FriendOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    List<FriendOrder> selectByExample(FriendOrderExample example);

    List<FriendOrder> selectByExample(FriendOrderExample example,RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    FriendOrder selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    int updateByExampleSelective(@Param("record") FriendOrder record, @Param("example") FriendOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    int updateByExample(@Param("record") FriendOrder record, @Param("example") FriendOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    int updateByPrimaryKeySelective(FriendOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend_order
     *
     * @mbggenerated Sat Jul 19 15:42:15 CST 2014
     */
    int updateByPrimaryKey(FriendOrder record);
}