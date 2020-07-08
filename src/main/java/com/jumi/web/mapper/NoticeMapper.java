package com.jumi.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jumi.web.domain.NoticeVO;

public class NoticeMapper implements RowMapper<NoticeVO>	{

	@Override
	public NoticeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(rs.getInt("noticeId"));
		vo.setNoticeTitle(rs.getString("noticeTitle"));
		vo.setNoticeWriter(rs.getString("noticeWriter"));
		vo.setNoticeFile(rs.getString("noticeFile"));
		vo.setNoticeContent(rs.getString("noticeContent"));
		vo.setNoticeDate(rs.getDate("noticeDate"));
		vo.setNoticeView(rs.getInt("noticeView"));
		
		return vo;
	}

	
	  
	 

}
