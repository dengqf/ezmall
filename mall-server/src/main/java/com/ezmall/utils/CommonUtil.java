/*
 * CommonUtil.java 
 * Version: 2.01   2014-6-11
 * 
 * Copyright (c) 2013 YouGouInformation Technology Co.,Ltd 
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件
 */
package com.ezmall.utils;

import com.ezmall.model.Admin;
import com.ezmall.model.Member;
import com.ezmall.vo.Query;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;

/**
 * Class description goes here
 *
 * @Author: 邓奇峰
 * Date: 14-6-11
 */
public class CommonUtil {

    private static Hashtable memberMap = new Hashtable();

    public static <T> T getFirstObject(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 获取分页对象
     *
     * @return
     */
    public static RowBounds getRowBounds(Query query) {
        return new RowBounds(((query.getPage() - 1) * query.getPageSize()), query.getPageSize());
    }

    /**
     * 得到汇率
     * 每种货币兑换多少RMB
     * </#macro>
     *
     * @param currencyType
     * @return
     */
    public static double getExchangeRate(String currencyType) {

        if (StringUtils.equals(currencyType, CommonConstrant.CURRENCY_TYPE_CNY)) {
            return 1;
        } else if (StringUtils.equalsIgnoreCase(currencyType, CommonConstrant.CURRENCY_TYPE_USD)) {
            return 6.24;
        } else if (StringUtils.equalsIgnoreCase(currencyType, CommonConstrant.CURRENCY_TYPE_GBP)) {
            return 10.6;
        } else if (StringUtils.equalsIgnoreCase(currencyType, CommonConstrant.CURRENCY_TYPE_KER)) {
            return 0.006;
        } else if (StringUtils.equalsIgnoreCase(currencyType, CommonConstrant.CURRENCY_TYPE_JPY)) {
            return 0.06;
        } else if (StringUtils.equalsIgnoreCase(currencyType, CommonConstrant.CURRENCY_TYPE_HKD)) {
            return 0.8;
        } else if (StringUtils.equalsIgnoreCase(currencyType, CommonConstrant.CURRENCY_TYPE_EUR)) {
            return 8.46;
        } else {
            //暂时按RMB算
            return 1;
        }
    }

    public static Member getMemberByRequest(HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(CommonConstrant.MEMBER_SESSION_NAME);
        if (member == null) {
            //判断TOKEN
            String token = request.getParameter("token");
            if (StringUtils.isNotBlank(token)) {
                member = CommonUtil.getMemberByToken(token);
            }
        }
        return member;
    }

    public static Admin getAdminByRequest() {
        Subject user = SecurityUtils.getSubject();
        Object obj = user.getSession().getAttribute(CommonConstrant.ADMIN_SESSION_NAME);
        return (Admin) obj;

    }

    /**
     * 得到登录用户的商家名称
     * 如果是管理员或者系统管理员，则返回NULL
     *
     * @return
     */
    public static String getMerchantName() {
        Subject user = SecurityUtils.getSubject();
        Admin obj = (Admin) user.getSession().getAttribute(CommonConstrant.ADMIN_SESSION_NAME);
        if (StringUtils.equals(obj.getType(), CommonConstrant.ADMIN_TYPE_MERCHANT)) {
            return obj.getUsername();
        }
        return null;

    }

    public static void addMember(Member member, String token) {
        memberMap.put(token, member);
    }
    public static void removeMember( String token) {
        memberMap.remove(token);
    }

    public static Member getMemberByToken(String token) {
        return (Member) memberMap.get(token);
    }

    public static void main(String[] args) {

    }
}
