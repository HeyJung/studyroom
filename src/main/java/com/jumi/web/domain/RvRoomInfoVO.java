package com.jumi.web.domain;

import java.sql.Date;
import java.sql.Time;

public class RvRoomInfoVO extends RvRoomVO{
	private String rName;
	private int price;
	private int payment;
	
	public RvRoomInfoVO() {
		// TODO Auto-generated constructor stub
	}
	
	public RvRoomInfoVO(int rvId, int roomId, String memId, Date rvDate, Time rvStart, Time rvEnd, boolean isDeposit, String rName, int price) {
		super(rvId, roomId, memId, rvDate, rvStart, rvEnd, isDeposit);
		this.rName = rName;
		this.price = price;
		
		int end = Integer.parseInt(rvEnd.toString().substring(0, 2));
		int start = Integer.parseInt(rvStart.toString().substring(0, 2));
		this.payment = (end - start +1) * price ;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "RvRoomInfoVO [rName=" + rName + ", price=" + price + ", payment=" + payment + "]";
	}

	

	
}
