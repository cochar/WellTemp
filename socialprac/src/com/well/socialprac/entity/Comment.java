package com.well.socialprac.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;

@Table("comment")
public class Comment {
	

	@Name
	@Column("id")
	@Prev(els=@EL("uuid(32)")) // 可以是 uuid() uuid(32)
	private String id;
	
	@Column("text_content")
	private String textContent;
	
	@Column("user_id")
	private String userId;
	
	@Column("comment_time")
	private Date commentTime;
	
	@Column("status_id")
	private int statusId;
	
}
