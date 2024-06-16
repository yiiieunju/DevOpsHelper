package com.ktds.faqnaservice.dto;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@DynamicUpdate
public class FaqItemDTO {
	private String Id;
	private String userId;
	private String devOpsDivCd;
	private String systmCategory;
	private String fstCategory;
	private String title;
	private String content;
	private long retvcount;
	private String regDate;
	private String chgDate;
}
