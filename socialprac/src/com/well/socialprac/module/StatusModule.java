package com.well.socialprac.module;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.well.BaseModule;
import com.well.socialprac.entity.PracticeStatus;
import com.well.socialprac.entity.TeamInfo;
import com.well.socialprac.entity.UserInfo;

@IocBean
@At("/status")
public class StatusModule extends BaseModule {
	
//	@At
//	@Ok("jsp:jsp.")
//	public PracticeStatus edit(HttpSession session){
//		PracticeStatus practiceStatus = new PracticeStatus();
////		ps.setUserId((String) session.getAttribute("user"));  //为测试，暂注释
//		return practiceStatus;
//	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@At
	public String save(@Param("..") PracticeStatus practiceStatus,HttpSession session) throws Exception{
		session.setAttribute("user", "41314005");
//		System.out.println("============"+practiceStatus.getTextContent());
		practiceStatus.setReleaseTime(sdf.parse(sdf.format(new Date())));//考虑插入类型，最好日期，是不是可以sql.date；
		System.out.println("==========="+session.getAttribute("user"));
		UserInfo user=dao.fetch(UserInfo.class,(String) session.getAttribute("user"));
		practiceStatus.setUserId(user.getId());
		dao.insert(practiceStatus);
		//dao.insertWith(practiceStatus, "practiceStatusData");
		user.setScore(user.getScore()+3);
		if(user.getTeamId()!=null){
			dao.fetchLinks(user, "team");
			TeamInfo team=user.getTeam();
			team.setScore(team.getScore()+3);
		}
		dao.updateWith(user, "team");
		return "1";
//		response.sendRedirect("list");
	}
	
	@At
	@Ok("jsp:/list")
	public List<PracticeStatus> list(Pager pager,HttpSession session){
		session.setAttribute("user", "41314005");
//		Map<String, Object> result = new HashMap<String, Object>();
		List<PracticeStatus> list=dao.query(PracticeStatus.class, Cnd.orderBy().desc("releaseTime"),pager);
		dao.fetchLinks(list, "user");
//		System.out.println("========================the very firt weibo:"+list.get(0).getTextContent());
//		result.put("list", list);
		return list;
	}
	
	@At
	public String praise(String id,HttpSession session){
		PracticeStatus practiceStatus = dao.fetch(PracticeStatus.class, id);
		practiceStatus.setPraiseNumber(practiceStatus.getPraiseNumber()+1);
		dao.update(practiceStatus);
		UserInfo user=dao.fetch(UserInfo.class,(String) session.getAttribute("user"));
		user.setScore(user.getScore()+1);
		dao.fetchLinks(practiceStatus, "user");
		UserInfo creator = practiceStatus.getUser();
		creator.setScore(creator.getScore()+1);
		if(creator.getTeamId()!=null){
			dao.fetchLinks(creator, "team");
			TeamInfo team=creator.getTeam();
			team.setScore(team.getScore()+1);
		}
		dao.updateWith(user, "team");
		return "1";
	}
	
	@At
	public PracticeStatus single(String id){
		PracticeStatus practiceStatus = new PracticeStatus();
		practiceStatus = dao.fetch(PracticeStatus.class,id);
		dao.fetchLinks(practiceStatus, "commentList");
		return practiceStatus;
	}
}
