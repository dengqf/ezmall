/*
 * BaseTestCase.java 
 * Version: 2.01   2014-7-8
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.gms;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.util.DigestUtils;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-7-8
 */
public class  BaseTestCase extends AbstractDependencyInjectionSpringContextTests {


    protected String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[]{
                "classpath:/application-test.xml"
        };
    }

    public void test() {
        String md5Str = DigestUtils.md5DigestAsHex("V2_高级_列表_优惠券".getBytes());
        System.out.println(md5Str);

    }



}

