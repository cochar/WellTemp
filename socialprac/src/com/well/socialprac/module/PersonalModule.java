package com.well.socialprac.module;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

import com.well.socialprac.entity.UserInfo;

@IocBean
@At("/me") 
public class PersonalModule extends BaseModule {

	@At
	public UserInfo me(HttpSession session){
//		Map<String,Object> result = new HashMap<String,Object>();
		UserInfo user = dao.fetch(UserInfo.class,(String) session.getAttribute("user"));
		dao.fetchLinks(user,"team");
		return user;
	}
}
