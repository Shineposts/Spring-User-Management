package com.springprojectJS.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springprojectJS.dto.LoginFormDTO;
import com.springprojectJS.dto.RegisterFormDTO;
import com.springprojectJS.dto.ResetPwdFormDTO;
import com.springprojectJS.dto.UserDTO;
import com.springprojectJS.repo.CityRepo;
import com.springprojectJS.repo.StateRepo;
import com.springprojectJS.repo.CountryRepo;
import com.springprojectJS.repo.UserRepo;
import com.springprojectJS.entity.CountryEntity;
import com.springprojectJS.entity.StateEntity;
import com.springprojectJS.entity.CityEntity;
import com.springprojectJS.entity.UserEntity;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private CityRepo cityRepo;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired	
	private EmailService emailService;
	

	@Override
	public Map<Integer, String> getCountries() {
		// TODO Auto-generated method stub
		
		Map<Integer, String> countryMap=new HashMap<>();
		
		List<CountryEntity> countriesList=countryRepo.findAll();	
		
		countriesList.forEach(c ->{
			countryMap.put(c.getCountryId(), c.getCountryName());
		});		
		
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		// TODO Auto-generated method stub
		Map<Integer, String> statesMap=new HashMap<>();
		
		List<StateEntity> statesList=stateRepo.findByCountryId(countryId);
		
		statesList.forEach(s ->{
			statesMap.put(s.getState_Id(), s.getStateName());
		});		
		
		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		// TODO Auto-generated method stub
		Map<Integer, String> citiesMap=new HashMap<>();
		
		List<CityEntity> citiesList=cityRepo.findByStateId(stateId);
		
		citiesList.forEach(c ->{
			citiesMap.put(c.getCityId(), c.getCityName());
		});		
		
		return citiesMap;
	}

	@Override
	public boolean duplicateEmailCheck(String email) {
		// TODO Auto-generated method stub
		
		UserEntity byEmail=userRepo.findByEmail(email);
		if (byEmail != null) {
			return true;
		}else {
		return false;	
		}
	}
	@Override
	public boolean saveUser(RegisterFormDTO regFormDTO) {
		// TODO Auto-generated method stub
		
		UserEntity userEntity=new UserEntity();
		//beanutils helps to copy data from one object to another object
		//here from form dto to entity
		BeanUtils.copyProperties(regFormDTO, userEntity); //it copies object from registerationdto to userEntity. (source param, target param)
		
		CountryEntity country=countryRepo.findById(regFormDTO.getCountryId()).orElse(null);
		userEntity.setCountry(country);
		
		StateEntity state=stateRepo.findById(regFormDTO.getStateId()).orElse(null);
		userEntity.setState(state);
		
		CityEntity city=cityRepo.findById(regFormDTO.getCityId()).orElse(null);
		userEntity.setCity(city);
		
		String randomPwd=generateRandomPwd();
		
		userEntity.setPwd(randomPwd);
		userEntity.setPwdUpdated("No");
		
		UserEntity savedUser=userRepo.save(userEntity);
		
		if(null!=savedUser.getUserId()) {
			String subject="Your Account Created";
			String body="Your password to login"+randomPwd;
			String to=regFormDTO.getEmail();
			
			emailService.sendEmail(subject, body, to);
			
			return true;
			
		}
		return false;
	}

	@Override
	public UserDTO login(LoginFormDTO loginFormDTO) {
		
		
		// TODO Auto-generated method stub
		
		UserEntity userEntity=userRepo.findByEmailAndPwd(loginFormDTO.getEmail(),loginFormDTO.getPwd());
		
		if (userEntity !=null) {
		UserDTO userDTO=new UserDTO();	
		BeanUtils.copyProperties(userEntity, userDTO); //ENTITY TO DTO, which ever value(variable) in DTO will be copied to Entity.
		return userDTO;
	}
		return null;
	}
	
	@Override
	public boolean resetPwd(ResetPwdFormDTO resetPwdDTO) {
		// TODO Auto-generated method stub
		
		String email=resetPwdDTO.getEmail();
		UserEntity entity=userRepo.findByEmail(email);
		
		//SETTING NEW PASSWORD
		entity.setPwd(resetPwdDTO.getNewPwd());
		entity.setPwdUpdated("Yes");
		
		userRepo.save(entity); //UPSERT
		return true;
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String generateRandomPwd(){
		
		String upperCaseLetters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters="abcdefghijklmnopqrstuvwxyz";
		
		String alphabets=upperCaseLetters + lowerCaseLetters;
		
		Random random=new Random();
		
		StringBuffer generatePwd=new StringBuffer();		
		
		
		for(int i=0;i<5; i++) {
			//give any number from 0 to 51
		int randomIndex=random.nextInt(alphabets.length());
		generatePwd.append(alphabets.charAt(randomIndex));		
		}
	
		return generatePwd.toString();
		}

	}
