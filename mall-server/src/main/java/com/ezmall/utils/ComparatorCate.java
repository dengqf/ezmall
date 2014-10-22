/*
 * ComparatorCate.java 
 * Version: 2.01   2014-7-1
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.utils;

import com.ezmall.model.Category;

import java.util.Comparator;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-7-1
 */
public class ComparatorCate implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Category ca1 = (Category) o1;
        Category ca2 = (Category) o2;
        return ca1.getStruct().compareTo(ca2.getStruct());

    }
}
