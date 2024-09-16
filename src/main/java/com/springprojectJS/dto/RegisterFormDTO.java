package com.springprojectJS.dto;

import com.springprojectJS.entity.CityEntity;
import com.springprojectJS.entity.CountryEntity;
import com.springprojectJS.entity.StateEntity;

public class RegisterFormDTO {
	
	/*Binding the registration from & convert to Entity 
	When ever you submit user registration form, the form data will be captured and stored into object. 
	That is called RegisterformDTO object. And make sure variables in RegisterFormDTO match with Entity class.
	We need to copy the data from binding object to variable in Register match with entity class.
	//copy from (DTO)binding class to entity class*/
		
	private Integer userId;	
	
	private String uname;	
	
	private String email;	
	
	private String pwd;	

	private String pwdUpdated;	
	
	private String phno;	

	private Integer countryId;
	
	private Integer stateId;	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdUpdated() {
		return pwdUpdated;
	}

	public void setPwdUpdated(String pwdUpdated) {
		this.pwdUpdated = pwdUpdated;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	private Integer cityId;	

}
