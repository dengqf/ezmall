/*
 * SequenceServiceImpl.java 
 * Version: 2.01   2014-6-11
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.service.impl;

import com.ezmall.mapper.SequenceMapper;
import com.ezmall.service.ISequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
@Service
public class SequenceServiceImpl implements ISequenceService {

    @Autowired
    SequenceMapper sequenceMapper;

    @Override
    public String getGoodsNo() {
        return sequenceMapper.getGoodsNo();
    }

    @Override
    public String getBrandNo() {
        return sequenceMapper.getBrandNo();
    }

    @Override
    public String getCategoryNo() {
        return sequenceMapper.getCategoryNo();
    }

    @Override
    public String getPropItemNo() {
        return sequenceMapper.getPropItemNo();
    }

    @Override
    public String getOrderNo() {
        return sequenceMapper.getOrderNo();
    }

//    @Override
//    public String getPropValueNo() {
//        return sequenceMapper.getPropValueNo();
//    }
}
