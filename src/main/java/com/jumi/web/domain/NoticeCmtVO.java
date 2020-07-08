package com.jumi.web.domain;

import java.util.Date;

public class NoticeCmtVO {

	private int cmtId;
	private String cmtWriter;
	private int cmtNoticeId;
	private String cmtContent;
	private Date cmtDate;
	
	public NoticeCmtVO() {
		// TODO Auto-generated constructor stub
	}
	
	public NoticeCmtVO(int cmtId, String cmtWriter, int cmtNoticeId, String cmtContent, Date cmtDate) {
		this.cmtId = cmtId;
		this.cmtWriter = cmtWriter;
		this.cmtNoticeId = cmtNoticeId;
		this.cmtContent = cmtContent;
		this.cmtDate = cmtDate;
	}
	public int getCmtId() {
		return cmtId;
	}
	public void setCmtId(int cmtId) {
		this.cmtId = cmtId;
	}
	public String getCmtWriter() {
		return cmtWriter;
	}
	public void setCmtWriter(String cmtWriter) {
		this.cmtWriter = cmtWriter;
	}
	public int getCmtNoticeId() {
		return cmtNoticeId;
	}
	public void setCmtNoticeId(int cmtNoticeId) {
		this.cmtNoticeId = cmtNoticeId;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public Date getCmtDate() {
		return cmtDate;
	}
	public void setCmtDate(Date cmtDate) {
		this.cmtDate = cmtDate;
	}
	@Override
	public String toString() {
		return "NoticeCmt [cmtId=" + cmtId + ", cmtWriter=" + cmtWriter + ", cmtNoticeId=" + cmtNoticeId
				+ ", cmtContnet=" + cmtContent + ", cmtDate=" + cmtDate + "]";
	}
	
	
	
}
