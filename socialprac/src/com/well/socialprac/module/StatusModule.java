package com.well.socialprac.module;

import javax.servlet.http.HttpSession;

import org.nutz.mvc.annotation.At;

import com.well.socialprac.entity.PracticeStatus;

@At("/status")
public class StatusModule extends BaseModule {
	
	@At
	
	public PracticeStatus edit(String id,HttpSession session){
		if(id!=null){
			PracticeStatus ps = dao.fetch(PracticeStatus.class,id);
			return ps;
		}
		PracticeStatus ps = new PracticeStatus();
//		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
//		ps.setUserId(user.getId());
		ps.setUserId((String) session.getAttribute("user"));
		return ps;
	}
}
