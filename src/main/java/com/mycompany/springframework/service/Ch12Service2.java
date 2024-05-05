package com.mycompany.springframework.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12Service2 {
	private Ch12Service2() {}
		
		public static Ch12Service2 getInstance() { //객체가 생성되어서  리턴되었다 라는 의미
			log.info("실행");
			return new Ch12Service2();
		}
	
}
