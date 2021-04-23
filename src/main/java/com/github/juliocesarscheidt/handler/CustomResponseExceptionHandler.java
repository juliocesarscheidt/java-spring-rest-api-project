package com.github.juliocesarscheidt.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.juliocesarscheidt.exception.ResponseException;
import com.github.juliocesarscheidt.exception.BadRequestException;

@RestControllerAdvice
@RestController
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseException> handleExceptions(Exception ex, WebRequest req) {		
		String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
	            .format(new Date());
		
		ResponseException responseException = new ResponseException(
				formatted, ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ResponseException> handleBadRequestExceptions(Exception ex, WebRequest req) {
		String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
	            .format(new Date());
		
		ResponseException responseException = new ResponseException(
				formatted, ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
	}
}
