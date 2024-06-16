package com.ktds.faqnaservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktds.faqnaservice.domain.FaqItem;
import com.ktds.faqnaservice.dto.FaqItemDTO;

@Repository
public interface FaqItemRepository extends JpaRepository<FaqItem, String>{ // 제네릭 타입: <엔티티 클래스, 엔티티클래스의 기본키>
//	public List<FaqItem> retrieveAllFaqItem();
//	public Optional<FaqItem> retrieveFaqItemById(String id);
//	public void insertFaqItem(FaqItemDTO faqItemDTO);
//	public void updateFaqItem(FaqItemDTO faqItemDTO);	//수정하
//	public void deleteFaqItemById(String id);
}
