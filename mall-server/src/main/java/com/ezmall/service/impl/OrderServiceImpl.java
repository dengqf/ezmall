package com.ezmall.service.impl;

import com.ezmall.mapper.OrderMapper;
import com.ezmall.mapper.SubOrderMapper;
import com.ezmall.model.*;
import com.ezmall.service.IGoodsService;
import com.ezmall.service.IOrderService;
import com.ezmall.service.ISequenceService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.OrderQueryVo;
import com.ezmall.vo.OrderSubmitVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-27
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    ISequenceService sequenceService;
    @Autowired
    IGoodsService goodsService;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    SubOrderMapper subOrderMapper;

    @Override
    @Transactional
    public Map<String, OrderSubmitVo> createOrder(OrderSubmitVo vo, String payment) {
        /**
         * 依照不同的商家，分为几个单？
         */

        Map<String, OrderSubmitVo> map = new HashMap<String, OrderSubmitVo>();
        Date today = new Date();
        for (SubOrder subOrder : vo.getSubOrders()) {
            String merchantName = subOrder.getMerchantName();
            if (StringUtils.isBlank(merchantName)) {
                merchantName = CommonConstrant.GOODS_MERCHANT_SYSTEM;
            }
            if (map.containsKey(merchantName)) {
                OrderSubmitVo a = map.get(merchantName);
                Order order = a.getOrder();
                int totalAmount = order.getTotalAmount() + subOrder.getAmount();
                double totalExpressPrice = order.getTotalExpressPrice() + subOrder.getExpressPrice();
                double totalPreferentitalPrice = order.getTotalPreferentitalPrice() + subOrder.getPreferentitalPrice();
                double totalPrice = order.getTotalPrice() + subOrder.getTotalOrderPrice();
                int totalGiveScore = order.getTotalGiveScore() + subOrder.getGiveScore();

                order.setTotalAmount(totalAmount);
                order.setTotalExpressPrice(totalExpressPrice);
                order.setTotalPrice(totalPrice);
                order.setTotalGiveScore(totalGiveScore);
                order.setTotalPreferentitalPrice(totalPreferentitalPrice);
                a.getSubOrders().add(subOrder);
            } else {

                Order order = new Order();
                order.setAddress(vo.getOrder().getAddress());
                order.setLinkman(vo.getOrder().getLinkman());
                order.setMobile(vo.getOrder().getMobile());
                order.setUsername(vo.getOrder().getUsername());
                order.setId(UUIDUtil.getUUID());
                //订单分离
                order.setMerchantName(merchantName);
                order.setCreateDate(today);
                order.setUpdateDate(today);
                order.setPayFlag(CommonConstrant.VALID_FLAG_NO);
                order.setShipmentFlag(CommonConstrant.VALID_FLAG_NO);

                order.setPayment(payment);
                order.setViewFlag(CommonConstrant.VALID_FLAG_YES);
                order.setStatus(CommonConstrant.ORDER_STATUS_CREATE);

                int totalAmount = 0;
                double totalExpressPrice = 0.0;
                double totalPrice = 0.0;
                int totalGiveScore = 0;
                double totalPreferentitalPrice = 0.0;


                totalAmount += subOrder.getAmount();
                totalExpressPrice += subOrder.getExpressPrice();
                totalPreferentitalPrice += subOrder.getPreferentitalPrice();
                totalPrice += subOrder.getTotalOrderPrice();

                order.setTotalAmount(totalAmount);
                order.setTotalExpressPrice(totalExpressPrice);
                order.setTotalPrice(totalPrice);
                order.setTotalGiveScore(totalGiveScore);
                order.setTotalPreferentitalPrice(totalPreferentitalPrice);


                List<SubOrder> list = new ArrayList<SubOrder>();
                list.add(subOrder);

                OrderSubmitVo obj = new OrderSubmitVo(order, list);
                map.put(merchantName, obj);

            }
        }

        for (OrderSubmitVo obj : map.values()) {
            int i = 1;
            String orderNo = sequenceService.getOrderNo();

            Order order = obj.getOrder();
            order.setNo(orderNo);

            for (SubOrder subOrder : obj.getSubOrders()) {
                String subNo = orderNo + "_" + i;
                subOrder.setId(UUIDUtil.getUUID());
                subOrder.setOrderNo(orderNo);
                subOrder.setOrderSubNo(subNo);
                subOrder.setCreateDate(today);
                subOrder.setUpdateDate(today);
                subOrder.setPayFlag(CommonConstrant.VALID_FLAG_NO);
                subOrder.setPayment(payment);
                subOrder.setStatus(CommonConstrant.ORDER_STATUS_CREATE);

                subOrderMapper.insertSelective(subOrder);
            }
            orderMapper.insertSelective(order);
        }

//        Order order = vo.getOrder();
//        String orderNo = sequenceService.getOrderNo();
//        order.setId(UUIDUtil.getUUID());
//        order.setNo(orderNo);
//
//        order.setCreateDate(today);
//        order.setUpdateDate(today);
//        order.setPayFlag(CommonConstrant.VALID_FLAG_NO);
//        order.setPayment(payment);
//        order.setViewFlag(CommonConstrant.VALID_FLAG_YES);
//        order.setStatus(CommonConstrant.ORDER_STATUS_CREATE);
//
//        orderMapper.insertSelective(order);
//        List<SubOrder> subOrders = vo.getSubOrders();
//        int i = 1;
//        for (SubOrder subOrder : subOrders) {
//            String subNo = orderNo + "_" + i;
//            subOrder.setId(UUIDUtil.getUUID());
//            subOrder.setOrderNo(orderNo);
//            subOrder.setOrderSubNo(subNo);
//            subOrder.setCreateDate(today);
//            subOrder.setUpdateDate(today);
//            subOrder.setPayFlag(CommonConstrant.VALID_FLAG_NO);
//            subOrder.setPayment(payment);
//            subOrder.setStatus(CommonConstrant.ORDER_STATUS_CREATE);
//            subOrderMapper.insertSelective(subOrder);
//        }
        return map;
    }

    @Override
    public OrderSubmitVo getOrderSubmitVo(String id) {
        Order order = getOrderById(id);
        if (null == order) {
            return null;
        }
        List<SubOrder> subOrders = getSubOrdersByOrderNo(order.getNo());
        return new OrderSubmitVo(order, subOrders);
    }

    @Override
    public OrderSubmitVo getOrderSubmitVoByOrderNo(String no) {
        Order order = getOrderByNo(no);
        if (null == order) {
            return null;
        }
        List<SubOrder> subOrders = getSubOrdersByOrderNo(order.getNo());
        return new OrderSubmitVo(order, subOrders);
    }

    @Override
    public Order getOrderByNo(String orderNo) {
        OrderExample example = new OrderExample();
        example.or().andNoEqualTo(orderNo);
        return CommonUtil.getFirstObject(orderMapper.selectByExample(example));
    }

    @Override
    public Order getOrderById(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer save(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    @Transactional
    public Integer saveOrderSubmitVo(OrderSubmitVo vo) {
        Date today = new Date();
        Order order = vo.getOrder();
        order.setUpdateDate(today);

        List<SubOrder> list = vo.getSubOrders();
        for (SubOrder obj : list) {
            obj.setStatus(order.getStatus());
            obj.setUpdateDate(today);
            obj.setShipmentFlag(order.getShipmentFlag());
            obj.setExpressCompany(order.getExpressCompany());
            obj.setExpressOrderNo(order.getExpressOrderNo());
            subOrderMapper.updateByPrimaryKeySelective(obj);
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    @Transactional
    public Integer cancelOrder(String id) {
        Order order = getOrderById(id);
        if (null == order) {
            return -1;
        }
        order.setViewFlag(CommonConstrant.VALID_FLAG_NO);
        order.setStatus(CommonConstrant.ORDER_STATUS_USER_CANCEL);
        order.setUpdateDate(new Date());
        List<SubOrder> list = getSubOrdersByOrderNo(order.getNo());
        for (SubOrder subOrder : list) {
            subOrder.setStatus(CommonConstrant.ORDER_STATUS_USER_CANCEL);
            subOrder.setUpdateDate(new Date());
            subOrderMapper.updateByPrimaryKeySelective(subOrder);
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order getOrderByNo(String orderNo, String userName) {
        Order order = getOrderByNo(orderNo);
        if (null != order) {
            if (!StringUtils.equals(order.getUsername(), userName)) {
                return null;
            }
        }
        return order;
    }

    @Override
    public SubOrder getSubOrderByNo(String no) {
        SubOrderExample example = new SubOrderExample();
        example.or().andOrderSubNoEqualTo(no);
        return CommonUtil.getFirstObject(subOrderMapper.selectByExample(example));
    }

    @Override
    public List<SubOrder> getSubOrdersByOrderNo(String orderNo) {
        SubOrderExample example = new SubOrderExample();
        example.or().andOrderNoEqualTo(orderNo);
        return subOrderMapper.selectByExample(example);
    }

    @Override
    public PageData<OrderSubmitVo> getOrderSubmitVoPageData(OrderQueryVo vo, Query query) {
        PageData<OrderSubmitVo> pageData = null;
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.or();
        if (StringUtils.isNotBlank(vo.getUserName())) {
            criteria.andUsernameEqualTo(vo.getUserName());
        }
        if (StringUtils.isNotBlank(vo.getStartCreateDate())) {
//            Date date=
//            criteria.andCreateDateGreaterThanOrEqualTo(vo.getStartCreateDate());
        }
        if (StringUtils.isNotBlank(vo.getEndCreateDate())) {
//            criteria.andCreateDateLessThanOrEqualTo(vo.getEndCreateDate());
        }
        if (StringUtils.isNotBlank(vo.getViewFlag())) {
            criteria.andViewFlagEqualTo(vo.getViewFlag());
        }
        if (StringUtils.isNotBlank(vo.getMerchantName())) {
            criteria.andMerchantNameEqualTo(vo.getMerchantName());
        }
        if (StringUtils.isNotBlank(vo.getOrderNo())) {
            criteria.andNoEqualTo(vo.getOrderNo());
        }
        if (StringUtils.isNotBlank(vo.getOrderStatus())) {
            criteria.andStatusEqualTo(vo.getOrderStatus());
        }
        Integer rows = orderMapper.countByExample(example);
        if (rows < 1) {
            pageData = new PageData<OrderSubmitVo>(1, 0);
        } else {
            pageData = new PageData<OrderSubmitVo>(query.getPage(), query.getPageSize(), rows);
            example.setOrderByClause("create_date desc");
            List<Order> list = orderMapper.selectByExample(example,CommonUtil.getRowBounds(query));
            List<OrderSubmitVo> os = new ArrayList<OrderSubmitVo>();
            for (Order order : list) {
                List<SubOrder> subOrders = getSubOrdersByOrderNo(order.getNo());
                OrderSubmitVo result = new OrderSubmitVo(order, subOrders);
                os.add(result);
            }
            pageData.setData(os);
        }
        return pageData;
    }

    @Override
    public PageData<SubOrder> getSubOrderPageData(OrderQueryVo vo, Query query) {
        Integer row = subOrderMapper.countSubOrderPageData(vo);
        PageData<SubOrder> pageData = null;
        if (row < 1) {
            pageData = new PageData<SubOrder>(1, 0);
        } else {
            pageData = new PageData<SubOrder>(query.getPage(), query.getPageSize(), row);
            List<SubOrder> list = subOrderMapper.getSubOrderPageData(vo, CommonUtil.getRowBounds(query));
            pageData.setData(list);
        }
        return pageData;
    }

    @Override
    public OrderSubmitVo createOrderSubmitVo(String[] shopCart, HttpServletRequest request) {
        Double totalPrice = 0.0;
        Integer totalAmount = 0;
        Double totalExpressPrice = 0.0;
        Integer totalGiveScore = 0;
        Double totalPreferentitalPrice = 0.0;
        List<SubOrder> subOrders = new ArrayList<SubOrder>();
        for (String shopCartInfo : shopCart) {
            String[] infos = StringUtils.split(shopCartInfo, "_");
            if (infos.length < 2) {
                continue;
            }
            String no = infos[0];
            Integer amount = Integer.parseInt(infos[1]);

            Goods goods = goodsService.getGoodsByNo(no);
            //商品不能被删除或者下架
            if (goods == null || CommonConstrant.VALID_FLAG_NO.equals(goods.getValidFlag()) || CommonConstrant.GOODS_STATUS_OFFSALE.equals(goods.getStatus())) {
                continue;
            }
            Double express = goods.getExpressPrice();
            if (null == express) {
                express = 0.0;
            }
            Integer giveScore = goods.getGiftScore();
            if (null == giveScore) {
                giveScore = 0;
            }
            SubOrder subOrder = new SubOrder();
            subOrder.setAmount(amount);
            subOrder.setGoodsNo(no);
            subOrder.setGoodsSpecName(goods.getSpecName());
            subOrder.setCurrencyType(goods.getCurrencyType());
            subOrder.setGoodsPrice(goods.getSellPrice());
            subOrder.setRate(CommonUtil.getExchangeRate(goods.getCurrencyType()));//当前汇率
            subOrder.setGoodsName(goods.getName());
            subOrder.setGoodsMarketPrice(goods.getMarketPrice());
            subOrder.setGoodsPicture(goods.getGoodsPicture());
            subOrder.setGiveScore(giveScore * amount);
            subOrder.setMerchantName(goods.getMerchantName());

            //订单价，暂时为商品价格，以后是活动价
            subOrder.setOrderPrice(goods.getSellPrice());
            //子订单运费（累计计算,先按商品累计计算，以后添加运费规则）

            subOrder.setExpressPrice(express * amount);
            //优惠金额  暂时设定为 （市场价-订单价）*数量
            Double preferentitalPrice = (subOrder.getGoodsMarketPrice() - subOrder.getOrderPrice()) * subOrder.getAmount();
            subOrder.setPreferentitalPrice(preferentitalPrice);
            //子订单最终价（不含运费）
            Double subTotal = subOrder.getAmount() * subOrder.getOrderPrice() * subOrder.getRate();
            subOrder.setTotalOrderPrice(subTotal);
            subOrder.setCommentFlag(CommonConstrant.VALID_FLAG_NO);

            totalAmount += subOrder.getAmount();
            totalPrice += subTotal;
            totalExpressPrice += subOrder.getAmount() * subOrder.getExpressPrice();
            totalGiveScore += subOrder.getGiveScore();
            totalPreferentitalPrice += preferentitalPrice;
            subOrders.add(subOrder);
        }

        Member member = CommonUtil.getMemberByRequest(request);
        //todo 订单地址和收货人暂时只取注册用户信息，以后添加收货地址管理
        Order order = new Order();
        order.setAddress(member.getAddress());
        order.setLinkman(member.getLinkman());
        order.setMobile(member.getMobile());
        order.setUsername(member.getUsername());
        order.setTotalAmount(totalAmount);
        order.setTotalExpressPrice(totalExpressPrice);
        order.setTotalPrice(totalPrice);
        order.setTotalGiveScore(totalGiveScore);
        order.setTotalPreferentitalPrice(totalPreferentitalPrice);


        return new OrderSubmitVo(order, subOrders);
    }
}
