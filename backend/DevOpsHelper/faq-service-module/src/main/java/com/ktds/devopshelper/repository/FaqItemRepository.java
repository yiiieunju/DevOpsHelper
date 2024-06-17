package com.ktds.devopshelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktds.devopshelper.domain.FaqItem;

public interface FaqItemRepository extends JpaRepository<FaqItem, String>{

}
