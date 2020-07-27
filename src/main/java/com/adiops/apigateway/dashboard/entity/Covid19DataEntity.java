package com.adiops.apigateway.dashboard.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

/**
 * The Main data entity class for Covid19 Data
 * @author Deepak Pal
 *
 */
@Entity(name = "Covid19Data")
public class Covid19DataEntity implements Covid19Data {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	@CsvBindByPosition(position = 0)
	@CsvDate(value = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date recordDate;	
	@Column
	@CsvBindByPosition(position = 1)
	private String state;
	@Column
	@CsvBindByPosition(position = 2)
	private Long cured;	
	@Column
	@CsvBindByPosition(position = 3)
	private Long death;
	
	@Column
	@CsvBindByPosition(position = 4)
	private Long total;
	
	@Column
	private Long cases;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Date getRecordDate() {
		return recordDate;
	}

	@Override
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public Long getCured() {
		return cured;
	}

	@Override
	public void setCured(Long cured) {
		this.cured = cured;
	}

	@Override
	public Long getDeath() {
		return death;
	}

	@Override
	public void setDeath(Long death) {
		this.death = death;
	}

	@Override
	public Long getTotal() {
		return total;
	}

	@Override
	public void setTotal(Long total) {
		this.total = total;
	}

	@Override
	public Long getCases() {
		return cases;
	}

	@Override
	public void setCases(Long cases) {
		this.cases = cases;
	}	
	
}
