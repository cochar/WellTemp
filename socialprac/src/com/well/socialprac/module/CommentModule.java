package com.well.socialprac.module;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.well.BaseModule;
import com.well.socialprac.entity.Comment;
import com.well.socialprac.entity.PracticeStatus;
import com.well.socialprac.entity.TeamInfo;
import com.well.socialprac.entity.UserInfo;

@IocBean
@At("/comment")
public class CommentModule extends BaseModule {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@At
	@Ok("jsp:/comment_text")
	public String create(String id){
		return id;
	}
	
	
	@At
	public String save(@Param("..")Comment comment,HttpSession session) throws Exception{
		System.out.println("----"+ session.getAttribute("user").toString());
		comment.setUserId((String) session.getAttribute("user")); //为测试，暂注释
		PracticeStatus practiceStatus = dao.fetch(PracticeStatus.class, comment.getStatusId());
		practiceStatus.setCommentNumber(practiceStatus.getCommentNumber()+1);
		comment.setCommentTime(sdf.parse(sdf.format(new Date())));
		
		dao.insert(comment);
		dao.update(practiceStatus);
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
		dao.updateWith(user, "team");
		return "1";
	}
}
