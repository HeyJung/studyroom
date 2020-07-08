package com.jumi.web.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class MemberVO {
	
	@NotBlank(message ="���̵� �ۼ����ּ���")
	@Size(min=3, max=10, message="���̵�� 3~10�ڸ��Դϴ�")
	private String memId;
	
	@NotBlank(message ="��й�ȣ�� �ۼ����ּ���")
	@Size(min=4, max=15, message="��й�ȣ�� 4~15�ڸ��Դϴ�")
	private String memPw;
	
	@NotBlank(message ="�̸��� �ۼ����ּ���")
	private String memName;
	
	@Email(message ="������ ����� �����ּ���")
	private String memEmail;
	
	private int memAccount;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberVO(String memId, String memPw, String memName, String memEmail, int memAccount) {
		this.memId = memId;
		this.memPw = memPw;
		this.memName = memName;
		this.memEmail = memEmail;
		this.memAccount = memAccount;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public int getMemAccount() {
		return memAccount;
	}
	public void setMemAccount(int memAccount) {
		this.memAccount = memAccount;
	}
	
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memPw=" + memPw + ", memName=" + memName + ", memEmail=" + memEmail
				+ ", memAccount=" + memAccount + "]";
	}
	
}
