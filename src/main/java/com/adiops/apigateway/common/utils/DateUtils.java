package com.adiops.apigateway.common.utils;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Deepak Pal
 *
 */
public class DateUtils {

	/**
	 * This method return the list of last date of the month of current year for chart.
	 * @return
	 */
	public static List<LocalDate> getLastMonthDateOfCurrentYear() {
		List<LocalDate> dates = new ArrayList<>();
		LocalDate date = LocalDate.now();
		int month = date.getMonthValue();
		for (int currentMonth = 1; currentMonth <= month; currentMonth++) {
			date = date.withMonth(currentMonth);
			LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
			LocalDate currentDate = LocalDate.now().minusDays(1);
			// end of month
			if (currentDate.isAfter(lastDay))
				dates.add(lastDay);			
		}
		return dates;
	}

	public static void main(String[] args) {
		getLastMonthDateOfCurrentYear();
	}

}
