package com.springprojectJS.service;

import java.util.*;
import com.springprojectJS.dto.*;

public interface UserService {

	
	public Map<Integer, String> getCountries();
	
	public Map<Integer, String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	public boolean duplicateEmailCheck(String email);
	
	public boolean saveUser(RegisterFormDTO regFormDTO);
	
	public UserDTO login(LoginFormDTO loginFormDTO);
	public boolean resetPwd(ResetPwdFormDTO resetPwdDTO);
	public UserDTO getUserByEmail(String email);
	
	
}
