package com.jumi.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumi.web.dao.RoomDAO;
import com.jumi.web.domain.RoomVO;

@Service
public class RoomServiceImpl implements RoomService{
	
	private RoomDAO dao;
	
	@Autowired
	public void setDao(RoomDAO dao) {
		this.dao = dao;
	}

	@Override
	public int insert(RoomVO vo) {
		// TODO Auto-generated method stub
		return dao.insert(vo);
	}

	@Override
	public RoomVO get(int rId) {
		// TODO Auto-generated method stub
		return dao.get(rId);
	}

	@Override
	public List<RoomVO> getList() {
		// TODO Auto-generated method stub
		return dao.getList();
	}

	@Override
	public int update(RoomVO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

	@Override
	public int delete(int rId) {
		// TODO Auto-generated method stub
		return dao.delete(rId);
	}

}
