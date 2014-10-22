/*
 * IGoodsService.java
 * Version: 2.01   2014-6-11
 *  
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.service;

import com.ezmall.dto.GoodsDto;
import com.ezmall.dto.ResultDto;
import com.ezmall.model.Goods;
import com.ezmall.model.GoodsPropRp;
import com.ezmall.server.api.TGoodsDto;
import com.ezmall.vo.GoodsQueryVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;

import java.util.List;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
public interface IGoodsService {

    ResultDto importGoods(GoodsDto dto);

    Goods getGoodsById(String id);

    Goods getGoodsByNo(String no);

    /**
     * 增加商品
     *
     * @param goods 商品基本属性
     * @param list  商品扩展属性项
     * @return
     */
    Integer insert(Goods goods, List<GoodsPropRp> list);

    Integer insert(Goods goods);

    Integer save(Goods goods);

    /**
     * 删除商品及商品的扩展属性
     *
     * @param goods
     * @return
     */
    Integer delGoods(Goods goods);

    PageData<Goods> getGoodsPageData(GoodsQueryVo vo, Query query);

    /**
     * 得到某个分类下的商品
     *
     * @param categoryNo 分类
     * @return
     */
    List<Goods> getGoodsListByCategoryNo(String categoryNo);

    /**
     * 得到某个品牌下的商品
     *
     * @param brandNo 分类
     * @return
     */
    List<Goods> getGoodsListByBrandNo(String brandNo);

    /**
     * 批量修改状态
     *
     * @param status  需要修改的状态
     * @param goodsNo 商品编号列表
     * @return 修改结果
     */
    String changeGoodsStatus(String status, String[] goodsNo);

    /**
     * 批量删除商品
     * @param goodsNo
     * @return
     */
    String delGoodsList(String[] goodsNo);

    /**
     * 得到商品关联的属性项
     *
     * @param goodsNo 商品编号
     * @return List<GoodsPropRp>
     */
    List<GoodsPropRp> getGoodsPropRpsByGoodsNo(String goodsNo);

    Integer addPropToGoods(String goodsNo,List<GoodsPropRp> list);

    /**
     * 判断商品编号是否已经被导入
     * @param thirdNo
     * @param thirdDomain
     * @return
     */
    Goods isGoodImport(String thirdNo,String thirdDomain);

    /**
     * 判断第三方商品ID是否已经被导入
     * @param thirdID
     * @param thirdDomain
     * @return
     */
    Goods isGoodImportByThirdId(String thirdID,String thirdDomain);

    /**
     * 得到最低的几个国家的商品信息
     * @param vo
     * @param size
     * @return
     */
    List<Goods> getGoodsTop(GoodsQueryVo vo,Integer size);

    /**
     * 得到某个商品在某个国家内价格最低的商品信息
     * @param vo
     * @param size
     * @return
     */
    List<Goods> getGoodsTopInCountry(GoodsQueryVo vo,Integer size);

    Goods getMinPriceGoodsByCountry(String country,String name);

}
