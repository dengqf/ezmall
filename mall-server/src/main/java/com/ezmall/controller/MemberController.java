package com.ezmall.controller;

import com.ezmall.model.Member;
import com.ezmall.service.IMemberService;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.MessageVo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-25
 * Time: 下午9:09
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MemberController {
    @Autowired
    IMemberService memberService;

    @ResponseBody
    @RequestMapping("checkMemberExist.html")
    String checkMemberExist(HttpServletRequest request) {
        MessageVo messageVo = new MessageVo();
        Member member = CommonUtil.getMemberByRequest(request);

        messageVo.setSuccess(member != null);
        return JSONObject.fromObject(messageVo).toString();
    }

    @RequestMapping("toLoginMember.html")
    String toLoginMember(String url, ModelMap modelMap) {
        if (StringUtils.isNotBlank(url)) {
            modelMap.put("url", url);
        }
        return "member_login";
    }

    @RequestMapping("logOutMember.html")
    String logOutMember(HttpServletRequest request) {
        request.getSession().invalidate();
        return "member_login";
    }


    @ResponseBody
    @RequestMapping("loginMember.html")
    String loginMember(String username, String password, String token, HttpSession session) {
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
            messageVo.setMessage("用户名或密码错误，请重新输入");
        }

        return JSONObject.fromObject(messageVo).toString();
    }

    @RequestMapping("toEditMember.html")
    String toEditMember(HttpServletRequest request, ModelMap modelMap) {

        Member member = CommonUtil.getMemberByRequest(request);

        if (null == member) {
            member = new Member();
        }
        modelMap.put("member", member);
        return "member_edit";
    }

    @ResponseBody
    @RequestMapping("editMember.html")
    String registerMember(Member member, HttpServletRequest request) {
        MessageVo messageVo = new MessageVo();
        messageVo.setSuccess(false);
        //注册用户
        if (StringUtils.isBlank(member.getId())) {
            member.setId(UUIDUtil.getUUID());

            Member old = memberService.getMemberByUsername(member.getUsername());
            if (old != null) {
                messageVo.setMessage("用户名已经被注册，请重新选择");
                return JSONObject.fromObject(messageVo).toString();
            }
            old = memberService.getMemberByMobile(member.getMobile());
            if (old != null) {
                messageVo.setMessage("手机号码已经被其他人注册，如有问题，请与客服联系");
                return JSONObject.fromObject(messageVo).toString();
            }
            old = memberService.getMemberByEmail(member.getEmail());
            if (old != null) {
                messageVo.setMessage("EMAIL已经被其他人注册，如有问题，请与客服联系");
                return JSONObject.fromObject(messageVo).toString();
            }
            memberService.insert(member);

            messageVo.setMessage("注册成功");
            request.getSession().setAttribute(CommonConstrant.MEMBER_SESSION_NAME, member);

        } else {
            //检查用户是否存在
            Member obj = CommonUtil.getMemberByRequest(request);
            //用户不存在或者ID不相等
            if (obj == null || !StringUtils.equals(obj.getId(), member.getId())) {
                messageVo.setMessage("用户尚未登录或修改资料与登录用户不符");
                return JSONObject.fromObject(messageVo).toString();
            }
            //修改
            member.setUsername(null);
            //如果手机号码有修改，则检查是否有注册
            if (!StringUtils.equals(obj.getMobile(), member.getMobile())) {
                Member old = memberService.getMemberByMobile(member.getMobile());
                if (old != null) {
                    messageVo.setMessage("手机号码已经被其他人注册，如有问题，请与客服联系");
                    return JSONObject.fromObject(messageVo).toString();
                }
            }
            //邮箱有修改
            if (!StringUtils.equals(obj.getEmail(), member.getEmail())) {
                Member old = memberService.getMemberByEmail(member.getEmail());
                if (old != null) {
                    messageVo.setMessage("EMAIL已经被其他人注册，如有问题，请与客服联系");
                    return JSONObject.fromObject(messageVo).toString();
                }
            }
            member.setId(obj.getId());
            memberService.save(member);
            messageVo.setMessage("资料成功");

        }
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();
    }

    @RequestMapping("member/index.html")
    String toIndex(String message) {

        return "member/index";
    }

    @RequestMapping("member/toUpdatePassword.html")
    String toUpdatePsssword(HttpServletRequest request) {
        return "member/member_password";
    }

    @ResponseBody
    @RequestMapping("member/updatePassword.html")
    String updatePsssword(String password, String newPassword, HttpServletRequest request) {
        MessageVo messageVo = new MessageVo();
        Member obj = CommonUtil.getMemberByRequest(request);
        if (!StringUtils.equals(password, obj.getPassword())) {
            messageVo.setSuccess(false);
            messageVo.setMessage("输入的旧密码不正确");
            return JSONObject.fromObject(messageVo).toString();
        }
        obj.setPassword(newPassword);
        memberService.save(obj);
        messageVo.setSuccess(true);
        return JSONObject.fromObject(messageVo).toString();

    }

}
