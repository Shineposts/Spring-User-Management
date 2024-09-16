package com.springprojectJS.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springprojectJS.entity.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, Integer> {

}
