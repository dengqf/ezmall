package com.ezmall.controller;

import com.ezmall.model.Brand;
import com.ezmall.model.Category;
import com.ezmall.model.PropItem;
import com.ezmall.service.ICategoryService;
import com.ezmall.service.IPropItemService;
import com.ezmall.service.ISequenceService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.MessageVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.PropItemQueryVo;
import com.ezmall.vo.Query;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-20
 * Time: 下午10:11
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/gms/prop")
public class PropItemController {
    @Autowired
    ISequenceService sequenceService;
    @Autowired
    IPropItemService propItemService;
    @Autowired
    ICategoryService categoryService;

    @RequestMapping(value = "toPropItemManager.html")
    public String toPropItemManager(ModelMap modelMap, PropItemQueryVo vo) {
        Query query = new Query();
        PageData<PropItem> pageData = propItemService.getPropItemPageData(vo, query);
        modelMap.put("pageData", pageData);
        modelMap.put("vo", vo);
        return "gms/propItem_manager";

    }

    @RequestMapping(value = "propItemManager.html")
    public String propItemManager(ModelMap modelMap, PropItemQueryVo vo, Query query) {
        PageData<PropItem> pageData = propItemService.getPropItemPageData(vo, query);
        modelMap.put("pageData", pageData);
        modelMap.put("vo", vo);
        modelMap.put("query", query);
        return "gms/propItem_manager";

    }

    @RequestMapping(value = "toPropItemEdit.html")
    public String toPropItemEdit(String id, ModelMap modelMap) {
        PropItem item = null;
        if (StringUtils.isNotBlank(id)) {
            item = propItemService.getPropItemById(id);
        }
        if (item == null) {
            item = new PropItem();
        }
        modelMap.put("item", item);
        return "gms/propItem_edit";

    }

    @ResponseBody
    @RequestMapping(value = "propItemEdit.html")
    public String propItemEdit(PropItem item, String thirdCategory) {
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        if (StringUtils.isBlank(item.getId())) {
            PropItem old = propItemService.getPropItemByName(item.getName());
            if (old != null) {
                messageVo.setMessage("属性名称已经存在，请重新选择");
                return JSONObject.fromObject(messageVo).toString();
            }
            Category category = categoryService.getCategoryByNo(thirdCategory);
            if (null == category) {
                messageVo.setMessage("分类不存在");
                return JSONObject.fromObject(messageVo).toString();
            }
            if (!StringUtils.equals(category.getLevel(), CommonConstrant.CATEGORY_LEVEL_THIRD)) {
                messageVo.setMessage("属性必须关联第三级分类");
                return JSONObject.fromObject(messageVo).toString();
            }
            item.setCreateDate(new Date());
            item.setId(UUIDUtil.getUUID());
            item.setNo(sequenceService.getPropItemNo());
            propItemService.insert(item, thirdCategory);
        } else {
            PropItem old = propItemService.getPropItemById(item.getId());
            if (old == null) {
                messageVo.setMessage("属性ID不存在");
                return JSONObject.fromObject(messageVo).toString();
            }
            //如果名字有了变化，判断
            if (!StringUtils.equalsIgnoreCase(old.getName(), item.getName())) {
                PropItem obj = propItemService.getPropItemByName(item.getName());
                if (obj != null) {
                    messageVo.setMessage("属性名称已经存在，请重新选择");
                    return JSONObject.fromObject(messageVo).toString();
                }
            }
            propItemService.save(item);
            //修改数据
        }

        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();

    }

    @ResponseBody
    @RequestMapping(value = "delPropItem.html")
    public String delPropItem(String id) {
        MessageVo messageVo = new MessageVo();
        PropItem item = propItemService.getPropItemById(id);
        if (item == null) {
            messageVo.setMessage("属性不存在");
            messageVo.setSuccess(false);
            return JSONObject.fromObject(messageVo).toString();
        }
        propItemService.delPropItemByNo(item);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

}
