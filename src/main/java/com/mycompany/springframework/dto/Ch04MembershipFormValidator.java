package com.mycompany.springframework.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04MembershipFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		return Ch04MembershipForm.class.isAssignableFrom(clazz); //
	}

	@Override
	public void validate(Object target, Errors errors) {
		Ch04MembershipForm membershipForm = (Ch04MembershipForm) target;
		
		//아이디 검사
		String mid = membershipForm.getMid();
		if(mid == null || mid.equals("")) {
			errors.rejectValue("mid", null, "아이디는 반드시 입력해야 합니다."); //
		} else if(mid.length() < 6 || mid.length() > 12) {
			errors.rejectValue("mid", null, "아이디는 6자 이상 12자 이하로 입력해야 합니다."); //
		}
		
		//비밀번호 검사
		String mpassword = membershipForm.getMpassword();
		String pattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}";
		if(mpassword == null || mpassword.equals("")) {
			errors.rejectValue("mpassword", null, "비밀번호는 반드시 입력해야 합니다."); //
		} else if(mpassword.length() < 8 || mpassword.length() > 15) {
			errors.rejectValue("mpassword", null, "비밀번호는 8자 이상 15자 이하로 입력해야 합니다."); //
		} else if(!Pattern.matches(pattern, mpassword)) {
			errors.rejectValue("mpassword", null, "비밀번호는 알파벳 대소문자 및 숫자를 포함해야 합니다."); //
		}
		
		//이름 검사
		String mname = membershipForm.getMname();
		String patternName = "[가-힣]{2,5}";
		if(mname == null || mname.equals("")) {
			errors.rejectValue("mname", null, "이름은 반드시 입력해야 합니다."); //
		} else if(!Pattern.matches(patternName, mname)) {
			errors.rejectValue("mname", null, "이름을 다시 입력해주세요."); //
		}
	
		
		//전화번호 검사
		String mphone = membershipForm.getMphone();
		String patternPhone = "(010|011)\\d{3,4}\\d{4}";
		if(mphone == null || mphone.equals("")) {
			errors.rejectValue("mphone", null, "전화번호는 필수 입력 사항입니다."); //
		} else if(!Pattern.matches(patternPhone, mphone)) {
			errors.rejectValue("mphone", null, "전화번호는 - 없이 입력해주세요."); //
		}
		
		//이메일 검사
		String memail = membershipForm.getMemail();
		String patternEmail = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+";
		if(memail == null || memail.equals("")) {
			errors.rejectValue("memail", null, "이메일은 필수 입력 사항입니다."); //
		} else if(!Pattern.matches(patternEmail, memail)) {
			errors.rejectValue("memail", null, "이메일 형식에 맞게 입력해야 합니다."); //
		}
		
		
		
	}
	
	
	
}
