package com.jumi.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumi.web.dao.NoticeAttachDAO;
import com.jumi.web.dao.NoticeDAO;
import com.jumi.web.domain.Critetia;
import com.jumi.web.domain.NoticeAttachVO;
import com.jumi.web.domain.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeDAO dao;
	@Autowired
	
	private NoticeAttachDAO attachdao;
	
	@Transactional
	@Override
	public int insert(NoticeVO vo) {
		int nextNoticeId = getNextNoticeId();
		dao.insert(vo);
		if(vo.getAttachList() == null || vo.getAttachList().size() <= 0) return 1;
		
		vo.getAttachList().forEach(attach -> {
			attach.setNoticeId(nextNoticeId);
			attachdao.insert(attach);
		});
		return 1;
	}

	@Override
	public NoticeVO get(int noticeId) {
		// TODO Auto-generated method stub
		return dao.get(noticeId);
	}

	@Override
	public List<NoticeVO> getList(Critetia cri) {
		// TODO Auto-generated method stub
		return dao.getList(cri);
	}
	
	@Transactional
	@Override
	public int update(NoticeVO vo) {
		attachdao.deleteAll(vo.getNoticeId());
		boolean updateResult = dao.update(vo) == 1;
		if(updateResult && vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			vo.getAttachList().forEach(attach -> {
				attach.setNoticeId(vo.getNoticeId());
				attachdao.insert(attach);
			});
		}
		return 1;
	}
	
	@Transactional
	@Override
	public int delete(int noticeId) {
		attachdao.deleteAll(noticeId);
		
		return dao.delete(noticeId);
	}

	@Override
	public int noticeCount(Critetia cri) {
		// TODO Auto-generated method stub
		return dao.nocieCount(cri);
	}

	@Override
	public int getNextNoticeId() {
		// TODO Auto-generated method stub
		return dao.getNextNoticeId();
	}
	
	public List<NoticeAttachVO> getAttachList(int noticeId){
		
		return attachdao.findByNoticeId(noticeId);
	}

}
