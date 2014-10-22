package com.ezmall.controller;

import com.ezmall.model.Brand;
import com.ezmall.model.Goods;
import com.ezmall.service.IBrandService;
import com.ezmall.service.IGoodsService;
import com.ezmall.service.ISequenceService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.GoodsQueryVo;
import com.ezmall.vo.MessageVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-17
 * Time: 下午10:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/gms/brand")
public class BrandController {

    @Autowired
    IBrandService brandService;
    @Autowired
    ISequenceService sequenceService;
    @Autowired
    IGoodsService goodsService;

    @RequestMapping(value = "toBrandManager.html")
    public String toBrandManager(ModelMap modelMap) {

        Brand brand = new Brand();
        modelMap.put("brand", brand);
        return "gms/brand_manager";

    }

    @RequestMapping(value = "brandManager.html")
    public String brandManager(Brand brand, ModelMap modelMap, Query query) {
        PageData<Brand> pageData = brandService.getBrandPageData(brand, query);
        modelMap.put("brand", brand);
        modelMap.put("pageData", pageData);
        return "gms/brand_manager";

    }

    @RequestMapping(value = "allBrand.html")
    @ResponseBody
    public String allBrand() {
        List<Brand> brands = brandService.getAllBrands();
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(true);
        messageVo.setObj(brands);
        return JSONObject.fromObject(messageVo).toString();


    }


    @RequestMapping(value = "toBrandEdit.html")
    public String toBrandManager(String id, ModelMap modelMap) {
        Brand brand = brandService.getBrandById(id);
        if (null == brand) {
            brand = new Brand();
        }
        modelMap.put("brand", brand);
        return "gms/brand_edit";

    }

    @ResponseBody
    @RequestMapping(value = "delBrand.html")
    public String delBrand(String id) {
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        Brand brand = brandService.getBrandById(id);
        if (brand == null) {
            messageVo.setMessage("找不到对应的品牌");
            return JSONObject.fromObject(messageVo).toString();
        }
        List<Goods> list = goodsService.getGoodsListByBrandNo(brand.getNo());
        if (!CollectionUtils.isEmpty(list)) {
            messageVo.setMessage("该品牌下还有商品，不能删除");
            return JSONObject.fromObject(messageVo).toString();
        }

        //删除分类与品牌的关联
        brandService.delBrand(brand);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();

    }

    @RequestMapping(value = "brandEdit.html")
    @ResponseBody
    public String brandManager(Brand brand, ModelMap modelMap) {
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        if (StringUtils.isNotBlank(brand.getId())) {
            Brand obj = brandService.getBrandById(brand.getId());
            if (obj == null) {
                messageVo.setMessage("品牌ID不存在");
                return JSONObject.fromObject(messageVo).toString();
            }
            //名字不同判断
            if (!StringUtils.equals(obj.getName(), brand.getName())) {
                obj = brandService.getBrandByName(brand.getName());
                if (obj != null) {
                    messageVo.setMessage("品牌名存在，请重新选择");
                    return JSONObject.fromObject(messageVo).toString();
                }
            }
            brand.setUpdateDate(new Date());
            messageVo.setMessage("品牌修改成功");
            brandService.save(brand);
        } else {
            Brand obj = brandService.getBrandByName(brand.getName());
            if (obj != null) {
                messageVo.setMessage("品牌名存在，请重新选择");
                return JSONObject.fromObject(messageVo).toString();
            }
            brand.setId(UUIDUtil.getUUID());
            brand.setNo(sequenceService.getBrandNo());
            brand.setCreateDate(new Date());
            brand.setUpdateDate(new Date());
            messageVo.setMessage("品牌创建成功");
            brandService.insert(brand);

        }
        messageVo.setId(brand.getId());
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();

    }
}
