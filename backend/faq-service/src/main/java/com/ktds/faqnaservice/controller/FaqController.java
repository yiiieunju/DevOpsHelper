package com.ktds.faqnaservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktds.faqnaservice.domain.FaqItem;
import com.ktds.faqnaservice.dto.FaqItemDTO;
import com.ktds.faqnaservice.dto.ResponseDTO;
import com.ktds.faqnaservice.dto.ResponseFaqDTO;
import com.ktds.faqnaservice.dto.ResponseFaqListDTO;

import com.ktds.faqnaservice.dto.ResponseFaqListDTO.ResponseFaqListDTOBuilder;
import com.ktds.faqnaservice.service.FaqService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController	//Restful api로 사용하기위한 컨트롤러로 구성시켜주는 어노테이
@RequestMapping(value="faq")	//api의 접근 url패턴을 설정해주는 어노테이션
@Slf4j
@RequiredArgsConstructor
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
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST) // @PostMapping("path"), RequestMapping 차이?
	public ResponseEntity<ResponseDTO> add(@RequestBody FaqItemDTO faqItemDTO) {
		
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();
		
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
