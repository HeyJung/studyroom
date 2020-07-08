package com.jumi.web.controller;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jumi.web.domain.EventVO;
import com.jumi.web.domain.ReservedTimeVO;
import com.jumi.web.domain.RvRoomVO;
import com.jumi.web.service.ReservationService;

@RequestMapping("/reservation/*")
@RestController
public class ReservationManagerController {
	
	@Autowired
	private ReservationService service;
	
	@PostMapping(value = "/new",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> register(@RequestBody RvRoomVO vo){
		int result = service.register(vo);
		
		return result == 1
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/{rvId}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
			})
	public ResponseEntity<RvRoomVO> get(@PathVariable("rvId") int rvId){
		return new ResponseEntity<RvRoomVO> (service.get(rvId), HttpStatus.OK);
	}
	
	@GetMapping(value="/myrv/{memId}",
			produces = {MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<EventVO>> getEventDate(@PathVariable("memId") String memId){

		return new ResponseEntity<List<EventVO>>(service.getEventDate(memId), HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
			value="/{rvId}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> update(@PathVariable("rvId") int rvId, @RequestBody RvRoomVO vo){
		vo.setRvId(rvId);
		return service.update(vo) == 1
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/{rvId}",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delete(@PathVariable("rvId") int rvId){
		return service.delete(rvId) == 1
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/{rvDate}/{roomId}",
			produces = {MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<Set<Integer>> getReservedTime(
			@PathVariable("rvDate") Date rvDate, @PathVariable("roomId") int roomId){
		List<ReservedTimeVO> list = service.getReservedTime(rvDate, roomId);
		
		Set<Integer> rvOk = new TreeSet<Integer>();
		
		for(int i=0; i<list.size(); i++) {
			int[] arr = list.get(i).getMin();
			for(int j=0; j<arr.length; j++) {
				rvOk.add(arr[j]);
			}
		}
		
		return new ResponseEntity<Set<Integer>>(rvOk, HttpStatus.OK) ;
	}
	
	
	
	
	
}
