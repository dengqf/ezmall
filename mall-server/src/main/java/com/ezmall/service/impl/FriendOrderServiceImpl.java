package com.ezmall.service.impl;

import com.ezmall.mapper.FriendOrderMapper;
import com.ezmall.model.FriendOrder;
import com.ezmall.model.FriendOrderExample;
import com.ezmall.model.Order;
import com.ezmall.service.IFriendOrderService;
import com.ezmall.service.IOrderService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.OrderSubmitVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-7-19
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FriendOrderServiceImpl implements IFriendOrderService {

    @Autowired
    FriendOrderMapper friendOrderMapper;
    @Autowired
    IOrderService orderService;

    @Override
    public FriendOrder getFriendOrderByOrderNo(String orderNo) {
        FriendOrderExample example = new FriendOrderExample();
        example.or().andOrderNoEqualTo(orderNo);
        return CommonUtil.getFirstObject(friendOrderMapper.selectByExample(example));
    }

    @Override
    public FriendOrder getFriendOrderById(String id) {
        return friendOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer insert(FriendOrder friendOrder) {
        return friendOrderMapper.insertSelective(friendOrder);
    }

    @Override
    public Integer save(FriendOrder friendOrder) {
        return friendOrderMapper.updateByPrimaryKeySelective(friendOrder);
    }

    @Override
    @Transactional
    public Integer delFriendOrderById(String id) {
        FriendOrder obj = getFriendOrderById(id);
        if (obj != null) {
            String orderNo = obj.getOrderNo();
            Order order = orderService.getOrderByNo(orderNo);
            if (order != null) {
                order.setStatus(CommonConstrant.ORDER_STATUS_CREATE);
                orderService.save(order);
            }
        }
        return friendOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Integer insertFriendOrder(Order order, String username, String memberName) {
        Integer result = 0;
        String orderNo = order.getNo();
        FriendOrder friendOrder = getFriendOrderByOrderNo(orderNo);
        if (friendOrder != null) {
            friendOrder.setCreateDate(new Date());
            friendOrder.setStatus(CommonConstrant.FRIEND_ORDER_STATUS_CREATE);
            friendOrder.setFriendName(username);
            result = save(friendOrder);
        } else {
            //建立好友订单
            friendOrder = new FriendOrder();
            friendOrder.setId(UUIDUtil.getUUID());
            friendOrder.setCreateDate(new Date());
            friendOrder.setFriendName(username);
            friendOrder.setMemberName(memberName);
            friendOrder.setOrderNo(orderNo);
            friendOrder.setStatus(CommonConstrant.FRIEND_ORDER_STATUS_CREATE);
            order.setStatus(CommonConstrant.ORDER_STATUS_SEND);
            orderService.save(order);
            result = friendOrderMapper.insertSelective(friendOrder);
        }


        return result;
    }

    @Override
    public PageData<FriendOrder> getFriendOrderPageData(FriendOrder friendOrder, Query query) {

        PageData<FriendOrder> pageData = null;
        FriendOrderExample example = new FriendOrderExample();
        FriendOrderExample.Criteria criteria = example.or();
        if (StringUtils.isNotBlank(friendOrder.getFriendName())) {
            criteria.andFriendNameEqualTo(friendOrder.getFriendName());
        }
        if (StringUtils.isNotBlank(friendOrder.getMemberName())) {
            criteria.andMemberNameEqualTo(friendOrder.getMemberName());
        }
        if (StringUtils.isNotBlank(friendOrder.getStatus())) {
            criteria.andStatusEqualTo(friendOrder.getStatus());
        }
        int count = friendOrderMapper.countByExample(example);
        if (count < 1) {
            pageData = new PageData<FriendOrder>(1, 0);
        } else {
            pageData = new PageData<FriendOrder>(query.getPage(), query.getPageSize(), count);
            example.setOrderByClause(" CREATE_DATE DESC");
            List<FriendOrder> list = friendOrderMapper.selectByExample(example, CommonUtil.getRowBounds(query));
            pageData.setData(list);
        }
        return pageData;
    }

    @Override
    public PageData<OrderSubmitVo> getOrderSubmitVoPageData(FriendOrder friendOrder, Query query) {
        PageData<OrderSubmitVo> pageData = null;
        PageData<FriendOrder> obj = getFriendOrderPageData(friendOrder, query);
        if (CollectionUtils.isEmpty(obj.getData())) {
            pageData = new PageData<OrderSubmitVo>(1, 0);
        } else {
            List<FriendOrder> list = obj.getData();
            List<OrderSubmitVo> orders = new ArrayList<OrderSubmitVo>();
            for (FriendOrder fr : list) {
                String orderNo = fr.getOrderNo();
                OrderSubmitVo vo = orderService.getOrderSubmitVoByOrderNo(orderNo);
                orders.add(vo);
            }
            pageData = new PageData<OrderSubmitVo>(obj.getPageNo(), obj.getPageSize(), obj.getRowCount());
            pageData.setData(orders);
        }
        return pageData;
    }

    @Override
    public String checkOrderCanSend(Order order) {


        //如果订单不存在或者订单不处于初始状态
        if (order == null) {
            return ("对不起，找不到你需要的订单");

        }
        if (!(StringUtils.equals(CommonConstrant.ORDER_STATUS_CREATE, order.getStatus()) || StringUtils.equals(CommonConstrant.ORDER_STATUS_SEND, order.getStatus()))) {
            return ("只有处于新建或者转发状态的订单才能转发好友");

        }
        FriendOrder friendOrder = getFriendOrderByOrderNo(order.getNo());
        //如果好友订单存在且处于同意的状态下不能添加
        if (friendOrder != null && StringUtils.equals(CommonConstrant.FRIEND_ORDER_STATUS_AGREE, friendOrder.getStatus())) {
            return ("对不起，您的订单已经发给好友，并已在处理中，不能转发给其他好友");

        }
        return CommonConstrant.SUCCESS;

    }
}
