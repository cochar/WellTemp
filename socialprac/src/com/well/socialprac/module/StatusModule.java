package com.well.socialprac.module;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.well.socialprac.entity.Comment;
import com.well.socialprac.entity.PracticeStatus;
import com.well.socialprac.entity.TeamInfo;
import com.well.socialprac.entity.UserInfo;

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
		
//		System.out.println("============"+practiceStatus.getTextContent());
		practiceStatus.setReleaseTime(sdf.parse(sdf.format(new Date())));//考虑插入类型，最好日期，是不是可以sql.date；
//		System.out.println("==========="+session.getAttribute("user"));
		UserInfo user=dao.fetch(UserInfo.class,(String) session.getAttribute("user"));
		practiceStatus.setUserId(user.getId());
		dao.insertWith(practiceStatus, "practiceStatusData");
		user.setScore(user.getScore()+3);
		if(user.getTeamId()!=null){
			dao.fetchLinks(user, "team");
			TeamInfo team=user.getTeam();
			team.setScore(team.getScore()+3);
		}
		return "1";
//		response.sendRedirect("list");
	}
	
	@At
	@Ok("jsp:index")
	public List<PracticeStatus> list(Pager pager,HttpSession session){
		session.setAttribute("user", "01");
//		Map<String, Object> result = new HashMap<String, Object>();
		List<PracticeStatus> list=dao.query(PracticeStatus.class, Cnd.orderBy().desc("releaseTime"),pager);
		dao.fetchLinks(list, "practiceStatusData");
//		result.put("list", list);
		return list;
	}
	
	@At
	public String comment(Comment comment,HttpSession session) throws Exception{
		comment.setUserId((String) session.getAttribute("user")); //为测试，暂注释
		PracticeStatus practiceStatus = dao.fetch(PracticeStatus.class, comment.getStatusId());
		practiceStatus.setCommentNumber(practiceStatus.getCommentNumber()+1);
		comment.setCommentTime(sdf.parse(sdf.format(new Date())));
		dao.update(practiceStatus);
		dao.insert(comment);
		UserInfo user=dao.fetch(UserInfo.class,(String) session.getAttribute("user"));
		user.setScore(user.getScore()+2);
		dao.fetchLinks(practiceStatus, "user");
		UserInfo creator = practiceStatus.getUser();
		creator.setScore(creator.getScore()+2);
		if(creator.getTeamId()!=null){
			dao.fetchLinks(creator, "team");
			TeamInfo team=creator.getTeam();
			team.setScore(team.getScore()+2);
		}
		return "1";
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
		return "1";
	}
}
