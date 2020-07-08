package com.jumi.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.jumi.web.domain.Critetia;
import com.jumi.web.domain.NoticeVO;
import com.jumi.web.mapper.NoticeMapper;

@Repository
public class NoticeDAOImpl implements NoticeDAO{

	private JdbcTemplate template;
	
	@Autowired
	public NoticeDAOImpl(DriverManagerDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(NoticeVO vo) {
		String sql = "INSERT INTO notice (noticeTitle, noticeWriter, noticeFile, noticeContent) VALUES (?, ?, ?, ?)";
		return template.update(sql, vo.getNoticeTitle(), vo.getNoticeWriter(), vo.getNoticeFile(), vo.getNoticeContent());
	}

	@Override
	public NoticeVO get(int noticeId) {
		String sql = "SELECT * FROM notice WHERE noticeId = ?";
		return template.queryForObject(sql, new Object[]{noticeId}, new NoticeMapper());
	}

	@Override
	public List<NoticeVO> getList(Critetia cri) {
		String sql = "SELECT * FROM ("
				+ "SELECT @rownum:=@rownum+1 as rn, n.* FROM notice n, (SELECT @rownum:=0) tmp "
				+ "WHERE "+ cri.getField() +" LIKE ? ORDER BY noticeId DESC) list "
				+ "WHERE rn <= ?  limit ?, 10";
		return template.query(sql, new Object[]{("%"+cri.getKeyword()+"%"),(cri.getPage()*10), (cri.getPage()-1)*10}, new NoticeMapper());
	}

	@Override
	public int update(NoticeVO vo) {
		String sql = "UPDATE notice SET noticeTitle = ?, noticeFile = ?, noticeContent = ? WHERE noticeId = ?";
		return template.update(sql, vo.getNoticeTitle(), vo.getNoticeFile(), vo.getNoticeContent(), vo.getNoticeId());
	}

	@Override
	public int delete(int noticeId) {
		String sql = "DELETE FROM notice WHERE noticeId = ?";
		return template.update(sql, noticeId);
	}

	@Override
	public int nocieCount(Critetia cri) {
		String sql = "SELECT COUNT(noticeId) COUNT FROM "
				+ "(SELECT * FROM notice WHERE " + cri.getField() + " LIKE ?) B";
		return template.queryForObject(sql, new Object[] {"%"+cri.getKeyword()+"%"}, Integer.class);
	}
	
	public int getNextNoticeId() {
		String sql= "SELECT Auto_increment "
				+ "FROM information_schema.tables "
				+ "WHERE table_schema ='stdroom' AND table_name ='Notice'";
		return template.queryForObject(sql, int.class);
	}

}
