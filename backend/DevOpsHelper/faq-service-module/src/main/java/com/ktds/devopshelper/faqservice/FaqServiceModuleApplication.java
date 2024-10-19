package com.ktds.devopshelper.faqservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//별다른 설정을 하지 않는다면 Spring 은 다른 모듈에 있는 클래스들을 Bean 으로 등록할 수 없습니다.
//scanBasePackages 에 패키지 이름들을 입력해줘서 Component Scan 시에 스캔할 패키지를 지정해야 Spring 이 정상적으로 모든 Bean 들을 등록할 수 있게 됩니다.

@SpringBootApplication
//@ComponentScan(basePackages = {"com.ktds.devopshelper.faqservice"})
public class FaqServiceModuleApplication {
	
	public static void main(String[] args) {
		//System.setProperty("spring.config.name", "bootstrap");
		SpringApplication.run(FaqServiceModuleApplication.class, args);
	}

}
