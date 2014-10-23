package com.ezmall.controller;

import com.ezmall.model.*;
import com.ezmall.service.*;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.ImgUploadUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-9
 * Time: 下午9:24
 * To change this template use File | Settings | File Templates.
 * 商品管理
 * 增加购买积分，商品运费设置
 */
@Controller
@RequestMapping("/gms/goods")
public class GoodsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IGoodsService goodsService;
    @Autowired
    IGoodsPriceService goodsPriceService;
    @Autowired
    IBrandService brandService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    ISequenceService sequenceService;
    @Autowired
    IPropItemService propItemService;


    @RequestMapping(value = "toGoodsCreate.html")
    public String toGoodCreate() {

        return "gms/goods_create";
    }

    @RequestMapping(value = "toGoodsEdit.html")
    public String toGoodsEdit(String id, String thirdCategory, ModelMap modelMap) {

        Goods goods = null;
        //选择的商品分类
        String goodsCateNo = null;
        List<GoodsPropRp> relations = null;
        GoodsQueryVo vo = new GoodsQueryVo();
        if (StringUtils.isNotBlank(id)) {
            goods = goodsService.getGoodsById(id);
        }
        if (goods == null) {
            goods = new Goods();
            goodsCateNo = thirdCategory;
        } else {
            goodsCateNo = goods.getCategoryNo();
            relations = goodsService.getGoodsPropRpsByGoodsNo(goods.getNo());
        }
        Category category = categoryService.getCategoryByNo(goodsCateNo);
        if (null == category) {
            modelMap.put(CommonConstrant.MESSAGE_ERROR, "分类不存在");
            return "redirect:/error.html";
        }
        if (CollectionUtils.isEmpty(relations)) {
            relations = new ArrayList<GoodsPropRp>();
        }
        //得到所有品牌
        List<Brand> brands = brandService.getBrandsByCategoryNo(goodsCateNo);
        //得到商品分类下所有属性项
        List<PropItem> propItemList = propItemService.getPropItemListByCategoryNo(goodsCateNo);

        modelMap.put("relations", relations);
        modelMap.put("propItemList", propItemList);
        modelMap.put("goods", goods);
        modelMap.put("vo", vo);
        modelMap.put("brands", brands);
        modelMap.put("category", category);
        return "gms/goods_edit";

    }

    @RequestMapping(value = "goodsEdit.html")
    public String goodsEdit(Goods goods, ModelMap modelMap, HttpServletRequest request, String[] propName) {
        Date today = new Date();
        String categoryNo = goods.getCategoryNo();
        Brand brand = brandService.getBrandByNo(goods.getBrandNo());
        if (brand == null) {
            modelMap.put(CommonConstrant.MESSAGE_ERROR, "品牌不存在");
            return "redirect:/error.html";
        }
//        if (ArrayUtils.isEmpty(propName)) {
//            modelMap.put(CommonConstrant.MESSAGE_ERROR, "商品属性不能为空，请至少选择一样");
//            return "redirect:/error.html";
//        }
        if (StringUtils.isNotBlank(categoryNo)) {
            Category category = categoryService.getCategoryByNo(categoryNo);
            if (null == category) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, "分类不存在");
                return "redirect:/error.html";
            }
            if (!StringUtils.equals(category.getLevel(), CommonConstrant.CATEGORY_LEVEL_THIRD)) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, "商品需关联在第三级分类");
                return "redirect:/error.html";
            }
            goods.setCategoryStruct(category.getStruct());
            goods.setCategoryStructName(category.getStructName());
        }


        goods.setCategoryNo(categoryNo);
        goods.setBrandName(brand.getName());
        String picParam = "pic";
        if (StringUtils.isNotBlank(goods.getId())) {
            Goods obj = goodsService.getGoodsById(goods.getId());
            if (obj == null) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, "保存的商品不存在");
                return "redirect:/error.html";
            }
            //判断价格变化
            if (goods.getSellPrice() != null && (!goods.getSellPrice().equals(obj.getSellPrice()))) {
                GoodsPrice goodsPrice = new GoodsPrice();
                goodsPrice.setId(UUIDUtil.getUUID());
                goodsPrice.setCreateDate(new Date());
                goodsPrice.setGoodsNo(obj.getNo());
                goodsPrice.setPrice(goods.getSellPrice());
                goodsPrice.setUsername(CommonUtil.getAdminByRequest().getUsername());
                goodsPriceService.insert(goodsPrice);
            }

            //如果更新了图片
            String checkPic = ImgUploadUtil.isSpecImgIfExist(request, picParam);
            if (!StringUtils.equals(checkPic, CommonConstrant.SUCCESS)) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, checkPic);
                return "redirect:/error.html";
            }
            goods.setNo(obj.getNo());
            goods.setUpdateDate(today);
            //如果品牌发生了变化，先复制图片去新的目录，再处理
            if (!StringUtils.equals(goods.getBrandNo(), obj.getBrandNo())) {
                String result = ImgUploadUtil.dealGoodsPicByBrandNo(obj, goods);
//                if (StringUtils.equals(result, CommonConstrant.SUCCESS)) {
//
//                }
                goods.setGoodsPicture(ImgUploadUtil.getGoodsPicUrl(goods));
            }
            ImgUploadUtil.uploadGoodsImage(request, goods, picParam);


            goodsService.save(goods);
            modelMap.put(CommonConstrant.MESSAGE_INFO, "商品修改成功");
        } else {
            //创建商品，需要判断图片是否存在
            String checkPic = ImgUploadUtil.isSpecImg(request, picParam);
            if (!StringUtils.equals(checkPic, CommonConstrant.SUCCESS)) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, checkPic);
                return "redirect:/error.html";
            }
            String no = sequenceService.getGoodsNo();
            goods.setNo(no);
            ImgUploadUtil.uploadGoodsImage(request, goods, picParam);
            goods.setGoodsPicture(ImgUploadUtil.getGoodsPicUrl(goods));
            goods.setId(UUIDUtil.getUUID());

            goods.setCreateDate(today);
            goods.setUpdateDate(today);
            goods.setValidFlag(CommonConstrant.VALID_FLAG_YES);
            goods.setStatus(CommonConstrant.GOODS_STATUS_CREATE);
            //判断创建的商家是否是招商商家
            Admin admin = CommonUtil.getAdminByRequest();
            if (admin == null) {
                modelMap.put(CommonConstrant.MESSAGE_ERROR, "登录SESSION已过期，请重新登录");
                return "redirect:/error.html";
            }
            //如果创建的是招商商家
            if (StringUtils.equals(admin.getType(), CommonConstrant.ADMIN_TYPE_MERCHANT)) {
                goods.setMerchantName(admin.getUsername());
                goods.setMerchantCompany(admin.getCompany());
            } else {
                goods.setMerchantName("admin");//其他都默认为ADMIN
                goods.setMerchantCompany("商城自营");
            }
            modelMap.put(CommonConstrant.MESSAGE_INFO, "商品添加成功");
            goodsService.insert(goods);
        }
        //添加商品属性
        List<GoodsPropRp> rpList = new ArrayList<GoodsPropRp>();
        //商品属性1-1003-11
        for (String str : propName) {
            String[] propInfo = StringUtils.splitByWholeSeparator(str, "#*#");
            if (propInfo.length < 3) {
                continue;
            }
            //如果属性值为空，
            if (StringUtils.isBlank(propInfo[2])) {
                continue;
            }
            GoodsPropRp rp = new GoodsPropRp();
            rp.setId(UUIDUtil.getUUID());
            rp.setCreateDate(new Date());
            rp.setGoodsNo(goods.getNo());
            rp.setPropItemName(propInfo[0]);
            rp.setPropItemNo(propInfo[1]);
            rp.setPropItemValue(propInfo[2]);
            rpList.add(rp);

        }
        //数据不为空的时候处理

        goodsService.addPropToGoods(goods.getNo(), rpList);
        modelMap.put("id", goods.getId());
        modelMap.put(CommonConstrant.URL, "gms/goods/toGoodsEdit.html?id=" + goods.getId());
        return "redirect:/info.html";

    }

    @RequestMapping(value = "toGoodsManager.html")
    public String toGoodsManager(ModelMap modelMap, GoodsQueryVo vo) {

        //得到所有品牌 1
        List<Brand> brands = brandService.getAllBrands();
        modelMap.put("brands", brands);
        modelMap.put("vo", vo);
        return "gms/goods_manager";

    }

    @RequestMapping(value = "goodsManager.html")
    public String goodsManager(ModelMap modelMap, GoodsQueryVo vo, Query query) {
        String categoryNo = null;
        if (StringUtils.isNotBlank(vo.getThirdCategory())) {
            categoryNo = vo.getThirdCategory();
        } else if (StringUtils.isNotBlank(vo.getSecondCategory())) {
            categoryNo = vo.getSecondCategory();
        } else if (StringUtils.isNotBlank(vo.getFirstCategory())) {
            categoryNo = vo.getFirstCategory();
        }
        if (StringUtils.isNotBlank(categoryNo)) {
            Category category = categoryService.getCategoryByNo(categoryNo);
            if (category != null) {
                vo.setStruct(category.getStruct());
            }
        }
        vo.setMerchantName(CommonUtil.getMerchantName());
        PageData<Goods> pageData = goodsService.getGoodsPageData(vo, query);
        //得到所有品牌
        List<Brand> brands = brandService.getAllBrands();
        modelMap.put("brands", brands);
        modelMap.put("vo", vo);
        modelMap.put("pageData", pageData);
        return "gms/goods_manager";

    }

    @ResponseBody
    @RequestMapping(value = "delGoods.html")
    public String delGoods(String id) {
        MessageVo messageVo = new MessageVo();
        Goods goods = goodsService.getGoodsById(id);
        if (goods == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("找不到对应的商品");
            return JSONObject.fromObject(messageVo).toString();
        }
        goodsService.delGoods(goods);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

    @ResponseBody
    @RequestMapping(value = "changeGoodsStatus.html")
    public String changeGoodsStatus(String status, HttpServletRequest request) {
        MessageVo messageVo = new MessageVo();
        String[] goodsNo = request.getParameterValues("goodsNo[]");
        if (ArrayUtils.isEmpty(goodsNo)) {
            messageVo.setSuccess(false);
            messageVo.setMessage("找不到对应的商品");
            return JSONObject.fromObject(messageVo).toString();
        }
        String result = goodsService.changeGoodsStatus(status, goodsNo);
        if (CommonConstrant.SUCCESS.equals(result)) {
            messageVo.setSuccess(true);
        } else {
            messageVo.setSuccess(false);
            messageVo.setMessage(result);
        }

        return JSONObject.fromObject(messageVo).toString();

    }

    @ResponseBody
    @RequestMapping(value = "delGoodsList.html")
    public String delGoodsList(HttpServletRequest request) {
        String[] goodsNo = request.getParameterValues("goodsNo[]");
        MessageVo messageVo = new MessageVo();

        if (ArrayUtils.isEmpty(goodsNo)) {
            messageVo.setSuccess(false);
            messageVo.setMessage("找不到对应的商品");
            return JSONObject.fromObject(messageVo).toString();
        }

        String result = goodsService.delGoodsList(goodsNo);
        if (CommonConstrant.SUCCESS.equals(result)) {
            messageVo.setSuccess(true);
        } else {
            messageVo.setSuccess(false);
            messageVo.setMessage(result);
        }

        return JSONObject.fromObject(messageVo).toString();

    }

    @RequestMapping(value = "toAddGoodsProp.html")
    public String toAddGoodsProp(String no, ModelMap modelMap) {
        Goods goods = goodsService.getGoodsByNo(no);
        if (goods == null) {
            modelMap.put(CommonConstrant.MESSAGE_ERROR, "商品不存在");
            return "redirect:/error.html";
        }
        //得到商品分类下所有属性项
        Query query = new Query();
        query.setPage(1);
        query.setPageSize(Integer.MAX_VALUE);
        PropItemQueryVo vo = new PropItemQueryVo();
        vo.setThirdCategory(goods.getCategoryNo());
        PageData<PropItem> pageData = propItemService.getPropItemPageData(vo, query);
        List<PropItem> propItemList = pageData.getData();
        if (CollectionUtils.isEmpty(propItemList)) {
            propItemList = new ArrayList<PropItem>();
        }
        //得到商品自身关联的所有属性项
        List<GoodsPropRp> relations = goodsService.getGoodsPropRpsByGoodsNo(no);
        modelMap.put("relations", relations);
        modelMap.put("goods", goods);
        modelMap.put("propItemList", propItemList);
        return "gms/goods_addProp";
    }

    @ResponseBody
    @RequestMapping(value = "addGoodsProp.html")
    public String addGoodsProp(String no, String[] propName) {
        MessageVo messageVo = new MessageVo();
        Goods goods = goodsService.getGoodsByNo(no);
        if (goods == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("找不到对应的商品");
            return JSONObject.fromObject(messageVo).toString();
        }
        if (ArrayUtils.isEmpty(propName)) {
            messageVo.setSuccess(false);
            messageVo.setMessage("添加的商品属性为空");
            return JSONObject.fromObject(messageVo).toString();
        }
        List<GoodsPropRp> rpList = new ArrayList<GoodsPropRp>();
        //商品属性1-1003-11
        for (String str : propName) {
            String[] propInfo = StringUtils.splitByWholeSeparator(str, "#*#");
            if (propInfo.length < 3) {
                continue;
            }
            //如果属性值为空，
            if (StringUtils.isBlank(propInfo[2])) {
                continue;
            }
            GoodsPropRp rp = new GoodsPropRp();
            rp.setId(UUIDUtil.getUUID());
            rp.setCreateDate(new Date());
            rp.setGoodsNo(no);
            rp.setPropItemName(propInfo[0]);
            rp.setPropItemNo(propInfo[1]);
            rp.setPropItemValue(propInfo[2]);
            rpList.add(rp);

        }
        //数据不为空的时候处理
        if (!CollectionUtils.isEmpty(rpList)) {
            goodsService.addPropToGoods(no, rpList);
        } else {
            messageVo.setSuccess(false);
            messageVo.setMessage("商品的商品属性不能少于1");
            return JSONObject.fromObject(messageVo).toString();
        }
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

}
