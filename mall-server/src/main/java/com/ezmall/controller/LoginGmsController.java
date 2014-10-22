package com.ezmall.controller;

import com.ezmall.model.Admin;
import com.ezmall.service.IAdminService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-16
 * Time: 下午9:29
 * To change this template use File | Settings | File Templates.
 * GMS管理后台登陆
 */
@Controller
public class LoginGmsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PasswordService passwordService;
    @Autowired
    IAdminService adminService;

    @RequestMapping(value = "gms/login.html")
    public String login(String username, String password) {
        // 创建 Token
        String password1 = passwordService.encryptPassword(password);
        logger.info("加密后的密码:{}", password1);

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(false);

            // 获取当前用户，并进行登录操作
            Subject user = SecurityUtils.getSubject();

            user.login(token);

            Admin admin=adminService.getAdminByUsername(username);
            user.getSession().setAttribute(CommonConstrant.ADMIN_SESSION_NAME,admin);

        } catch (AuthenticationException e) {
            logger.error("AuthenticationException", e);
            return "gms/login";
        }
        return "redirect:index.html";
    }

    @RequestMapping(value = "gms/logOut.html")
    public String logOut(String username, String password) {
        Subject user = SecurityUtils.getSubject();

        user.logout();

        // 重定向到登录
        return "gms/login";

    }

    @RequestMapping(value = "gms/toLogin.html")
    public String toLogin() {
        Subject user = SecurityUtils.getSubject();
        user.logout();
        // 重定向到首页
        return "gms/login";

    }

    @RequestMapping(value = "gms/index.html")
    public String index() {

        // 重定向到首页
        return "gms/index";

    }
}

