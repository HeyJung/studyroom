package com.jumi.web.domain;

public class NoticeAttachVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	private int noticeId;
	
	public NoticeAttachVO() {
		// TODO Auto-generated constructor stub
	}
	
	public NoticeAttachVO(String uuid, String uploadPath, String fileName, boolean fileType, int noticeId) {
		this.uuid = uuid;
		this.uploadPath = uploadPath;
		this.fileName = fileName;
		this.fileType = fileType;
		this.noticeId = noticeId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean getFileType() {
		return fileType;
	}
	public void setFileType(boolean fileType) {
		this.fileType = fileType;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	@Override
	public String toString() {
		return "NoticeAttachVO [uuid=" + uuid + ", uploadPath=" + uploadPath + ", fileName=" + fileName + ", fileType="
				+ fileType + ", noticeId=" + noticeId + "]";
	}
	
	
}
