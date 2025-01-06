package com.jashngoyl.blogging_platform_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.Gson;

@Configuration
public class AppConfig {

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
