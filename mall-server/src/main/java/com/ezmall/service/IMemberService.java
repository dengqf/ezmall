package com.ezmall.service;

import com.ezmall.model.Member;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-25
 * Time: 下午9:10
 * To change this template use File | Settings | File Templates.
 */
public interface IMemberService {

    Member getLoginMember(String username, String password);

    Member getMemberById(String id);

    Member getMemberByUsername(String username);

    Member getMemberByEmail(String email);

    Member getMemberByMobile(String mobile);

    Integer insert(Member member);

    Integer save(Member member);

    /**
     * 根据微博ID得到用户信息
     * @param uid
     * @return
     */
    Member getMemberByWeiBoUid(String uid);

    PageData<Member> getMemberPageData(Member member,Query query);




}
