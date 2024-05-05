package com.mycompany.springframework.exception;

public class Ch10CustomException extends Exception{ //일반예외로 만들기
	public Ch10CustomException() {
		
	}
	
	public Ch10CustomException(String message) {
		super(message);
	}
	
}
