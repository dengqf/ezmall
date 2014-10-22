package com.ezmall.controller;

import com.ezmall.model.Favorite;
import com.ezmall.model.Goods;
import com.ezmall.model.Member;
import com.ezmall.service.IFavoriteService;
import com.ezmall.service.IGoodsService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.FavoriteVo;
import com.ezmall.vo.MessageVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-26
 * Time: 下午10:57
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class FavoriteController {

    @Autowired
    IFavoriteService favoriteService;
    @Autowired
    IGoodsService goodsService;

    @ResponseBody
    @RequestMapping(value = "member/addFavoriteToMember.html")
    String addFavoriteToMember(String goodsNo, HttpServletRequest request) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByRequest(request);
        if (member == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("只有登录用户才能收藏商品");
            return JSONObject.fromObject(messageVo).toString();
        }
        Goods goods = goodsService.getGoodsByNo(goodsNo);
        if (null == goods) {
            messageVo.setSuccess(false);
            messageVo.setMessage("找不到对应的商品");
            return JSONObject.fromObject(messageVo).toString();
        }
        if (StringUtils.equals(goods.getValidFlag(), CommonConstrant.VALID_FLAG_NO)) {
            messageVo.setSuccess(false);
            messageVo.setMessage("该商品已退市");
            return JSONObject.fromObject(messageVo).toString();
        }

        favoriteService.insert(member.getUsername(), goodsNo);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

    @ResponseBody
    @RequestMapping(value = "member/delFavorite.html")
    String delFavorite(String id) {
        MessageVo messageVo = new MessageVo();

        favoriteService.delete(id);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

    @RequestMapping(value = "member/delFavoriteList.html")
    String delFavoriteList(String[] id) {

        List<String> list = Arrays.asList(id);
        favoriteService.delete(list);
        return "redirect:favoriteList.html";
    }

    @RequestMapping(value = "member/favoriteList.html")
    String favoriteList(Query query, HttpServletRequest request, ModelMap modelMap) {
        Member member = CommonUtil.getMemberByRequest(request);
        PageData<FavoriteVo> pageData = favoriteService.getFavoritePageData(member.getUsername(), query);
        modelMap.put("query", query);
        modelMap.put("pageData", pageData);
        return "member/favorite_list";
    }

}
