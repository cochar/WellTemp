package com.well.socialprac.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

import com.well.socialprac.entity.TeamInfo;
import com.well.socialprac.entity.UserInfo;

@IocBean
@At("score")
public class ScoreModule extends BaseModule {

	@At
	public Map<String,Object> scoreList(){
		Map<String,Object> result=new HashMap<String,Object>();
//		Sql sqlTeam = Sqls.create("selet score teamScore from team_info order by score desc");
//		sqlTeam.setCallback(Sqls.callback.entities());
//		sqlTeam.setEntity(dao.getEntity(Record.class));
//		dao.execute(sqlTeam);
//		result.put("team", sqlTeam.getList(Record.class));
//		Sql sqlSelf = Sqls.create("selet score selfScore from user_info where team_id=null order by score desc");
//		sqlSelf.setCallback(Sqls.callback.entities());
//		sqlSelf.setEntity(dao.getEntity(Record.class));
//		dao.execute(sqlSelf);
//		result.put("team", sqlSelf.getList(Record.class));
		List<TeamInfo> teamList = dao.query(TeamInfo.class,Cnd.orderBy().desc("score"));
		List<UserInfo> userList = dao.query(UserInfo.class,Cnd.where("team_id","=",null).desc("score"));
		result.put("teamList", teamList);
		result.put("userList", userList);
		return result;	
	}
}
