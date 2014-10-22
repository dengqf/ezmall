/*
 * GoodsServiceImpl.java 
 * Version: 2.01   2014-6-11
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.service.impl;

import com.ezmall.dto.GoodsDto;
import com.ezmall.dto.ResultDto;
import com.ezmall.mapper.CategoryPropRpMapper;
import com.ezmall.mapper.GoodsMapper;
import com.ezmall.mapper.GoodsPropRpMapper;
import com.ezmall.model.*;
import com.ezmall.service.*;
import com.ezmall.utils.*;
import com.ezmall.vo.GoodsQueryVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ISequenceService sequenceService;
    @Autowired
    IBrandService brandService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IPropItemService propItemService;
    @Autowired
    IPropValueService propValueService;
    @Autowired
    GoodsPropRpMapper goodsPropRpMapper;
    @Autowired
    CategoryPropRpMapper categoryPropRpMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public ResultDto importGoods(GoodsDto dto) {
        ResultDto result = new ResultDto();
        String structName = dto.getStructName();
        if (StringUtils.isBlank(structName)) {
            result.setMessage("分类结构不能为空");
            return result;
        }
        Goods goods = dto.getGoods();
        if (null == goods) {
            result.setMessage("商品对象不能为空");
            return result;
        }
        String goodsNo = sequenceService.getGoodsNo();
        String[] names = StringUtils.split(structName, "-");
        Date today = new Date();
        if (names.length < 3) {
            result.setMessage("分类结构不规范");
            return result;
        }
        //处理品牌信息
        String brandName = dto.getBrandName();
        String englishName = dto.getBrandEnglishName();
        logger.info("接收到的品牌名:{}", brandName);
        if (StringUtils.isBlank(brandName) && StringUtils.isBlank(englishName)) {
            result.setMessage("品牌名、品牌英文名不能同时为空");
            return result;
        }
        //判断structName是否存在，如果存在，取出对象
        Brand brand = brandService.getBrandByName(brandName);
        if (null == brand) {
            logger.info("品牌不存在，创建品牌");
            if (StringUtils.isNotBlank(englishName)) {
                //TODO 暂时不管
//                brand = brandService.getBrandByEnglishName(englishName);
            }

            if (brand == null) {
                String brandNo = sequenceService.getBrandNo();
                //创建BRAND
                brand = new Brand();
                brand.setId(UUIDUtil.getUUID());
                brand.setName(brandName);
                brand.setEnglishName(englishName);
                brand.setNo(brandNo);
                brandService.insert(brand);

            }
        }
        Category category = categoryService.getOrCreateCategory(structName);
        //判断品牌和分类的关联是否存在，如果不存在则设置关联
        CategoryBrandRp categoryBrandRp = categoryService.getCategoryBrandRpByNo(brand.getNo(), category.getNo());
        if (null == categoryBrandRp) {
            categoryBrandRp = new CategoryBrandRp();
            categoryBrandRp.setId(UUIDUtil.getUUID());
            categoryBrandRp.setBrandNo(brand.getNo());
            categoryBrandRp.setCategoryNo(category.getNo());
            categoryService.insertCategoryBrandRp(categoryBrandRp);

        }
        //判断属性项
        Map<String, String> propMap = dto.getPropMap();
        if (!CollectionUtils.isEmpty(propMap)) {
            for (String propItemName : propMap.keySet()) {
                String propItemValue = propMap.get(propItemName);
                //判断名称是否存在，如果存在则取出对象，不存在，则生成对象
                PropItem item = propItemService.getPropItemByName(propItemName);

                if (null == item) {
                    //直接创建属性项及对应的属性值
                    String itemNo = sequenceService.getPropItemNo();
                    item = new PropItem();
                    item.setId(UUIDUtil.getUUID());
                    item.setName(propItemName);
                    item.setCreateDate(today);
                    item.setNo(itemNo);
                    propItemService.insert(item);
                    //创建属性项与分类关系
                    CategoryPropRp cateRp = new CategoryPropRp();
                    cateRp.setId(UUIDUtil.getUUID());
                    cateRp.setCategoryNo(category.getNo());
                    cateRp.setPropitemNo(itemNo);
                    categoryPropRpMapper.insertSelective(cateRp);

                } else {
                    //判断属性是否在分类里面
                    CategoryPropRp cateRp = categoryService.getCategoryPropRp(category.getNo(), item.getNo());
                    if (null == cateRp) {
                        cateRp = new CategoryPropRp();
                        cateRp.setId(UUIDUtil.getUUID());
                        cateRp.setCategoryNo(category.getNo());
                        cateRp.setPropitemNo(item.getNo());
                        categoryPropRpMapper.insertSelective(cateRp);
                    }
                }
                //创建商品与属性项的关系
                GoodsPropRp rp = new GoodsPropRp();
                rp.setCreateDate(today);
                rp.setGoodsNo(goodsNo);
                rp.setId(UUIDUtil.getUUID());
                rp.setPropItemName(item.getName());
                rp.setPropItemNo(item.getNo());
                rp.setPropItemValue(propItemValue);
                goodsPropRpMapper.insertSelective(rp);

            }
        }
        //保存商品
        goods.setId(UUIDUtil.getUUID());
        goods.setNo(goodsNo);
        goods.setBrandNo(brand.getNo());
        goods.setBrandName(brand.getName());
        goods.setCategoryNo(category.getNo());
        goods.setCategoryStruct(category.getStruct());
        goods.setCategoryStructName(category.getStructName());
        goods.setValidFlag(CommonConstrant.VALID_FLAG_YES);
        goods.setStatus(CommonConstrant.GOODS_STATUS_CREATE);
        goods.setCreateDate(today);
        goods.setUpdateDate(today);
        goods.setGoodsPicture(ImgUploadUtil.getGoodsPicUrl(goods));
        //商品图片保存路径
        String separator = System.getProperty("file.separator");
        String fileUrl = ImgUploadUtil.getUploadPath() + separator + "goods" + separator + goodsNo + separator + goodsNo + ".jpeg";
        //
        result.setMessage(fileUrl);
        goodsMapper.insertSelective(goods);
        result.setSuccess(true);

        return result;
    }

    @Override
    public Goods getGoodsById(String id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public Goods getGoodsByNo(String no) {
        GoodsExample example = new GoodsExample();
        example.or().andNoEqualTo(no);
        return CommonUtil.getFirstObject(goodsMapper.selectByExample(example));
    }

    @Override
    public Integer insert(Goods goods, List<GoodsPropRp> list) {
        for (GoodsPropRp rp : list) {
            goodsPropRpMapper.insertSelective(rp);
        }
        return goodsMapper.insertSelective(goods);
    }

    @Override
    public Integer insert(Goods goods) {
        return goodsMapper.insertSelective(goods);
    }

    @Override
    public Integer save(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    @Transactional
    public Integer delGoods(Goods goods) {
        goods.setValidFlag(CommonConstrant.VALID_FLAG_NO);
        goods.setUpdateDate(new Date());
        GoodsPropRpExample example = new GoodsPropRpExample();
        example.or().andGoodsNoEqualTo(goods.getNo());
        goodsPropRpMapper.deleteByExample(example);
        return save(goods);
    }

    @Override
    public PageData<Goods> getGoodsPageData(GoodsQueryVo vo, Query query) {
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.or();
        criteria.andValidFlagEqualTo(CommonConstrant.VALID_FLAG_YES);
        if (StringUtils.isNotBlank(vo.getBrandNo())) {
            criteria.andBrandNoEqualTo(vo.getBrandNo());
        }
        if (StringUtils.isNotBlank(vo.getCategoryNo())) {
            criteria.andCategoryNoEqualTo(vo.getCategoryNo());
        }
        if (StringUtils.isNotBlank(vo.getCountry())) {
            criteria.andCountryEqualTo(vo.getCountry());
        }
        if (StringUtils.isNotBlank(vo.getCity())) {
            criteria.andCityEqualTo(vo.getCity());
        }
        if (StringUtils.isNotBlank(vo.getMall())) {
            criteria.andMallEqualTo(vo.getMall());
        }
        if (StringUtils.isNotBlank(vo.getGoodsName())) {
            criteria.andNameLike("%" + vo.getGoodsName() + "%");
        }
        if (StringUtils.isNotBlank(vo.getStruct())) {
            if (vo.getStruct().length() == 8) {
                criteria.andCategoryStructEqualTo(vo.getStruct());

            } else {
                criteria.andCategoryStructLike(vo.getStruct() + "%");

            }

        }
        if (StringUtils.isNotBlank(vo.getStatus())) {
            criteria.andStatusEqualTo(vo.getStatus());
        }
        if (StringUtils.isNotBlank(vo.getMerchantName())) {
            criteria.andMerchantNameEqualTo(vo.getMerchantName());
        }
        Integer rowsCount = goodsMapper.countByExample(example);
        PageData<Goods> pageData = null;
        if (rowsCount < 1) {
            pageData = new PageData<Goods>(1, 0);
        } else {
            if (StringUtils.isNotBlank(vo.getOrderColumn())) {
                String sort = CommonConstrant.SORT_DESC;
                if (StringUtils.equalsIgnoreCase(CommonConstrant.SORT_ASC, vo.getSort())) {
                    sort = CommonConstrant.SORT_ASC;
                }
                example.setOrderByClause(vo.getOrderColumn() + " " + sort);
            } else {
                example.setOrderByClause("UPDATE_DATE DESC");
            }

            List<Goods> list = goodsMapper.selectByExample(example, CommonUtil.getRowBounds(query));
            pageData = new PageData<Goods>(query.getPage(), query.getPageSize(), rowsCount);
            pageData.setData(list);
        }
        return pageData;
    }

    @Override
    public List<Goods> getGoodsListByCategoryNo(String categoryNo) {
        GoodsExample example = new GoodsExample();
        example.or().andCategoryNoEqualTo(categoryNo).andValidFlagEqualTo(CommonConstrant.VALID_FLAG_YES);
        return goodsMapper.selectByExample(example);
    }

    @Override
    public List<Goods> getGoodsListByBrandNo(String brandNo) {
        GoodsExample example = new GoodsExample();
        example.or().andBrandNoEqualTo(brandNo).andValidFlagEqualTo(CommonConstrant.VALID_FLAG_YES);
        return goodsMapper.selectByExample(example);
    }

    @Override
    public String changeGoodsStatus(String status, String[] goodsNo) {
        //如果是上架，需要做判断，其他不需要
        if (StringUtils.equals(status, CommonConstrant.GOODS_STATUS_ONSALE)) {

        }
        GoodsExample example = new GoodsExample();
        example.or().andNoIn(Arrays.asList(goodsNo));
        Goods goods = new Goods();
        goods.setUpdateDate(new Date());
        goods.setStatus(status);
        goodsMapper.updateByExampleSelective(goods, example);
        return CommonConstrant.SUCCESS;
    }

    @Override
    public String delGoodsList(String[] goodsNo) {
        GoodsExample example = new GoodsExample();
        example.or().andNoIn(Arrays.asList(goodsNo));
        Goods goods = new Goods();
        goods.setUpdateDate(new Date());
        goods.setValidFlag(CommonConstrant.VALID_FLAG_NO);
        goodsMapper.updateByExampleSelective(goods, example);
        return CommonConstrant.SUCCESS;
    }

    @Override
    public List<GoodsPropRp> getGoodsPropRpsByGoodsNo(String goodsNo) {
        GoodsPropRpExample example = new GoodsPropRpExample();
        example.or().andGoodsNoEqualTo(goodsNo);
        return goodsPropRpMapper.selectByExample(example);
    }

    @Override
    public Integer addPropToGoods(String goodsNo, List<GoodsPropRp> list) {
        //先删除   goodsNo得对应属性，再添加
        GoodsPropRpExample example = new GoodsPropRpExample();
        example.or().andGoodsNoEqualTo(goodsNo);
        goodsPropRpMapper.deleteByExample(example);
        for (GoodsPropRp rp : list) {
            rp.setGoodsNo(goodsNo);
            goodsPropRpMapper.insertSelective(rp);
        }
        return list.size();
    }

    @Override
    public Goods isGoodImport(String thirdNo, String thirdDomain) {
        GoodsExample example = new GoodsExample();
        example.or().andThirdNoEqualTo(thirdNo).andThirdDomainEqualTo(thirdDomain).andValidFlagEqualTo(CommonConstrant.VALID_FLAG_YES);
        Goods goods = CommonUtil.getFirstObject(goodsMapper.selectByExample(example));
        return goods;
    }

    @Override
    public Goods isGoodImportByThirdId(String thirdID, String thirdDomain) {
        GoodsExample example = new GoodsExample();
        example.or().andThirdIdEqualTo(thirdID).andThirdDomainEqualTo(thirdDomain).andValidFlagEqualTo(CommonConstrant.VALID_FLAG_YES);
        Goods goods = CommonUtil.getFirstObject(goodsMapper.selectByExample(example));
        return goods;
    }

    @Override
    public List<Goods> getGoodsTop(GoodsQueryVo vo, Integer size) {
        List<Area> list = goodsMapper.getAllCountryList();
        List<Goods> goodsList = new ArrayList<Goods>();
        for (Area area : list) {
            if (area == null) {
                continue;
            }
            String country = area.getCountry();
            if (StringUtils.isBlank(country)) {
                continue;
            }
            Goods goods = getMinPriceGoodsByCountry(country, vo.getGoodsName());
            if (goods != null) {
                goodsList.add(goods);
            }
        }
        Collections.sort(goodsList, new ComparatorGoods());
        if(goodsList.size()<size){
            return goodsList;
        }
        return goodsList.subList(0, size);
    }

    @Override
    public List<Goods> getGoodsTopInCountry(GoodsQueryVo vo, Integer size) {
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.or();
        criteria.andValidFlagEqualTo(CommonConstrant.VALID_FLAG_YES);

        if (StringUtils.isNotBlank(vo.getCountry())) {
            criteria.andCountryEqualTo(vo.getCountry());
        }
        if (StringUtils.isNotBlank(vo.getGoodsName())) {
            criteria.andNameEqualTo(vo.getGoodsName());
        }
        example.setOrderByClause(" sell_price asc limit " + size);
        return goodsMapper.selectByExample(example);
    }

    @Override
    public Goods getMinPriceGoodsByCountry(String country, String name) {
        if (StringUtils.isBlank(country)) {
            return null;
        }
        GoodsExample example = new GoodsExample();
        example.or().andCountryEqualTo(country).andNameEqualTo(name);
        example.setOrderByClause(" sell_price asc limit 1");
        return CommonUtil.getFirstObject(goodsMapper.selectByExample(example));
    }


}
