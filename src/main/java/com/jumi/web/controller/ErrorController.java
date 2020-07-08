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
			errorMsg = "Http Error Code: 400. �߸��� ��û�Դϴ�";
			break;
		case 401:
			errorMsg = "Http Error Code: 401. ���ٱ����� �����ϴ�";
			break;
		case 404:
			errorMsg = "Http Error Code: 404. �������� �ʴ� �������Դϴ�";
			break;			
		case 500:
			errorMsg = "Http Error Code: 500. ���������� �߻��߽��ϴ�";
			break;	
		default:
			errorMsg = "������ �߻��߽��ϴ�";
			break;
		}
		
		model.addAttribute("errorMsg", errorMsg);
		return "errorPage";
	}
	
	private int getErrorConde(HttpServletRequest request) {
		return (Integer)request.getAttribute("javax.servlet.error.status_code");
	}
}
