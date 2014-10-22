/*
 * UserInterceptor.java 
 * Version: 2.01   2014-6-26
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.utils;

import com.ezmall.model.Member;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-26
 */
public class MemberInterceptor extends HandlerInterceptorAdapter {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String loginUrl;

    @Override
    /**
     * Action之前执行:
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.debug("用户拦截器验证----" + request.getRequestURI());
        String url = request.getRequestURI();
        if (url.endsWith("/user_login.html") || url.contains("user_loginAdmin")) {
            return true;
        }
        //todo 拦截
        Member user = CommonUtil.getMemberByRequest(request);
        // 用户为null跳转到登陆页面
        if (user == null) {
            String token = request.getParameter("token");
            if (StringUtils.isNotBlank(token)) {
                user = CommonUtil.getMemberByToken(token);
            }
        }

        if (null == user) {
            try {
                String ctx = request.getContextPath();
                logger.debug("session已经过期");

                response.sendRedirect(ctx + "/toLoginMember.html?url=" + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    /**
     * 生成视图之前执行
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        logger.error("重新设置" + modelAndView);
//        modelAndView.addObject("errorInfo", "对不起,session过期，请重新登录");
        super.postHandle(request, response, handler, modelAndView);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    /**
     * 最后执行，可用于释放资源
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}


