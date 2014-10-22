/*
 * PropItemServiceImpl.java 
 * Version: 2.01   2014-6-11
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.service.impl;

import com.ezmall.mapper.CategoryPropRpMapper;
import com.ezmall.mapper.GoodsPropRpMapper;
import com.ezmall.mapper.PropItemMapper;
import com.ezmall.model.*;
import com.ezmall.service.ICategoryService;
import com.ezmall.service.IPropItemService;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.PageData;
import com.ezmall.vo.PropItemQueryVo;
import com.ezmall.vo.Query;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
@Service
public class PropItemServiceImpl implements IPropItemService {

    @Autowired
    PropItemMapper propItemMapper;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    CategoryPropRpMapper categoryPropRpMapper;
    @Autowired
    GoodsPropRpMapper goodsPropRpMapper;

    @Override
    public Integer insert(PropItem item) {
        return propItemMapper.insertSelective(item);
    }

    @Override
    @Transactional
    public Integer insert(PropItem item, String categoryNo) {
        insert(item);
        CategoryPropRp rp=new CategoryPropRp();
        rp.setPropitemNo(item.getNo());
        rp.setCategoryNo(categoryNo);
        rp.setId(UUIDUtil.getUUID());
        return categoryPropRpMapper.insertSelective(rp);
    }

    @Override
    public Integer save(PropItem item) {
        return propItemMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public PropItem getPropItemById(String id) {
        return propItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public PropItem getPropItemByNo(String no) {
        PropItemExample example = new PropItemExample();
        example.or().andNoEqualTo(no);
        return CommonUtil.getFirstObject(propItemMapper.selectByExample(example));
    }

    @Override
    public PropItem getPropItemByName(String name) {
        PropItemExample example = new PropItemExample();
        example.or().andNameEqualTo(name);
        return CommonUtil.getFirstObject(propItemMapper.selectByExample(example));
    }

    @Override
    public List<PropItem> getAllPropItems() {
        return propItemMapper.selectByExample(null);
    }

    @Override
    public PageData<PropItem> getPropItemPageData(PropItemQueryVo vo, Query query) {
        PageData<PropItem> pageData = null;
        PropItemExample example = new PropItemExample();
        PropItemExample.Criteria criteria = example.or();
        if (StringUtils.isNotBlank(vo.getName())) {
            criteria.andNameLike("%" + vo.getName() + "%");
        }
        List<String> categoryNos = categoryService.getCategoryNosBySelectCategory(vo.getFirstCategory(), vo.getSecondCategory(), vo.getThirdCategory());
        if (CollectionUtils.isNotEmpty(categoryNos)) {
            //取得关联的属性NO
            CategoryPropRpExample example1 = new CategoryPropRpExample();
            example1.or().andCategoryNoIn(categoryNos);
            List<CategoryPropRp> rps = categoryPropRpMapper.selectByExample(example1);
            List<String> propItemNos = new ArrayList<String>();
            if (CollectionUtils.isNotEmpty(rps)) {
                for (CategoryPropRp rp : rps) {
                    propItemNos.add(rp.getPropitemNo());
                }
            }
            //如果一条关联都没，就说明没有，可以直接返回
            if(CollectionUtils.isEmpty(propItemNos)){
                return new PageData<PropItem>(1, 0);
            }else {
                criteria.andNoIn(propItemNos);
            }


        }
        Integer rows = propItemMapper.countByExample(example);
        if (rows < 1) {
            pageData = new PageData<PropItem>(1, 0);
        } else {
            pageData = new PageData<PropItem>(query.getPage(), query.getPageSize(), rows);
            List<PropItem> propItems = propItemMapper.selectByExample(example, CommonUtil.getRowBounds(query));
            pageData.setData(propItems);
        }
        return pageData;
    }

    @Override
    public List<PropItem> getPropItemListByCategoryNo(String categoryNo) {
        Query query = new Query();
        query.setPage(1);
        query.setPageSize(Integer.MAX_VALUE);
        PropItemQueryVo itemQueryVo = new PropItemQueryVo();
        itemQueryVo.setThirdCategory(categoryNo);
        PageData<PropItem> pageData = getPropItemPageData(itemQueryVo, query);

        List<PropItem> propItemList = pageData.getData();
        if (org.springframework.util.CollectionUtils.isEmpty(propItemList)) {
            propItemList = new ArrayList<PropItem>();
        }
        return propItemList;
    }

    @Override
    public Integer delPropItemByNo(PropItem item) {
        String no =item.getNo();
        //删除商品与属性的关联
        GoodsPropRpExample example=new GoodsPropRpExample();
        example.or().andPropItemNoEqualTo(no);
        goodsPropRpMapper.deleteByExample(example);
        //删除分类与属性的关联
        CategoryPropRpExample example1 = new CategoryPropRpExample();
        example1.or().andPropitemNoEqualTo(no);
        categoryPropRpMapper.deleteByExample(example1);
        //删除属性
        return propItemMapper.deleteByPrimaryKey(item.getId());
    }
}
