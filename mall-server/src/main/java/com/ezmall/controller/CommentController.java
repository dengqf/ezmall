package com.ezmall.controller;

import com.ezmall.model.GoodsComment;
import com.ezmall.model.Member;
import com.ezmall.model.Order;
import com.ezmall.model.SubOrder;
import com.ezmall.service.IGoodsCommentService;
import com.ezmall.service.IGoodsService;
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
 * Date: 14-7-1
 * Time: 下午10:22
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CommentController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IGoodsService goodsService;
    @Autowired
    IGoodsCommentService goodsCommentService;




    /**
     * 用户的商品评论
     *
     * @return
     */
    @RequestMapping(value = "member/goodsComment.html")
    public String goodsComment() {

        return "member/goods_comment";

    }

    /**
     * 用户的商品评论
     *
     * @return
     */
    @RequestMapping(value = "member/commentList.html")
    public String commentList(HttpServletRequest request, Query query, ModelMap modelMap) {
        Member member = CommonUtil.getMemberByRequest(request);
        GoodsComment obj = new GoodsComment();
        obj.setUsername(member.getUsername());
        obj.setValidFlag(CommonConstrant.VALID_FLAG_YES);
        PageData<GoodsComment> pageData = goodsCommentService.getGoodsCommentPageData(obj, query);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        return "member/comment_list";

    }

    /**
     * 用户的商品评论
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "member/addGoodsComment.html")
    public String addGoodsComment(GoodsComment obj, HttpServletRequest request) {
        Member member = CommonUtil.getMemberByRequest(request);
        String orderSubNo = obj.getOrderSubNo();
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        SubOrder subOrder = orderService.getSubOrderByNo(orderSubNo);
        if (subOrder == null) {
            messageVo.setMessage("提交评论的订单不存在");
            return JSONObject.fromObject(messageVo).toString();
        }
        Order order = orderService.getOrderByNo(subOrder.getOrderNo());
        if (order == null) {
            messageVo.setMessage("提交评论的订单不存在");
            return JSONObject.fromObject(messageVo).toString();
        }
        if (!StringUtils.equals(subOrder.getStatus(), CommonConstrant.ORDER_STATUS_COMPLETE)) {
            messageVo.setMessage("只有已完成的订单才能添加评论");
            return JSONObject.fromObject(messageVo).toString();

        }
        if (!StringUtils.equals(order.getUsername(), member.getUsername())) {
            messageVo.setMessage("对不起，这个订单不属于您，你不能添加评论");
            return JSONObject.fromObject(messageVo).toString();

        }
        obj.setId(UUIDUtil.getUUID());
        obj.setGoodsName(subOrder.getGoodsName());
        obj.setGoodsNo(subOrder.getGoodsNo());
        obj.setGoodsPicture(subOrder.getGoodsPicture());
        obj.setValidFlag(CommonConstrant.VALID_FLAG_YES);
        obj.setOrderSubNo(orderSubNo);
        obj.setOrderNo(subOrder.getOrderNo());
        obj.setCreateDate(new Date());
        obj.setUsername(member.getUsername());

        subOrder.setCommentFlag(CommonConstrant.VALID_FLAG_YES);
        subOrder.setUpdateDate(new Date());
        goodsCommentService.addComment(obj, subOrder);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();

    }


}
