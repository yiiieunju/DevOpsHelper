package com.ktds.devopshelper.gatewayserver.service;

import org.springframework.stereotype.Service;

import com.ktds.devopshelper.gatewayserver.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	private final AccountRepository accountRepository;
	
	// 토큰과 사용자ID를 통해 row여부 조회
	public boolean existsByUserIdAndToken(String userId, String token) {
		return accountRepository.existsByUserIdAndToken(userId, token);
	}
}
