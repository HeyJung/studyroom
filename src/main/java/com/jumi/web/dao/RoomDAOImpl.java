package com.jumi.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.jumi.web.domain.RoomVO;
import com.jumi.web.mapper.RoomMapper;

@Repository
public class RoomDAOImpl implements RoomDAO{

	private JdbcTemplate template;
	
	@Autowired
	public RoomDAOImpl(DriverManagerDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(RoomVO vo) {
		String sql = "INSERT INTO ROOM(rName, rPersonnel, rPrice) VALUES (?, ?, ?)";
		return template.update(sql, vo.getrName(), vo.getrPersonnel(), vo.getrPrice());
	}

	@Override
	public RoomVO get(int rId) {
		String sql = "SELECT * FROM ROOM WHERE rId = ?";
		return template.queryForObject(sql, new Object[]{rId}, new RoomMapper());
	}

	@Override
	public List<RoomVO> getList() {
		String sql = "SELECT * FROM ROOM";
		return template.query(sql, new RoomMapper());
	}

	@Override
	public int update(RoomVO vo) {
		String sql ="UPDATE ROOM SET rName = ?, rPersonnel = ?, rPrice = ? WHERE rId = ?";
		return template.update(sql, vo.getrName(), vo.getrPersonnel(), vo.getrPrice(), vo.getrId());
	}

	@Override
	public int delete(int rId) {
		String sql = "DELETE FROM ROOM WHERE rId = ?";
		return template.update(sql, rId);
	}


	
}
