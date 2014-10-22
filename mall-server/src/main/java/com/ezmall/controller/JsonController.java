/*
 * JsonController.java 
 * Version: 2.01   2014-7-16
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.controller;

import com.ezmall.dto.JsonDto;
import com.ezmall.model.*;
import com.ezmall.service.*;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.*;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-7-16
 */
@Controller
@RequestMapping("/json")
public class JsonController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IGoodsService goodsService;
    @Autowired
    IGoodsPriceService goodsPriceService;
    @Autowired
    IGoodsCommentService goodsCommentService;
    @Autowired
    IMemberService memberService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IBrandService brandService;
    @Autowired
    IAreaService areaService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IFriendService friendService;
    @Autowired
    IFriendOrderService friendOrderService;

    String[] searchList = new String[]{"\\r", "\\n", "\\t"};
    String[] replacementList = new String[]{"", "", ""};

    /**
     * 商品的单品页
     *
     * @param no
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "goodsJson.html")
    public void getGoodsJson(String no, HttpServletResponse response) {
        Goods goods = goodsService.getGoodsByNo(no);
        List<GoodsPropRp> list = goodsService.getGoodsPropRpsByGoodsNo(no);
        GoodsVo vo = new GoodsVo(goods, list);
        createJsonWithObject(vo, response);

    }

    /**
     * 商品的评论页
     *
     * @param no
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "goodsCommentJson.html")
    public void goodsCommentJson(String no, Integer pageNo, Integer pageSize, HttpServletResponse response) {
        Query query = new Query(pageNo, pageSize);
        GoodsComment obj = new GoodsComment();
        obj.setGoodsNo(no);
        PageData<GoodsComment> pageData = goodsCommentService.getGoodsCommentPageData(obj, query);
        createJson(pageData, response);
    }


    /**
     * 商品的单品页
     *
     * @param no
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "goodsPriceJson.html")
    public void getGoodsPriceJson(String no, Integer pageSize, HttpServletResponse response) {

        List<GoodsPrice> list = goodsPriceService.getGoodsPricesByGoodsNo(no, pageSize);

        createJsonWithObject(list, response);

    }

    /**
     * 商品比价页 (全球)
     * 按商品名称对比
     *
     * @param no
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "goodsTopJson.html")
    public void goodsTopJson(String no, Integer pageSize, HttpServletResponse response) {
        Goods goods = goodsService.getGoodsByNo(no);
        GoodsQueryVo vo = new GoodsQueryVo();
        vo.setGoodsName(goods.getName());
        List<Goods> list = goodsService.getGoodsTop(vo, pageSize);

        createJsonWithObject(list, response);

    }

    @ResponseBody
    @RequestMapping(value = "goodsTopInCountryJson.html")
    public void goodsTopJson(String no, String country, Integer pageSize, HttpServletResponse response) {
        Goods goods = goodsService.getGoodsByNo(no);
        GoodsQueryVo vo = new GoodsQueryVo();
        vo.setGoodsName(goods.getName());
        String _country = decode(country);
        vo.setCountry(_country);
        List<Goods> list = goodsService.getGoodsTopInCountry(vo, pageSize);

        createJsonWithObject(list, response);

    }

    /**
     * 分类列表
     *
     * @param no
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "categoryJson.html")
    public void getCategoryJson(String no, HttpServletResponse response) {
        List<Category> list = categoryService.getCategoriesByParentNo(no);
        createJsonWithObject(list, response);

    }

    /**
     * 品牌列表
     *
     * @param index
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "brandJson.html")
    public void brandJson(String index, HttpServletResponse response) {
        if (StringUtils.isNotBlank(index)) {
            index = index.toUpperCase();
        }
        List<Brand> list = brandService.getBrandsByIndex(index);
        createJsonWithObject(list, response);

    }

    /**
     * 国家列表
     *
     * @param index
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "countryJson.html")
    public void countryJson(String index, HttpServletResponse response) {
        if (StringUtils.isNotBlank(index)) {
            index = index.toUpperCase();
        }
        List<Area> list = areaService.getCountryList(index);
        createJsonWithObject(list, response);

    }


    /**
     * 国家列表
     *
     * @param country
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "cityJson.html")
    public void cityJson(String country, HttpServletResponse response) {

        country = decode(country);
        List<Area> list = areaService.getCityList(country);
        for (Area area : list) {
            area.setCountry(country);
        }
        createJsonWithObject(list, response);

    }

    /**
     * 国家列表
     *
     * @param country
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "mallJson.html")
    public void mallJson(String country, String city, HttpServletResponse response) {
        System.out.println("============" + country + "#" + city);
        country = decode(country);
        city = decode(city);
        List<Area> list = areaService.getMallList(country, city);
        for (Area area : list) {
            area.setCountry(country);
            area.setCity(city);
        }
        createJsonWithObject(list, response);

    }

    /**
     * 用户信息
     *
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "memberJson.html")
    public void memberJson(String token, HttpServletResponse response) {
        Member member = CommonUtil.getMemberByToken(token);
        createJsonWithObject(member, response);

    }

    /**
     * 用户好友列表信息
     *
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "myFriendJson.html")
    public void myFriendJson(String token, String status, Integer pageSize, Integer pageNo, HttpServletResponse response) {
        Member member = CommonUtil.getMemberByToken(token);
        PageData<FriendVo> pageData = null;
        if (member != null) {

            Query query = new Query(pageNo, pageSize);
            pageData = friendService.getMyFriendVo(member.getUsername(), status, query);

        } else {
            pageData = new PageData<FriendVo>(1, 0);
        }
        createJson(pageData, response);
    }

    /**
     * 查询好友，
     *
     * @param email
     * @param mobile
     * @param username
     * @param pageSize
     * @param pageNo
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "searchMemberJson.html")
    public void searchMemberJson(String email, String mobile, String username, String linkman, Integer pageSize, Integer pageNo, HttpServletResponse response) {
        Member member = new Member();
        if (StringUtils.isNotBlank(email)) {
            member.setEmail(email);
        }
        if (StringUtils.isNotBlank(mobile)) {
            member.setMobile(mobile);
        }
        if (StringUtils.isNotBlank(username)) {
            member.setUsername(username);
        }
        if (StringUtils.isNotBlank(linkman)) {
            member.setLinkman(linkman);
        }
        Query query = new Query(pageNo, pageSize);
        PageData<Member> pageData = memberService.getMemberPageData(member, query);
        createJson(pageData, response);

    }

    /**
     * 申请添加好友
     *
     * @param token
     * @param username 好友的用户用户名
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "addFriendJson.html")
    public void addFriendJson(String token, String username, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);
        if (member == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("没有登录");
        } else {
            String result = friendService.addFriend(username, member);
            if (StringUtils.equals(CommonConstrant.SUCCESS, result)) {
                messageVo.setSuccess(true);
            } else {
                messageVo.setSuccess(false);
                messageVo.setMessage(result);
            }
        }
        createJsonWithObject(messageVo, response);
    }

    /**
     * 删除好友，需要删除2条记录
     *
     * @param id 好友记录ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delFriendJson.html")
    public void delFriend(String token, String id, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);
        if (member == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("没有登录");
        } else {
            friendService.deleteFriend(id);
            messageVo.setSuccess(true);
        }

        createJsonWithObject(messageVo, response);
    }

    /**
     * 拒绝价位好友
     *
     * @param id 好友信息ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "refuseFriendJson.html")
    public void refuseFriend(String id, String token, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);
        if (member == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("没有登录");
        } else {
            Friend friend = friendService.getFriendById(id);
            if (friend == null) {
                messageVo.setSuccess(false);
                messageVo.setMessage("找不到添加的好友资料");
            } else {
                friend.setStatus(CommonConstrant.FRIEND_STATUS_REFUSE);
                friendService.save(friend);
                messageVo.setSuccess(true);

            }
        }


        createJsonWithObject(messageVo, response);
    }

    /**
     * 阻止加为好友(黑名单)
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "blockFriendJson.html")
    public void blockFriendJson(String username, String token, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);
        if (member == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("没有登录");
        } else {
            friendService.blockFriend(member.getUsername(), username);
            messageVo.setSuccess(true);
        }

        createJsonWithObject(messageVo, response);
    }


    /**
     * 判断是否是好友
     *
     * @param username 好友用户名
     * @param token
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "isFriendJson.html")
    public void isFriendJson(String username, String token, HttpServletResponse response) {
        JsonDto dto = new JsonDto();
        Member member = CommonUtil.getMemberByToken(token);

        if (member == null) {
            dto.setResult(CommonConstrant.MESSAGE_ERROR);
            dto.setMessage("没有登录");
        } else {
            Friend friend = friendService.isFriend(member.getUsername(), username);
            if (friend == null) {
                dto.setResult(CommonConstrant.MESSAGE_ERROR);
                dto.setMessage("不是好友");
            } else {
                dto.setResult(CommonConstrant.SUCCESS);
                dto.setData(friend);
            }

        }

        createJson(dto, response);
    }

    /**
     * 同意加为好友
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "agreeFriendJson.html")
    public void agreeFriend(String id, String token, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);
        if (member == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("没有登录");
        } else {
            String result = friendService.agreeFriend(id);
            if (CommonConstrant.SUCCESS.equals(result)) {
                messageVo.setSuccess(true);
            } else {
                messageVo.setSuccess(false);
                messageVo.setMessage(result);
            }
        }

        createJsonWithObject(messageVo, response);
    }

    /**
     * 受邀请列表
     *
     * @param token
     * @param pageSize
     * @param pageNo
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "beInvitedJson.html")
    public void beInvited(String token, Integer pageSize, Integer pageNo, HttpServletResponse response) {
        PageData<FriendVo> pageData = null;
        Member member = CommonUtil.getMemberByToken(token);
        if (member != null) {
            Query query = new Query(pageNo, pageSize);
            pageData = friendService.getBeInviteFriendVo(member.getUsername(), CommonConstrant.FRIEND_STATUS_CREATE, query);
        } else {
            pageData = new PageData<FriendVo>(1, 0);
        }
        createJson(pageData, response);

    }


    @ResponseBody
    @RequestMapping(value = "getGoodsListByAreaJson.html")
    public void getGoodsListByAreaJson(String country, String city, String mall, Integer pageSize, Integer pageNo, String orderType, String sort, HttpServletResponse response) {
        System.out.println("$$$$$$$$$$$$$$$" + country + "#" + city + "#" + mall);
        String _country = decode(country);
        String _city = decode(city);
        String _mall = decode(mall);
        GoodsQueryVo vo = new GoodsQueryVo();
        if (StringUtils.isNotBlank(_country)) {
            vo.setCountry(_country);
        }
        if (StringUtils.isNotBlank(_city)) {
            vo.setCity(_city);
        }
        if (StringUtils.isNotBlank(_mall)) {
            vo.setMall(_mall);
        }

        System.out.println("@@@@@@@@@@@@@@@@==" + _country + "#" + _city + "#" + _mall);
        //TODO 暂时取消
//        vo.setStatus(CommonConstrant.GOODS_STATUS_ONSALE);

        if (StringUtils.equalsIgnoreCase(CommonConstrant.SORT_ASC, sort)) {
            vo.setSort(CommonConstrant.SORT_ASC);
        } else {
            vo.setSort(CommonConstrant.SORT_DESC);
        }
        if (StringUtils.isNotBlank(orderType)) {
            //销售价格
            if (StringUtils.equals("1", orderType)) {
                vo.setOrderColumn("SELL_PRICE");
            } else if (StringUtils.equals("2", orderType)) {  //销量(暂时没有)
                //TODO
                vo.setOrderColumn("UPDATE_DATE");
            } else {
                vo.setOrderColumn("UPDATE_DATE");//
            }
        }
        Query query = new Query();
        query.setPage(pageNo);
        query.setPageSize(pageSize);
        PageData<Goods> pageData = goodsService.getGoodsPageData(vo, query);
        createJson(pageData, response);
    }

    /**
     * 得到品牌下的2级分类
     *
     * @param brandNo
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "getCateByBrandNoJson.html")
    public void getCateByBrandNoJson(String brandNo, HttpServletResponse response) {
        List<CategoryBrandRp> list = categoryService.getCategoryBrandRpsByBrandNo(brandNo);
        Map<String, Category> map = new HashMap<String, Category>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (CategoryBrandRp rp : list) {
                String categoryNo = rp.getCategoryNo();
                Category category = categoryService.getCategoryByNo(categoryNo);
                if (category != null) {
                    String parentNo = category.getParentNo();
                    if (!map.containsKey(parentNo)) {
                        Category parent = categoryService.getCategoryByNo(parentNo);
                        if (parent != null) {
                            map.put(parentNo, parent);
                        }
                    }

                }
            }
        }
        createJsonWithObject(map.values().toArray(), response);


    }


    /**
     * 得到品牌下的2级分类
     *
     * @param brandNo  品牌No
     * @param parentNo 2级分类的NO
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "getCateByBrandNoAndParentNoJson.html")
    public void getCateByBrandNoJson(String brandNo, String parentNo, HttpServletResponse response) {
        List<CategoryBrandRp> list = categoryService.getCategoryBrandRpsByBrandNo(brandNo);
        List<Category> categories = new ArrayList<Category>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (CategoryBrandRp rp : list) {
                String categoryNo = rp.getCategoryNo();
                Category category = categoryService.getCategoryByNo(categoryNo);
                if (category != null) {
                    if (StringUtils.equals(category.getParentNo(), parentNo)) {
                        categories.add(category);
                    }

                }
            }
        }
        createJsonWithObject(categories, response);


    }

    /**
     * @param categoryNo 分类编号
     * @param brandNo    品牌编号
     * @param pageSize   分页每页显示个数
     * @param pageNo     分页 第几页
     * @param orderType  1表示按价格，2表示按销量，3表示按时间
     * @param sort       DESC,ASC
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "getGoodsListJson.html")
    public void getGoodsByCategoryJson(String categoryNo, String brandNo, Integer pageSize, Integer pageNo, String orderType, String sort, HttpServletResponse response) {
        GoodsQueryVo vo = new GoodsQueryVo();
        if (StringUtils.isNotBlank(categoryNo)) {
            vo.setCategoryNo(categoryNo);
        }
        if (StringUtils.isNotBlank(brandNo)) {
            vo.setBrandNo(brandNo);
        }
        //TODO 暂时取消
//        vo.setStatus(CommonConstrant.GOODS_STATUS_ONSALE);

        if (StringUtils.equalsIgnoreCase(CommonConstrant.SORT_ASC, sort)) {
            vo.setSort(CommonConstrant.SORT_ASC);
        } else {
            vo.setSort(CommonConstrant.SORT_DESC);
        }
        if (StringUtils.isNotBlank(orderType)) {
            //销售价格
            if (StringUtils.equals("1", orderType)) {
                vo.setOrderColumn("SELL_PRICE");
            } else if (StringUtils.equals("2", orderType)) {  //销量(暂时没有)
                //TODO
                vo.setOrderColumn("UPDATE_DATE");
            } else {
                vo.setOrderColumn("UPDATE_DATE");//
            }
        }
        Query query = new Query();
        query.setPage(pageNo);
        query.setPageSize(pageSize);
        PageData<Goods> pageData = goodsService.getGoodsPageData(vo, query);
        createJson(pageData, response);

    }

    @ResponseBody
    @RequestMapping(value = "loginMemberJson.html")
    public void loginMemberJson(String username, String password, String token, HttpSession session, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = memberService.getLoginMember(username, password);

        if (member != null) {
            //TODO SESSION保存的对象需要序列化
            //添加TOKEN
            if (StringUtils.isNotBlank(token)) {
                CommonUtil.addMember(member, token);
            }

            session.setAttribute(CommonConstrant.MEMBER_SESSION_NAME, member);
            messageVo.setSuccess(true);
        } else {
            messageVo.setSuccess(false);
            messageVo.setMessage("password is error");
        }
        createJsonWithObject(messageVo, response);
    }

    /**
     * 注册用户
     *
     * @param username
     * @param password
     * @param mobile
     * @param email
     * @param linkman
     * @param address
     * @param token
     * @param session
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "regMemberJson.html")
    public void regMemberJson(String username, String password, String mobile, String email, String linkman, String address, String token, HttpSession session, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setId(UUIDUtil.getUUID());
        member.setAddress(decode(address));
        member.setEmail(email);
        member.setMobile(mobile);
        member.setCreateDate(new Date());
        member.setUpdateDate(new Date());
      //  member.setLinkman(linkman);
        Member old = memberService.getMemberByUsername(member.getUsername());
        if (StringUtils.isBlank(password)) {
            messageVo.setMessage("密码不能为空");
            createJsonWithObject(messageVo, response);
            return;
        }
        if (StringUtils.isBlank(linkman)) {
            messageVo.setMessage("联系人不能为空");
            createJsonWithObject(messageVo, response);
            return;
        }
        member.setLinkman(decode(linkman));
        if (old != null) {
            messageVo.setMessage("用户名已经被注册，请重新选择");
            createJsonWithObject(messageVo, response);
            return;
        }
        old = memberService.getMemberByMobile(member.getMobile());
        if (old != null) {
            messageVo.setMessage("手机号码已经被其他人注册，如有问题，请与客服联系");
            createJsonWithObject(messageVo, response);
            return;
        }
        old = memberService.getMemberByEmail(member.getEmail());
        if (old != null) {
            messageVo.setMessage("EMAIL已经被其他人注册，如有问题，请与客服联系");
            createJsonWithObject(messageVo, response);
            return;
        }
        memberService.insert(member);
        CommonUtil.addMember(member,token);
        messageVo.setSuccess(true);
        messageVo.setMessage("注册成功");
        createJsonWithObject(messageVo, response);
    }

    @ResponseBody
    @RequestMapping(value = "loginChkJson.html")
    public void loginChkJson(String token, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);

        if (member != null) {
            //TODO SESSION保存的对象需要序列化
            //添加TOKEN

            messageVo.setSuccess(true);
        } else {
            messageVo.setSuccess(false);
            messageVo.setMessage("没有登录");
        }
        createJsonWithObject(messageVo, response);
    }

    @ResponseBody
    @RequestMapping(value = "loginOutJson.html")
    public void loginOutJson(String token, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        CommonUtil.removeMember(token);
        messageVo.setSuccess(true);

        createJsonWithObject(messageVo, response);
    }

    /**
     * @param shopCart 商品编号_购买数量
     * @param request
     * @param payment  0货到付款 1，线上付款，默认为传1
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "createOrderJson.html")
    public void createOrderJson(String[] shopCart, HttpServletRequest request, String payment, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        OrderSubmitVo vo = orderService.createOrderSubmitVo(shopCart, request);
        Map<String, OrderSubmitVo> orderMap = orderService.createOrder(vo, payment);
        if (org.springframework.util.CollectionUtils.isEmpty(orderMap)) {
            messageVo.setSuccess(false);
        } else {
            messageVo.setSuccess(true);
        }


        createJsonWithObject(messageVo, response);
    }


    /**
     * @param response
     * @param pageNo
     * @param pageSize
     * @param status
     * @return
     */
    @RequestMapping(value = "orderListJson.html")
    public void orderListJson(HttpServletResponse response, HttpServletRequest request, Integer pageNo, Integer pageSize, String status) {
        Member member = CommonUtil.getMemberByRequest(request);
        PageData<OrderSubmitVo> pageData = null;
        if (member != null) {
            OrderQueryVo vo = new OrderQueryVo();
            //-10000代表查询所有
            if (StringUtils.isNotBlank(status) && (!"-10000".equals(status))) {
                vo.setOrderStatus(status);
            }

            vo.setUserName(member.getUsername());
            vo.setViewFlag(CommonConstrant.VALID_FLAG_YES);
            Query query = new Query(pageNo, pageSize);
            pageData = orderService.getOrderSubmitVoPageData(vo, query);
        } else {
            pageData = new PageData<OrderSubmitVo>(1, 0);
        }
        createJson(pageData, response);


    }

    @ResponseBody
    @RequestMapping(value = "delOrderJson.html")
    public void delOrder(String orderNo, String token, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);
        messageVo.setSuccess(false);
        if (member == null) {

            messageVo.setSuccess(false);
            messageVo.setMessage("没有登录");
        } else {
            Order obj = orderService.getOrderByNo(orderNo);

            if (obj != null) {


                if (!CommonConstrant.ORDER_STATUS_CREATE.equals(obj.getStatus())) {
                    messageVo.setSuccess(false);
                    messageVo.setMessage("只有初始状态的订单可以删除");
                } else {
                    //判断是否是该用户的
                    if (StringUtils.equals(obj.getUsername(), member.getUsername())) {
                        obj.setStatus(CommonConstrant.ORDER_STATUS_USER_CANCEL);
                        obj.setViewFlag(CommonConstrant.VALID_FLAG_NO);
                        orderService.save(obj);

                    } else {
                        messageVo.setSuccess(false);
                        messageVo.setMessage("该订单不属于此用户");
                    }


                }
            } else {
                messageVo.setSuccess(false);
                messageVo.setMessage("找不到订单");
            }


        }
        createJsonWithObject(messageVo, response);

    }

    /**
     * 发送订单给好友
     *
     * @param orderNo
     * @param username
     * @param token
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "confirmOrderToFriendJson.html")
    public void confirmOrderToFriendJson(String orderNo, String username, String token, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);

        if (member == null) {

            messageVo.setSuccess(false);
        } else {
            Order order = orderService.getOrderByNo(orderNo);
            String result = friendOrderService.checkOrderCanSend(order);
            if (!StringUtils.equals(CommonConstrant.SUCCESS, result)) {
                messageVo.setMessage(result);

            } else {
                friendOrderService.insertFriendOrder(order, username, member.getUsername());
                messageVo.setSuccess(true);
            }

        }
        createJsonWithObject(messageVo, response);
    }

    /**
     * 好友给我的订单
     *
     * @param token
     * @param pageNo
     * @param pageSize
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "myFriendOrdersJson.html")
    public void myFriendOrdersJson(String token, String status, Integer pageNo, Integer pageSize, HttpServletResponse response) {
        PageData<OrderSubmitVo> pageData = null;
        Member member = CommonUtil.getMemberByToken(token);

        if (member == null) {

            pageData = new PageData<OrderSubmitVo>(1, 0);
        } else {
            FriendOrder obj = new FriendOrder();
            obj.setFriendName(member.getUsername());
            if (StringUtils.isNotBlank(status)) {
                obj.setStatus(status);
            }
            Query query = new Query(pageNo, pageSize);
            pageData = friendOrderService.getOrderSubmitVoPageData(obj, query);

        }
        createJson(pageData, response);
    }

    /**
     * 我给好友的订单列表
     *
     * @param token
     * @param pageNo
     * @param pageSize
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "myOrderToFriendJson.html")
    public void myOrderToFriendJson(String token, String status, Integer pageNo, Integer pageSize, HttpServletResponse response) {
        PageData<OrderSubmitVo> pageData = null;
        Member member = CommonUtil.getMemberByToken(token);

        if (member == null) {

            pageData = new PageData<OrderSubmitVo>(1, 0);
        } else {
            FriendOrder obj = new FriendOrder();
            obj.setMemberName(member.getUsername());
            if (StringUtils.isNotBlank(status)) {
                obj.setStatus(status);
            }
            Query query = new Query(pageNo, pageSize);
            pageData = friendOrderService.getOrderSubmitVoPageData(obj, query);

        }
        createJson(pageData, response);
    }

    /**
     * 修改好友订单状态
     *
     * @param orderNo  好友订单ID
     * @param status   状体
     * @param token
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "changeFriendOrderJson.html")
    public void changeFriendOrderJson(String orderNo, String status, String token, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);

        if (member == null) {

            messageVo.setSuccess(false);
        } else {
            FriendOrder obj = friendOrderService.getFriendOrderByOrderNo(orderNo);
            if (obj == null) {
                messageVo.setSuccess(false);
                messageVo.setMessage("找不到对应的好友订单");
            } else {
                obj.setStatus(status);
                friendOrderService.save(obj);
                messageVo.setSuccess(true);
            }

        }
        createJsonWithObject(messageVo, response);


    }

    @ResponseBody
    @RequestMapping(value = "delFriendOrderJson.html")
    public void delFriendOrder(String orderNo, String token, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);
        messageVo.setSuccess(false);
        if (member == null) {

            messageVo.setSuccess(false);
            messageVo.setMessage("没有登录");
        } else {
            FriendOrder obj = friendOrderService.getFriendOrderByOrderNo(orderNo);

            if (obj != null) {
                if (CommonConstrant.FRIEND_ORDER_STATUS_AGREE.equals(obj.getStatus())) {
                    messageVo.setSuccess(false);
                    messageVo.setMessage("好友已经同意了该订单，不能删除");
                } else {
                    friendOrderService.delFriendOrderById(obj.getId());
                    messageVo.setSuccess(true);
                }
            }


        }
        createJsonWithObject(messageVo, response);

    }


    /**
     * @param data     数据
     * @param response response
     */
    private void createJsonWithObject(Object data, HttpServletResponse response) {
        createJson(new JsonDto(data), response);
    }

    private void createJson(Object object, HttpServletResponse response) {
        String json = "var jsonObj=";
//        json += JSONArray.fromObject(pageData.getData()).toString();
        json += JSONObject.fromObject(object).toString();
        json += ";";
        json = StringUtils.replaceEach(json, searchList, replacementList);
        response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式
        PrintWriter out = null; //获取写入对象
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(json); //将json数据写入流中
        out.flush();
    }


    private String decode(String str) {
        if (StringUtils.isNotBlank(str)) {
            try {
                str = java.net.URLDecoder.decode(str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return str;
    }

    private void checkToken(String token, HttpServletResponse response) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByToken(token);
        if (member == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("没有登录");
//            createJsonWithDto(messageVo,response);
        }
    }
}
