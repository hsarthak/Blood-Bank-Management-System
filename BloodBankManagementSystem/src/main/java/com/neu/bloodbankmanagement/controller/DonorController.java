package com.neu.bloodbankmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.bloodbankmanagement.dao.DonationHistoryDao;
import com.neu.bloodbankmanagement.dao.DonorDao;
import com.neu.bloodbankmanagement.exception.DonationHistoryException;
import com.neu.bloodbankmanagement.exception.DonorException;
import com.neu.bloodbankmanagement.pojo.DonationHistory;
import com.neu.bloodbankmanagement.pojo.Donor;
import com.neu.bloodbankmanagement.utils.UtilsSecure;



@Controller
public class DonorController {
	
	@Autowired
	private DonorDao donorDao;
	
	@Autowired
	private DonationHistoryDao donationHistoryDao;
	
	//InitBinder is a preprocessor used here to remove white spaces
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping(value="/login/homedonor/donationhistory", method=RequestMethod.GET)
	public String getDonorHistory(HttpServletRequest request, HttpServletResponse response) throws DonorException, DonationHistoryException {
		
		HttpSession session = request.getSession();
		System.out.println("\n\n\n***Donor username "+(String)session.getAttribute("password")+"\n\n"+request.getRequestURI());
		
		if(session.getAttribute("userName")==null) {
			request.setAttribute("request", request);
			return "ExceptionLoginRequired";
		}
		//get donor id from the session w.t.h. of Donor dao
		Donor donor = donorDao.getDonor((String)session.getAttribute("userName"), (String)session.getAttribute(("password")));
		System.out.println(donor);
		
		//get donation history of the Donor and print to console
		System.out.println("Date|BloodBank Name| ");
		
		for(DonationHistory dh: donationHistoryDao.getDonorHistory(donor.getId())) {
			System.out.println(dh.getDate()+" | "+dh.getBloodBank().getName());
		}
		
		//Send donation history list to the jsp
		request.setAttribute("donationHistoryList", donationHistoryDao.getDonorHistory(donor.getId()));
		
		return "donorHistory";
		
	}
	

}
