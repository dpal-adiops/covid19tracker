package com.adiops.apigateway.dashboard.repository;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.adiops.apigateway.common.response.RestException;
import com.adiops.apigateway.dashboard.service.Covid19DataService;

@Component
public class Covid19DataRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Covid19DataRepositoryCommandLineRunner.class);
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private Covid19DataService mCovid19DataService;
	
	@Override
	public void run(String... args) throws Exception {
		 Resource resource = resourceLoader.getResource("classpath:covid19_india_data.csv");
		 InputStream inputStream = resource.getInputStream();
		 try {
			mCovid19DataService.importCSV(inputStream);
		} catch (RestException e) {
			log.error(e.getMessage());
		}
	}

}
