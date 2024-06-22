package com.ktds.devopshelper.authentication.util;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ktds.devopshelper.authentication.dto.AccountDTO;

public class JWTUtil {
	public static String generate(AccountDTO accountDTO) {
		Date now = new Date();
		return JWT.create()
				.withSubject(accountDTO.getUserId())				//token 명
				.withExpiresAt(DateUtils.addSeconds(now, 10))		//token 유효기
				.withIssuedAt(now)									//token 발행
				.sign(Algorithm.HMAC512("secretJey"));				//token 암호화 알고리즘 
	}
}
