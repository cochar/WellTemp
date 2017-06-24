package com.well.socialprac.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;

@Table("practice_status")
public class PracticeStatus {

	@Name
	@Column("id")
	@Prev(els=@EL("uuid(32)")) // 可以是 uuid() uuid(32)
	private String id;
	
	@Column("source_id")
	private String sourceId;
	
	@Column("user_id")
	private String userId;
	
	@Column("release_time")
	private Date releaseTime;
	
	@Column("praise_number")
	private int praiseNumber;
	
	@Column("comment_number")
	private int commentNumber;
	
	@Column("forward_number")
	private int forwardNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public int getPraiseNumber() {
		return praiseNumber;
	}

	public void setPraiseNumber(int praiseNumber) {
		this.praiseNumber = praiseNumber;
	}

	public int getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

	public int getForwardNumber() {
		return forwardNumber;
	}

	public void setForwardNumber(int forwardNumber) {
		this.forwardNumber = forwardNumber;
	}
	
}
