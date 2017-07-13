package com.well.socialprac.module;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

import com.well.BaseModule;
import com.well.socialprac.entity.PracticeStatus;
import com.well.socialprac.entity.UserInfo;

@IocBean
@At("/me") 
public class PersonalModule extends BaseModule {

	@At
	public UserInfo info(HttpSession session){
//		Map<String,Object> result = new HashMap<String,Object>();
		UserInfo user = dao.fetch(UserInfo.class,(String) session.getAttribute("user"));
		dao.fetchLinks(user,"team");
		return user;
	}
	
	@At
	public List<PracticeStatus> myStatus(String userId,Pager pager){
		List<PracticeStatus> list = new ArrayList<PracticeStatus>();
		list = dao.query(PracticeStatus.class, Cnd.where("userId","=",userId),pager);
		dao.fetchLinks(list, "user");
		return list;
	}
}
