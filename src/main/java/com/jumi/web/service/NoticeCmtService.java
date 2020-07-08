package com.jumi.web.service;

import java.util.List;

import com.jumi.web.domain.CmtPageVO;
import com.jumi.web.domain.Critetia;
import com.jumi.web.domain.NoticeCmtVO;

public interface NoticeCmtService {
	public int insert(NoticeCmtVO vo);
	public NoticeCmtVO get(int cmtId);
	public List<NoticeCmtVO> getList(Critetia cri, int cmtNoticeId);
	public int update(NoticeCmtVO vo);
	public int delete(int cmtId);
	
	public CmtPageVO getListPage(Critetia cri, int cmtNoticeId);
}
