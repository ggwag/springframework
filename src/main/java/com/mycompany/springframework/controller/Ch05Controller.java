package com.mycompany.springframework.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch05")
public class Ch05Controller {
/*	@GetMapping("/header")
	public String header(Model model ,  
			@RequestHeader("User-Agent") String userAgent) {
		log.info("실행");
		log.info("User-Agent: " + userAgent);
		
		//브라우저의 종류 알아내기
		if(userAgent.contains("Edg")) {
			model.addAttribute("browser", "Edge");
		} else {
			model.addAttribute("browser", "Chrome");
		}
		
		model.addAttribute("chNum", "ch05");
		return "ch05/header";
	}*/
	
	@GetMapping("/header")
	public String header(Model model , HttpServletRequest request)		{
		log.info("실행");
		
		//브라우저의 종류 알아내기
		String userAgent = request.getHeader("user-agent"); // @RequestHeader("User-Agent")를 하지않는 방법
		
		if(userAgent.contains("Edg")) {
			model.addAttribute("browser", "Edge");
		} else {
			model.addAttribute("browser", "Chrome");
		}
		
		//브라우저가 실행하는 컴퓨터의 IP 주소 알아내기
		String clientIp = request.getRemoteAddr();
		model.addAttribute("clientIp", clientIp);
		
		model.addAttribute("chNum", "ch05");
		return "ch05/header";
	}
	
	@GetMapping("/createCookie")
	public String createCookie(Model model , HttpServletResponse response) {
		//Cookie 생성하기
		Cookie cookie = new Cookie("useremail","summer@naver.com");
		
		//Cookie를 응답 HTTP 에 포함시키기 무조건 response 사용
		response.addCookie(cookie);
		
		model.addAttribute("chNum", "ch05");
		return "redirect:/";
	}
	 //쿠키 이름이 같으면 ("userEmail") 을 value 옆에 안써도 된다.
	@GetMapping("/readCookie")
	public String readCookie(@CookieValue String useremail , Model model) {
		log.info("usereamil: " + useremail);
		
		model.addAttribute("chNum", "ch05");
		model.addAttribute("useremail", useremail);
		return "ch05/cookie";
		
	}
	
}