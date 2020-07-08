package com.jumi.web.dao;

import java.util.List;

import com.jumi.web.domain.Critetia;
import com.jumi.web.domain.NoticeCmtVO;

public interface NoticeCmtDAO {
	public int insert(NoticeCmtVO vo);
	public NoticeCmtVO get(int cmtId);
	public List<NoticeCmtVO> getList(Critetia cri, int cmtNoticeId);
	public int update(NoticeCmtVO vo);
	public int delete(int cmtId);
	
	public int getCmtCount(int cmtNoticeId);
}
