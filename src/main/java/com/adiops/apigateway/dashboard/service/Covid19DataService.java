package com.adiops.apigateway.dashboard.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.adiops.apigateway.common.response.RestException;
import com.adiops.apigateway.dashboard.resourceobject.Covid19DataRO;

/**
 * This is service for Covid19Data
 * @author Deepak Pal
 *
 */
public interface Covid19DataService {

	List<Covid19DataRO> getCovid19DataROs();
	
	List<String> getCovid19DataStates();
	
	void importCSV(MultipartFile file) throws RestException;
	void importCSV(InputStream is) throws RestException;
	List<Covid19DataRO> getCovid19DataByState(String state);

	List<Covid19DataRO> getCovid19CountryData();

	Covid19DataRO getLatestCovid19DataByState(String state);

}
