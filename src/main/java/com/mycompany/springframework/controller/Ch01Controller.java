package com.mycompany.springframework.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ch01")
@Slf4j
public class Ch01Controller {
	//private static final Logger LOGGER = LoggerFactory.getLogger(Ch01Controller.class);
	//상수정의 slf4j 를 쓰지않을 때
	
	@RequestMapping("/content")
	public String content(Model model) {
		log.info("content()실행");
		
		model.addAttribute("chNum", "ch01");
		return "ch01/content";
	}
}
