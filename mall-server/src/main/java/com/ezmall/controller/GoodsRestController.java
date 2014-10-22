package com.ezmall.controller;

import com.ezmall.model.*;
import com.ezmall.service.*;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.ComparatorCate;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.GoodsQueryVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.session.HttpServletSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-22
 * Time: 下午9:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class GoodsRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IGoodsService goodsService;
    @Autowired
    IBrandService brandService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IGoodsCommentService goodsCommentService;
    @Autowired
    IAdminService adminService;

    @Autowired
    IMemberService memberService;

    @RequestMapping("index.html")
    public String index(ModelMap modelMap) {
        Query query = new Query(12);
        query.setPage(1);
        PageData<Goods> pageData = goodsService.getGoodsPageData(new GoodsQueryVo(), query);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        return "index";

    }

    /**
     * 商品评论列表
     *
     * @return
     */
    @RequestMapping("/{no}/{pageNo}/goodsCommentList.html")
    public String goodsCommentList(@PathVariable String no, @PathVariable Integer pageNo, ModelMap modelMap) {
        GoodsComment obj = new GoodsComment();
        obj.setGoodsNo(no);
        obj.setValidFlag(CommonConstrant.VALID_FLAG_YES);
        Query query = new Query(1);
        query.setPage(pageNo);
        PageData<GoodsComment> pageData = goodsCommentService.getGoodsCommentPageData(obj, query);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        modelMap.put("no", no);
        return "goods_comment_list";

    }

    @RequestMapping(value = "/{no}/goods.html")
    public String goodsInfo(@PathVariable String no, ModelMap map) {
        Goods goods = goodsService.getGoodsByNo(no);
        if (goods == null || StringUtils.equals(goods.getValidFlag(), CommonConstrant.VALID_FLAG_NO)) {
            logger.error("找不到商品编号{}:对应的商品或者商品已经下架", no);
            return "errors/404";
        }
        getModelMapByGoodsNo(map, goods);
        return "goods_info";
    }

    /**
     * 试验SINA微博
     * 必须是POST
     *
     * @param no
     * @param map
     * @return
     */
