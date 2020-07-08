package com.jumi.web.domain;

public class AttachFileVO {
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
	
	public AttachFileVO() {
		// TODO Auto-generated constructor stub
	}
	
	public AttachFileVO(String fileName, String uploadPath, String uuid, boolean image) {
		this.fileName = fileName;
		this.uploadPath = uploadPath;
		this.uuid = uuid;
		this.image = image;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public boolean isImage() {
		return image;
	}
	public void setImage(boolean image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "AttachFileVO [fileName=" + fileName + ", uploadPath=" + uploadPath + ", uuid=" + uuid + ", image="
				+ image + "]";
	}
	
	
}
