package com.jumi.web.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumi.web.dao.ReservationDAO;
import com.jumi.web.domain.EventVO;
import com.jumi.web.domain.ReservedTimeVO;
import com.jumi.web.domain.RvRoomInfoVO;
import com.jumi.web.domain.RvRoomVO;
@Service
public class ReservationServiceImpl implements ReservationService{
	
	private ReservationDAO dao;
	
	@Autowired
	public void setDao(ReservationDAO dao) {
		this.dao = dao;
	}

	@Override
	public int register(RvRoomVO vo) {
		// TODO Auto-generated method stub
		return dao.register(vo);
	}

	@Override
	public RvRoomVO get(int rvId) {
		// TODO Auto-generated method stub
		return dao.get(rvId);
	}

	@Override
	public List<RvRoomVO> getList() {
		// TODO Auto-generated method stub
		return dao.getList();
	}

	@Override
	public List<EventVO> getEventDate(String memId) {
		
		return dao.getEvent(memId);
	}
	
	@Override
	public int update(RvRoomVO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

	@Override
	public int delete(int rvId) {
		// TODO Auto-generated method stub
		return dao.delete(rvId);
	}

	@Override
	public List<ReservedTimeVO> getReservedTime(Date rvDate, int roomId) {
		// TODO Auto-generated method stub
		return dao.getReservedTime(rvDate, roomId);
	}

	@Override
	public List<RvRoomInfoVO> getUserList(String memId) {
		// TODO Auto-generated method stub
		return dao.getUserList(memId);
	}
	
	@Override
	public int payment(int rvId) {

		return dao.payment(rvId);
	}


	
}
