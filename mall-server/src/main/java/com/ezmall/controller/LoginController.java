/*
 * LoginController.java 
 * Version: 2.01   2014-4-16
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.controller;

import com.ezmall.utils.CommonConstrant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-4-16
 */
@Controller
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("error.html")
    public String error(String error, String url, ModelMap map) {

        map.put(CommonConstrant.MESSAGE_ERROR, error);
        map.put(CommonConstrant.URL, url);
        return "gms/error";

    }

    @RequestMapping("info.html")
    public String info(String info, String url, ModelMap map) {

        map.put(CommonConstrant.MESSAGE_INFO, info);
        map.put(CommonConstrant.URL, url);
        return "gms/info";

    }
}
