package com.ktds.devopshelper.authentication.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktds.devopshelper.authentication.domain.Account;
import com.ktds.devopshelper.authentication.dto.AccountDTO;
import com.ktds.devopshelper.authentication.dto.ResponseAccountDTO;
import com.ktds.devopshelper.authentication.repository.AccountRepository;
import com.ktds.devopshelper.authentication.service.AccountService;
import com.ktds.devopshelper.authentication.util.JWTUtil;
import com.ktds.devopshelper.commonservice.dto.ResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="devopsh/account")
@Slf4j
@RequiredArgsConstructor
public class AuthnticationController {
	private final AccountService accountService;
	
	
	// 1.회원등록 API
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public ResponseEntity<ResponseAccountDTO> join(@Valid @RequestBody AccountDTO accountDTO) throws Exception{
		ResponseAccountDTO.ResponseAccountDTOBuilder responseBuilder = ResponseAccountDTO.builder();
		// user 찾기
		Account account = accountService.selectAccount(accountDTO);
		
		if(account != null) {
			responseBuilder.code("100").message("already join user");
		}
		else {
			accountService.saveAccount(accountDTO, null);
			responseBuilder.code("200").message("success");
		}
		//log
		log.debug("join.account.id = {}", accountDTO.getUserId());
		
		return ResponseEntity.ok(responseBuilder.build());
	}
	
	// 2.token 발급 요청 API 
	@RequestMapping(value="/token", method=RequestMethod.POST)
	public ResponseEntity<ResponseAccountDTO> token(@Valid @RequestBody AccountDTO accountDTO) throws Exception{
		ResponseAccountDTO.ResponseAccountDTOBuilder responseBuilder = ResponseAccountDTO.builder();
		Account account = accountService.selectAccount(accountDTO);
		
		//등록된 회원이 아닌데 토큰정보를 요청한다는게 이상하다.
		if(account == null) {
			responseBuilder.code("101").message("Unknown User");
		}
		else {
			String token = getToken(accountDTO);
			accountService.saveAccount(accountDTO, token);
			responseBuilder.code("200").message("success");
			responseBuilder.token(token);
		}
		//log
		log.debug("token.account.id = {}", accountDTO.getUserId());
		return ResponseEntity.ok(responseBuilder.build());
	}
	
	//3. Token 생성
	private String getToken(AccountDTO accountDTO) {
		
		return JWTUtil.generate(accountDTO);
	}
	
}
