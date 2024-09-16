package com.springprojectJS.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springprojectJS.service.UserService;
import com.springprojectJS.service.DashboardService;
import com.springprojectJS.dto.*;

@Controller
public class UserController {
	
	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/register")
	public String loadRegisterPage(Model model) {
		
		//MODEL API helps from controller to UI
		
		Map<Integer, String> countriesMap= userService.getCountries();				
		model.addAttribute("countries", countriesMap);
		
		RegisterFormDTO registerFormDTO= new RegisterFormDTO();
		model.addAttribute("registerForm", registerFormDTO);
		
		return "register";
	}
	
	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer, String> getStates(@PathVariable Integer countryId, Model model) {
		
		//MODEL API helps to send data from controller to UI
		
		Map<Integer, String> statesMap= userService.getStates(countryId);				
	
		model.addAttribute("states", statesMap);	
		System.out.println(statesMap);
			
		return statesMap;
	}
	
	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer, String> getCities(@PathVariable Integer stateId, Model model) {
		
		//MODEL API helps from controller to UI
		
		Map<Integer, String> citiesMap= userService.getCities(stateId);				
	
		model.addAttribute("cities", citiesMap);		
			
		return citiesMap;
	}
	
	@PostMapping("/register")
	public String handleRegisterPage(RegisterFormDTO registerFormDTO, Model model) {
		
		boolean status=userService.duplicateEmailCheck(registerFormDTO.getEmail());
		
		if (status) {
			model.addAttribute("emsg", "Duplicate Email Found");
		}else {
			boolean saveUser= userService.saveUser(registerFormDTO);
			if (saveUser) {
				//user saved
				model.addAttribute("smsg", "Registeration Successfull, Please check your email..!!");
			} else {
				//failed to save
				model.addAttribute("emsg", "Registeration Failed");
				
			}
			
		}
		model.addAttribute("registerForm", new RegisterFormDTO());				
		model.addAttribute("countries", userService.getCountries());
				
		return "register";
	}
	 
	@GetMapping("/")
	public String index(Model model) {
		
		LoginFormDTO loginFormDTO = new LoginFormDTO();
		
		model.addAttribute("loginForm", loginFormDTO);
		
		return "login";		
		
	}

	@PostMapping("/login")
	public String handleUserLogin(LoginFormDTO loginFormDTO, Model model) {
		
		UserDTO userDTO=userService.login(loginFormDTO);
		
		if(userDTO==null) {
			model.addAttribute("emsg", "Invalid Credentials");
		} else {
			String pwdUpdated=userDTO.getPwdUpdated();
			if("Yes".equals(pwdUpdated)) {
				//display dashboard
				return "redirect:dashboard";
			}else {
				//display reset pwd page
				return "redirect:reset-pwd-page?email="+ userDTO.getEmail();
			}
		}
		
		
		model.addAttribute("loginForm", loginFormDTO);
		
		return "login";		
		
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		
		QuoteApiResponseDTO quoteApiResponseDTO=dashboardService.getQuote();
		
		model.addAttribute("quote", quoteApiResponseDTO);
		
		return "dashboard";		
				
	}
	
	@GetMapping("/reset-pwd-page")
	public String loadResetPwdPage(@RequestParam String email, Model model) {
		
		ResetPwdFormDTO resetPwdFormDTO=new ResetPwdFormDTO();
		resetPwdFormDTO.setEmail(email);
		
		model.addAttribute("resetPwd", resetPwdFormDTO);
		
		return "resetpwd";
		
	}
	@PostMapping("/resetPwd")
	public String handleResetPwdPage(ResetPwdFormDTO resetPwdFormDTO, Model model) {
		
		boolean resetPwd=userService.resetPwd(resetPwdFormDTO);
		
		if(resetPwd) {
			return "redirect:dashboard";
		}

		return "resetPwd";
		
	}
}

