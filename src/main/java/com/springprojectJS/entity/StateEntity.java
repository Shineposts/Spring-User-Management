package com.springprojectJS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="state_master")
public class StateEntity {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "state_id")
	private Integer state_Id;
	
	@Column(name= "state_name")
	private String stateName;
	
	@Column(name= "country_id")
	private Integer countryId;

	public Integer getState_Id() {
		return state_Id;
	}

	public void setState_Id(Integer state_Id) {
		this.state_Id = state_Id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
	
	

	
	
	
}
