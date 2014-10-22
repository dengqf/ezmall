package com.ezmall.service.impl;

import com.ezmall.mapper.AdminMapper;
import com.ezmall.mapper.AdminRoleRpMapper;
import com.ezmall.mapper.RoleMapper;
import com.ezmall.model.*;
import com.ezmall.service.IAdminService;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-7-3
 * Time: 下午9:57
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AdminRoleRpMapper adminRoleRpMapper;

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.selectByExample(null);
    }

    @Override
    public Admin getAdminById(String id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        AdminExample example = new AdminExample();
        example.or().andUsernameEqualTo(username);
        return CommonUtil.getFirstObject(adminMapper.selectByExample(example));
    }

    @Override
    public List<AdminRoleRp> getAdminRoleRps(String adminId) {
        AdminRoleRpExample example = new AdminRoleRpExample();
        example.or().andUserIdEqualTo(adminId);
        return adminRoleRpMapper.selectByExample(example);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        AdminExample example = new AdminExample();
        example.or().andEmailEqualTo(email);
        return CommonUtil.getFirstObject(adminMapper.selectByExample(example));
    }

    @Override
    public Admin geAdminByMobile(String mobile) {
        AdminExample example = new AdminExample();
        example.or().andMobileEqualTo(mobile);
        return CommonUtil.getFirstObject(adminMapper.selectByExample(example));
    }

    @Override
    public Role getRoleById(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Integer insertAdmin(Admin admin, List<AdminRoleRp> rpList) {
        admin.setCreateDate(new Date());

        for (AdminRoleRp rp : rpList) {
            rp.setUserId(admin.getId());
            rp.setId(UUIDUtil.getUUID());
            adminRoleRpMapper.insertSelective(rp);
        }
        return adminMapper.insertSelective(admin);
    }

    @Override
    @Transactional
    public Integer saveAdmin(Admin admin, List<AdminRoleRp> rpList) {
        //先删除，再插入
        if (CollectionUtils.isNotEmpty(rpList)) {
            AdminRoleRpExample example = new AdminRoleRpExample();
            example.or().andUserIdEqualTo(admin.getId());
            adminRoleRpMapper.deleteByExample(example);

            for (AdminRoleRp rp : rpList) {
                rp.setUserId(admin.getId());
                rp.setId(UUIDUtil.getUUID());
                adminRoleRpMapper.insertSelective(rp);
            }
        }

        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public Integer save(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public PageData<Admin> getAdminPageData(Admin admin, Query query) {
        PageData<Admin> pageData = null;
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.or();
        if (StringUtils.isNotBlank(admin.getUsername())) {
            criteria.andUsernameLike("%" + admin.getUsername() + "%");
        }

        if (StringUtils.isNotBlank(admin.getCompany())) {
            criteria.andCompanyLike("%" + admin.getCompany() + "%");
        }
        if (StringUtils.isNotBlank(admin.getType())) {
            criteria.andTypeEqualTo(admin.getType());
        }
        int row = adminMapper.countByExample(example);
        if (row < 1) {
            pageData = new PageData<Admin>(1, 0);
        } else {
            pageData = new PageData<Admin>(query.getPage(), query.getPageSize(), row);
            List<Admin> list = adminMapper.selectByExample(example, CommonUtil.getRowBounds(query));
            pageData.setData(list);
        }
        return pageData;
    }
}
