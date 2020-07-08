package com.jumi.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jumi.web.domain.RvRoomInfoVO;

public class RvRoomInfoMapper implements RowMapper<RvRoomInfoVO>	{

	@Override
	public RvRoomInfoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		RvRoomInfoVO vo = new RvRoomInfoVO();
		vo.setRvId(rs.getInt("rvId"));
		vo.setRoomId(rs.getInt("roomId"));
		vo.setMemId(rs.getString("memId"));
		vo.setRvDate(rs.getDate("rvDate"));
		vo.setRvStart(rs.getTime("rvStart"));
		vo.setRvEnd(rs.getTime("rvEnd"));
		vo.setDeposit(rs.getBoolean("isDeposit"));	
		vo.setrName(rs.getString("rName"));
		vo.setPrice(rs.getInt("rPrice"));
		int end = Integer.parseInt(vo.getRvEnd().toString().substring(0, 2));
		int start = Integer.parseInt(vo.getRvStart().toString().substring(0, 2));
		vo.setPayment((end - start +1)*vo.getPrice());
		return vo;
	}

	
	  
	 

}
