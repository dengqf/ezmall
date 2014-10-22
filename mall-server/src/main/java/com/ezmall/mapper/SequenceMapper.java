/*
 * SequenceMapper.java
 * Version: 2.01   2014-6-11
 *  
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.mapper;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
public interface SequenceMapper {

    String getGoodsNo();

    String getBrandNo();

    String getCategoryNo();

    String getPropItemNo();

    String getPropValueNo();

    String getOrderNo();
}
