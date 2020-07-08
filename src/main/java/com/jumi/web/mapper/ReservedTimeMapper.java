package com.jumi.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jumi.web.domain.ReservedTimeVO;

public class ReservedTimeMapper implements RowMapper<ReservedTimeVO>	{

	@Override
	public ReservedTimeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReservedTimeVO vo = new ReservedTimeVO();
		vo.setRvStart(rs.getTime("rvStart"));
		vo.setRvEnd(rs.getTime("rvEnd"));
		
		return vo;
	}

	
	  
	 

}
