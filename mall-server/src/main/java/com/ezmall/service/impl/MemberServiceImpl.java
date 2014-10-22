package com.ezmall.service.impl;

import com.ezmall.mapper.MemberMapper;
import com.ezmall.model.Member;
import com.ezmall.model.MemberExample;
import com.ezmall.service.IMemberService;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-25
 * Time: 下午9:13
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public Member getLoginMember(String username, String password) {
        MemberExample example = new MemberExample();
        example.or().andUsernameEqualTo(username).andPasswordEqualTo(password);
        return CommonUtil.getFirstObject(memberMapper.selectByExample(example));
    }

    @Override
    public Member getMemberById(String id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public Member getMemberByUsername(String username) {
        MemberExample example = new MemberExample();
        example.or().andUsernameEqualTo(username);
        return CommonUtil.getFirstObject(memberMapper.selectByExample(example));
    }

    @Override
    public Member getMemberByEmail(String email) {
        MemberExample example = new MemberExample();
        example.or().andEmailEqualTo(email);
        return CommonUtil.getFirstObject(memberMapper.selectByExample(example));
    }

    @Override
    public Member getMemberByMobile(String mobile) {
        MemberExample example = new MemberExample();
        example.or().andMobileEqualTo(mobile);
        return CommonUtil.getFirstObject(memberMapper.selectByExample(example));
    }

    @Override
    public Integer insert(Member member) {
        member.setUpdateDate(new Date());
        member.setCreateDate(new Date());
        return memberMapper.insertSelective(member);
    }

    @Override
    public Integer save(Member member) {
        member.setUpdateDate(new Date());
        return memberMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public Member getMemberByWeiBoUid(String uid) {
        MemberExample example = new MemberExample();
        example.or().andWbUidEqualTo(uid);
        return CommonUtil.getFirstObject(memberMapper.selectByExample(example));
    }

    @Override
    public PageData<Member> getMemberPageData(Member member, Query query) {
        PageData<Member> pageData = null;
        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.or();
        if (StringUtils.isNotBlank(member.getUsername())) {
            criteria.andUsernameEqualTo(member.getUsername());
        }
        if (StringUtils.isNotBlank(member.getMobile())) {
            criteria.andMobileEqualTo(member.getMobile());
        }
        if (StringUtils.isNotBlank(member.getEmail())) {
            criteria.andEmailEqualTo(member.getEmail());
        }
        if(StringUtils.isNotBlank(member.getLinkman())){
            criteria.andLinkmanLike("%"+member.getLinkman()+"%");
        }
        Integer row=memberMapper.countByExample(example);
        if(row<1){
            pageData=new PageData<Member>(1,0);
        } else {
            pageData=new PageData<Member>(query.getPage(),query.getPageSize(),row);
            List<Member> list=memberMapper.selectByExample(example,CommonUtil.getRowBounds(query));
            pageData.setData(list);
        }
        return pageData;
    }
}
