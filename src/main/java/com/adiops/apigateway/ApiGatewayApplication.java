package com.adiops.apigateway;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This ia the main boot application class 
 * @author Deepak Pal
 *
 */
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	/**
	 * Add Model Mapper to container to clone Entity to RO 
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	

}
