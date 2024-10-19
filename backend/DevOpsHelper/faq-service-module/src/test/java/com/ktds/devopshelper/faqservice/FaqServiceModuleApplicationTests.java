package com.ktds.devopshelper.faqservice;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktds.devopshelper.faqservice.controller.FaqController;
import com.ktds.devopshelper.faqservice.dto.FaqItemDTO;
import com.ktds.devopshelper.faqservice.service.FaqService;

import lombok.extern.slf4j.Slf4j;

//@SpringBootTest	
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Slf4j
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
class FaqServiceModuleApplicationTests {
	
	@Autowired
	private FaqService faqService;
	
	@Autowired
	MockMvc mvc;
	
	@BeforeEach
	public void setup()
	{
		mvc = MockMvcBuilders.standaloneSetup(new FaqController(faqService))
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.build();
	}
	
	@Test
	@DisplayName("Faq 등록 테스")
	void test() throws Exception{
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			
			log.info(objectMapper.writeValueAsString(getTestFaq()) + "");
			
			mvc.perform(post("/faq/add")
					.contentType(MediaType.APPLICATION_JSON)
					.header("userId", "admin")
					.accept(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(getTestFaq())))
					.andDo(print()) // 결과를 print. MockMvcBuilders의 alwaysDo(print())로 대체 가능
					.andExpect(status().isOk()); // 호출 결과값이 OK가 나오면 정상처리
		}catch(Exception e) {
			fail(ExceptionUtils.getStackTrace(e));
		}
	}
	
	private FaqItemDTO getTestFaq() {
		FaqItemDTO faqDTO = new FaqItemDTO();
		faqDTO.setId("1");
		faqDTO.setTitle("FAQ 테스트 ");
		faqDTO.setContent("이거이건 먼가요?");
		faqDTO.setRetvcount(1);
		return faqDTO;
	}

	//@Test
	//void contextLoads() {	//테스트 로직 구 
	//}

}
