package com.jumi.web.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jumi.web.domain.Critetia;
import com.jumi.web.domain.NoticeAttachVO;
import com.jumi.web.domain.NoticeVO;
import com.jumi.web.domain.PageVO;
import com.jumi.web.service.NoticeService;

@RequestMapping("/notice/*")
@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@GetMapping("/insert")
	public String insert() {
		
		return "/notice/insert";
	}
	
	@PostMapping("/insert")
	public String insert(NoticeVO vo, RedirectAttributes rttr) {
		if(vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> System.out.println(attach));
		}
		int result = service.insert(vo);
		if(result > 0)
			rttr.addFlashAttribute("result", "등록이 완료되었습니다.");
		return "redirect:/notice/list";
	}
	
	@GetMapping("/list")
	public String getList(Model model, Critetia cri) {
		if(cri.getField() == null || cri.getField() == "")
			cri.setField("noticeTitle");
		if(cri.getKeyword() == null)
			cri.setKeyword("");
		
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageVO(service.noticeCount(cri), cri));
		return "/notice/list";
	}
	
	@GetMapping("/detail")
	public String get(@RequestParam("noticeId") int noticeId, @ModelAttribute("cri") Critetia cri, Model model) {
		model.addAttribute("n", service.get(noticeId));
		
		return "/notice/detail";
	}
	
	@GetMapping("/update")
	public String updatePage(@RequestParam("noticeId") int noticeId, @ModelAttribute("cri") Critetia cri, Model model) {
		model.addAttribute("n", service.get(noticeId));
		
		return "/notice/update";
	}
	@PostMapping("/update")
	public String update(NoticeVO vo, Critetia cri, RedirectAttributes rttr) {
		int result = service.update(vo);
		if(result > 0)
			rttr.addFlashAttribute("result", "수정이 완료되었습니다.");	
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("noticeId", vo.getNoticeId());
		
		return "redirect:/notice/detail";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("noticeId") int noticeId,  Critetia cri, RedirectAttributes rttr) {
		List<NoticeAttachVO> attachList = service.getAttachList(noticeId);
		
		int result = service.delete(noticeId);
		
		if(result > 0) deleteFiles(attachList);
		if(result > 0)
			rttr.addFlashAttribute("result", "삭제가 완료되었습니다.");			
		rttr.addAttribute("page", cri.getPage());
		return "redirect:/notice/list";
	}
	
	@GetMapping(value="/getAttachList", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<NoticeAttachVO>> getAttachList(int noticeId){
		
		return new ResponseEntity<List<NoticeAttachVO>>(service.getAttachList(noticeId), HttpStatus.OK);
	}
	
	private void deleteFiles(List<NoticeAttachVO> attachList) {
		if(attachList == null|| attachList.size() == 0) return;
		
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("C:\\upload\\" + attach.getUploadPath() + "\\" + attach.getUuid() 
					+ "-" + attach.getFileName());
				Files.deleteIfExists(file);
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:\\upload\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() 
					+ "-" + attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	
}
