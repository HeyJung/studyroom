package com.jumi.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jumi.web.domain.NoticeAttachVO;

public class NoticeAttachMapper implements RowMapper<NoticeAttachVO>	{

	@Override
	public NoticeAttachVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		NoticeAttachVO vo = new NoticeAttachVO();
		vo.setUuid(rs.getString("uuid"));
		vo.setUploadPath(rs.getString("uploadPath"));
		vo.setFileName(rs.getString("fileName"));
		String fileType = rs.getString("fileType"); 
		if(fileType.equals("1")) vo.setFileType(true);
		else vo.setFileType(false);
		vo.setNoticeId(rs.getInt("noticeId"));
		
		return vo;
	}

	
	  
	 

}
