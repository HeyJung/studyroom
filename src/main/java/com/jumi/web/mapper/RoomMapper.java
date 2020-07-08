package com.jumi.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jumi.web.domain.RoomVO;

public class RoomMapper implements RowMapper<RoomVO>	{

	@Override
	public RoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		RoomVO vo = new RoomVO();
		vo.setrId(rs.getInt("rId"));
		vo.setrName(rs.getString("rName"));
		vo.setrPersonnel(rs.getInt("rPersonnel"));
		vo.setrPrice(rs.getInt("rPrice"));
		
		return vo;
	}

	
	  
	 

}
