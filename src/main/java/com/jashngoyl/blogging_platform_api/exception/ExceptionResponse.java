package com.jashngoyl.blogging_platform_api.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExceptionResponse(
		
		@JsonProperty("status_code")
		int statusCode,
		
		@JsonProperty("http_method")
		String httpMethod,
		
		String message,
		
		@JsonProperty("backend_message")
		String backendMessage,
		
		LocalDateTime timestamp,
		
		List<String> details
		
		) {

}
