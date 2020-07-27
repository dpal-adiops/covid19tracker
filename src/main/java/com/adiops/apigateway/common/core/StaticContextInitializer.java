package com.adiops.apigateway.common.core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adiops.apigateway.dashboard.helper.Covid19DataQueryHelper;
import com.adiops.apigateway.dashboard.repository.Covid19DataRepository;

/**
 * This class is responsible to initialize static classes in its @PostConstruct
 * annotated method.
 * 
 * @author Deepak Pal
 *
 */
@Component
public class StaticContextInitializer {

	@Autowired
	Covid19DataRepository mCovid19DataRepository;
	
	@PostConstruct
	public void init() {		
		Covid19DataQueryHelper.setCovid19DataRepository(mCovid19DataRepository);
	}
}
