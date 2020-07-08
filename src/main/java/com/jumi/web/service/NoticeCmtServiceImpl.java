package com.jumi.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumi.web.dao.NoticeCmtDAO;
import com.jumi.web.domain.CmtPageVO;
import com.jumi.web.domain.Critetia;
import com.jumi.web.domain.NoticeCmtVO;

@Service
public class NoticeCmtServiceImpl implements NoticeCmtService{
	
	@Autowired
	private NoticeCmtDAO dao;
	
	@Override
	public int insert(NoticeCmtVO vo) {
		// TODO Auto-generated method stub
		return dao.insert(vo);
	}

	@Override
	public NoticeCmtVO get(int cmtId) {
		// TODO Auto-generated method stub
		return dao.get(cmtId);
	}

	@Override
	public List<NoticeCmtVO> getList(Critetia cri, int cmtNoticeId) {
		// TODO Auto-generated method stub
		return dao.getList(cri, cmtNoticeId);
	}

	@Override
	public int update(NoticeCmtVO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

	@Override
	public int delete(int cmtId) {
		// TODO Auto-generated method stub
		return dao.delete(cmtId);
	}

	@Override
	public CmtPageVO getListPage(Critetia cri, int cmtNoticeId) {
		// TODO Auto-generated method stub
		return new CmtPageVO(dao.getCmtCount(cmtNoticeId), dao.getList(cri, cmtNoticeId));
	}

}
