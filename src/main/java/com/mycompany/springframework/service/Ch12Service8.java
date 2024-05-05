package com.mycompany.springframework.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.Ch12Dao3;
import com.mycompany.springframework.dao.Ch12Dao4;
import com.mycompany.springframework.dao.Ch12Dao5;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch12Service8 {
	//field 주입
	//@Autowired //자동적으로 ch12Dao3 객체가 대입된다.
	@Resource
	private Ch12Dao3 ch12Dao3;
	
	private Ch12Dao4 ch12Dao4;
	private Ch12Dao5 ch12Dao5; //setter 추가하기 
	
	
	//constructor 주입   생성자 주입은 반드시  Autowired를 사용해야함
	@Autowired
	public Ch12Service8(Ch12Dao4 ch12Dao4) { //자동적으로 객체가 주입
		log.info("실행");
		this.ch12Dao4 = ch12Dao4; //생성자 주입  반드시 관리객체가 되어야 주입할 수 있다.(조건: xml 이나 @Repository 어노테이션이 불어야함)
	}
	
	//setter 주입 source -> generate getter and setter
	//@Autowired
	@Resource
	public void setCh12Dao5(Ch12Dao5 ch12Dao5) {
		log.info("실행");
		this.ch12Dao5 = ch12Dao5;
	}
}
