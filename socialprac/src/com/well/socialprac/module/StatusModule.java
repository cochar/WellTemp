package com.well.socialprac.module;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.well.BaseModule;
import com.well.socialprac.entity.PracticeStatus;
import com.well.socialprac.entity.PraiseMap;
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
		practiceStatus.setDisplayName(user.getDisplayName());
		//dao.insertWith(practiceStatus, "practiceStatusData");
		user.setScore(user.getScore()+3);
		if(user.getTeamId()!=null){
			dao.fetchLinks(user, "team");
			TeamInfo team=user.getTeam();
			team.setScore(team.getScore()+3);
			practiceStatus.setDisplayName(team.getName());
		}
		dao.insert(practiceStatus);
		dao.updateWith(user, "team");
		return "1";
//		response.sendRedirect("list");
	}
	
	@At
	@Ok("jsp:/list")
	public List<PracticeStatus> list(int pageNo,HttpSession session){
		Pager pager = dao.createPager(pageNo+1, 1);
		session.setAttribute("user", "41314005");
		List<PracticeStatus> list = new ArrayList<PracticeStatus>();
//		Map<String, Object> result = new HashMap<String, Object>();
		list=dao.query(PracticeStatus.class, Cnd.orderBy().desc("releaseTime"),pager);
//		dao.fetchLinks(list, "user");
//		System.out.println("========================the very firt weibo:"+list.get(0).getTextContent());
//		result.put("list", list);
		return list;
	}
	
	@At
	@Ok("json")
	public String scrollUp(int pageNo,HttpSession session){
		Pager pager = dao.createPager(pageNo+1, 1);
		session.setAttribute("user", "41314005");
		List<PracticeStatus> list = new ArrayList<PracticeStatus>();
//		Map<String, Object> result = new HashMap<String, Object>();
		list=dao.query(PracticeStatus.class, Cnd.orderBy().desc("releaseTime"),pager);
//		dao.fetchLinks(list, "user");
//		System.out.println("========================the very firt weibo:"+list.get(0).getTextContent());
//		result.put("list", list);
		JSONArray jsArr = JSONArray.fromObject(list);  
		System.out.println("==="+jsArr);
		return jsArr.toString();
	}
	
	
	public String toJson(List<PracticeStatus> list){
		String json = "{v:[";
		for(int i = 0 ; i < list.size();i++){
		   json = json + list.get(i).toString();
		   if(i != list.size()-1){
		          json = json + ",";
		   }
		}
		json = json + "]}";
		return json;		
		
	}
	@At
	public String praise(String id,HttpSession session){
		String userId = (String) session.getAttribute("user");
		if(null!=dao.fetch(PraiseMap.class,Cnd.where("statusId","=",id).and("userId","=",userId)))
			return "0";
		PracticeStatus practiceStatus = dao.fetch(PracticeStatus.class, id);
		practiceStatus.setPraiseNumber(practiceStatus.getPraiseNumber()+1);
		dao.update(practiceStatus);
		UserInfo user=dao.fetch(UserInfo.class,userId);
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
	@Ok("jsp:/comment")
	public PracticeStatus single(String id){
		PracticeStatus practiceStatus = new PracticeStatus();
		practiceStatus = dao.fetch(PracticeStatus.class,id);
		dao.fetchLinks(practiceStatus, "commentList");
		return practiceStatus;
	}
}
