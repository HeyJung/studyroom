package com.jumi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jumi.web.service.RoomService;

@RequestMapping("/rvpage/*")
@Controller
public class ReservationController {
	@Autowired
	private RoomService rservice;
	
	@GetMapping("/calendar")
	public String calendar(Model model) {
		model.addAttribute("roomList", rservice.getList());
		return "/rvpage/calendar";
	}
	

	
	
}
