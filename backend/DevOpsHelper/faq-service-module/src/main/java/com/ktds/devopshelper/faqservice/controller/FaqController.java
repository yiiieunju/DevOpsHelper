package com.ktds.devopshelper.faqservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktds.devopshelper.commonservice.dto.ResponseDTO;
//import com.ktds.devopshelper.faqservice.dto.ResponseDTO;

import com.ktds.devopshelper.faqservice.dto.FaqItemDTO;
import com.ktds.devopshelper.faqservice.dto.ResponseFaqDTO;
import com.ktds.devopshelper.faqservice.dto.ResponseFaqListDTO;
import com.ktds.devopshelper.faqservice.service.FaqService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@OpenAPIDefinition(info = @Info(title = "FAQ 처리요청 API", description ="FAQ 처리요청 API", version = "v1"))
@RestController	//Restful api로 사용하기위한 컨트롤러로 구성시켜주는 어노테이
@RequestMapping(value="faq")	//api의 접근 url패턴을 설정해주는 어노테이션
@Slf4j
@RequiredArgsConstructor
@Validated
@ComponentScan("com.ktds.devopshelper.commonservice")
public class FaqController {
	
	@Autowired
	private final FaqService faqService;
	
	//전체 데이터 Return (Read)
	@RequestMapping(value="/allfaqs") // @PostMapping("path"), RequestMapping 차이?
	public ResponseEntity<ResponseFaqListDTO> retrieveAllFaqItem() {
		ResponseFaqListDTO.ResponseFaqListDTOBuilder responseBuilder = ResponseFaqListDTO.builder();
		List<FaqItemDTO> faqItems = faqService.retrieveAllFaqItem();
		
		log.debug("request retieve item id = {}", faqItems.get(0).getId());
		
		
		responseBuilder.code("200").message("success").faqItems(faqItems);
		
		return ResponseEntity.ok(responseBuilder.build());
	}
	
	//해당 ID데이터 Return (Read)
	@RequestMapping(value="/retrieve/{id}", method=RequestMethod.POST) // @PostMapping("path"), RequestMapping 차이?
	public ResponseEntity<ResponseFaqDTO> retrieveFqaItemById(@PathVariable("id") String id) {
		
		Optional<FaqItemDTO> faqItem = faqService.retrieveFaqItemById(id);

		
		if(!faqItem.isPresent()) {
            throw new IllegalArgumentException(String.format("ID[%s] not found",id));
        }
		
		ResponseFaqDTO.ResponseFaqDTOBuilder responseBuilder = ResponseFaqDTO.builder();
		responseBuilder.code("200").message("success").faqItem(faqItem);
		
		return ResponseEntity.ok(responseBuilder.build());
	}
	
	@Operation(summary = "FAQ 추가 요청", description = "FAQ 등록을 진행할 수 있다.", tags = {"addFAQ"})
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "SUCCESS"),
			@ApiResponse(responseCode = "501", description = "API EXCEPTION")
	})
	@RequestMapping(value="/add", method=RequestMethod.POST) // @PostMapping("path"), RequestMapping 차이?
	public ResponseEntity<ResponseDTO> add(@Valid @RequestBody FaqItemDTO faqItemDTO) throws Exception{
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();
		
		
		//인위적인 에러 체크
//		try {
//			Integer.parseInt("test");
//		}catch(Exception e) {
//			throw new ApiException("test에러~ 문구 ");
//		}
		System.out.print("asdasdasd");
		//insert
		faqService.insertFaqItem(faqItemDTO);
		log.debug("request add item id = {}", faqItemDTO.getId());
		
		responseBuilder.code("200").message("success");
		return ResponseEntity.ok(responseBuilder.build()); 	
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST) // @PostMapping("path"), RequestMapping 차이?
	public ResponseEntity<ResponseDTO> update(@PathVariable("id") String id, @RequestBody FaqItemDTO faqItemDTO) {
		
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();
		
		faqService.updateFaqItemById(id, faqItemDTO);
		log.debug("request update item id = {}", id);
		
		responseBuilder.code("200").message("success");
		return ResponseEntity.ok(responseBuilder.build());
	}
	
	@RequestMapping(value="/delete/{id}") // @PostMapping("path"), RequestMapping 차이?
	public ResponseEntity<ResponseDTO> delete(@PathVariable("id") String id) {
		
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();
		
		//delete
		faqService.deleteFaqItemById(id);
		log.debug("request delete item id = {}", id);
		
		responseBuilder.code("200").message("success");
		return ResponseEntity.ok(responseBuilder.build());
	}
}
