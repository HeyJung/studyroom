package com.jumi.web.service;

import java.sql.Date;
import java.util.List;

import com.jumi.web.domain.EventVO;
import com.jumi.web.domain.ReservedTimeVO;
import com.jumi.web.domain.RvRoomInfoVO;
import com.jumi.web.domain.RvRoomVO;

public interface ReservationService {
	public int register(RvRoomVO vo);
	public RvRoomVO get(int rvId);
	public List<RvRoomVO> getList();
	public List<EventVO> getEventDate(String memId);
	public int update(RvRoomVO vo);
	public int delete(int rvId);
	
	public List<ReservedTimeVO> getReservedTime(Date rvDate, int roomId);
	public List<RvRoomInfoVO> getUserList(String memId);
	public int payment(int rvId);
}
