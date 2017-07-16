package com.well.socialprac.module;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.well.BaseModule;
import com.well.socialprac.entity.TeamInfo;
import com.well.socialprac.entity.UserInfo;
import com.well.utils.CacheUtil;
import com.well.utils.MD5Util;
import com.well.wechat.handler.DefaultWxHandler;

@At("")
public class LoginModule extends BaseModule {

//	@At("/authenticate")
////	@Filters
//	public void authenticate(int ifPracticeMember,String userId,String teamId){
//		if(ifPracticeMember == 1 && dao.fetch(UserInfo.class, Cnd.where("id","=",userId).and("teamId","=",teamId))!=null){
//			
//		}
//	}
	
//	@Filters( @By(type=CheckSession.class, args={"token", "/QRCode.jsp"}))
//	@SuppressWarnings("unchecked")
	@Filters()
	@Ok("jsp:/login")
	@At
	public View toLogin(String token,HttpSession session){
		if("success".equals(session.getAttribute("token")))
			return new ViewWrapper(new JspView("/login"),null);
		if(token!=null){
//			List<String> tokenList = new ArrayList<String>();
//			tokenList = (List<String>) CacheUtil.get("tokenList");
			if(DefaultWxHandler.tokenListGlobal.contains(token)){
				session.setAttribute("token", "success");
				DefaultWxHandler.tokenListGlobal.remove(token);
//				CacheUtil.put("tokenList", tokenList);
				return new ViewWrapper(new JspView("/login"),null);
			}
			return new ViewWrapper(new JspView("/QRCode"),null);
		}else return new ViewWrapper(new JspView("/QRCode"),null);
	}
	
//	@Filters( @By(type=CheckSession.class, args={"token", "/QRCode.jsp"}))
	@Filters()
	@Ok("json")
	@At
	public String login(String password,String userName,HttpSession session){
		if(session.getAttribute("token")==null)
			return "noToken";
		UserInfo user = dao.fetch(UserInfo.class,Cnd.where("name","=",userName));
		if(user==null)
			return "wrongName";
		else if(MD5Util.parseStrToMd5L16(password).equals(user.getPassword())){
			session.setAttribute("user", user.getId());
			return "success";
		}else return "wrongPassword";
	}
	
//	@Filters( @By(type=CheckSession.class, args={"token", "/QRCode.jsp"}))
	@Filters()
	@Ok("json")
	@At
	public String register(UserInfo user,HttpSession session,String identity){
		if(session.getAttribute("token")==null)
			return "noToken";
		if("member".equals(identity)){
			if(dao.fetch(UserInfo.class,user.getId())==null)
				return "wrongNo";
			else if(dao.fetch(TeamInfo.class,Cnd.where("name","=",user.getTeamName()))==null)
				return "wrongTeam";
			user.setPassword(MD5Util.parseStrToMd5L16(user.getPassword()));
			dao.update(user);
			session.setAttribute("user", user.getId());
			return "success";
		}
		else if(null!=dao.fetch(UserInfo.class,Cnd.where("name","=",user.getName())))
				return "invalidName";
		user.setPassword(MD5Util.parseStrToMd5L16(user.getPassword()));
		user.setIfPracticeMember(0);
		user.setDisplayName(user.getName());
		dao.insert(user);
		session.setAttribute("user", user.getId());
		return "success";
	}
	
	@Ok("jsp:/QRCode")
	@At
	public void logout(HttpSession session){
		session.removeAttribute("user");
		session.removeAttribute("token");
	}
}
