package com.jumi.web.domain;

import java.util.List;

public class CmtPageVO {
	private int cmtCount;
	private List<NoticeCmtVO> list;
	
	public CmtPageVO() {
		// TODO Auto-generated constructor stub
	}
	
	public CmtPageVO(int cmtCount, List<NoticeCmtVO> list) {
		this.cmtCount = cmtCount;
		this.list = list;
	}
	public int getCmtCount() {
		return cmtCount;
	}
	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}
	public List<NoticeCmtVO> getList() {
		return list;
	}
	public void setList(List<NoticeCmtVO> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "CmtPageVO [cmtCount=" + cmtCount + ", list=" + list + "]";
	}
	
	
}
