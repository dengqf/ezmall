package com.ezmall.service;

import com.ezmall.model.Admin;
import com.ezmall.model.AdminRoleRp;
import com.ezmall.model.Role;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-7-3
 * Time: 下午9:57
 * To change this template use File | Settings | File Templates.
 */
public interface IAdminService {
    /**
     * 得到所有的角色
     * @return
     */
    List<Role> getAllRoles();

    Admin getAdminById(String id);

    Admin getAdminByUsername(String username);

    /**
     * 根据管理员ID得到管理员与角色的关联标
     * @param adminId
     * @return
     */
    List<AdminRoleRp> getAdminRoleRps(String adminId);

    Admin getAdminByEmail(String email);

    Admin geAdminByMobile(String mobile);

    Role getRoleById(String id);

    Integer insertAdmin(Admin admin,List<AdminRoleRp> rpList);

    Integer saveAdmin(Admin admin,List<AdminRoleRp> rpList);

    Integer save(Admin admin);

    PageData<Admin> getAdminPageData(Admin admin,Query query);





}
