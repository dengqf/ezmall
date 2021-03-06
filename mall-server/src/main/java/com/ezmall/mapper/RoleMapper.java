package com.ezmall.mapper;

import com.ezmall.model.Role;
import com.ezmall.model.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    int countByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    int deleteByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    List<Role> selectByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    Role selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbggenerated Thu Jul 03 21:55:09 CST 2014
     */
    int updateByPrimaryKey(Role record);
}