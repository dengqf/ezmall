package com.ezmall.controller;

import com.ezmall.model.Admin;
import com.ezmall.model.AdminRoleRp;
import com.ezmall.model.Brand;
import com.ezmall.model.Role;
import com.ezmall.service.IAdminService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.ImgUploadUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.MessageVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-7-3
 * Time: 下午9:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/gms/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;
    @Autowired
    private PasswordService passwordService;


    @RequestMapping(value = "toAdminManager.html")
    public String toAdminManager(ModelMap modelMap, Admin admin) {
        modelMap.put("admin", admin);
        return "gms/admin_manager";
    }

    @RequestMapping(value = "adminManager.html")
    public String adminManager(ModelMap modelMap, Admin admin, Query query) {

        PageData<Admin> pageData = adminService.getAdminPageData(admin, query);
        modelMap.put("admin", admin);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        return "gms/admin_manager";
    }

    @RequestMapping(value = "toEditAdmin.html")
    public String toEditAdmin(ModelMap modelMap, String id) {

        Admin admin = adminService.getAdminById(id);
        List<AdminRoleRp> rpList = null;
        if (null == admin) {
            admin = new Admin();
        } else {
            rpList = adminService.getAdminRoleRps(admin.getId());
        }
        //得到管理员与角色的关系
        if (CollectionUtils.isEmpty(rpList)) {
            rpList = new ArrayList<AdminRoleRp>();
        }
        List<Role> roles = adminService.getAllRoles();
        modelMap.put("admin", admin);
        modelMap.put("roles", roles);
        modelMap.put("rpList", rpList);
        return "gms/admin_edit";

    }


    @RequestMapping(value = "editAdmin.html")
    public String editAdmin(ModelMap modelMap, Admin admin, HttpServletRequest request) {
        String picParam = "pic";
        //如果更新了图片
//        String checkPic = ImgUploadUtil.isSpecImgIfExist(request, picParam);
//        if (!StringUtils.equals(checkPic, CommonConstrant.SUCCESS)) {
//            modelMap.put(CommonConstrant.MESSAGE_ERROR, checkPic);
//            return "redirect:/error.html";
//        }


        //注册用户
        if (StringUtils.isBlank(admin.getId())) {
            admin.setId(UUIDUtil.getUUID());

            Admin old = adminService.getAdminByUsername(admin.getUsername());
            if (old != null) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, "用户名已经被注册，请重新选择");
                return "redirect:/error.html";
            }
            old = adminService.geAdminByMobile(admin.getMobile());
            if (old != null) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, "手机号码已经被其他人注册，如有问题，请与客服联系");
                return "redirect:/error.html";
            }
            old = adminService.getAdminByEmail(admin.getEmail());
            if (old != null) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, "EMAIL已经被其他人注册，如有问题，请与客服联系");
                return "redirect:/error.html";
            }


            List<AdminRoleRp> rpList = getAdminRoleRpsByAdminType(admin.getType());
            if (CollectionUtils.isEmpty(rpList)) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, "请为用户选择相应的权限");
                return "redirect:/error.html";
            }


            String password = passwordService.encryptPassword(admin.getPassword());
            admin.setPassword(password);
            adminService.insertAdmin(admin, rpList);
            modelMap.put(CommonConstrant.MESSAGE_INFO,"用户资料创建成功");


        } else {
            //检查用户是否存在
            Admin obj = adminService.getAdminById(admin.getId());
            //用户不存在或者ID不相等
            if (obj == null) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, "用户不存在");
                return "redirect:/error.html";
            }
            //修改
            admin.setUsername(null);
            //如果手机号码有修改，则检查是否有注册
            if (!StringUtils.equals(obj.getMobile(), admin.getMobile())) {
                Admin old = adminService.geAdminByMobile(admin.getMobile());
                if (old != null) {
                    modelMap.put(CommonConstrant.MESSAGE_ERROR, "手机号码已经被其他人注册，如有问题，请与客服联系");
                    return "redirect:/error.html";
                }
            }
            //邮箱有修改
            if (!StringUtils.equals(obj.getEmail(), admin.getEmail())) {
                Admin old = adminService.getAdminByEmail(admin.getEmail());
                if (old != null) {
                    modelMap.put(CommonConstrant.MESSAGE_ERROR, "EMAIL已经被其他人注册，如有问题，请与客服联系");
                    return "redirect:/error.html";
                }
            }
            //权限有了变化
            List<AdminRoleRp> rpList = null;
            if (StringUtils.isNotBlank(admin.getType())) {
                if (!StringUtils.equals(obj.getType(), admin.getType())) {
                    rpList = getAdminRoleRpsByAdminType(admin.getType());
                    if (CollectionUtils.isEmpty(rpList)) {
                        modelMap.put(CommonConstrant.MESSAGE_ERROR, "请为用户选择相应的权限");
                        return "redirect:/error.html";
                    }

                }
            }


            admin.setId(obj.getId());
