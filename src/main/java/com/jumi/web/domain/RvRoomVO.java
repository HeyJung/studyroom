package com.jumi.web.domain;

import java.sql.Date;
import java.sql.Time;

public class RvRoomVO {
	private int rvId;
	private int roomId;
	private String memId;
	private Date rvDate;
	private Time rvStart;
	private Time rvEnd;
	private boolean isDeposit;
	
	public RvRoomVO() {
		// TODO Auto-generated constructor stub
	}

	public RvRoomVO(int rvId, int roomId, String memId, Date rvDate, Time rvStart, Time rvEnd, boolean isDeposit) {
		this.rvId = rvId;
		this.roomId = roomId;
		this.memId = memId;
		this.rvDate = rvDate;
		this.rvStart = rvStart;
		this.rvEnd = rvEnd;
		this.isDeposit = isDeposit;
	}



	public int getRvId() {
		return rvId;
	}

	public void setRvId(int rvId) {
		this.rvId = rvId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public Date getRvDate() {
		return rvDate;
	}

	public void setRvDate(Date rvDate) {
		this.rvDate = rvDate;
	}

	public Time getRvStart() {
		return rvStart;
	}

	public void setRvStart(Time rvStart) {
		this.rvStart = rvStart;
	}

	public Time getRvEnd() {
		return rvEnd;
	}

	public void setRvEnd(Time rvEnd) {
		this.rvEnd = rvEnd;
	}

	public boolean isDeposit() {
		return isDeposit;
	}

	public void setDeposit(boolean isDeposit) {
		this.isDeposit = isDeposit;
	}

	@Override
	public String toString() {
		return "RvRoomVO [rvId=" + rvId + ", roomId=" + roomId + ", memId=" + memId + ", rvDate=" + rvDate
				+ ", rvStart=" + rvStart + ", rvEnd=" + rvEnd + ", isDeposit=" + isDeposit + "]";
	}




	
	
}
