package com.jumi.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jumi.web.domain.MemberVO;
import com.jumi.web.domain.NoticeVO;

public class MemberMapper implements RowMapper<MemberVO>	{

	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO vo = new MemberVO();
		vo.setMemId(rs.getString("memID"));
		vo.setMemPw(rs.getString("memPw"));
		vo.setMemName(rs.getString("memName"));
		vo.setMemEmail(rs.getString("memEmail"));
		vo.setMemAccount(rs.getInt("memAccount"));
		
		return vo;
	}

	
	  
	 

}