//            if (StringUtils.equals(checkPic, CommonConstrant.SUCCESS)) {
//                ImgUploadUtil.uploadAdminImage(request, obj.getUsername(), picParam);
//            }
            adminService.saveAdmin(admin, rpList);
            modelMap.put(CommonConstrant.MESSAGE_INFO,"用户资料修改成功");


        }

        modelMap.put("id", admin.getId());
        modelMap.put(CommonConstrant.URL, "gms/admin/toEditAdmin.html?id=" + admin.getId());
        return "redirect:/info.html";


    }

    /**
     * 根据用户类型赋予权限
     *
     * @param type
     * @return
     */
    private List<AdminRoleRp> getAdminRoleRpsByAdminType(String type) {
        List<AdminRoleRp> rpList = new ArrayList<AdminRoleRp>();
        Role adminRole = adminService.getRoleById(CommonConstrant.ROLE_ADMIN_ID);
        Role systemRole = adminService.getRoleById(CommonConstrant.ROLE_SYSTEM_ID);
        Role merchantRole = adminService.getRoleById(CommonConstrant.ROLE_MERCHANT_ID);
        if (null == adminRole || systemRole == null || merchantRole == null) {
            return rpList;
        }
        AdminRoleRp adminRp = new AdminRoleRp();
        adminRp.setRoleId(adminRole.getId());

        AdminRoleRp systemRp = new AdminRoleRp();
        systemRp.setRoleId(systemRole.getId());


        AdminRoleRp merchantRp = new AdminRoleRp();
        merchantRp.setRoleId(merchantRole.getId());
        if (StringUtils.equals(type, CommonConstrant.ADMIN_TYPE_SUPER)) {

            rpList.add(adminRp);
            rpList.add(systemRp);
            rpList.add(merchantRp);

        } else if (StringUtils.equals(type, CommonConstrant.ADMIN_TYPE_SYSTEM)) {
            //赋予商家及数据管理权限
            rpList.add(systemRp);
            rpList.add(merchantRp);

        } else if (StringUtils.equals(type, CommonConstrant.ADMIN_TYPE_MERCHANT)) {
            rpList.add(merchantRp);
        }
        return rpList;
    }

    @RequestMapping("toAdminUpdate.html")
    String toAdminUpdate(ModelMap modelMap) {
        Admin obj = CommonUtil.getAdminByRequest();
        modelMap.put("admin", obj);
        return "gms/admin_update";
    }


    @RequestMapping("toUpdatePassword.html")
    String toUpdatePsssword(HttpServletRequest request) {
        return "gms/admin_password";
    }


    @ResponseBody
    @RequestMapping("updatePassword.html")
    String updatePsssword(String password, String newPassword, HttpServletRequest request) {
        MessageVo messageVo = new MessageVo();
        Admin obj = CommonUtil.getAdminByRequest();

        //检查密码是否正确
        boolean checkPassword = passwordService.passwordsMatch(password, obj.getPassword());
        newPassword = passwordService.encryptPassword(newPassword);
        if (!checkPassword) {
            messageVo.setSuccess(false);
            messageVo.setMessage("输入的旧密码不正确");
            return JSONObject.fromObject(messageVo).toString();
        }
        obj.setPassword(newPassword);
        adminService.save(obj);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();

    }
}
