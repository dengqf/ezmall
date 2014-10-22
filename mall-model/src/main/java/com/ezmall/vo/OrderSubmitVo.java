package com.ezmall.vo;

import com.ezmall.model.Order;
import com.ezmall.model.SubOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-29
 * Time: 上午9:07
 * To change this template use File | Settings | File Templates.
 * 订单提交前的VO
 */
public class OrderSubmitVo {

    Order order;
    List<SubOrder> subOrders;

    public OrderSubmitVo(Order order, List<SubOrder> subOrders) {
        this.order = order;
        this.subOrders = subOrders;
    }

    public Order getOrder() {
        if(order==null){
            order=new Order();
        }
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<SubOrder> getSubOrders() {
        if(null==subOrders){
            subOrders=new ArrayList<SubOrder>();
        }
        return subOrders;
    }

    public void setSubOrders(List<SubOrder> subOrders) {
        this.subOrders = subOrders;
    }
}
