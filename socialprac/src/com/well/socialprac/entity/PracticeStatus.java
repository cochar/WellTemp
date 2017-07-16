package com.well.socialprac.entity;

import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
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
	
	private String releaseTimeStr;
	
	@Column("praise_number")
	private int praiseNumber;
	
	@Column("comment_number")
	private int commentNumber;
	
	@Column("forward_number")
	private int forwardNumber;
	
	@Column("data_id")
	private String dataId;
	
	@Column("text_content")
	private String textContent;
	
	@Column("photo_path")
	private String photoPath;
	
	@Column("video_path")
	private String videoPath;
	
	@Column("display_name")
	private String displayName;

	@One(field="userId")
	private UserInfo user;
	
	@One(field="dataId")
	private PracticeStatusData practiceStatusData;
	
	@Many(field="statusId")
	private List<Comment> commentList;
	
	@Many(field="statusId")
	private List<PraiseMap> praiseList;
	
	private int ifPraised;
	
	private List<String> picList;

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

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public PracticeStatusData getPracticeStatusData() {
		return practiceStatusData;
	}

	public void setPracticeStatusData(PracticeStatusData practiceStatusData) {
		this.practiceStatusData = practiceStatusData;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public List<PraiseMap> getPraiseList() {
		return praiseList;
	}

	public void setPraiseList(List<PraiseMap> praiseList) {
		this.praiseList = praiseList;
	}

	public int getIfPraised() {
		return ifPraised;
	}

	public void setIfPraised(int ifPraised) {
		this.ifPraised = ifPraised;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public String getReleaseTimeStr() {
		return releaseTimeStr;
	}

	public void setReleaseTimeStr(String releaseTimeStr) {
		this.releaseTimeStr = releaseTimeStr;
	}
	
}
