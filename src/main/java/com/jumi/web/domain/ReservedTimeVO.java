package com.jumi.web.domain;

import java.sql.Time;

public class ReservedTimeVO {
	private Time rvStart;
	private Time rvEnd;

	public ReservedTimeVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ReservedTimeVO(Time rvStart, Time rvEnd) {
		this.rvStart = rvStart;
		this.rvEnd = rvEnd;
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
	
	public int[] getMin() {
		String start_ = String.valueOf(rvStart).split(":", 2)[0];
		String end_ = String.valueOf(rvEnd).split(":", 2)[0];
		int start = Integer.parseInt(start_);
		int end = Integer.parseInt(end_);
		int[] list = new int[(end-start)+1];
		list[0] = start;
		for(int i=1; i<list.length; i++) {
			list[i] = list[i-1] + 1;
		}
		return list;
	}
	
	@Override
	public String toString() {
		return "ReservedTimeVO [rvStart=" + rvStart + ", rvEnd=" + rvEnd + "]";
	}
	
	
	
}
