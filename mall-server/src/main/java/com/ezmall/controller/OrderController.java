package com.ezmall.controller;

import com.ezmall.model.Goods;
import com.ezmall.model.Member;
import com.ezmall.model.Order;
import com.ezmall.model.SubOrder;
import com.ezmall.service.IGoodsService;
import com.ezmall.service.IOrderService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.vo.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-27
 * Time: 下午9:54
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class OrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IGoodsService goodsService;

    /**
     * 单品页提交订单
     *
     * @param amount
     * @param no
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "member/toOrderSubmit.html")
    public String toOrderSubmit(Integer amount, String no, ModelMap modelMap, HttpServletRequest request) {
        String[] shopCart = new String[]{no + "_" + amount};

        OrderSubmitVo vo = orderService.createOrderSubmitVo(shopCart, request);
        modelMap.put("vo", vo);
        return "member/order_submit";

    }

    /**
     * 购物车提交订单
     *
     * @param shopCart
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "member/toShopCartSubmit.html")
    public String toShopCartSubmit(String[] shopCart, ModelMap modelMap, HttpServletRequest request) {
        OrderSubmitVo vo = orderService.createOrderSubmitVo(shopCart, request);
        modelMap.put("vo", vo);
        return "member/order_submit";

    }

    /**
     * 创建订单
     *
     * @param shopCart
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "member/createOrder.html")
    public String createOrder(String[] shopCart, ModelMap modelMap, HttpServletRequest request, String payment) {
        OrderSubmitVo vo = orderService.createOrderSubmitVo(shopCart, request);
        Map<String, OrderSubmitVo> orderMap = orderService.createOrder(vo, payment);
        if (CollectionUtils.isEmpty(orderMap)) {
            //错误
        }
        //如果是一个订单，则直接进入付款页 ，如果是多个订单，则进入列表页

        if (orderMap.size() == 1) {
            Order order = null;
            for (OrderSubmitVo a : orderMap.values()) {
                order = a.getOrder();

            }
            if (order == null) {
                return "redirect:orderList.html";
            } else {
                modelMap.put("id", order.getId());
                //进入支付页
                return "redirect:paySubmit.html";
            }

        } else {
            return "redirect:orderList.html";
        }


    }


    /**
     * 订单支付页
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "member/orderInfo.html")
    public String orderInfo(String id, ModelMap modelMap, HttpServletRequest request) {
        OrderSubmitVo vo = orderService.getOrderSubmitVo(id);
        //进入支付页
        if (vo == null) {
            //TODO
        }
        //判断订单是否属于该用户
        Order order = vo.getOrder();
        Member member = CommonUtil.getMemberByRequest(request);
        if (!StringUtils.equals(order.getUsername(), member.getUsername())) {
            //TODO 订单不属于该用户

        }
        modelMap.put("vo", vo);
        return "member/order_info";

    }

    /**
     * 用户订单列表
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "member/orderList.html")
    public String orderList(ModelMap modelMap, HttpServletRequest request, Query query) {
        Member member = CommonUtil.getMemberByRequest(request);
        OrderQueryVo vo = new OrderQueryVo();
        vo.setUserName(member.getUsername());
        vo.setViewFlag(CommonConstrant.VALID_FLAG_YES);
        PageData<OrderSubmitVo> pageData = orderService.getOrderSubmitVoPageData(vo, query);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        return "member/order_list";

    }


    /**
     * 订单支付页
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "member/paySubmit.html")
    public String paySubmit(String id, ModelMap modelMap) {

        //进入支付页
        Order order = orderService.getOrderById(id);
        if (order == null) {
            //TODO
        }
        modelMap.put("order", order);
        return "member/pay_submit";

    }

    @ResponseBody
    @RequestMapping(value = "member/cancelOrder.html")
    public String cancelOrder(String id, ModelMap modelMap) {
        MessageVo messageVo = new MessageVo();
        Order order = orderService.getOrderById(id);
        if (order == null) {
            messageVo.setMessage("找不到对应的订单");
            messageVo.setSuccess(false);
            return JSONObject.fromObject(messageVo).toString();
        }

        if (StringUtils.equals(order.getStatus(), CommonConstrant.ORDER_STATUS_CREATE)) {
            messageVo.setMessage("对不起，只有新建的订单才能取消订单，如有问题，请与客服联系");
            messageVo.setSuccess(false);
            return JSONObject.fromObject(messageVo).toString();
        }
        Integer result = orderService.cancelOrder(id);

        if (result < 1) {
            messageVo.setMessage("找不到对应的订单");
            messageVo.setSuccess(false);

        } else {
            messageVo.setSuccess(true);
        }


        return JSONObject.fromObject(messageVo).toString();

    }


    /**
     * 得到尚未评论的子订单（完成状态）
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "member/notCommentSubOrder.html")
    public String notCommentSubOrder(ModelMap modelMap, HttpServletRequest request, Query query) {
        Member member = CommonUtil.getMemberByRequest(request);
        OrderQueryVo vo = new OrderQueryVo();
        vo.setUserName(member.getUsername());
        vo.setSubOrderStatus(CommonConstrant.ORDER_STATUS_COMPLETE);
        vo.setCommentFlag(CommonConstrant.VALID_FLAG_NO);
        //进入支付页
        PageData<SubOrder> pageData = orderService.getSubOrderPageData(vo, query);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        return "member/order_notComment";

    }

    @RequestMapping(value = "gms/order/toOrderManager.html")
    public String toOrderManager(ModelMap modelMap) {
        OrderQueryVo vo = new OrderQueryVo();
        modelMap.put("vo", vo);
        return "gms/order_manager";

    }

    @RequestMapping(value = "gms/order/toOrderEdit.html")
    public String toOrderEdit(ModelMap modelMap, String id) {
        OrderSubmitVo vo = orderService.getOrderSubmitVo(id);
        if (vo == null) {
            modelMap.put(CommonConstrant.MESSAGE_ERROR, "找不到对应的订单");
            return "redirect:/error.html";
        }
        modelMap.put("vo", vo);
        return "gms/order_edit";

    }

    @ResponseBody
    @RequestMapping(value = "gms/order/orderEdit.html")
    public String orderEdit(ModelMap modelMap, Order order) {
        OrderSubmitVo obj = orderService.getOrderSubmitVo(order.getId());
        MessageVo messageVo = new MessageVo();

        if (obj == null) {
            messageVo.setMessage("找不到对应的订单");
            messageVo.setSuccess(false);
        } else {
            obj.setOrder(order);
            orderService.saveOrderSubmitVo(obj);
            messageVo.setSuccess(true);
        }


        return JSONObject.fromObject(messageVo).toString();

    }

    @RequestMapping(value = "gms/order/orderManager.html")
    public String orderManager(ModelMap modelMap, OrderQueryVo vo, Query query) {

        String checkMerchant = CommonUtil.getMerchantName();
        if (StringUtils.isNotBlank(checkMerchant)) {
            vo.setMerchantName(checkMerchant);
        }

        PageData<OrderSubmitVo> pageData = orderService.getOrderSubmitVoPageData(vo, query);
        modelMap.put("vo", vo);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        return "gms/order_manager";

    }



}
