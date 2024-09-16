package com.springprojectJS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springprojectJS.entity.CityEntity;
import com.springprojectJS.entity.StateEntity;

public interface CityRepo extends JpaRepository<CityEntity, Integer>{
	
	//based on state id, i want to get cities
	
	public List<CityEntity> findByStateId(Integer stateId);	

}