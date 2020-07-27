package com.adiops.apigateway.dashboard.entity;

import java.util.Date;

/**
 * Interface for Covid19Data
 * @author Deepak Pal
 *
 */
public interface Covid19Data {

	void setCases(Long cases);

	Long getCases();

	void setTotal(Long total);

	Long getTotal();

	void setDeath(Long death);

	Long getDeath();

	void setCured(Long cured);

	Long getCured();

	void setState(String state);

	String getState();

	void setRecordDate(Date recordDate);

	Date getRecordDate();

	void setId(long id);

	long getId();

}
