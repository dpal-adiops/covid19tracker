package com.adiops.apigateway.docs;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * This class auto configure the swagger implementation.
 * @author Deepak Pal
 *
 */
@Configuration
@EnableSwagger2
@Import(SpringfoxWebMvcConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket api() {
		HashSet<String> consumesAndProduces = new HashSet<String>(Arrays.asList("application/json", "application/xml"));
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(metadata())
				.consumes(consumesAndProduces)
				.produces(consumesAndProduces)
				.pathMapping("/");
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title("Covid19 API")
				.description("Covid19 API Description")
				.version("1.0")
				.contact(new Contact("Deepak", "http://www.adiopsconsulting.com",
						"adiopsconsulting@gmail.com"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.build();
	}
}
