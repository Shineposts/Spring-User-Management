package com.springprojectJS.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springprojectJS.dto.QuoteApiResponseDTO;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	private String quoteApiURL="https://dummyjson.com/quotes/random";
	

	@Override
	public QuoteApiResponseDTO getQuote() {
		// TODO Auto-generated method stub
		
		RestTemplate rt=new RestTemplate();
		
		ResponseEntity<QuoteApiResponseDTO> forEntity=rt.getForEntity(quoteApiURL, QuoteApiResponseDTO.class);
		
		QuoteApiResponseDTO body=forEntity.getBody();
		
		return body;
		
	}

}
