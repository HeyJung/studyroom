package com.jumi.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jumi.web.domain.MemberVO;
import com.jumi.web.service.MemberService;
import com.jumi.web.service.ReservationService;
import com.jumi.web.service.RoomService;

@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	@Autowired
	private ReservationService rvservice;
	
	@Autowired
	private RoomService roomservice;
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("memberVO", new MemberVO());
		return "/member/register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("memberVO") @Valid MemberVO vo, BindingResult result,RedirectAttributes rttr) {
		if(result.hasErrors()) {
			return "/member/register";
		} else {
			int rs = service.register(vo);
			if(rs > 0)
				rttr.addFlashAttribute("result", "회원가입이 완료되었습니다.");
		}
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "/member/login";
	}
	
	@PostMapping("/login")
	public String login(MemberVO vo, RedirectAttributes rttr, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String login = null;
		try {
			login = service.login(vo);
			
		} catch (EmptyResultDataAccessException e) {
			
			session.setAttribute("memId", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/login";
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		session.setAttribute("memId", login);
		rttr.addFlashAttribute("msg", true);

		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/member/login";
	}
	
	@GetMapping("/mypage/update")
	public String update(Model model, HttpSession session) {
		String memId = (String) session.getAttribute("memId");
		model.addAttribute("info", service.get(memId));
		return "/member/mypage/update";
	}
	
	@PostMapping("/mypage/update")
	public String update(MemberVO vo, RedirectAttributes rttr, @RequestParam("memPwBefore") String memPwBef, HttpSession session) {
		String memId = (String) session.getAttribute("memId");
		String password = service.password(memId);
		//트랜잭션 처리해야하나?(1. 기존비밀번호 확인 -> 성공하면 업데이트..?아닌가)
		if(!password.equals(memPwBef)) {
			rttr.addFlashAttribute("msg", "기존 비밀번호를 잘못 입력하셨습니다");
			return "redirect:/member/mypage/update";
		}
		
		int result = service.update(vo);
		if(result > 0)
			rttr.addFlashAttribute("update", "업데이트가 완료되었습니다.");
		return "redirect:/member/mypage/home";
	}
	
	@GetMapping("/mypage/home")
	public String mypage(Model model, HttpSession session) {
		String memId = (String) session.getAttribute("memId");
		model.addAttribute("info", service.get(memId));
		model.addAttribute("myrv", rvservice.getUserList(memId));

		return "/member/mypage/home";
	}
	
	@PostMapping("/mypage/delete")
	public String delete(@RequestParam("memId") String memId, HttpSession session) {
		service.delete(memId);
		session.invalidate();
		return "redirect:/";
	}
	
	@ResponseBody
	@GetMapping(value="/register/idChk")
	public int idChk(@RequestParam("inputId") String inputId){
		if(inputId.length() <3 || inputId.length() >10) {
			return 1;
		}
		int rs = service.idChk(inputId);

		return rs;
	}
	
	@PostMapping("/mypage/payment")
	public String payment(RedirectAttributes rttr, String memId, int rvId, int payment) {
		int result =  service.payment(memId, payment, rvId);
		if(result == -1) rttr.addFlashAttribute("paymentResult", "금액이 부족합니다.");
		
		else if(result == 1) rttr.addFlashAttribute("paymentResult", "결제가 완료되었습니다");
		return "redirect:/member/mypage/home";
	}
	
	
	
	
	
}
