/*
 * PropValueServiceImpl.java 
 * Version: 2.01   2014-6-11
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.service.impl;

import com.ezmall.mapper.PropValueMapper;
import com.ezmall.model.PropValue;
import com.ezmall.model.PropValueExample;
import com.ezmall.service.IPropValueService;
import com.ezmall.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
@Service
public class PropValueServiceImpl implements IPropValueService {
    @Autowired
    PropValueMapper propValueMapper;

    @Override
    public Integer insert(PropValue value) {
        return propValueMapper.insert(value);
    }

    @Override
    public Integer save(PropValue value) {
        return propValueMapper.updateByPrimaryKeySelective(value);
    }

    @Override
    public PropValue getPropValueByNameAndItemNo(String name, String itemNo) {
        PropValueExample example = new PropValueExample();
        example.or().andNameEqualTo(name).andItemNoEqualTo(itemNo);
        return CommonUtil.getFirstObject(propValueMapper.selectByExample(example));
    }
}
