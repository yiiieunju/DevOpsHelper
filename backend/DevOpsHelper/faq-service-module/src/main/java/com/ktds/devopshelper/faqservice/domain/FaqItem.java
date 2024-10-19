package com.ktds.devopshelper.faqservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="FAQ_BAS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FaqItem {
	@Id
	@Column(name= "ID", length = 100)
	private String id;
	
	@Column(name= "USER_ID", length = 100)
	@NonNull
	private String userId;
	
	@Column(name= "DEVOPS_DIV_CD", length = 200)
	private String devOpsDivCd;
	
	@Column(name= "SYSTEM_CATEGORY", length = 200)
	private String systmCategory;
	
	@Column(name= "TAG", length = 200)
	private String tag;
	
	@Column(name= "TITLE", length = 1000)
	@NonNull
	private String title;

	@Column(name= "CONTENT", length = 5000)
	@NonNull
	private String content;
	
	@Column(name= "RETV_COUNT")
	private long retvCount;
	
	@Column(name= "REG_DTS", length = 25)
	@NonNull
	private String regDate;
	
	@Column(name= "CHG_DTS", length = 25)
	private String chgDate;
	
	public void update(String systmCategory, String tag, String title, String content, long retvCount, String regDate, String chgDate) {
        this.systmCategory = systmCategory;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.retvCount = retvCount;
        this.chgDate = chgDate;
	}
	
}
