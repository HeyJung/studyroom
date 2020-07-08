package com.jumi.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jumi.web.domain.NoticeCmtVO;

public class NoticeCmtMapper implements RowMapper<NoticeCmtVO>	{

	@Override
	public NoticeCmtVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		NoticeCmtVO vo = new NoticeCmtVO();
		vo.setCmtId(rs.getInt("cmtId"));
		vo.setCmtWriter(rs.getString("cmtWriter"));
		vo.setCmtNoticeId(rs.getInt("cmtNoticeId"));
		vo.setCmtContent(rs.getString("cmtContent"));
		vo.setCmtDate(rs.getTimestamp("cmtDate"));

		return vo;
	}

	
	  
	 

}
