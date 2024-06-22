package com.ktds.devopshelper.authentication.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ktds.devopshelper.authentication.domain.Account;
import com.ktds.devopshelper.authentication.dto.AccountDTO;
import com.ktds.devopshelper.authentication.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	private final AccountRepository accountRepository;
	
	//1. 계정조회
		 public Account selectAccount(AccountDTO accountDTO) {
			 // input으로 들어온 accountDTO로 제 DB에같은게 있는 지 check
			 Optional<Account> optional = accountRepository.findById(accountDTO.getUserId());
			 
			 //DB에 존재하는 userId라
			 if(optional.isPresent()) {
				 Account account = optional.get();
				 //에 있는 패스워드 가져와서 input으로 들어온 accountDTO.password값이랑 비
				 if(account.getPassword().equals(accountDTO.getPassword())) {
					 return account;
				 }
			 }
			 return null;
		 }
		 
		//2. 계정정보 저장
		public void saveAccount(AccountDTO accountDTO, String token) {
			accountRepository.save(Account.builder().userId(accountDTO.getUserId())
													.password(accountDTO.getPassword())
													.token(token)
													.build());
		}
}
