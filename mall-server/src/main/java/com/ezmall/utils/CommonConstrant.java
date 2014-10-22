package com.ezmall.utils;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-17
 * Time: 下午11:24
 * To change this template use File | Settings | File Templates.
 */
public interface CommonConstrant {

    public final static long IMG_MAX_SIZE = 1024 * 1024;
    //站点
    public final static String DOMAIN_NAME = "ezmall";

    //信息提示
    public final static String MESSAGE_ERROR = "error";
    public final static String MESSAGE_INFO = "info";
    public final static String URL = "url";
    public final static String SUCCESS = "success";

    public final static String SORT_DESC = "DESC";
    public final static String SORT_ASC = "ASC";
    //管理员
    public final static String ADMIN_TYPE_SUPER = "1";       //超级管理员
    public final static String ADMIN_TYPE_SYSTEM = "2";     //系统管理员
    public final static String ADMIN_TYPE_MERCHANT = "3";  //商家

    public final static String ROLE_ADMIN_ID = "1";  //商家
    public final static String ROLE_SYSTEM_ID = "2";  //商家
    public final static String ROLE_MERCHANT_ID = "3";  //商家


    //商品参数
    public final static String GOODS_STATUS_CREATE = "0"; //新建
    public final static String GOODS_STATUS_CANCEL = "-1"; //用户取消
    public final static String GOODS_STATUS_ONSALE = "1"; //上架
    public final static String GOODS_STATUS_OFFSALE = "2";//下架

    public final static String GOODS_MERCHANT_SYSTEM = "admin";//下架


    //标志默认
    public final static String VALID_FLAG_YES = "Y";//有效
    public final static String VALID_FLAG_NO = "N"; //删除

    //分类
    public final static String ZERO_PARENT_NO = "0000"; //删除
    public final static String CATEGORY_LEVEL_FIRST = "1"; //
    public final static String CATEGORY_LEVEL_SECOND = "2"; //
    public final static String CATEGORY_LEVEL_THIRD = "3"; //

    //货币类型

    public final static String CURRENCY_TYPE_CNY = "CNY";
    public final static String CURRENCY_TYPE_USD = "USD";
    public final static String CURRENCY_TYPE_GBP = "GBP";
    public final static String CURRENCY_TYPE_KER = "KER";
    public final static String CURRENCY_TYPE_JPY = "JPY";
    public final static String CURRENCY_TYPE_HKD = "HKD";
    public final static String CURRENCY_TYPE_EUR = "EUR";

    //订单
    public final static String ORDER_STATUS_SYSTEM_CLOSE = "-9";//系统自动关闭订单
    public final static String ORDER_STATUS_MERCHANT_CLOSE = "-4";//商家关闭订单
    public final static String ORDER_STATUS_MERCHANT_REFUNDED = "-3";//商家完成退款
    public final static String ORDER_STATUS_USER_REFUND = "-2";//用户申请退款
    public final static String ORDER_STATUS_USER_CANCEL = "-1";//用户撤单
    public final static String ORDER_STATUS_CREATE = "0";//下单
    public final static String ORDER_STATUS_PAYED = "1";//付款
    public final static String ORDER_STATUS_SHIPMENT = "2";//发货
    public final static String ORDER_STATUS_RECEIVE = "3";//收货

    public final static String ORDER_STATUS_SEND = "10";//订单转发

    public final static String ORDER_STATUS_COMPLETE = "99";   //完成状态

    public final static String ORDER_PAYMENT_OFFLINE = "0";//货到付款，线下
    public final static String ORDER_PAYMENT_ONLINE = "1";//线上支付
    public final static String ORDER_PAYMENT_NOTHING = "-1";//用户尚未选择

    public final static String MEMBER_LOGIN_MALL = "0";//用户在网站登录
    public final static String MEMBER_LOGIN_WEIBO = "1";//用户在微博登录
    public final static String MEMBER_LOGIN_WEIXIN = "2";//用户在微信登录

    //    friend
    public final static String FRIEND_STATUS_CREATE = "0";//邀请
    public final static String FRIEND_STATUS_AGREE = "1";//同意
    public final static String FRIEND_STATUS_REFUSE = "-1";//拒绝
    public final static String FRIEND_STATUS_BLOCK = "-2";//阻止

    //好友订单
    public final static String FRIEND_ORDER_STATUS_CREATE = "0";//邀请
    public final static String FRIEND_ORDER_STATUS_REFUSE = "-1";//拒绝
    public final static String FRIEND_ORDER_STATUS_AGREE = "1";//同意

    //购物车COOKIE name

    public final static String SHOPCART_COOKIE_NAME = "EZMALL_SHOPCART_INFO";
    //MEMBER session的NAME
    public final static String MEMBER_SESSION_NAME = "EZMALL_MEMBER";
    public final static String ADMIN_SESSION_NAME = "EZMALL_ADMIN";


}
