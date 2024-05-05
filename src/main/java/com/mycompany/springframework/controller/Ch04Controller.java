package com.mycompany.springframework.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch04LoginForm;
import com.mycompany.springframework.dto.Ch04LoginFormValidator;
import com.mycompany.springframework.dto.Ch04MembershipForm;
import com.mycompany.springframework.dto.Ch04MembershipFormValidator;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch04")
public class Ch04Controller {
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		model.addAttribute("chNum" ,"ch04");
		return "ch04/loginForm";
	}
	
	@InitBinder("ch04LoginForm")
	public void ch04LoginFormValidator(WebDataBinder binder) {
		binder.setValidator(new Ch04LoginFormValidator());
	}
	
	@RequestMapping("/login")
	public String login(@Valid Ch04LoginForm loginForm, Errors errors ,Model model) {
		log.info("실행");
		//유효성 검사 실패시 다시 로그인 폼 보여주기
		if(errors.hasErrors()) {
			model.addAttribute("chNum", "ch04");
			return "ch04/loginForm";
		}
		
		//로그인 처리
		return "redirect:/";
	}
	
		//회원가입
		@GetMapping("/membershipForm")
		public String membershipForm(Model model) {
			model.addAttribute("chNum" ,"ch04");
			return "ch04/membershipForm";
		}
		
		@InitBinder("ch04MembershipForm")
		public void ch04MembershipFormValidator(WebDataBinder binder) {
			binder.setValidator(new Ch04MembershipFormValidator());
		}
		
		@RequestMapping("/membership")
		public String member(@Valid Ch04MembershipForm membershipForm, Errors errors ,Model model) {
			log.info("실행");
			//유효성 검사 실패시 다시 가입 폼 보여주기
			if(errors.hasErrors()) {
				model.addAttribute("chNum", "ch04");
				return "ch04/membershipForm";
			}
			
			//가입 처리
			return "redirect:/";
		}
	
}
