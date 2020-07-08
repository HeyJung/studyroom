package com.jumi.web.domain;

import java.sql.Date;

public class EventVO {
	private Date rvDate;
	private int rvId;
	
	public EventVO() {
		// TODO Auto-generated constructor stub
	}
	
	public EventVO(Date rvDate, int rvId) {
		this.rvDate = rvDate;
		this.rvId = rvId;
	}
	public Date getRvDate() {
		return rvDate;
	}
	public void setRvDate(Date rvDate) {
		this.rvDate = rvDate;
	}
	public int getRvId() {
		return rvId;
	}
	public void setRvId(int rvId) {
		this.rvId = rvId;
	}
	@Override
	public String toString() {
		return "EventVO [rvDate=" + rvDate + ", rvId=" + rvId + "]";
	}
	
	
}
