package com.well.socialprac.module;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
	public Map<String, Object> list(int pageNo,HttpSession session){
		Pager pager = dao.createPager(pageNo+1, 10);
		session.setAttribute("user", "41314005");
		String userId = (String) session.getAttribute("user");
		List<PracticeStatus> list = new ArrayList<PracticeStatus>();
		Map<String, Object> result = new HashMap<String, Object>();
		list=dao.query(PracticeStatus.class, Cnd.orderBy().desc("releaseTime"),pager);
//		dao.fetchLinks(list,"praiseList");
		for(PracticeStatus ps:list){
			ps.setIfPraised(0);
			if(null!=dao.fetch(PraiseMap.class,Cnd.where("statusId","=",ps.getId()).and("userId","=",userId)))
				ps.setIfPraised(1);
		}
//		dao.fetchLinks(list, "user");
//		System.out.println("========================the very firt weibo:"+list.get(0).getTextContent());
		result.put("statusList", list);
		
		//个人
		UserInfo user= dao.fetch(UserInfo.class,userId);
		dao.fetchLinks(user,"team");
		result.put("user", user);
		
		//排行榜
		List<TeamInfo> teamList = dao.query(TeamInfo.class,Cnd.orderBy().desc("score"),pager);
		List<UserInfo> userList = dao.query(UserInfo.class,Cnd.where("team_id","=",null).desc("score"),pager);
		result.put("teamList", teamList);
		result.put("userList", userList);
		return result;
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
		PraiseMap praiseMap = new PraiseMap();
		praiseMap.setStatusId(practiceStatus.getId());
		praiseMap.setUserId(userId);
		dao.insert(praiseMap);
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
	@At("/upload")
	@Ok("json")
	public String upload(HttpServletRequest request,
			HttpSession session,HttpServletResponse response)
			throws Exception {
//		Map<String, Object> result = new HashMap<String, Object>();
			UserInfo user=dao.fetch(UserInfo.class,(String) session.getAttribute("user"));
	       //把文件上传到服务器指定位置，并向前台返回文件名
		
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			upload.setHeaderEncoding("utf-8");
			List fileList = null;
			try {
				// 文件类型解析req
				fileList = upload.parseRequest(request);
			} catch (FileUploadException ex) {
				// 终止文件上传，此处抛出异常
				ex.printStackTrace();
			}
			Iterator it = fileList.iterator();
			while (it.hasNext()) {
				String extName = "";
				FileItem item = (FileItem) it.next();
				if (!item.isFormField()) {
					String filename = item.getName();
					/*name = name.substring(name.lastIndexOf(File.separator) + 1) ;
					System.out.println("附件名字为："+name);*/
					
					//为上传附件重命名
					Date date=new Date();
					long timename=date.getTime();
					String name=timename+"";
					
					//name = name.substring(name.lastIndexOf(File.separator) + 1) ;
					//获取文件格式
					String type = item.getContentType();
					type="."+type.substring(type.lastIndexOf("/")+1);
					name=name+type;
					System.out.println("======文件名字"+name+"=======文件格式"+type+"==========文件真实的名字"+filename);
					if (item.getName() == null|| item.getName().trim().equals("")) {
						continue;
					}
					//String path = request.getSession().getServletContext().getRealPath("/cfg.xml"); 
					String filePath=request.getSession().getServletContext().getRealPath("")+"\\"+"upload"+"\\";
					System.out.println(name+"========文件路径"+filePath);
					File file = new File(filePath+name);
					try {
						File fileFolder = new File(filePath);
						if(!fileFolder.exists()){
							fileFolder.mkdirs();
						}
						// 将文件存入本地服务器
						if(!file.exists())
							file.createNewFile();
						item.write(file);
						//向表scan里记录数据
						
						// 向前台返回文件名					
//						PrintWriter pw = response.getWriter();
//						pw.print(name);
//						System.out.println("================================="+name);
//						pw.close();
//						pw.flush();
						Map map = new HashMap();
						String jsonStr = null;
						map.put("success", "yes");
						map.put("name", name);
		                jsonStr = String.valueOf(JSONObject.fromObject(map));
		                //更新记录
		               // systemService.updateScaninfo(user.getId(),name,filename,filePath);
						return jsonStr;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		
		return null;
	}
}