//    @RequestMapping(value = "/{no}/goodsToWeibo.html", method = RequestMethod.POST)
    @RequestMapping(value = "/{no}/goodsToWeibo.html")
    public String getGoodsInfo(@PathVariable String no, ModelMap map, String signed_request, HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeyException, IOException, WeiboException {
        Goods goods = goodsService.getGoodsByNo(no);
        if (goods == null || StringUtils.equals(goods.getValidFlag(), CommonConstrant.VALID_FLAG_NO)) {
            logger.error("找不到商品编号{}:对应的商品或者商品已经下架", no);
            return "errors/404";
        }
//        {tPTu924HAm9pBuexU8BcDBq9PZ5mEman7abB74fvSXs.eyJ1c2VyIjp7ImNvdW50cnkiOiJjbiIsImxvY2FsZSI6IiIsInZlcnNpb24iOjV9LCJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImlzc3VlZF9hdCI6MTQwNTA1OTE0NiwiZXhwaXJlcyI6MTU3NjgwMDAwLCJvYXV0aF90b2tlbiI6IjIuMDBFVnZUYkYwMVNVaUJkZjlmMGRiYmRmMHI5N0d2IiwidXNlcl9pZCI6NTEzNDUyMTEzOCwicmVmZXJlciI6Imh0dHA6XC9cL29wZW4ud2VpYm8uY29tXC9hcHBzXC8yNTM3OTIyNFwvaW5mb1wvdGVzdCIsInNjb3BlIjoiIiwiZXh0X2RhdGEiOiIiLCJvdWlkIjoiNTEzNDUyMTEzOCJ9
        System.out.print("[signed_request]={" + signed_request);
        if (StringUtils.isNotBlank(signed_request)) {
            //验证SINA的信息
            Oauth oauth = new Oauth();
            //得到SINA的UID

            oauth.parseSignedRequest(signed_request);
            String uid = oauth.user_id;
            String token = oauth.access_token;
            Users users = new Users();
            users.setToken(token);
            User user = users.showUserById(uid);
            //取得用户信息
            logger.info("[微博用户信息]:{}", user.toString());
            //判断UID是否存在，如果根据UID得到用户，如果不存在，则自动生成用户，存在则取出对象自动存入SESSION
            Member member = memberService.getMemberByWeiBoUid(uid);
            if (member == null) {
                //注册MEMBER
                member = new Member();
                member.setId(UUIDUtil.getUUID());
                //密码随机
                member.setPassword(UUIDUtil.getUUID());
                member.setUsername("WB_" + uid);
                member.setCreateDate(new Date());
                member.setUpdateDate(new Date());
                member.setWbUid(uid);
                member.setWbToken(token);
                member.setLinkman(user.getName());
                member.setLoginType(CommonConstrant.MEMBER_LOGIN_WEIBO);

                memberService.insert(member);

            }
            request.getSession().setAttribute(CommonConstrant.MEMBER_SESSION_NAME, member);
        }


        getModelMapByGoodsNo(map, goods);
        return "goods_info";
    }

    /**
     * @param no       分类编号
     * @param pageNo   第几页
     * @param modelMap ModelMap
     * @return
     */
    @RequestMapping("/{no}/{pageNo}/goods_cate_list.html")
    public String goodsCateList(@PathVariable String no, @PathVariable Integer pageNo, ModelMap modelMap) {

        Category category = categoryService.getCategoryByNo(no);
        if (category == null) {
            logger.error("找不到分类编号{}:对应的分类", no);
            return "errors/404";
        }
        GoodsQueryVo vo = new GoodsQueryVo();
        vo.setStruct(category.getStruct());
        Query query = new Query(12);
        query.setPage(pageNo);


        PageData<Goods> pageData = goodsService.getGoodsPageData(vo, query);
        //得到父分类
        List<Category> parentList = new ArrayList<Category>();
        String parentNo = category.getParentNo();
        while (!StringUtils.equals(parentNo, CommonConstrant.ZERO_PARENT_NO)) {
            Category obj = categoryService.getCategoryByNo(parentNo);
            if (obj != null) {
                parentNo = obj.getParentNo();
                parentList.add(obj);
            } else {
                break;
            }
        }
        Collections.sort(parentList, new ComparatorCate());
        modelMap.put("pageData", pageData);
        modelMap.put("category", category);
        modelMap.put("parentList", parentList);

        return "goods_cate_list";
    }

    /**
     * @param no       分类编号
     * @param pageNo   第几页
     * @param modelMap ModelMap
     * @return
     */
    @RequestMapping("/{no}/{pageNo}/goods_brand_list.html")
    public String goodsBrandList(@PathVariable String no, @PathVariable Integer pageNo, ModelMap modelMap) {

        Brand brand = brandService.getBrandByNo(no);
        if (brand == null) {
            logger.error("找不到品牌编号{}:对应的品牌", no);
            return "errors/404";
        }
        GoodsQueryVo vo = new GoodsQueryVo();
        vo.setBrandNo(no);
        Query query = new Query(12);
        query.setPage(pageNo);
        PageData<Goods> pageData = goodsService.getGoodsPageData(vo, query);
        modelMap.put("pageData", pageData);
        modelMap.put("brand", brand);
        modelMap.put("query", query);

        return "goods_brand_list";
    }


    private ModelMap getModelMapByGoodsNo(ModelMap map, Goods goods) {
        Category thirdCategory = categoryService.getCategoryByNo(goods.getCategoryNo());
        Category secondCategory = categoryService.getCategoryByNo(thirdCategory.getParentNo());
        Category firstCategory = categoryService.getCategoryByNo(secondCategory.getParentNo());

        //得到节省的钱
        Double economy = goods.getMarketPrice() - goods.getSellPrice();
        if (economy < 0) {
            economy = 0.0;
        }
        //属性列表
        List<GoodsPropRp> props = goodsService.getGoodsPropRpsByGoodsNo(goods.getNo());
        String merchantName = goods.getMerchantName();
        Admin merchant = null;
        if (StringUtils.isNotBlank(merchantName)) {
            merchant = adminService.getAdminByUsername(merchantName);
        }
        if (merchant == null) {
            merchant = new Admin();
        }
        economy = CommonUtil.getExchangeRate(goods.getCurrencyType()) * economy;
        map.put("goods", goods);
        map.put("economy", economy);
        map.put("thirdCategory", thirdCategory);
        map.put("secondCategory", secondCategory);
        map.put("firstCategory", firstCategory);
        map.put("props", props);
        map.put("merchant", merchant);
        return map;
    }
}
