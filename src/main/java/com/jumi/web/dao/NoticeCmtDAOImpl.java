package com.jumi.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.jumi.web.domain.Critetia;
import com.jumi.web.domain.NoticeCmtVO;
import com.jumi.web.mapper.NoticeCmtMapper;
import com.jumi.web.mapper.NoticeMapper;

@Repository
public class NoticeCmtDAOImpl implements NoticeCmtDAO{
	
	private JdbcTemplate template;
	
	@Autowired
	public NoticeCmtDAOImpl(DriverManagerDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	@Override
	public int insert(NoticeCmtVO vo) {
		String sql = "INSERT INTO noticeCmt (cmtWriter, cmtNoticeId, cmtContent) VALUES (?, ?, ?)";
		return template.update(sql, vo.getCmtWriter(), vo.getCmtNoticeId(), vo.getCmtContent());
	}

	@Override
	public NoticeCmtVO get(int cmtId) {
		String sql = "SELECT * FROM noticeCmt WHERE cmtId = ?";
		return template.queryForObject(sql, new Object[]{cmtId}, new NoticeCmtMapper());
	}

	@Override
	public List<NoticeCmtVO> getList(Critetia cri, int cmtNoticeId) {
		String sql = "SELECT * FROM ("
				+ "SELECT @rownum:=@rownum+1 as rn, n.* FROM noticeCmt n, (SELECT @rownum:=0) tmp "
				+ "WHERE cmtNoticeId = ? ORDER BY cmtId DESC) list "
				+ "WHERE rn <= ?  limit ?, 10";
		return template.query(sql, new Object[]{cmtNoticeId, (cri.getPage()*10), (cri.getPage()-1)*10}, new NoticeCmtMapper());
	}

	@Override
	public int update(NoticeCmtVO vo) {
		String sql = "UPDATE noticeCmt SET cmtContent = ?, cmtDate = ? WHERE cmtId = ?";
		return template.update(sql, vo.getCmtContent(), vo.getCmtDate(), vo.getCmtId());
	}

	@Override
	public int delete(int cmtId) {
		String sql = "DELETE FROM noticeCmt WHERE cmtId = ?";
		return template.update(sql, cmtId);
	}
	@Override
	public int getCmtCount(int cmtNoticeId) {
		String sql = "SELECT count(cmtId) from noticeCmt WHERE cmtNoticeId = ?";
		return template.queryForObject(sql, new Object[]{cmtNoticeId}, Integer.class);
	}

}
