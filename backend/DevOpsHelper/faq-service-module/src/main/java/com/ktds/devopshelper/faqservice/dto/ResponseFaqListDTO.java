package com.ktds.devopshelper.faqservice.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseFaqListDTO {
	private String code;
	private String message;
	private List<FaqItemDTO> faqItems;
}
