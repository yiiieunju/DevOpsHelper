package com.ktds.devopshelper.commonservice.exception;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ktds.devopshelper.commonservice.dto.ResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice //spring에서 전적으로 Exception에 대한 처리를 진행해주는 클래로 지정해주는 노테이션.
//원래 @ControllerAdvice 어노테이션을 사용해도 되는데, 여기에 ResponseBody와 같이 특정 객체를 json형으로 응답값을
//전달하기 위해 @RestControllerAdvice 어노테이션을 사용한다.
public class ApiExceptionHandler {
	
	//모든 익셉션에 대한 체크 구현
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> Exception(HttpServletRequest request, Exception e) throws Exception{
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();
		responseBuilder.code("500").message(e.getMessage());
		return ResponseEntity.ok(responseBuilder.build());
	}
	
	//유효성 체크에 대한 익셉션 구현
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) throws JSONException{
		
		BindingResult bindingResult = ex.getBindingResult();
		StringBuilder builder = new StringBuilder();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			builder.append("[");
			builder.append(fieldError.getField());
			builder.append("](은)");
			builder.append(fieldError.getDefaultMessage());
			builder.append(" 입력된 값: [");
			builder.append(fieldError.getRejectedValue());
			builder.append("]");
		}
			
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();
		responseBuilder.code("500").message(builder.toString());
		
		return ResponseEntity.ok(responseBuilder.build());
	}
	
	//사용자정의 Api Exception Class 내용 Json으로 처리
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> ApiException(HttpServletRequest request, ApiException e) throws Exception {
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();
		responseBuilder.code("501").message(e.getMessage());
		return ResponseEntity.ok(responseBuilder.build());
	}
}
