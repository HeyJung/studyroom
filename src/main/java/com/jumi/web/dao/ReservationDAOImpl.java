package com.jumi.web.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.jumi.web.domain.EventVO;
import com.jumi.web.domain.ReservedTimeVO;
import com.jumi.web.domain.RvRoomInfoVO;
import com.jumi.web.domain.RvRoomVO;
import com.jumi.web.mapper.EventMapper;
import com.jumi.web.mapper.ReservedTimeMapper;
import com.jumi.web.mapper.RvRoomInfoMapper;
import com.jumi.web.mapper.RvRoomMapper;

@Repository
public class ReservationDAOImpl implements ReservationDAO{
	
	private JdbcTemplate template;
	
	@Autowired
	public ReservationDAOImpl(DriverManagerDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	@Override
	public int register(RvRoomVO vo) {
		String sql ="INSERT INTO rvroom(roomId, memId, rvDate, rvStart, rvEnd) VALUES(?, ?, ?, ?, ?)";

		return template.update(sql, vo.getRoomId(), vo.getMemId(), vo.getRvDate(), vo.getRvStart(), vo.getRvEnd());
	}

	@Override
	public RvRoomVO get(int rvId) {
		String sql = "SELECT * FROM rvroom WHERE rvId = ?";
		return template.queryForObject(sql, new Object[]{rvId}, new RvRoomMapper());
	}

	@Override
	public List<RvRoomVO> getList() {
		
		String sql = "SELECT * FROM rvroom";
		return template.query(sql, new RvRoomMapper());
	}
	
	@Override
	public List<EventVO> getEvent(String memId) {
		//date_format(datetime, '%Y-%m-%d') 
		String sql ="SELECT rvDate, rvId FROM rvroom WHERE memId = ?";
		return template.query(sql, new Object[]{memId}, new EventMapper());
	}
	
	@Override
	public int update(RvRoomVO vo) {
		String sql ="UPDATE rvroom SET roomId = ?, rvDate = ?, rvStart = ?, rvEnd = ? WHERE rvId = ?";
		
		return template.update(sql,
				vo.getRoomId(), vo.getRvDate(), vo.getRvStart(), vo.getRvEnd(), vo.getRvId());
	}

	@Override
	public int delete(int rvId) {
		String sql = "DELETE FROM rvroom WHERE rvId = ?";
		return template.update(sql, rvId);
	}
	@Override
	public List<ReservedTimeVO> getReservedTime(Date rvDate, int roomId) {
		String sql = "SELECT rvStart, rvEnd FROM rvroom WHERE rvDate = ? AND roomId = ?";
		return template.query(sql, new Object[]{rvDate, roomId}, new ReservedTimeMapper());
	}
	@Override
	public List<RvRoomInfoVO> getUserList(String memId) {
		//String sql = "SELECT * FROM rvroom WHERE memID = ? ORDER BY rvDate DESC";
		String sql = "SELECT RV.*, R.rName, R.rPrice FROM rvroom RV INNER JOIN room R ON RV.roomId = R.rId "
				+ "WHERE RV.memId = ? ORDER BY RV.rvDate DESC";
		return template.query(sql, new Object[]{memId}, new RvRoomInfoMapper());
	}
	@Override
	public int payment(int rvId) {
		String sql ="UPDATE rvroom SET isDeposit = 1 WHERE rvId = ?";
		return template.update(sql, rvId);
	}

}
