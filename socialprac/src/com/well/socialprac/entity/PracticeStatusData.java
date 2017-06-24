package com.well.socialprac.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;

@Table("practice_status_data")
public class PracticeStatusData {
	
	@Name
	@Column("id")
	@Prev(els=@EL("uuid(32)")) // 可以是 uuid() uuid(32)
	private String id;
	
	@Column("status_id")
	private String statusId;
	
	@Column("text_content")
	private String textContent;
	
	@Column("photo_path")
	private String photoPath;
	
	@Column("video_path")
	private String videoPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
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
	
	

}
