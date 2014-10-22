/*
 * CategoryServiceImpl.java 
 * Version: 2.01   2014-6-11
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.service.impl;

import com.ezmall.mapper.CategoryBrandRpMapper;
import com.ezmall.mapper.CategoryMapper;
import com.ezmall.mapper.CategoryPropRpMapper;
import com.ezmall.model.*;
import com.ezmall.service.ICategoryService;
import com.ezmall.service.ISequenceService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryBrandRpMapper categoryBrandRpMapper;
    @Autowired
    ISequenceService sequenceService;
    @Autowired
    CategoryPropRpMapper categoryPropRpMapper;

    @Override
    public Category getCategoryById(String id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Category getCategoryByNo(String no) {
        CategoryExample example = new CategoryExample();
        example.or().andNoEqualTo(no);
        return CommonUtil.getFirstObject(categoryMapper.selectByExample(example));
    }

    @Override
    public Category getCategoryByStruct(String struct) {
        CategoryExample example = new CategoryExample();
        example.or().andStructEqualTo(struct);
        return CommonUtil.getFirstObject(categoryMapper.selectByExample(example));
    }

    @Override
    public Category getCategoryByStructName(String structName) {
        CategoryExample example = new CategoryExample();
        example.or().andStructNameEqualTo(structName);
        return CommonUtil.getFirstObject(categoryMapper.selectByExample(example));
    }

    @Override
    public Category getOrCreateCategory(String structName) {
        String separator = "-";
        String[] names = StringUtils.split(structName, separator);
        String firstName = names[0];
        String secondName = names[1];
        String thirdName = names[2];
        Category category = getCategoryByStructName(structName);
        Date today = new Date();
        if (category != null) {
            return category;
        }
        Category firstCategory = null;
        Category secondCategory = null;
        Category thirdCategory = null;
        //判断一级分类是否存在
        firstCategory = getCategoryByStructName(firstName);
        if (null == firstCategory) {
            //创建 1,2,3及分类 ，返回第三级别分类
            firstCategory = new Category();
            String firstNo = sequenceService.getCategoryNo();
            String firstStruct = getStructByParentNo(CommonConstrant.ZERO_PARENT_NO, null);
            firstCategory.setCreateDate(today);
            firstCategory.setUpdateDate(today);
            firstCategory.setId(UUIDUtil.getUUID());
            firstCategory.setLevel(CommonConstrant.CATEGORY_LEVEL_FIRST);
            firstCategory.setName(firstName);
            firstCategory.setNo(firstNo);
            firstCategory.setStruct(firstStruct);
            firstCategory.setStructName(firstName);
            firstCategory.setValidFlag(CommonConstrant.VALID_FLAG_YES);
            firstCategory.setParentNo(CommonConstrant.ZERO_PARENT_NO);
            // categoryMapper.insertSelective(firstCategory);
            //创建2及分类

            secondCategory = new Category();
            String secondStruct = getStructByParentNo(firstNo, firstStruct);
            String secondNo = sequenceService.getCategoryNo();
            secondCategory.setCreateDate(today);
            secondCategory.setUpdateDate(today);
            secondCategory.setId(UUIDUtil.getUUID());
            secondCategory.setLevel(CommonConstrant.CATEGORY_LEVEL_SECOND);
            secondCategory.setName(secondName);
            secondCategory.setNo(secondNo);
            secondCategory.setStruct(secondStruct);
            secondCategory.setStructName(firstName + separator + secondName);
            secondCategory.setValidFlag(CommonConstrant.VALID_FLAG_YES);
            secondCategory.setParentNo(firstNo);
            //创建3及分类
            thirdCategory = new Category();
            String thirdStruct = getStructByParentNo(secondNo, secondStruct);
            thirdCategory.setCreateDate(today);
            thirdCategory.setUpdateDate(today);
            thirdCategory.setId(UUIDUtil.getUUID());
            thirdCategory.setLevel(CommonConstrant.CATEGORY_LEVEL_THIRD);
            thirdCategory.setName(thirdName);
            thirdCategory.setNo(sequenceService.getCategoryNo());
            thirdCategory.setStruct(thirdStruct);
            thirdCategory.setStructName(structName);
            thirdCategory.setValidFlag(CommonConstrant.VALID_FLAG_YES);
            thirdCategory.setParentNo(secondNo);
            categoryMapper.insertSelective(firstCategory);
            categoryMapper.insertSelective(secondCategory);
            categoryMapper.insertSelective(thirdCategory);
        } else {
            //判断2及分类是否存在
            secondCategory = getCategoryByStructName(firstName + separator + secondName);
            if (null == secondCategory) {
                secondCategory = new Category();
                String secondStruct = getStructByParentNo(firstCategory.getNo(), firstCategory.getStruct());
                String secondNo = sequenceService.getCategoryNo();
                secondCategory.setCreateDate(today);
                secondCategory.setUpdateDate(today);
                secondCategory.setId(UUIDUtil.getUUID());
                secondCategory.setLevel(CommonConstrant.CATEGORY_LEVEL_SECOND);
                secondCategory.setName(secondName);
                secondCategory.setNo(secondNo);
                secondCategory.setStruct(secondStruct);
                secondCategory.setStructName(firstName + separator + secondName);
                secondCategory.setValidFlag(CommonConstrant.VALID_FLAG_YES);
                secondCategory.setParentNo(firstCategory.getNo());

                //创建3及分类
                thirdCategory = new Category();
                String thirdStruct = getStructByParentNo(secondNo, secondStruct);
                thirdCategory.setCreateDate(today);
                thirdCategory.setUpdateDate(today);
                thirdCategory.setId(UUIDUtil.getUUID());
                thirdCategory.setLevel(CommonConstrant.CATEGORY_LEVEL_THIRD);
                thirdCategory.setName(thirdName);
                thirdCategory.setNo(sequenceService.getCategoryNo());
                thirdCategory.setStruct(thirdStruct);
                thirdCategory.setStructName(structName);
                thirdCategory.setValidFlag(CommonConstrant.VALID_FLAG_YES);
                thirdCategory.setParentNo(secondNo);
                categoryMapper.insertSelective(secondCategory);
                categoryMapper.insertSelective(thirdCategory);
            } else {
                thirdCategory = new Category();
                String thirdStruct = getStructByParentNo(secondCategory.getNo(), secondCategory.getStruct());
                thirdCategory.setCreateDate(today);
                thirdCategory.setUpdateDate(today);
                thirdCategory.setId(UUIDUtil.getUUID());
                thirdCategory.setLevel(CommonConstrant.CATEGORY_LEVEL_THIRD);
                thirdCategory.setName(thirdName);
                thirdCategory.setNo(sequenceService.getCategoryNo());
                thirdCategory.setStruct(thirdStruct);
                thirdCategory.setStructName(structName);
                thirdCategory.setValidFlag(CommonConstrant.VALID_FLAG_YES);
                thirdCategory.setParentNo(secondCategory.getNo());
                categoryMapper.insertSelective(thirdCategory);
            }
        }

        return thirdCategory;
    }

    @Override
    public List<Category> getAllCategories() {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("struct");
        return categoryMapper.selectByExample(example);
    }

    @Override
    public Integer getCategoryCountByParentNo(String parentNo) {
        CategoryExample example = new CategoryExample();
        example.or().andParentNoEqualTo(parentNo);
        return categoryMapper.countByExample(example);
    }

    @Override
    public List<Category> getCategoriesByParentNo(String parentNo) {
        CategoryExample example = new CategoryExample();
        example.or().andParentNoEqualTo(parentNo);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<Category> getCategoriesLikeStruct(String struct) {
        CategoryExample example = new CategoryExample();
        example.or().andStructLike(struct + "%");
        return categoryMapper.selectByExample(example);
    }

    @Override
    public String getStructByParentNo(String parentNo, String parentStuct) {
        int i = 10;
        String struct = "";
        while (true) {
            int index = getCategoryCountByParentNo(parentNo) + i;
            if (StringUtils.isBlank(parentStuct)) {
                struct = index + "";
            } else {
                struct = parentStuct + "-" + index;
            }
            Category info = getCategoryByStruct(struct);
            if (info == null) {
                return struct;
            }
            i++;
        }
    }

    @Override
    public CategoryBrandRp getCategoryBrandRpByNo(String brandNo, String categoryNo) {
        CategoryBrandRpExample example = new CategoryBrandRpExample();
        example.or().andBrandNoEqualTo(brandNo).andCategoryNoEqualTo(categoryNo);
        return CommonUtil.getFirstObject(categoryBrandRpMapper.selectByExample(example));
    }

    @Override
    public Integer insertCategoryBrandRp(CategoryBrandRp rp) {
        return categoryBrandRpMapper.insertSelective(rp);
    }

    @Override
    public Integer insert(Category obj) {
        return categoryMapper.insertSelective(obj);
    }

    @Override
    @Transactional
    public Integer addBrandToCategory(String categoryNo, String[] brandNo) {
        CategoryBrandRpExample example = new CategoryBrandRpExample();
        example.or().andCategoryNoEqualTo(categoryNo);
        categoryBrandRpMapper.deleteByExample(example);
        for (String no : brandNo) {
            CategoryBrandRp rp = new CategoryBrandRp();
            rp.setBrandNo(no);
            rp.setCategoryNo(categoryNo);
            rp.setId(UUIDUtil.getUUID());
            categoryBrandRpMapper.insertSelective(rp);
        }
        return brandNo.length;
    }

    @Override
    public Integer addPropItemToCategory(String categoryNo, String[] propItemNo) {
        CategoryPropRpExample example = new CategoryPropRpExample();
        example.or().andCategoryNoEqualTo(categoryNo);
        categoryPropRpMapper.deleteByExample(example);
        for (String no : propItemNo) {
            CategoryPropRp rp = new CategoryPropRp();
            rp.setPropitemNo(no);
            rp.setCategoryNo(categoryNo);
            rp.setId(UUIDUtil.getUUID());
            categoryPropRpMapper.insertSelective(rp);
        }
        return propItemNo.length;
    }

    @Override
    public List<CategoryBrandRp> getCategoryBrandRpsByCategoryNo(String categoryNo) {
        CategoryBrandRpExample example = new CategoryBrandRpExample();
        example.or().andCategoryNoEqualTo(categoryNo);
        return categoryBrandRpMapper.selectByExample(example);
    }

    @Override
    public List<CategoryBrandRp> getCategoryBrandRpsByBrandNo(String brandNo) {
        CategoryBrandRpExample example = new CategoryBrandRpExample();
        example.or().andBrandNoEqualTo(brandNo);
        return categoryBrandRpMapper.selectByExample(example);
    }

    @Override
    public List<CategoryPropRp> getCategoryPropRpsByCategoryNo(String categoryNo) {
        CategoryPropRpExample example = new CategoryPropRpExample();
        example.or().andCategoryNoEqualTo(categoryNo);
        return categoryPropRpMapper.selectByExample(example);
    }

    @Override
    public CategoryPropRp getCategoryPropRp(String categoryNo, String propItemNo) {
        CategoryPropRpExample example=new CategoryPropRpExample();
        example.or().andCategoryNoEqualTo(categoryNo).andPropitemNoEqualTo(propItemNo);
        return CommonUtil.getFirstObject(categoryPropRpMapper.selectByExample(example));
    }

    @Override
    @Transactional
    public Integer delCategory(Category category) {
        String no = category.getNo();
        //删除品牌与分类的关联
        CategoryBrandRpExample example = new CategoryBrandRpExample();
        example.or().andCategoryNoEqualTo(no);
        categoryBrandRpMapper.deleteByExample(example);
        //删除属性和分类的关联
        CategoryPropRpExample example1 = new CategoryPropRpExample();
        example1.or().andCategoryNoEqualTo(no);
        categoryPropRpMapper.deleteByExample(example1);

        return categoryMapper.deleteByPrimaryKey(category.getId());
    }

    @Override
    public List<String> getCategoryNosBySelectCategory(String firstCategory, String secondCategory, String thirdCategory) {
        String categoryNo = null;
        List<String> list = new ArrayList<String>();
        if (StringUtils.isNotBlank(thirdCategory)) {
            categoryNo = thirdCategory;
        } else if (StringUtils.isNotBlank(secondCategory)) {
            categoryNo = secondCategory;
        } else if (StringUtils.isNotBlank(firstCategory)) {
            categoryNo = firstCategory;
        }
        if (StringUtils.isNotBlank(categoryNo)) {
            Category category = getCategoryByNo(categoryNo);
            if (StringUtils.equals(category.getLevel(), CommonConstrant.CATEGORY_LEVEL_THIRD)) {
                list.add(category.getNo());
            } else {
                //模糊查询
                List<Category> categories = getCategoriesLikeStruct(category.getStruct());
                for (Category obj : categories) {
                    list.add(obj.getNo());
                }
            }
        }

        return list;
    }
}
