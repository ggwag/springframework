package com.mycompany.springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.springframework.dao.mybatis.Ch13AccountDao;
import com.mycompany.springframework.dto.Ch15Account;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch15Service {
	//비지니스 로직
	@Autowired
	private Ch13AccountDao accountDao;
	
	public List<Ch15Account> getAccountList() {
		List<Ch15Account> accountList = accountDao.selectAll();
		return accountList; //잔고 기능
	}
	
	//계좌이체 기능
	//출금이 성공하고 입금이 실패할 때 출금도 실패하게 만들도록 하기 위해 트랜잭셔널 어노테이션을 붙임 springframework 선택
	@Transactional 
	public void transfer(int fromAno, int toAno, int amount) {
		//출금 작업 fromAno값을 넣어주면 fromAccount 값을 받음
		Ch15Account fromAccount = accountDao.selectByAno(fromAno);
		if(fromAccount == null) {
			throw new RuntimeException("출금 계좌 없음");
		}
		fromAccount.setBalance(fromAccount.getBalance() - amount); 	//기존 밸런스에서  - 어마운트
		accountDao.updateBalance(fromAccount); //새로 바뀐 밸런스 값으로 나옴
		
		
		//입금 작업
		Ch15Account toAccount = accountDao.selectByAno(toAno);
		if(toAccount == null) { //실제 계좌번호가 없을 때 예외 발생
			throw new RuntimeException("입금 계좌 없음");
		}
		toAccount.setBalance(toAccount.getBalance() + amount); 		//기존 밸런스에서  + 어마운트
		accountDao.updateBalance(toAccount);
	}
		
}
