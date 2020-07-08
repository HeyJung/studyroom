package com.jumi.web.controller;

import java.sql.Date;

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

import com.jumi.web.domain.CmtPageVO;
import com.jumi.web.domain.Critetia;
import com.jumi.web.domain.NoticeCmtVO;
import com.jumi.web.service.NoticeCmtService;

@RequestMapping("/noticecmt/*")
@RestController
public class NoticeCmtController {
	
	@Autowired
	private NoticeCmtService service;
	
	@PostMapping(value = "/new",
			consumes = "application/json",
			produces = "application/text; charset=utf8")
	public ResponseEntity<String> insert(@RequestBody NoticeCmtVO vo){

		int result = service.insert(vo);
		
		return result == 1
				? new ResponseEntity<String>("댓글이 작성되었습니다.", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/{cmtnoticeid}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
			})
	public ResponseEntity<NoticeCmtVO> get(@PathVariable("cmtnoticeid") int cmtNoticeId){
		return new ResponseEntity<NoticeCmtVO> (service.get(cmtNoticeId), HttpStatus.OK);
	}
	
	@GetMapping(value="/pages/{cmtnoticeid}/{page}",
			produces = {MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<CmtPageVO> getList(
			@PathVariable("page") int page, @PathVariable("cmtnoticeid") int cmtNoticeId){
		Critetia cri = new Critetia(page);
		return new ResponseEntity<CmtPageVO>(service.getListPage(cri, cmtNoticeId), HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
			value="/{cmtnoticeid}",
			consumes = "application/json",
			produces = "application/text; charset=utf8")
	public ResponseEntity<String> update(@PathVariable("cmtnoticeid") int cmtNoticeId, @RequestBody NoticeCmtVO vo){
		//vo.setCmtId(cmtNoticeId);
		Date today = new Date(new java.util.Date().getTime());
		vo.setCmtDate(today);
		return service.update(vo) == 1
				? new ResponseEntity<String>("수정완료!", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/{cmtnoticeid}",
			produces = "application/text; charset=utf8")
	public ResponseEntity<String> delete(@PathVariable("cmtnoticeid") int cmtNoticeId){
		return service.delete(cmtNoticeId) == 1
				? new ResponseEntity<String>("삭제완료!", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	
	
	
	
}
