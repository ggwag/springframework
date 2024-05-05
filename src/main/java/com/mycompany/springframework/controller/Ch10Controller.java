package com.mycompany.springframework.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.exception.Ch10CustomException;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch10")
public class Ch10Controller {
	@GetMapping("/handlingException1")
	public String handlingException1(String data) { //정상적인 값이 입력되지않았을 때  null
		try {
		if(data.equals("java")) {
			//NullPointerException 발생할 수 있다
			}
		} catch(NullPointerException e) {
			log.info("data가 넘어오지 않았습니다.");
			return "ch10/error500_null";
		}
		return "redirect:/";
	}
	
	@GetMapping("/handlingException2")
	public String handlingException2(String data) {
		if(data.equals("java")) {
			return "ch10/error500_null";
		}
		return "redirect:/";
	}
	
	@GetMapping("/handlingException3")
	public String handlingException3() throws Ch10CustomException {
		if(true) {
			//예외 발생코드 넣기
			throw new Ch10CustomException("XXX 이유 때문에 예외가 발생함");
		}
		return "redirect:/";
	}
	
	@GetMapping("/handlingException4")
	public String handlingException4() throws IOException {
		if(true) {
			//나머지 다른 예외 발생코드 넣기
			throw new IOException("입출력 예외 발생");
		}
		return "redirect:/";
	}
}
