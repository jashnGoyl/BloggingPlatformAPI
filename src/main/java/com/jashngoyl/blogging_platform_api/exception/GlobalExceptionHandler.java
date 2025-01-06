package com.jashngoyl.blogging_platform_api.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex, 
			HttpServletRequest httpServletRequest) {
	    ExceptionResponse response = new ExceptionResponse(
	            HttpStatus.NOT_FOUND.value(),
	            httpServletRequest.getMethod(),
	            "Resource Not Found",
	            ex.getMessage(),
	            LocalDateTime.now(),
	            null
	    );
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex,
			HttpServletRequest httpServletRequest) {

		List<String> errorDetails = ex.getBindingResult().getFieldErrors()
		        .stream()
		        .map(FieldError::getDefaultMessage)
		        .toList(); 

		ExceptionResponse exceptionResponse = new ExceptionResponse(
				HttpStatus.BAD_REQUEST.value(),
				httpServletRequest.getMethod(),
				"Validation Error. Enter valid data", 
				ex.getMessage(), 
				LocalDateTime.now(),
				errorDetails
				);
		
		log.info("Exception Response created: " + exceptionResponse);

		return ResponseEntity.badRequest().body(exceptionResponse);
	}
	
	@ExceptionHandler(Exception.class)
	  public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception,
	      HttpServletRequest request) {

	    int httpStatus = HttpStatus.BAD_REQUEST.value();
	    ExceptionResponse exceptionResponse = new ExceptionResponse(
	        httpStatus,
	        request.getMethod(),
	        "Error",
	        exception.getMessage(),
	        LocalDateTime.now(),
	        null
	    );

	    return ResponseEntity.status(httpStatus).body(exceptionResponse);
	  }

}
