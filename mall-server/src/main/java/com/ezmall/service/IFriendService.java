package com.ezmall.service;

import com.ezmall.model.Friend;
import com.ezmall.model.Member;
import com.ezmall.vo.FriendVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-7-13
 * Time: 上午11:08
 * To change this template use File | Settings | File Templates.
 */
public interface IFriendService {

    Friend getFriendById(String id);


    /**
     * 判断是否为好友
     * @param memberUserName 当前用户的用户名
     * @param friendUserName 好友的用户名
     * @return
     */
    Friend isFriend(String memberUserName, String friendUserName);

    PageData<Friend> getFriendPageData(Friend friend, Query query);

    /**
     * 得到用户的好友列表
     * @param memberUserName  用户
     * @param query  查询信息
     * @return
     */
    PageData<FriendVo> getMyFriendVo(String memberUserName,String status, Query query);

    /**
     * 得到用户被邀请为好友的列表
     * @param memberUserName   用户ID
     * @param query 查询信息
     * @return
     */
    PageData<FriendVo> getBeInviteFriendVo(String memberUserName,String status, Query query);


    Integer insert(Friend friend);

    Integer save(Friend friend);

    Integer deleteFriend(String id);

    /**
     * 同意添加好友
     *
     * @param id 申请的ID
     * @return
     */
    String agreeFriend(String id);

    /**
     * 阻止加为好友
     *
     * @param memberUsername
     * @param friendUsername
     * @return
     */
    Integer blockFriend(String memberUsername, String friendUsername);

    /**
     * 申请添加好友，成功返回SUCCESS，失败返回原因
     *
     * @param username     好友用户名
     * @param member 用户
     * @return
     */
    String addFriend(String username, Member member);


}
