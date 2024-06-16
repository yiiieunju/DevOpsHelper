package com.ktds.faqnaservice.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.faqnaservice.domain.FaqItem;
import com.ktds.faqnaservice.dto.FaqItemDTO;
import com.ktds.faqnaservice.repository.FaqItemRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {
	private final FaqItemRepository faqItemRepository;
	
	public List<FaqItemDTO> retrieveAllFaqItem(){
		List<FaqItem> faqList = faqItemRepository.findAll();
		
		ModelMapper mapper = new ModelMapper();
		List<FaqItemDTO> res = faqList.stream().map(faq -> mapper.map(faq, FaqItemDTO.class)).collect(Collectors.toList());
		
		return res;
		
	}
	
	public Optional<FaqItemDTO> retrieveFaqItemById(String id) {
		FaqItem faqItem =  faqItemRepository.findById(id).get();
	
		ModelMapper mapper = new ModelMapper();
		FaqItemDTO tmp = mapper.map(faqItem, FaqItemDTO.class);;
		
		Optional<FaqItemDTO> res = Optional.of(mapper.map(faqItem, FaqItemDTO.class));
		
		return res;
		
	}
	
	//insert
	public void insertFaqItem(FaqItemDTO faqItemDTO) {
		SimpleDateFormat form = new SimpleDateFormat("yyyyyMMddHHmmss");
		String date = form.format(new Date());
		
		FaqItem item = FaqItem.builder()
				.id(faqItemDTO.getId())
				.userId(faqItemDTO.getUserId())
				.devOpsDivCd(faqItemDTO.getDevOpsDivCd())
				.systmCategory(faqItemDTO.getSystmCategory())
				.fstCategory(faqItemDTO.getFstCategory())
				.title(faqItemDTO.getTitle())
				.content(faqItemDTO.getContent())
				.retvCount(faqItemDTO.getRetvcount())
				.regDate(date)
				.chgDate(date)
				.build();
		faqItemRepository.save(item);
	}
	
	//update
	@Transactional
	public void updateFaqItemById(String id, FaqItemDTO faqItemDTO) {
		SimpleDateFormat form = new SimpleDateFormat("yyyyyMMddHHmmss");
		String date = form.format(new Date());
		
		FaqItem item= faqItemRepository.findById(faqItemDTO.getId()).orElseThrow(() 
				-> new IllegalArgumentException("해당 게시이 없습니다. id= " + id));
		item.update(faqItemDTO.getUserId(),faqItemDTO.getSystmCategory(),faqItemDTO.getFstCategory(),faqItemDTO.getTitle(),
				faqItemDTO.getContent(), faqItemDTO.getRetvcount(),faqItemDTO.getRegDate()
				,date);
	}
	
	//delete
	@Transactional
	public void deleteFaqItemById(String id) {
		
		FaqItem item = faqItemRepository.findById(id).orElseThrow(() -> 
			new IllegalArgumentException("해당게시물이 없습니다."+id));
		
		faqItemRepository.delete(item);
	}
	
	
	
	
}
