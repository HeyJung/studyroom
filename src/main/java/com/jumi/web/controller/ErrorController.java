package com.jumi.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@GetMapping("/errors")
	public String rederErrorPage(Model model, HttpServletRequest request) {
		
		String errorMsg ="";
		int httpErrorConde = getErrorConde(request);

		switch (httpErrorConde) {
		case 400:
			errorMsg = "Http Error Code: 400. 잘못된 요청입니다";
			break;
		case 401:
			errorMsg = "Http Error Code: 401. 접근권한이 없습니다";
			break;
		case 404:
			errorMsg = "Http Error Code: 404. 존재하지 않는 페이지입니다";
			break;			
		case 500:
			errorMsg = "Http Error Code: 500. 서버에러가 발생했습니다";
			break;	
		default:
			errorMsg = "에러가 발생했습니다";
			break;
		}
		
		model.addAttribute("errorMsg", errorMsg);
		return "errorPage";
	}
	
	private int getErrorConde(HttpServletRequest request) {
		return (Integer)request.getAttribute("javax.servlet.error.status_code");
	}
}
