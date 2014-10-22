package com.ezmall.service.impl;

import com.ezmall.mapper.FriendMapper;
import com.ezmall.model.Friend;
import com.ezmall.model.FriendExample;
import com.ezmall.model.Member;
import com.ezmall.service.IFriendService;
import com.ezmall.service.IMemberService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.FriendVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-7-13
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FriendServiceImpl implements IFriendService {

    @Autowired
    FriendMapper friendMapper;
    @Autowired
    IMemberService memberService;

    @Override
    public Friend getFriendById(String id) {
        return friendMapper.selectByPrimaryKey(id);
    }

    @Override
    public Friend isFriend(String memberUsername, String friendUsername) {
        FriendExample example = new FriendExample();
        example.or().andFriendUsernameEqualTo(friendUsername).andMemberUsernameEqualTo(memberUsername);
        return CommonUtil.getFirstObject(friendMapper.selectByExample(example));
    }


    @Override
    public PageData<Friend> getFriendPageData(Friend friend, Query query) {
        PageData<Friend> pageData = null;
        FriendExample example = new FriendExample();
        FriendExample.Criteria criteria = example.or();
        if (StringUtils.isNotBlank(friend.getMemberUsername())) {
            criteria.andMemberUsernameEqualTo(friend.getMemberUsername());
        }
        if (StringUtils.isNotBlank(friend.getFriendUsername())) {
            criteria.andFriendUsernameEqualTo(friend.getFriendUsername());
        }
        if (StringUtils.isNotBlank(friend.getStatus())) {
            criteria.andStatusEqualTo(friend.getStatus());
        }
        Integer row = friendMapper.countByExample(example);
        if (row < 1) {
            pageData = new PageData<Friend>(1, 0);
        } else {
            pageData = new PageData<Friend>(query.getPage(), query.getPageSize(), row);
            example.setOrderByClause("CREATE_DATE DESC");
            List<Friend> list = friendMapper.selectByExample(example, CommonUtil.getRowBounds(query));
            pageData.setData(list);
        }
        return pageData;
    }

    @Override
    public PageData<FriendVo> getMyFriendVo(String memberUsername, String status, Query query) {
        PageData<FriendVo> pageData = null;
        FriendExample example = new FriendExample();
        FriendExample.Criteria criteria = example.or();
        if (StringUtils.isNotBlank(memberUsername)) {
            criteria.andMemberUsernameEqualTo(memberUsername);
        }

        if (StringUtils.isNotBlank(status)) {
            criteria.andStatusEqualTo(status);
        }
        Integer count = friendMapper.countByExample(example);
        if (count > 0) {
            List<FriendVo> list = friendMapper.getMyFriendVo(memberUsername, status, CommonUtil.getRowBounds(query));
            pageData = new PageData<FriendVo>(query.getPage(), query.getPageSize(), count);
            pageData.setData(list);
        } else {
            pageData = new PageData<FriendVo>(1, 0);
        }
        return pageData;
    }

    @Override
    public PageData<FriendVo> getBeInviteFriendVo(String memberUsername, String status, Query query) {
        PageData<FriendVo> pageData = null;
        FriendExample example = new FriendExample();
        FriendExample.Criteria criteria = example.or();
        if (StringUtils.isNotBlank(memberUsername)) {
            criteria.andFriendUsernameEqualTo(memberUsername);
        }

        if (StringUtils.isNotBlank(status)) {
            criteria.andStatusEqualTo(status);
        }
        Integer count = friendMapper.countByExample(example);
        if (count > 0) {
            List<FriendVo> list = friendMapper.getBeInviteFriendVo(memberUsername, status, CommonUtil.getRowBounds(query));
            pageData = new PageData<FriendVo>(query.getPage(), query.getPageSize(), count);
            pageData.setData(list);
        } else {
            pageData = new PageData<FriendVo>(1, 0);
        }
        return pageData;
    }

    @Override
    public Integer insert(Friend friend) {
        friend.setCreateDate(new Date());
        return friendMapper.insertSelective(friend);
    }

    @Override
    public Integer save(Friend friend) {
        return friendMapper.updateByPrimaryKeySelective(friend);
    }

    @Override
    @Transactional
    public Integer deleteFriend(String id) {
        Friend friend = getFriendById(id);
        if (friend != null) {
            //查看在对方的好友列表是否存在，如果存在则删除
            Friend obj = isFriend(friend.getFriendUsername(), friend.getMemberUsername());
            if (obj != null) {
                //如果不是阻止状态则删除
                if (!StringUtils.equals(obj.getStatus(), CommonConstrant.FRIEND_STATUS_BLOCK)) {
                    friendMapper.deleteByPrimaryKey(obj.getId());
                }

            }
        }
        return friendMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public String agreeFriend(String id) {
        Friend friend = getFriendById(id);
        if (friend != null) {
            friend.setStatus(CommonConstrant.FRIEND_STATUS_AGREE);
            //查看在对方的好友列表是否存在，如果存在则修改状态
            Friend obj = isFriend(friend.getFriendUsername(), friend.getMemberUsername());
            if (obj != null) {
                obj.setStatus(CommonConstrant.FRIEND_STATUS_AGREE);
                friendMapper.updateByPrimaryKeySelective(obj);
            } else {
                obj = new Friend();
                obj.setId(UUIDUtil.getUUID());
                obj.setStatus(CommonConstrant.FRIEND_STATUS_AGREE);
                obj.setCreateDate(new Date());

                Member member = memberService.getMemberByUsername(friend.getFriendUsername());
                Member friMember = memberService.getMemberByUsername(friend.getMemberUsername());
                obj.setMemberUsername(member.getUsername());
                obj.setFriendUsername(friMember.getUsername());
                friendMapper.insertSelective(obj);
            }
            friendMapper.updateByPrimaryKeySelective(friend);
            return CommonConstrant.SUCCESS;
        } else {
            return "找不到对应的好友信息";
        }
    }

    @Override
    @Transactional
    public Integer blockFriend(String memberUsername, String friendUsername) {
        Friend friend = isFriend(memberUsername, friendUsername);
        int result = 0;
        if (friend != null) {
            friend.setStatus(CommonConstrant.FRIEND_STATUS_BLOCK);
            result = friendMapper.updateByPrimaryKeySelective(friend);
        } else {
            friend = new Friend();

            friend.setId(UUIDUtil.getUUID());

            friend.setCreateDate(new Date());
            friend.setStatus(CommonConstrant.FRIEND_STATUS_BLOCK);
            Member member = memberService.getMemberByUsername(memberUsername);
            Member friMember = memberService.getMemberByUsername(friendUsername);
            friend.setMemberUsername(member.getUsername());
            friend.setFriendUsername(friMember.getUsername());
            result = friendMapper.insertSelective(friend);
            //TODO 也可能这个没作用，先不写
        }
        //反过来再查一次
        Friend obj = isFriend(friendUsername, memberUsername);
        if (obj != null) {
            obj.setStatus(CommonConstrant.FRIEND_STATUS_REFUSE);
            friendMapper.updateByPrimaryKeySelective(obj);
        }
        return result;
    }

    @Override
    public String addFriend(String username, Member member) {
        Member mem = memberService.getMemberByUsername(username);
        if(StringUtils.equals(username,member.getUsername())){
            return "不能添加自己为好友";
        }
        if (mem == null) {
            return "找不到您要添加的用户";
        }
        if(StringUtils.equals(username,member.getUsername())){

        }
        String memberUsername = member.getUsername();
        //判断是否添加
        Friend friend = isFriend(memberUsername, username);
        //查看对方好友列表
        Friend other = isFriend(username, memberUsername);

        if (other != null) {
            if (StringUtils.equals(other.getStatus(), CommonConstrant.FRIEND_STATUS_BLOCK)) {
                return "对方已经拒绝加您为好友";

            }
        }


        if (friend != null) {
            if (StringUtils.equals(friend.getStatus(), CommonConstrant.FRIEND_STATUS_AGREE)) {
                return "对方已经在您的好友列表里了";

            }
            friend.setStatus(CommonConstrant.FRIEND_STATUS_CREATE);
            save(friend);

        } else {
            friend = new Friend();
            friend.setId(UUIDUtil.getUUID());
            //如果在对方的好友列表里，就直接是好友
            if (other != null && CommonConstrant.FRIEND_STATUS_AGREE.equals(other.getStatus())) {
                friend.setStatus(CommonConstrant.FRIEND_STATUS_AGREE);
            } else {
                friend.setStatus(CommonConstrant.FRIEND_STATUS_CREATE);
            }

            friend.setMemberUsername(member.getUsername());
            friend.setFriendUsername(mem.getUsername());
            insert(friend);
        }
        return CommonConstrant.SUCCESS;
    }

}
