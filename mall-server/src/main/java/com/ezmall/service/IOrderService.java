package com.ezmall.service;

import com.ezmall.model.Order;
import com.ezmall.model.SubOrder;
import com.ezmall.vo.OrderQueryVo;
import com.ezmall.vo.OrderSubmitVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-27
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */
public interface IOrderService {

    /**
     * 创建订单
     * @param vo
     * @return
     */
    Map<String, OrderSubmitVo> createOrder(OrderSubmitVo vo,String payment);

    OrderSubmitVo getOrderSubmitVo(String id);

    OrderSubmitVo getOrderSubmitVoByOrderNo(String no);

    Order getOrderByNo(String orderNo);

    Order getOrderById(String id);

    Integer save(Order order);

    /**
     * 需要更新订单及子订单信息
     * @param vo
     * @return
     */
    Integer saveOrderSubmitVo(OrderSubmitVo vo);

    Integer cancelOrder(String id);

    Order getOrderByNo(String orderNo,String userName);

    SubOrder getSubOrderByNo(String no);

    List<SubOrder> getSubOrdersByOrderNo(String orderNo);

    PageData<OrderSubmitVo> getOrderSubmitVoPageData(OrderQueryVo vo,Query query);

    PageData<SubOrder> getSubOrderPageData(OrderQueryVo vo,Query query);

    /**
     * 生成订单
     * @param shopCart       商品编号_购买数量
     * @param request
     * @return
     */
    OrderSubmitVo createOrderSubmitVo(String[] shopCart, HttpServletRequest request);
}
