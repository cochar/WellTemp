package com.well.wechat.module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.weixin.impl.WxLoginImpl;
import org.nutz.weixin.spi.WxLogin;
import org.nutz.weixin.spi.WxResp;

import com.well.BaseModule;
import com.well.socialprac.entity.UserInfo;

@At("/wxlogin/")
@IocBean
public class WechatLoginModule extends BaseModule{

    @Inject
    protected PropertiesProxy conf;

    // PC网页版微信登录
    // https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=fea88cbef0867899abcd3bb3c1bee60ea53e64b3&lang=zh_CN
    @At("/qrconnect")
    @Ok(">>:${obj}")
    public String qrconnect(HttpServletRequest req) {
        String redirect_uri = req.getRequestURL().toString().replace("qrconnect", "wxlogin/access_token");
        return wxLogin("wxlogin").qrconnect(redirect_uri, "snsapi_login", null);
    }
    
    // 公众号登录
    // https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
    @At("/authorize")
    @Ok(">>:${obj}")
    public String authorize(HttpServletRequest req) {
        String redirect_uri = req.getRequestURL().toString().replace("authorize", "weixin/access_token");
        return wxLogin("weixin").authorize(redirect_uri, "snsapi_userinfo", null); // snsapi_base静默登录,snsapi_userinfo需要确认,后者才能获取用户详细信息
    }
    
    @At("/?/access_token")
    @Ok(">>:/user/home?msg=${obj}")
    public String access_token(String prefix, @Param("code")String code, @Param("state")String state, HttpSession session) {
        WxResp resp = wxLogin(prefix).access_token(code);
        if (resp == null) {
            return "登录失败";
        }
        String openid = resp.getString("openid");
        // 因为已经得到openid, 可以用户表,确定对应的用户,完成登录操作
        // 下面假设user表有openid字段
        UserInfo user = dao.fetch(UserInfo.class, Cnd.where("openId", "=", openid));
        if (user == null) {
           // 新用户 xxooo
        }
        // 执行登录操作 (普通方式)
        session.setAttribute("me", user);
        // Shiro的方式
        // Subject subject = SecurityUtils.getSubject();
        // subject.login(new SimpleShiroToken(user.getId()));
        
        // 最后呢, 如果不是公众号的静默登录snsapi_base,还可以获取用户详细信息
        
        String access_token = resp.getString("access_token");
        resp = wxLogin(prefix).userinfo(openid, access_token);
        // 然后做你想做的事吧...
        System.out.println("======================用户信息回执内容："+resp.toString());
        return "ok";
    }
    
    protected WxLogin wxLogin(String prefix) {
        WxLoginImpl w = new WxLoginImpl();
        return w.configure(conf, prefix + ".");
    }
}
