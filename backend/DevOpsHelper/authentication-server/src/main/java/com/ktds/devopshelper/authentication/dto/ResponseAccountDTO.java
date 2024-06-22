package com.ktds.devopshelper.authentication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)	
//token 값이 안담아졌을때는 출력되지 않도록 
//token이 null인 경우에는 response에 나오지 않도록 클래스에 어노테이션 추
public class ResponseAccountDTO {
	private String code;
	private String message;
	private String token;
}

