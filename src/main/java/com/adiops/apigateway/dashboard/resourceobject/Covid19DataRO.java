package com.adiops.apigateway.dashboard.resourceobject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.adiops.apigateway.dashboard.entity.Covid19Data;

/**
 * This class is responsible for transfer the resource Covid19DataRO.
 * @author Deepak Pal
 *
 */
public class Covid19DataRO implements Covid19Data{

	private DateFormat df = new SimpleDateFormat("MMM");
	 
	private long id;
	
	private Date recordDate;	
	
	private String state;
	
	private Long cured;	
	
	private Long death;
	
	private Long total;
	
	private Long cases;
	
	private String month;
	
	private boolean latest;


	public Date getRecordDate() {
		return recordDate;
	}


	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Long getCured() {
		return cured;
	}


	public void setCured(Long cured) {
		this.cured = cured;
	}


	public Long getDeath() {
		return death;
	}


	public void setDeath(Long death) {
		this.death = death;
	}


	public Long getTotal() {
		return total;
	}


	public void setTotal(Long total) {
		this.total = total;
	}


	public Long getCases() {
		this.cases=total-death-cured;
		return this.cases;
	}


	public void setCases(Long cases) {
		this.cases = cases;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getMonth() {
		this.month=df.format(recordDate);
		return this.month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public boolean isLatest() {
		return latest;
	}


	public void setLatest(boolean latest) {
		this.latest = latest;
	}
	
}
