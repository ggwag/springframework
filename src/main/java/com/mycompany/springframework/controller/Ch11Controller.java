package com.mycompany.springframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch11")
public class Ch11Controller {
	@GetMapping("/joinForm")
	public String joinForm() {
		return "ch11/joinForm";
	}
	
}
