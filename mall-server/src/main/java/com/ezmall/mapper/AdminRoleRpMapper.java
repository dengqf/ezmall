package com.ezmall.mapper;

import com.ezmall.model.AdminRoleRp;
import com.ezmall.model.AdminRoleRpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminRoleRpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    int countByExample(AdminRoleRpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    int deleteByExample(AdminRoleRpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    int insert(AdminRoleRp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    int insertSelective(AdminRoleRp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    List<AdminRoleRp> selectByExample(AdminRoleRpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    AdminRoleRp selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    int updateByExampleSelective(@Param("record") AdminRoleRp record, @Param("example") AdminRoleRpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    int updateByExample(@Param("record") AdminRoleRp record, @Param("example") AdminRoleRpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    int updateByPrimaryKeySelective(AdminRoleRp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_admin_role_rp
     *
     * @mbggenerated Thu Jul 03 21:55:57 CST 2014
     */
    int updateByPrimaryKey(AdminRoleRp record);
}