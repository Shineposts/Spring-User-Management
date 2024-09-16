package com.springprojectJS.repo;

import com.springprojectJS.entity.StateEntity;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepo extends JpaRepository<StateEntity, Integer>{
	
	//based on country id, i want to get states
	public List<StateEntity> findByCountryId(Integer countryId);
	

}
