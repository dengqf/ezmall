/*
 * GoodsCommentServiceImpl.java 
 * Version: 2.01   2014-7-2
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.service.impl;

import com.ezmall.mapper.GoodsCommentMapper;
import com.ezmall.mapper.SubOrderMapper;
import com.ezmall.model.GoodsComment;
import com.ezmall.model.GoodsCommentExample;
import com.ezmall.model.SubOrder;
import com.ezmall.service.IGoodsCommentService;
import com.ezmall.utils.CommonUtil;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-7-2
 */
@Service
public class GoodsCommentServiceImpl implements IGoodsCommentService {

    @Autowired
    SubOrderMapper subOrderMapper;
    @Autowired
    GoodsCommentMapper goodsCommentMapper;

    @Override
    @Transactional
    public Integer addComment(GoodsComment comment, SubOrder subOrder) {
        subOrderMapper.updateByPrimaryKeySelective(subOrder);
        return goodsCommentMapper.insertSelective(comment);
    }

    @Override
    public PageData<GoodsComment> getGoodsCommentPageData(GoodsComment obj, Query query) {
        PageData<GoodsComment> pageData=null;
        GoodsCommentExample example = new GoodsCommentExample();
        GoodsCommentExample.Criteria criteria = example.or();
        if(StringUtils.isNotBlank(obj.getGoodsNo())){
            criteria.andGoodsNoEqualTo(obj.getGoodsNo());
        }

        if(StringUtils.isNotBlank(obj.getUsername())){
            criteria.andUsernameEqualTo(obj.getUsername());
        }
        Integer row=goodsCommentMapper.countByExample(example);
        if(row<1){
            pageData=new PageData<GoodsComment>(1,0);
        }else {
            pageData=new PageData<GoodsComment>(query.getPage(),query.getPageSize(),row);
            List<GoodsComment>  list=goodsCommentMapper.selectByExample(example, CommonUtil.getRowBounds(query));
            pageData.setData(list);
        }
        return pageData;
    }
}
