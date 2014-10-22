/*
 * CategoryController.java 
 * Version: 2.01   2014-6-19
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.controller;

import com.ezmall.model.*;
import com.ezmall.service.*;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.MessageVo;
import com.ezmall.vo.PageData;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-19
 */
@Controller
@RequestMapping("/gms/category")
public class CategoryController {
    @Autowired
    ISequenceService sequenceService;
    @Autowired
    IBrandService brandService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IPropItemService propItemService;
    @Autowired
    IGoodsService goodsService;

    @ResponseBody
    @RequestMapping(value = "delCategory.html")
    public String delCategory(String id) {
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            messageVo.setMessage("找不到对应的分类");
            return JSONObject.fromObject(messageVo).toString();
        }
        //判断下面是否有子分类
        List<Category> list = categoryService.getCategoriesByParentNo(category.getNo());
        if (CollectionUtils.isNotEmpty(list)) {
            messageVo.setMessage("该分类下还有子分类，不能删除");
            return JSONObject.fromObject(messageVo).toString();
        }
        //判断分类下是否有商品？
        List<Goods> goodsList = goodsService.getGoodsListByCategoryNo(category.getNo());
        if (CollectionUtils.isNotEmpty(goodsList)) {
            messageVo.setMessage("该分类下还有商品，不能删除");
            return JSONObject.fromObject(messageVo).toString();
        }
        categoryService.delCategory(category);
        messageVo.setSuccess(true);

