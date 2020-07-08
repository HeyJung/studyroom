package com.jumi.web.domain;

public class RoomVO {

	private int rId;
	private String rName;
	private int rPersonnel;
	private int rPrice;
	
	public RoomVO() {
		// TODO Auto-generated constructor stub
	}
	
	public RoomVO(int rId, String rName, int rPersonnel, int rPrice) {
		this.rId = rId;
		this.rName = rName;
		this.rPersonnel = rPersonnel;
		this.rPrice = rPrice;
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public int getrPersonnel() {
		return rPersonnel;
	}
	public void setrPersonnel(int rPersonnel) {
		this.rPersonnel = rPersonnel;
	}
	public int getrPrice() {
		return rPrice;
	}
	public void setrPrice(int rPrice) {
		this.rPrice = rPrice;
	}
	@Override
	public String toString() {
		return "RoomVO [rId=" + rId + ", rName=" + rName + ", rPersonnel=" + rPersonnel + ", rPrice=" + rPrice + "]";
	}
	
	
}
