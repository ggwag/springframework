package com.mycompany.springframework.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.Ch12DaoInterface;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch12Service9 {
	/*//field 주입
	//@Autowired @Qualifier("ch12Dao6") private Ch12DaoInterface field1; //필드주입  구현객체가 대입된다.
	@Resource(name="ch12Dao7") private Ch12DaoInterface field2; //현업에서는 resurce를 많이 씀 
	
	@Autowired
	public Ch12Service9(Ch12DaoInterface field1) { //ch8은 타입의 관리객체가 하나밖에 없기 때문에 주입이 됐다. 여기는 인터페이스타입 객체가 2개라 qualify해야함 
		
	}

	@Resource(name="ch12Dao7")
	public void setField2(Ch12DaoInterface field2) {
		this.field2 = field2;
	}
	*/
	
}
