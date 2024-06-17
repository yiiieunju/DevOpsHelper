package com.ktds.devopshelper.dto;

import java.util.Optional;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseFaqDTO {
	private String code;
	private String message;
	private Optional<FaqItemDTO> faqItem;
}
