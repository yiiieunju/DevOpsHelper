package com.ktds.devopshelper.faqservice.dto;

import org.hibernate.annotations.DynamicUpdate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@DynamicUpdate
public class FaqItemDTO {
	@Schema(description = "SEQ", example = "TESTSEQ")
	private String Id;
	
	@Schema(description = "개발운영구분", example = "개발")
	private String devOpsDivCd;
	
	@Schema(description = "카테고리", example = "Nexacro")
	private String systmCategory;
	
	@Schema(description = "태그", example = "계정/권한")
	private String  tag;
	
	@Schema(description = "제목", example = "제목")
	private String title;
	
	@Schema(description = "내용", example = "내용")
	private String content;
	
	@Schema(hidden = true)
	@Positive
	private long retvcount;
	
	@Schema(hidden = true)
	private String regDate;
	
	@Schema(hidden = true)
	private String chgDate;
}
