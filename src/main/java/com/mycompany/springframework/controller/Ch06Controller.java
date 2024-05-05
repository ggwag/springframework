package com.mycompany.springframework.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch06Cart;
import com.mycompany.springframework.dto.Ch06Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch06")
public class Ch06Controller {
	// 서버내부에서 이동 request범위
	@GetMapping("/forward")
	public String forward(Model model, HttpServletRequest request) { //1모델에 저장하는 방법 2.Http 저장하는 방법
		// 데이터 생성
		Ch06Member member = new Ch06Member();

		member.setMid("spring");
		member.setMname("김자");
		member.setMemail("gamja@naver.com");

		// request 범위에 객체 저장하기----------
		// (forward 할때 까지 계속 사용할수 있는 범위 ) = (응답이 가기 전까지가 request 범위)
		// 응답이 전송되면 request 범위는 사라진다.
		// --------------------------------
		model.addAttribute("chNum", "ch06");
		model.addAttribute("member1", member);
		request.setAttribute("member2", member);

		return "ch06/forward";
	}

	@GetMapping("/redirect")
	public String redirect(HttpSession session) {
		log.info("실행");

		// 데이터 생성
		List<String> items = new ArrayList<>();
		items.add("상품1");
		items.add("상품2");
		items.add("상품3");
		Ch06Cart cart = new Ch06Cart();
		cart.setItems(items);

		// session 범위에 저장
		// 동일한 client(브라우저)가 사용할 수 있는 범위
		session.setAttribute("cart", cart);

		return "redirect:/ch06/sessionData";
	}

	@GetMapping("/sessionData")
	public String sessionData(HttpSession session , Model model) {
		log.info("실행");
		
		//session 범위에 저장된 객체 가져오기
		Ch06Cart cart = (Ch06Cart) session.getAttribute("cart");
		if(cart != null) {
		for(String item : cart.getItems()) {
			log.info(item);
			
			}
		}
		model.addAttribute("chNum", "ch06");
		return "ch06/sessionData";
		
	}
}
