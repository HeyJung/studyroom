package com.jumi.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jumi.web.domain.EventVO;

public class EventMapper implements RowMapper<EventVO>	{

	@Override
	public EventVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventVO vo = new EventVO();
		vo.setRvDate(rs.getDate("rvDate"));
		vo.setRvId(rs.getInt("rvId"));
		
		return vo;
	}

	
	  
	 

}
