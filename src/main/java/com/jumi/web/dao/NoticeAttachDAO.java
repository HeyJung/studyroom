package com.jumi.web.dao;

import java.util.List;

import com.jumi.web.domain.NoticeAttachVO;

public interface NoticeAttachDAO {
	public void insert(NoticeAttachVO vo);
	public void delete(String uuid);
	public void deleteAll(int noticeId);
	public List<NoticeAttachVO> findByNoticeId(int noticeId);
	public List<NoticeAttachVO> getOldFiles();
}
