package com.jumi.web.dao;

import java.sql.Date;
import java.util.List;

import com.jumi.web.domain.EventVO;
import com.jumi.web.domain.ReservedTimeVO;
import com.jumi.web.domain.RvRoomInfoVO;
import com.jumi.web.domain.RvRoomVO;

public interface ReservationDAO {
	public int register(RvRoomVO vo);
	public RvRoomVO get(int rvId);
	public List<RvRoomVO> getList();
	public List<EventVO> getEvent(String memId);
	public int update(RvRoomVO vo);
	public int delete(int rvId);
	
	public List<ReservedTimeVO> getReservedTime(Date rvDate, int roomId);
	public List<RvRoomInfoVO> getUserList(String memId);
	public int payment(int rvId);

}
