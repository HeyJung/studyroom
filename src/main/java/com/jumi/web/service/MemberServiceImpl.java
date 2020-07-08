package com.jumi.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumi.web.dao.MemberDAO;
import com.jumi.web.dao.ReservationDAO;
import com.jumi.web.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private ReservationDAO reservationdao;
	
	@Override
	public int register(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.register(vo);
	}

	@Override
	public List<MemberVO> getList() {
		// TODO Auto-generated method stub
		return dao.getList();
	}

	@Override
	public MemberVO get(String memId) {
		// TODO Auto-generated method stub
		return dao.get(memId);
	}

	@Override
	public int update(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

	@Override
	public int delete(String memId) {
		// TODO Auto-generated method stub
		return dao.delete(memId);
	}

	@Override
	public String login(MemberVO vo) throws Exception{
		// TODO Auto-generated method stub
		return dao.login(vo);
	}

	@Override
	public String password(String memId) {
		// TODO Auto-generated method stub
		return dao.password(memId);
	}

	@Override
	public int idChk(String inputId) {
		// TODO Auto-generated method stub
		return dao.idChk(inputId);
	}
	
	@Transactional
	@Override
	public int payment(String memId, int payment, int rvId) {
		
		int accountResult = dao.payment(memId, payment);
		if(accountResult == -1) return -1;
		reservationdao.payment(rvId);
		
		return accountResult;
	}

}
