package com.adiops.apigateway.dashboard.helper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.adiops.apigateway.common.utils.DateUtils;
import com.adiops.apigateway.dashboard.entity.Covid19Data;
import com.adiops.apigateway.dashboard.repository.Covid19DataRepository;

/**
 * The helper for querying Covid19Data 
 * @author Deepak Pal
 *
 */
public class Covid19DataQueryHelper {

	private static Covid19DataRepository mCovid19DataRepository=null;

	public static void setCovid19DataRepository(Covid19DataRepository tCovid19DataRepository) {
		mCovid19DataRepository = tCovid19DataRepository;
	}

	public static Set<String> getDataStateSet() {
		Set<String> states = new HashSet<>();
		states.addAll(mCovid19DataRepository.retriveStateList());
		return states;
	}
	
	public static List<? extends Covid19Data> getStateCovid19Data(String state) {
		List<LocalDate> localdates= DateUtils.getLastMonthDateOfCurrentYear();
		 List<Date> dates= localdates.stream().map(				 
				 localDate-> Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
				 ).collect(Collectors.toList());
				
	 return	mCovid19DataRepository.findAllWithRecordDate(state,dates);
	}
	

	

}
