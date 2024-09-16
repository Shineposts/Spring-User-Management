package com.springprojectJS.service;

import org.springframework.stereotype.Service;

import com.springprojectJS.dto.QuoteApiResponseDTO;
@Service
public interface DashboardService {
	
	public QuoteApiResponseDTO getQuote() ;
}
