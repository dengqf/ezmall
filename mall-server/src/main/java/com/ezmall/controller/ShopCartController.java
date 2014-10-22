package com.ezmall.controller;

import com.ezmall.model.Goods;
import com.ezmall.service.IGoodsService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.vo.MessageVo;
import com.ezmall.vo.ShopCartVo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-24
 * Time: 下午9:35
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ShopCartController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IGoodsService goodsService;

    private String cookieSplitFlag = "|";
    private String cookieValueSplitFlag = "-";

    @ResponseBody
    @RequestMapping("addToShopCart.html")
    String addToShopCart(ShopCartVo vo, HttpServletResponse response,
                         @CookieValue(value = CommonConstrant.SHOPCART_COOKIE_NAME, defaultValue = "") String shopCartCookie) {
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        if (StringUtils.isBlank(vo.getNo())) {
            messageVo.setMessage("添加商品不能为空");
            return JSONObject.fromObject(messageVo).toString();
        }
        if (null == vo.getAmount() || vo.getAmount() < 1) {
            messageVo.setMessage("商品数量至少为1件");
            return JSONObject.fromObject(messageVo).toString();
        }
        String cookie = getReWriteCookie(shopCartCookie, vo);
        //COOKIE存放数据
        //商品编号-图片-商品名称-单价-货币类型-购买数量
        response.addCookie(new Cookie(CommonConstrant.SHOPCART_COOKIE_NAME, cookie));
        messageVo.setSuccess(true);
        //TODO 重复了一次查询，以后修改
        List<ShopCartVo> shopCartVos = getShopCartVosByCookie(cookie);
        double total = 0;
        for (ShopCartVo obj : shopCartVos) {
            total += obj.getPriceToCNY() * obj.getAmount();
        }
        DecimalFormat df = new DecimalFormat("0.00");

        messageVo.setMessage("您的购物车中共有" + shopCartVos.size() + "件商品，金额(折合人民币)共计 " + df.format(total) + "元");
        return JSONObject.fromObject(messageVo).toString();
    }

    @RequestMapping("toShopCart.html")
    public String toShopCart(ModelMap modelMap, @CookieValue(value = CommonConstrant.SHOPCART_COOKIE_NAME, defaultValue = "") String shopCartCookie) {
        List<ShopCartVo> list = getShopCartVosByCookie(shopCartCookie);
        modelMap.put("goodsList", list);
        return "shopCart_info";
    }

    @ResponseBody
    @RequestMapping("delShopCart.html")
    public String delShopCart(HttpServletResponse response, @CookieValue(value = CommonConstrant.SHOPCART_COOKIE_NAME, defaultValue = "") String shopCartCookie, String no) {
        MessageVo messageVo = new MessageVo();
        List<ShopCartVo> list = getShopCartVosByCookie(shopCartCookie);
        StringBuilder cookie = new StringBuilder();
        //重新写COOKIE
        for (ShopCartVo vo : list) {
            if (StringUtils.equals(vo.getNo(), no)) {
                continue;
            }
            cookie.append(vo.getNo()).append(cookieValueSplitFlag);
            cookie.append(vo.getAmount()).append(cookieSplitFlag);
        }
        response.addCookie(new Cookie(CommonConstrant.SHOPCART_COOKIE_NAME, StringUtils.removeEnd(cookie.toString(), cookieSplitFlag)));
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

    /**
     * 刷新COOKIE
     *
     * @param cookieValue
     * @param obj
     */
    private String getReWriteCookie(String cookieValue, ShopCartVo obj) {

        //如果COOKIE VALUE不为空
        List<ShopCartVo> shopCartVos = getShopCartVosByCookie(cookieValue);
        Map<String, ShopCartVo> map = new HashMap<String, ShopCartVo>();
        for (ShopCartVo vo : shopCartVos) {
            map.put(vo.getNo(), vo);
        }
        if (map.containsKey(obj.getNo())) {
            ShopCartVo info = map.get(obj.getNo());
            info.setAmount(info.getAmount() + obj.getAmount());
            map.put(obj.getNo(), info);
        } else {
            map.put(obj.getNo(), obj);
        }
        StringBuilder cookie = new StringBuilder();
        //重新写COOKIE
        for (ShopCartVo vo : map.values()) {
            cookie.append(vo.getNo()).append(cookieValueSplitFlag);
            cookie.append(vo.getAmount()).append(cookieSplitFlag);
        }
        return StringUtils.removeEnd(cookie.toString(), cookieSplitFlag);
    }

    /**
     * 根据COOKIE得到购物车购买物品列表
     *
     * @param cookieValue
     * @return
     */
    private List<ShopCartVo> getShopCartVosByCookie(String cookieValue) {
        List<ShopCartVo> vos = new ArrayList<ShopCartVo>();
        //如果COOKIE VALUE不为空
        if (StringUtils.isNotBlank(cookieValue)) {
            String[] cookieInfos = StringUtils.split(cookieValue, cookieSplitFlag);
            for (String cookie : cookieInfos) {
                if (StringUtils.isNotBlank(cookie)) {
                    String[] values = StringUtils.split(cookie, cookieValueSplitFlag);
                    //COOKIE<6不符合规范 ,商品编号-购买数量
                    if (values.length == 2) {
                        ShopCartVo vo = new ShopCartVo();
                        vo.setNo(values[0]);
                        vo.setGoodsNo(values[0]);
                        vo.setAmount(Integer.parseInt(values[1]));
                        //商品名称和价格，货币类型需要从数据库里读
                        Goods goods = goodsService.getGoodsByNo(vo.getNo());
                        if (goods == null) {
                            continue;
                        }

                        vo.setGoodsName(goods.getName());
                        vo.setSellPrice(goods.getSellPrice());
                        vo.setCurrencyType(goods.getCurrencyType());
                        vo.setStatus(goods.getStatus());
                        vo.setGoodsPicture(goods.getGoodsPicture());
                        Double cny = CommonUtil.getExchangeRate(goods.getCurrencyType()) * goods.getSellPrice();
                        vo.setPriceToCNY(cny);
                        vos.add(vo);
                    }
                }
            }
        }
        return vos;
    }
}
