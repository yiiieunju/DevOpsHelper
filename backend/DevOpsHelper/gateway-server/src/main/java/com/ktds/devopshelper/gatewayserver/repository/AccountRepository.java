package com.ktds.devopshelper.gatewayserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktds.devopshelper.gatewayserver.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	
	//account 테이블에 사용자 ID와 토큰 값으로 데이터가 존재하는 지 검색하기 위해 사용하는 함 -> 이 repository로 호출할 함수 생성 필요.
	boolean existsByUserIdAndToken(String userId, String token);
}
