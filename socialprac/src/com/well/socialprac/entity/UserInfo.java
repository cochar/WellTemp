package com.well.socialprac.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;

@Table("user_info")
public class UserInfo {
	
	@Name
	@Column("id")
	@Prev(els=@EL("uuid(32)")) // 可以是 uuid() uuid(32)
	private String id;
	
	@Column("name")
	private String name;
	
	@Column("school")
	private String school;
	
	@Column("class_no")
	private String classNo;
	
	@Column("phone")
	private String phone;
	
	@Column("score")
	private String score;
	
	@Column("open_id")
	private String openId;
	
	@Column("if_practice_member")
	private String ifPracticeMember;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIfPracticeMember() {
		return ifPracticeMember;
	}

	public void setIfPracticeMember(String ifPracticeMember) {
		this.ifPracticeMember = ifPracticeMember;
	}

}