        return JSONObject.fromObject(messageVo).toString();
    }

    @RequestMapping(value = "toCategoryManager.html")
    public String toCategoryManager(ModelMap modelMap) {
        //得到所有的一级分类
        List<Category> list = categoryService.getCategoriesByParentNo(CommonConstrant.ZERO_PARENT_NO);
//        List<Category> list = categoryService.getAllCategories();
//        PageData<Category> pageData = new PageData<Category>(1, list.size());
//        pageData.setData(list);
        modelMap.put("list", list);
        return "gms/category_manager";
    }

    @ResponseBody
    @RequestMapping(value = "categoryListByParentNo.html")
    public String categoryListByParentNo(String parentNo) {
        //得到所有的一级分类

        List<Category> categories = categoryService.getCategoriesByParentNo(parentNo);
        MessageVo messageVo = new MessageVo();
        messageVo.setObj(categories);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

    @RequestMapping(value = "toAddBrand.html")
    public String toAddBrand(ModelMap modelMap, String no) {
        //得到所有的一级分类
        Category category = categoryService.getCategoryByNo(no);
        if (category == null) {
            modelMap.put(CommonConstrant.MESSAGE_ERROR, "分类不存在");
            return "redirect:/error.html";
        }
        List<Brand> brands = brandService.getAllBrands();
        List<CategoryBrandRp> relations = categoryService.getCategoryBrandRpsByCategoryNo(no);
        modelMap.put("category", category);
        modelMap.put("brands", brands);
        modelMap.put("relations", relations);
        return "gms/category_addBrand";
    }


    @RequestMapping(value = "addBrand.html")
    @ResponseBody
    public String addBrand(String no, String[] brandNo) {
        //得到所有的一级分类
        MessageVo messageVo = new MessageVo();
        Category category = categoryService.getCategoryByNo(no);
        if (category == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("找不到对应的分类");
            return JSONObject.fromObject(messageVo).toString();
        }
        if (ArrayUtils.isEmpty(brandNo)) {
            messageVo.setSuccess(false);
            messageVo.setMessage("至少需要选择一个品牌");
            return JSONObject.fromObject(messageVo).toString();
        }
        if (!StringUtils.equals(category.getLevel(), CommonConstrant.CATEGORY_LEVEL_THIRD)) {
            messageVo.setMessage("品牌必须关联第三级分类");
            return JSONObject.fromObject(messageVo).toString();
        }
        //删除分类下所有关联的品牌再添加
        categoryService.addBrandToCategory(no, brandNo);
        messageVo.setSuccess(true);
        messageVo.setMessage("添加成功");
        return JSONObject.fromObject(messageVo).toString();
    }

    /**
     * 分类关联相关商品属性
     *
     * @param modelMap
     * @param no
     * @return
     */
    @RequestMapping(value = "toAddProp.html")
    public String toAddProp(ModelMap modelMap, String no) {
        //得到所有的一级分类
        Category category = categoryService.getCategoryByNo(no);
        if (category == null) {
            //TODO
        }
        List<CategoryPropRp> categoryPropRps = categoryService.getCategoryPropRpsByCategoryNo(no);
        List<PropItem> propItems = propItemService.getAllPropItems();
        modelMap.put("category", category);
        modelMap.put("propItems", propItems);
        modelMap.put("relations", categoryPropRps);
        return "gms/category_addProp";
    }

    @ResponseBody
    @RequestMapping(value = "addProp.html")
    public String addProp(ModelMap modelMap, String no, String[] propitemNo) {
        //得到所有的一级分类
        MessageVo messageVo = new MessageVo();
        Category category = categoryService.getCategoryByNo(no);
        if (category == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("找不到对应的分类");
            return JSONObject.fromObject(messageVo).toString();
        }
        if (!StringUtils.equals(category.getLevel(), CommonConstrant.CATEGORY_LEVEL_THIRD)) {
            messageVo.setMessage("属性必须关联第三级分类");
            return JSONObject.fromObject(messageVo).toString();
        }
        if (ArrayUtils.isEmpty(propitemNo)) {
            messageVo.setSuccess(false);
            messageVo.setMessage("至少需要选择一个属性");
            return JSONObject.fromObject(messageVo).toString();
        }
        //删除分类下所有关联的品牌再添加
        categoryService.addPropItemToCategory(no, propitemNo);
        messageVo.setSuccess(true);
        messageVo.setMessage("添加成功");
        return JSONObject.fromObject(messageVo).toString();
    }

    @RequestMapping(value = "toEditCategory.html")
    public String toEditCategory() {

        return "gms/category_edit";
    }

    @ResponseBody
    @RequestMapping(value = "editCategory.html")
    public String editCategory(String name, String firstCategory, String secondCategory) {
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        Category obj = new Category();
        obj.setId(UUIDUtil.getUUID());
        obj.setValidFlag(CommonConstrant.VALID_FLAG_YES);
        obj.setNo(sequenceService.getCategoryNo());
        obj.setName(name);
        obj.setUpdateDate(new Date());
        obj.setCreateDate(new Date());
        if (StringUtils.isBlank(name)) {
            messageVo.setMessage("分类名称不能为空");
            return JSONObject.fromObject(messageVo).toString();
        }
        String parentNo = CommonConstrant.ZERO_PARENT_NO;
        if (StringUtils.isNotBlank(secondCategory)) {
            //创建3及分类

            parentNo = secondCategory;

        } else if (StringUtils.isNotBlank(firstCategory)) {
            //创建2及分类
            parentNo = firstCategory;
        }
        if (StringUtils.equals(parentNo, CommonConstrant.ZERO_PARENT_NO)) {

            Category _category = categoryService.getCategoryByStructName(name);
            if (_category != null) {
                messageVo.setMessage("分类名称已经存在，请重新设定");
                return JSONObject.fromObject(messageVo).toString();
            }
            String struct = categoryService.getStructByParentNo(parentNo, null);
            obj.setStruct(struct);
            obj.setStructName(name);
            obj.setLevel("1");
            obj.setParentNo(parentNo);
        } else {
            Category category = categoryService.getCategoryByNo(parentNo);
            if (null == category) {
                messageVo.setMessage("选择的二级分类不存在或者已删除");
                return JSONObject.fromObject(messageVo).toString();
            }
            //判断StructName是否存在
            String structName = category.getStructName() + "-" + name;
            Category _category = categoryService.getCategoryByStructName(structName);
            if (_category != null) {
                messageVo.setMessage("当前级别下分类名称已经存在，请重新设定");
                return JSONObject.fromObject(messageVo).toString();
            }
            String struct = categoryService.getStructByParentNo(parentNo, category.getStruct());
            obj.setStruct(struct);
            obj.setStructName(structName);
            int level = Integer.parseInt(category.getLevel()) + 1;
            obj.setLevel(level + "");
            obj.setParentNo(parentNo);

        }
        categoryService.insert(obj);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }
}
