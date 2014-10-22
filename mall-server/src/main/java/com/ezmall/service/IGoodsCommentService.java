/*
 * IGoodsCommentService.java
 * Version: 2.01   2014-7-2
 *  
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.service;

import com.ezmall.model.GoodsComment;
import com.ezmall.model.SubOrder;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-7-2
 */
public interface IGoodsCommentService {
    Integer addComment(GoodsComment comment,SubOrder subOrder);

    PageData<GoodsComment> getGoodsCommentPageData(GoodsComment obj,Query query);
}
