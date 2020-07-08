package com.jumi.web.service;

import java.util.List;

import com.jumi.web.domain.MemberVO;

public interface MemberService {
	public int register(MemberVO vo);
	public List<MemberVO> getList();
	public MemberVO get(String memId);
	public int update(MemberVO vo);
	public int delete(String memId);
	
	public String login(MemberVO vo) throws Exception;
	public String password(String memId);
	public int idChk(String inputId);
	public int payment(String memId, int payment, int rvId);
}
