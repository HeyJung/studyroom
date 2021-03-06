package com.jumi.web.service;

import java.util.List;

import com.jumi.web.domain.Critetia;
import com.jumi.web.domain.NoticeAttachVO;
import com.jumi.web.domain.NoticeVO;

public interface NoticeService {
	public int insert(NoticeVO vo);
	public NoticeVO get(int noticeId);
	public List<NoticeVO> getList(Critetia cri);
	public int update(NoticeVO vo);
	public int delete(int noticeId);
	
	public int noticeCount(Critetia cri);
	public int getNextNoticeId();
	public List<NoticeAttachVO> getAttachList(int noticeId);
}
