package com.jumi.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jumi.web.domain.RvRoomVO;

public class RvRoomMapper implements RowMapper<RvRoomVO>	{

	@Override
	public RvRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		RvRoomVO vo = new RvRoomVO();
		vo.setRvId(rs.getInt("rvId"));
		vo.setRoomId(rs.getInt("roomId"));
		vo.setMemId(rs.getString("memId"));
		vo.setRvDate(rs.getDate("rvDate"));
		vo.setRvStart(rs.getTime("rvStart"));
		vo.setRvEnd(rs.getTime("rvEnd"));
		vo.setDeposit(rs.getBoolean("isDeposit"));	
		return vo;
	}

	
	  
	 

}
