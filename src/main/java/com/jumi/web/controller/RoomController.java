package com.jumi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jumi.web.domain.RoomVO;
import com.jumi.web.service.RoomService;

@RequestMapping("/room/*")
@Controller
public class RoomController {
	
	private RoomService service;
	
	@Autowired
	public void setService(RoomService service) {
		this.service = service;
	}
	
	@GetMapping("/insert")
	public String insert() {
		return "/room/insert";
	}
	
	@PostMapping("/insert")
	public String insert(RoomVO vo, RedirectAttributes rttr) {
		if(service.insert(vo) > 0)
			rttr.addFlashAttribute("result", "등록완료되었습니다");
		return "redirect:/room/list";
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		model.addAttribute("list", service.getList());
		return "/room/list";
	}
	
	@GetMapping("/detail")
	public String get(@RequestParam("rId") int rId ,Model model) {
		model.addAttribute("info", service.get(rId));
		return "/room/detail";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("rId") int rId, Model model) {
		model.addAttribute("info", service.get(rId));
		return "/room/update";
	}
	@PostMapping("/update")
	public String update(RoomVO vo, RedirectAttributes rttr) {
		if(service.update(vo) > 0)
			rttr.addFlashAttribute("result", "수정이 완료되었습니다.");
		return "redirect:/room/list";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("rId") int rId, RedirectAttributes rttr) {
		if(service.delete(rId) > 0)
			rttr.addFlashAttribute("result", "삭제가 완료되었습니다.");
		return "redirect:/room/list";
	}
	
	
	
	
	
}
