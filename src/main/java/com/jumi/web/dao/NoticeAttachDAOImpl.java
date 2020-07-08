package com.jumi.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.jumi.web.domain.NoticeAttachVO;
import com.jumi.web.mapper.NoticeAttachMapper;

@Repository
public class NoticeAttachDAOImpl implements NoticeAttachDAO{
	
	private JdbcTemplate template;
	
	@Autowired
	public NoticeAttachDAOImpl(DriverManagerDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	@Override
	public void insert(NoticeAttachVO vo) {
		String sql = "INSERT INTO noticeAttach(uuid, uploadPath, fileName, fileType, noticeId)"
				+ "VALUES (?, ?, ?, ?, ?)";
		template.update(sql, vo.getUuid(), vo.getUploadPath(), vo.getFileName(), vo.getFileType(), vo.getNoticeId());
	}

	@Override
	public void delete(String uuid) {
		String sql = "DELETE FROM noticeAttach WHERE uuid = ?";
		template.update(sql, uuid);
	}

	@Override
	public List<NoticeAttachVO> findByNoticeId(int noticeId) {
		String sql = "SELECT * FROM noticeAttach WHERE noticeId = ?";
		return template.query(sql, new Object[]{noticeId}, new NoticeAttachMapper());
	}
	@Override
	public void deleteAll(int noticeId) {
		String sql = "DELETE FROM noticeAttach WHERE noticeId = ?";
		template.update(sql, noticeId);
	}
	@Override
	public List<NoticeAttachVO> getOldFiles() {
		String sql = "SELECT * FROM noticeAttach WHERE uploadPath = date_format(CURDATE()-1, '%Y\\%m\\%d')";
		return template.query(sql, new NoticeAttachMapper());
	}

}
