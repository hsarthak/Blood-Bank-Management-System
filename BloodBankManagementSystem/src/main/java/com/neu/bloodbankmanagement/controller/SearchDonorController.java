package com.neu.bloodbankmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.bloodbankmanagement.dao.BloodBankDao;
import com.neu.bloodbankmanagement.dao.DonationHistoryDao;
import com.neu.bloodbankmanagement.dao.DonorDao;
import com.neu.bloodbankmanagement.pojo.DonationHistory;


@Controller
public class SearchDonorController {
	
	@Autowired
	private BloodBankDao bloodBankDao;

	@Autowired
	private DonorDao donorDao;

	@Autowired
	private DonationHistoryDao donationHistoryDao;
	
	@RequestMapping(value = "/login/bloodbank/searchdonor", method = RequestMethod.GET)
	public String showBloodBankRespectiveDonors(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) {
		
		return "bloodBankDonors";
	}

}
