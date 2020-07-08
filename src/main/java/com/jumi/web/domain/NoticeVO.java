package com.jumi.web.domain;

import java.sql.Date;
import java.util.List;

public class NoticeVO {
	private int noticeId;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeFile;
	private String noticeContent;
	private Date noticeDate;
	private int noticeView;
	
	private List<NoticeAttachVO> attachList;
	
	public NoticeVO() {
		// TODO Auto-generated constructor stub
	}
	
	public NoticeVO(int noticeId, String noticeTitle, String noticeWriter, String noticeFile, String noticeContent,
			Date noticeDate, int noticeView) {
		this.noticeId = noticeId;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeFile = noticeFile;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
		this.noticeView = noticeView;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	public String getNoticeFile() {
		return noticeFile;
	}
	public void setNoticeFile(String noticeFile) {
		this.noticeFile = noticeFile;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	public int getNoticeView() {
		return noticeView;
	}
	public void setNoticeView(int noticeView) {
		this.noticeView = noticeView;
	}
	

	public List<NoticeAttachVO> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<NoticeAttachVO> attachList) {
		this.attachList = attachList;
	}

	@Override
	public String toString() {
		return "NoticeVO [noticeId=" + noticeId + ", noticeTitle=" + noticeTitle + ", noticeWriter=" + noticeWriter
				+ ", noticeFile=" + noticeFile + ", noticeContent=" + noticeContent + ", noticeDate=" + noticeDate
				+ ", noticeView=" + noticeView + ", attachList=" + attachList + "]";
	}




}
