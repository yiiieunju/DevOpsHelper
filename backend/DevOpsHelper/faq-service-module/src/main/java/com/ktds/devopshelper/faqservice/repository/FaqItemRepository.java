package com.ktds.devopshelper.faqservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ktds.devopshelper.faqservice.domain.FaqItem;

public interface FaqItemRepository extends JpaRepository<FaqItem, String>{

}
