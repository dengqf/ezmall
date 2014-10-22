package com.ezmall.controller;

import com.ezmall.model.Friend;
import com.ezmall.model.FriendOrder;
import com.ezmall.model.Member;
import com.ezmall.model.Order;
import com.ezmall.service.IFriendOrderService;
import com.ezmall.service.IFriendService;
import com.ezmall.service.IOrderService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
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
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-7-19
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/member")
public class FriendOrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IFriendService friendService;
    @Autowired
    IFriendOrderService friendOrderService;

    @ResponseBody
    @RequestMapping(value = "checkOrderCanSend.html")
    /**
     * 检查订单是否可以发给好友
     * 订单NO
     */
    public String checkOrderCanSend(String no, HttpServletRequest request) {
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        Order order = orderService.getOrderByNo(no);
        String result = friendOrderService.checkOrderCanSend(order);
        if (!StringUtils.equals(CommonConstrant.SUCCESS, result)) {
            messageVo.setMessage(result);
            return JSONObject.fromObject(messageVo).toString();
        }

        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();

    }

    /**
     * 选择发送订单的好友
     *
     * @param orderNo
     * @param modelMap
     * @param query
     * @param request
     * @return
     */
    @RequestMapping(value = "sendOrderToFriend.html")
    public String toSendOrderToFriend(String orderNo, ModelMap modelMap, Query query, HttpServletRequest request) {
        Friend friend = new Friend();
        friend.setMemberUsername(CommonUtil.getMemberByRequest(request).getUsername());
        friend.setStatus(CommonConstrant.FRIEND_STATUS_AGREE);
        PageData<Friend> pageData = friendService.getFriendPageData(friend, query);
        modelMap.put("pageData", pageData);
        modelMap.put("orderNo", orderNo);
        modelMap.put("query", query);
        return "member/order_sendFriend";
    }

    /**
     * @param orderNo  订单编号
     * @param username 订单将要发给的好友
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "confirmOrderToFriend.html")
    public String confirmOrderToFriend(String orderNo, String username, HttpServletRequest request) {
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        Order order = orderService.getOrderByNo(orderNo);
        String result =friendOrderService.checkOrderCanSend(order);
        if (!StringUtils.equals(CommonConstrant.SUCCESS, result)) {
            messageVo.setMessage(result);
            return JSONObject.fromObject(messageVo).toString();
        }
        friendOrderService.insertFriendOrder(order,username,CommonUtil.getMemberByRequest(request).getUsername());
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

    /**
     * 好友给我的订单
     *
     * @return
     */
    @RequestMapping(value = "friendOrderToMe.html")
    public String friendOrderToMe(HttpServletRequest request, Query query, ModelMap modelMap) {
        Member member = CommonUtil.getMemberByRequest(request);
        FriendOrder obj = new FriendOrder();
        obj.setFriendName(member.getUsername());
        PageData<FriendOrder> pageData = friendOrderService.getFriendOrderPageData(obj, query);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        return "member/friendOrder_toMe";
    }

    /**
     * 我给好友的订单/我转发的订单
     *
     * @param request
     * @param query
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "friendOrderFromMe.html")
    public String friendOrderFromMe(HttpServletRequest request, Query query, ModelMap modelMap) {
        Member member = CommonUtil.getMemberByRequest(request);
        FriendOrder obj = new FriendOrder();
        obj.setMemberName(member.getUsername());
        PageData<FriendOrder> pageData = friendOrderService.getFriendOrderPageData(obj, query);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        return "member/friendOrder_fromMe";
    }


    @ResponseBody
    @RequestMapping(value = "changeFriendOrderStatus.html")
    public String changeFriendOrderStatus(String id, String status, HttpServletRequest request) {
        MessageVo messageVo = new MessageVo();
        FriendOrder obj = friendOrderService.getFriendOrderById(id);
        if (obj == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("找不到对应的好友订单");
        } else {
            obj.setStatus(status);
            friendOrderService.save(obj);
            messageVo.setSuccess(true);
        }
        return JSONObject.fromObject(messageVo).toString();
    }

    @ResponseBody
    @RequestMapping(value = "delFriendOrder.html")
    public String delFriendOrder(String id) {
        MessageVo messageVo = new MessageVo();
        friendOrderService.delFriendOrderById(id);
        messageVo.setSuccess(true);

        return JSONObject.fromObject(messageVo).toString();
    }


}
