package com.jumi.web.dao;

import java.util.List;

import com.jumi.web.domain.RoomVO;

public interface RoomDAO {
	public int insert(RoomVO vo);
	public RoomVO get(int rId);
	public List<RoomVO> getList();
	public int update(RoomVO vo);
	public int delete(int rId);
}
