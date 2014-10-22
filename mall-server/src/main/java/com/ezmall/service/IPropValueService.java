/*
 * IPropValueService.java 
 * Version: 2.01   2014-6-11
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.service;

import com.ezmall.model.PropValue;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
public interface IPropValueService {

    Integer insert(PropValue value);
    Integer save(PropValue value);
    /**
     * 根据属性项编号及属性值NAME，得到对象
     * @param name
     * @param itemNo
     * @return
     */
    PropValue getPropValueByNameAndItemNo(String name,String itemNo);
}
