package com.ezmall.service;

import com.ezmall.model.FriendOrder;
import com.ezmall.model.Order;
import com.ezmall.vo.OrderSubmitVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-7-19
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */
public interface IFriendOrderService {

    /**
     * 根据订单号得到发给好友的订单信息
     *
     * @param orderNo
     * @return
     */
    FriendOrder getFriendOrderByOrderNo(String orderNo);

    FriendOrder getFriendOrderById(String id);

    Integer insert(FriendOrder friendOrder);

    Integer save(FriendOrder friendOrder);

    Integer delFriendOrderById(String id);

    /**
     *
     * @param order 订单信息
     * @param username  订单转发的好友
     * @param memberName   生成订单的用户
     * @return
     */
    Integer insertFriendOrder(Order order,String username,String memberName);

    /**
     * 得到好友订单列表
     * @param friendOrder   查询条件
     * @param query
     * @return
     */
    PageData<FriendOrder> getFriendOrderPageData(FriendOrder friendOrder,Query query);

    PageData<OrderSubmitVo> getOrderSubmitVoPageData(FriendOrder friendOrder,Query query);

    /**
     * 检查订单是否可以被转发
     * @param order
     * @return
     */
    String checkOrderCanSend(Order order);
}
