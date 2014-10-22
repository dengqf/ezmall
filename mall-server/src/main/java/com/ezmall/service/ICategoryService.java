package com.ezmall.service;

import com.ezmall.model.Category;
import com.ezmall.model.CategoryBrandRp;
import com.ezmall.model.CategoryPropRp;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-10
 * Time: 下午11:42
 * To change this template use File | Settings | File Templates.
 */
public interface ICategoryService {


    Category getCategoryById(String id);

    Category getCategoryByNo(String no);

    Category getCategoryByStruct(String struct);

    Category getCategoryByStructName(String structName);

    /**
     * 根据分类结构名称得到分类，如果没有则创建
     *
     * @param structName 分类结构名称
     * @return Category
     */
    Category getOrCreateCategory(String structName);

    List<Category> getAllCategories();

    /**
     * 根据父NO得到总数
     *
     * @param parentNo 父NO
     * @return 总数
     */
    Integer getCategoryCountByParentNo(String parentNo);

    List<Category> getCategoriesByParentNo(String parentNo);

    List<Category> getCategoriesLikeStruct(String struct);

    /**
     * 根据父分类NO及父分类的STRUCT得到子分类的STRUCT
     *
     * @param parentNo
     * @param parentStuct
     * @return
     */
    String getStructByParentNo(String parentNo, String parentStuct);



    /**
     * 插入分类
     *
     * @param obj
     * @return
     */
    Integer insert(Category obj);

    Integer delCategory(Category category);

    /**
     * 根据选的一，二，三级分类查询得到的分类编号列表,支持模糊查询
     *
     * @param firstCategory  一级分类编号
     * @param secondCategory 二级分类编号
     * @param thirdCategory  三级分类编号
     * @return
     */
    List<String> getCategoryNosBySelectCategory(String firstCategory, String secondCategory, String thirdCategory);

    /**
     * 根据品牌NO，分类NO得到关联对象
     *
     * @param brandNo    品牌NO
     * @param categoryNo 分类NO
     * @return 关联对象
     */
    CategoryBrandRp getCategoryBrandRpByNo(String brandNo, String categoryNo);

    /**
     * 添加分类和品牌关联属性
     *
     * @param rp
     * @return
     */
    Integer insertCategoryBrandRp(CategoryBrandRp rp);

    /**
     * 将品牌添加到分类关联里
     *
     * @param categoryNo
     * @param brandNo
     * @return
     */
    Integer addBrandToCategory(String categoryNo, String[] brandNo);



    /**
     * 根据分类编号得到分类和品牌的关联
     *
     * @param categoryNo
     * @return
     */
    List<CategoryBrandRp> getCategoryBrandRpsByCategoryNo(String categoryNo);

    List<CategoryBrandRp> getCategoryBrandRpsByBrandNo(String brandNo);

    /**
     * 将属性添加到分类关联里
     *
     * @param categoryNo
     * @param propItemNo
     * @return
     */
    Integer addPropItemToCategory(String categoryNo, String[] propItemNo);

    /**
     *根据分类编号得到分类和属性的关联
     * @param categoryNo
     * @return
     */
    List<CategoryPropRp> getCategoryPropRpsByCategoryNo(String categoryNo);

    CategoryPropRp getCategoryPropRp(String categoryNo,String propItemNo);



}
