package com.ezmall.controller;

import com.ezmall.model.Friend;
import com.ezmall.model.Member;
import com.ezmall.service.IFriendService;
import com.ezmall.service.IMemberService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.MessageVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-7-13
 * Time: 上午11:13
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/member")
public class FriendController {
    @Autowired
    IFriendService friendService;
    @Autowired
    IMemberService memberService;

    /**
     * 我的好友列表
     *
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "myFriends.html")
    public String myFriends(Friend friend, Query query, ModelMap modelMap, HttpServletRequest request) {

        friend.setMemberUsername(CommonUtil.getMemberByRequest(request).getUsername());
        PageData<Friend> pageData = friendService.getFriendPageData(friend, query);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        modelMap.put("friend", friend);
        return "member/friend_mine";

    }

    @RequestMapping(value = "friendInfo.html")
    public String friendInfo() {
        return "member/friend_info";
    }

    @RequestMapping(value = "friendSearch.html")
    public String friendSearch(Member member, ModelMap modelMap, Query query) {
//        memberService.get

        PageData<Member> pageData = memberService.getMemberPageData(member, query);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        return "member/friend_search";
    }

    @RequestMapping(value = "toFriendSearch.html")
    public String toFriendSearch(Member member, ModelMap modelMap) {
        modelMap.put("member",member);
        return "member/friend_search";
    }

    /**
     * 申请添加好友
     * @param username 申请添加的好友用户名
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addFriend.html")
    public String addFriend(String username, HttpServletRequest request) {
        MessageVo messageVo = new MessageVo();
        String result = friendService.addFriend(username, CommonUtil.getMemberByRequest(request));
        if (StringUtils.equals(CommonConstrant.SUCCESS, result)) {
            messageVo.setSuccess(true);
        } else {
            messageVo.setSuccess(false);
            messageVo.setMessage(result);
        }

        return JSONObject.fromObject(messageVo).toString();
    }

    /**
     * 删除好友，需要删除2条记录
     *
     * @param id  好友信息ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delFriend.html")
    public String delFriend(String id) {
        MessageVo messageVo = new MessageVo();

        friendService.deleteFriend(id);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

    /**
     * 拒绝
     *
     * @param id   好友信息ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "refuseFriend.html")
    public String refuseFriend(String id) {
        MessageVo messageVo = new MessageVo();

        Friend friend = friendService.getFriendById(id);
        if (friend == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("找不到添加的好友资料");
        } else {
            friend.setStatus(CommonConstrant.FRIEND_STATUS_REFUSE);
            friendService.save(friend);
            messageVo.setSuccess(true);

        }

        return JSONObject.fromObject(messageVo).toString();
    }

    /**
     * 阻止加为好友
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "blockFriend.html")
    public String blockFriend(String memberUsername, String friendUsername) {
        MessageVo messageVo = new MessageVo();

        friendService.blockFriend(memberUsername, friendUsername);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

    /**
     * 同意加为好友
     *
     * @param id  好友信息ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "agreeFriend.html")
    public String agreeFriend(String id) {
        MessageVo messageVo = new MessageVo();
        String result=friendService.agreeFriend(id);
        if(CommonConstrant.SUCCESS.equals(result)){
             messageVo.setSuccess(true);
        }else {
            messageVo.setSuccess(false);
            messageVo.setMessage(result);
        }
        return JSONObject.fromObject(messageVo).toString();
    }

    /**
     * 被邀请的好友列表
     *
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "beInvited.html")
    public String beInvited(ModelMap modelMap, Query query, HttpServletRequest request) {
        Friend friend = new Friend();
        friend.setFriendUsername(CommonUtil.getMemberByRequest(request).getUsername());
        friend.setStatus(CommonConstrant.FRIEND_STATUS_CREATE);
        PageData<Friend> pageData = friendService.getFriendPageData(friend, query);
        modelMap.put("pageData", pageData);
        modelMap.put("query", query);
        modelMap.put("friend", friend);
        return "member/friend_beInvited";

    }

    /**
     * 查看好友信息
     *
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "viewFriend.html")
    public String viewFriend(String username) {
        MessageVo messageVo = new MessageVo();


        Member member = memberService.getMemberByUsername(username);
        if (member == null) {
            messageVo.setSuccess(false);
            messageVo.setMessage("对不起，你查看的用户不存在");
        } else {
            messageVo.setSuccess(true);
            messageVo.setObj(member);
        }

        return JSONObject.fromObject(messageVo).toString();
    }
}
