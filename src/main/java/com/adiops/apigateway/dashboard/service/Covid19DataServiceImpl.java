package com.adiops.apigateway.dashboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adiops.apigateway.common.helper.CSVHelper;
import com.adiops.apigateway.common.response.RestException;
import com.adiops.apigateway.dashboard.entity.Covid19Data;
import com.adiops.apigateway.dashboard.entity.Covid19DataEntity;
import com.adiops.apigateway.dashboard.helper.Covid19DataQueryHelper;
import com.adiops.apigateway.dashboard.repository.Covid19DataRepository;
import com.adiops.apigateway.dashboard.resourceobject.Covid19DataRO;

/**
 * This is the implementation class for Covid19Data responsible for all the CRUD operations and other business related operations.
 * @author Deepak Pal
 *
 */
@Service
public class Covid19DataServiceImpl implements Covid19DataService {

	@Autowired
	Covid19DataRepository mCovid19StateRepository;

	@Autowired
	private ModelMapper mModelMapper;

	/**
	 *
	 * fetch list of Covid19Data
	 */
	@Override
	public List<Covid19DataRO> getCovid19DataROs() {
		List<Covid19DataRO> tCovid19StateROs = mCovid19StateRepository.findAll().stream()
				.map(entity -> mModelMapper.map(entity, Covid19DataRO.class)).collect(Collectors.toList());
		return tCovid19StateROs;
	}

	/**
	 * upload file to DB
	 */
	@Override
	public void importCSV(MultipartFile file) throws RestException {
		try {
			List<Covid19DataEntity> tutorials = CSVHelper.csvToPOJOs(file.getInputStream(), Covid19DataEntity.class);
			mCovid19StateRepository.deleteAll();
			mCovid19StateRepository.saveAll(tutorials);
		} catch (IOException e) {
			throw new RestException(RestException.ERROR_STATUS_BAD_REQUEST,
					"fail to store csv data: " + e.getMessage());
		}
	}

	/**
	 * upload stream to DB
	 */
	@Override
	public void importCSV(InputStream is) throws RestException {
		try {
			List<Covid19DataEntity> tutorials = CSVHelper.csvToPOJOs(is, Covid19DataEntity.class);
			mCovid19StateRepository.saveAll(tutorials);
		} catch (Exception e) {
			throw new RestException(RestException.ERROR_STATUS_BAD_REQUEST,
					"fail to store csv data: " + e.getMessage());
		}
	}

	/**
	 * fetch list of states
	 */
	@Override
	public List<String> getCovid19DataStates() {
		return mCovid19StateRepository.retriveStateList();
	}

	/**
	 *
	 *get list of  regions
	 */
	@Override
	public List<Covid19DataRO> getCovid19DataByState(String state) {
		List<? extends Covid19Data> Covid19DataRows = Covid19DataQueryHelper.getStateCovid19Data(state);
		List<Covid19DataRO> tCovid19StateROs = Covid19DataRows.stream()
				.map(entity -> mModelMapper.map(entity, Covid19DataRO.class)).collect(Collectors.toList());
		Covid19DataRO tCovid19DataRO=getLatestCovid19DataByState(state);
		tCovid19StateROs.add(tCovid19DataRO);
		return tCovid19StateROs;
	}
	
	/**
	 *
	 *get latest region covid information
	 */
	@Override
	public Covid19DataRO getLatestCovid19DataByState(String state) {
		Covid19DataEntity entity=mCovid19StateRepository.findFirstByStateOrderByRecordDateDesc(state);
		Covid19DataRO tCovid19DataRO=mModelMapper.map(entity, Covid19DataRO.class);
		tCovid19DataRO.setLatest(true);
		return tCovid19DataRO;
	}
	
	/**
	 * get country data
	 */
	@Override
	public List<Covid19DataRO> getCovid19CountryData() {
		Map<String,Covid19DataRO> countryMonthlyData= new HashMap<>();
		
		List<String> states=getCovid19DataStates();
		for (String state : states) {			
			List<Covid19DataRO>  stateMonthlyRecords=getCovid19DataByState(state);
			Covid19DataRO tLtsCovid19DataRO=getLatestCovid19DataByState(state);
			if(tLtsCovid19DataRO!=null)
				stateMonthlyRecords.add(tLtsCovid19DataRO);
			stateMonthlyRecords.forEach(rec->{
				if(countryMonthlyData.containsKey(rec.getMonth()))
				{
					Covid19DataRO tCovid19DataRO=countryMonthlyData.get(rec.getMonth());
					Long total=tCovid19DataRO.getTotal()+rec.getTotal();
					tCovid19DataRO.setTotal(total);
					Long cases=tCovid19DataRO.getCases()+rec.getCases();
					tCovid19DataRO.setCases(cases);
					Long cured=tCovid19DataRO.getCured()+rec.getCured();
					tCovid19DataRO.setCured(cured);
					Long death=tCovid19DataRO.getDeath()+rec.getDeath();
					tCovid19DataRO.setDeath(death);					
				}
				else
				{
					rec.setState("India");
					countryMonthlyData.put(rec.getMonth(), rec);
				}
			});
			
		}
		return  new ArrayList<Covid19DataRO>(countryMonthlyData.values());
	}

}
