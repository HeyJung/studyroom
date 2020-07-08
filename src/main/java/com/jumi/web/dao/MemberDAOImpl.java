package com.jumi.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.jumi.web.domain.MemberVO;
import com.jumi.web.mapper.MemberMapper;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	private JdbcTemplate template;
	
	@Autowired
	public MemberDAOImpl(DriverManagerDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	@Override
	public int register(MemberVO vo) {
		String sql = "INSERT INTO MEMBER(memId, memPw, memName, MemEmail) VALUES(?, ?, ?, ?)";
		return template.update(sql, vo.getMemId(), vo.getMemPw(), vo.getMemName(), vo.getMemEmail());
	}

	@Override
	public List<MemberVO> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO get(String memId) {
		String sql = "SELECT * FROM MEMBER WHERE memId = ?";
		return template.queryForObject(sql, new Object[]{memId}, new MemberMapper());
	}

	@Override
	public int update(MemberVO vo) {
		String sql = "UPDATE MEMBER SET memPw = ?, memName = ?, memEmail = ? WHERE memID = ?";
		return template.update(sql, vo.getMemPw(), vo.getMemName(), vo.getMemEmail(), vo.getMemId());
	}

	@Override
	public int delete(String memId) {
		String sql = "DELETE MEMBER WHERE memID = ?";
		return template.update(sql, memId);
	}
	@Override
	public String login(MemberVO vo) throws Exception{
		String sql = "SELECT memID FROM Member WHERE memID = ? AND memPw = ?";
		return template.queryForObject(sql, new Object[]{vo.getMemId(), vo.getMemPw()}, String.class);
	}
	@Override
	public String password(String memId) {
		String sql = "SELECT memPw FROM Member WHERE memID = ?";
		return template.queryForObject(sql, new Object[]{memId}, String.class);
	}
	@Override
	public int idChk(String inputId){
		String sql ="SELECT COUNT(*) FROM Member WHERE memID = ?";
		return template.queryForObject(sql, new Object[]{inputId}, Integer.class);
	}
	@Override
	public int payment(String memId, int payment) {
		String sql = "UPDATE Member SET memAccount = ? WHERE memID = ?";
		int currentMoney = template.queryForObject("SELECT memAccount FROM Member WHERE memID = ?", new Object[]{memId}, int.class);
		if(currentMoney < payment) return -1;
		return template.update(sql, (currentMoney - payment), memId);
	}

}
