/*
 * ErrorController.java 
 * Version: 2.01   2014-7-1
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.controller;

import com.ezmall.model.Brand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-7-1
 */
@Controller
@RequestMapping("/errors")
public class ErrorController {
    @RequestMapping(value = "404.html")
    public String toError404() {

        return "errors/404";

    }

    @RequestMapping(value = "403.html")
    public String toError403() {

        return "errors/403";

    }

    @RequestMapping(value = "500.html")
    public String toError500() {

        return "errors/500";

    }
}
