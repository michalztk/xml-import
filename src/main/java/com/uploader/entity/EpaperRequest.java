package com.uploader.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class representing epaper request
 * 
 * @author michal
 *
 */
@Entity
@Table(name = "epaper_request")
public class EpaperRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "newspaper_name")
	private String newspaperName;
	@Column(name = "width")
	private short width;
	@Column(name = "height")
	private short height;
	@Column(name = "dpi")
	private short dpi;
	@Column(name = "upload_time", insertable = false, updatable = false) // column upload_time is set to CURRENT_TIMESTAMP by database so it's not inserted/ updated
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadTime;
	@Column(name = "file_name")
	private String fileName;

	public EpaperRequest() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNewspaperName() {
		return newspaperName;
	}

	public void setNewspaperName(String newspaperName) {
		this.newspaperName = newspaperName;
	}

	public short getWidth() {
		return width;
	}

	public void setWidth(short width) {
		this.width = width;
	}

	public short getHeight() {
		return height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	public short getDpi() {
		return dpi;
	}

	public void setDpi(short dpi) {
		this.dpi = dpi;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
