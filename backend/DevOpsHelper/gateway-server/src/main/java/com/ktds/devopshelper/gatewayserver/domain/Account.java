package com.ktds.devopshelper.gatewayserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//domain에 table 정보 mapping
@Entity
@Table(name="ACCOUNT")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	@Id
	@Column(name = "USER_ID", length=30)
	private String userId;
	
	@Column(name = "PASS_WD", length = 100)
	private String password;
	
	@Column(name = "TOKEN", length = 500)
	private String token;
}
