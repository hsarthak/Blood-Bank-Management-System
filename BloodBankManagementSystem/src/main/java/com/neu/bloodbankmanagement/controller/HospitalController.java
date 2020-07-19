package com.neu.bloodbankmanagement.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.bloodbankmanagement.dao.BloodBankDao;
import com.neu.bloodbankmanagement.dao.BloodRequestDao;
import com.neu.bloodbankmanagement.dao.DonationHistoryDao;
import com.neu.bloodbankmanagement.dao.DonorDao;
import com.neu.bloodbankmanagement.dao.HospitalDao;
import com.neu.bloodbankmanagement.exception.BloodBankException;
import com.neu.bloodbankmanagement.exception.BloodRequestException;
import com.neu.bloodbankmanagement.exception.DonationHistoryException;
import com.neu.bloodbankmanagement.exception.HospitalException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.BloodBankStockAvailability;
import com.neu.bloodbankmanagement.pojo.BloodRequest;
import com.neu.bloodbankmanagement.pojo.Hospital;
import com.neu.bloodbankmanagement.utils.EmailFunctionality;

@Controller
public class HospitalController {
	
	@Autowired
	private BloodBankDao bloodBankDao;
	
	@Autowired
	private HospitalDao hospitalDao;
	
	@Autowired
	private BloodRequestDao bloodRequestDao;
	
	@Autowired 
	private DonationHistoryDao donationHistoryDao;
	
	//InitBinder is a preprocessor used here to remove white spaces
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping(value = "login/homehospital/sendrequest", method = RequestMethod.GET)
	public ModelAndView showRequestForm(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) throws BloodBankException {
		//request.setAttribute("hospital", new Hospital());
		HttpSession session = request.getSession();
		if(session.getAttribute("userName")==null) {
			request.setAttribute("request", request);
			return new ModelAndView("ExceptionLoginRequired");
		}
		List<BloodBank> bloodBanks = new ArrayList<BloodBank>();
		bloodBanks = bloodBankDao.getAllBloodBanks();
		
		request.setAttribute("bloodBankList", bloodBanks);
				
		model.addAttribute("bloodRequest", new BloodRequest());
		return new ModelAndView("requestForm");
	}
	
	@RequestMapping(value = "login/homehospital/sendrequest", method = RequestMethod.POST)
	public String processRequestForm(@Valid @ModelAttribute("bloodRequest") BloodRequest bloodRequest, BindingResult bindingResult,HttpServletRequest request) throws BloodBankException, HospitalException, ParseException, EmailException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userName")==null) {
			request.setAttribute("request", request);
			
			return "ExceptionLoginRequired";
		}
		if(bindingResult.hasErrors()) {
			
			System.out.println("\n\nbindingResult.hasErrors() is TRUE");
			return "requestForm";
		}else {
			//get hospital from  the userName and password stored in session
			String userName = (String)session.getAttribute("userName");
			String password = (String)session.getAttribute("password");
			Hospital hospital = hospitalDao.getHospital(userName, password);
			System.out.println("\n\n****"+request.getParameter("date")+"\n\n");
			
			Date requestDate = new Date();
			bloodRequest.setDate(requestDate);
			
			//save hospital in bloodRequest
			bloodRequest.setHospital(hospital);
			
			//get bloodbank from form parameter bloodbank
			BloodBank bloodBank = bloodBankDao.getBloodBank(request.getParameter("bloodBankName"));
			
			//save bloodbank in bloodRequest
			bloodRequest.setBloodBank(bloodBank);
			
			//set confirmation to pending for now (until it get approved by the blood bank)
			bloodRequest.setConfirmation("pending");
			
			//save blood request
			bloodRequestDao.save(bloodRequest);
			EmailFunctionality.bloodRequestSent(hospital.getEmail(), bloodBank.getName());
			return "redirect:/login/homehospital";
		}		
		
	}
	
	@RequestMapping(value = "/login/homehospital/requesthistory", method = RequestMethod.GET)
	public String showBloodBankRequests(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) throws DonationHistoryException, NumberFormatException, BloodRequestException, HospitalException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userName")==null) {
			request.setAttribute("request", request);
			return "ExceptionLoginRequired";
		}
		//create a list to store the blood requests
		List<BloodRequest> bloodRequests = new ArrayList<BloodRequest>();
		
		//Get session
		System.out.println("\n\n\n***Blood Bank username"+(String)session.getAttribute("userName")+"\n\n");
		
		//Get hospital id from the username and password stored in session
		String userName = (String)session.getAttribute("userName");
		String password = (String)session.getAttribute("password");
		Hospital hospital = hospitalDao.getHospital(userName, password);
		
		//************DELETE*********
		
		if(request.getParameter("delete")!=null) {
			if(bloodRequestDao.getBloodRequestById(Long.parseLong(request.getParameter("bloodRequestId"))).getConfirmation().equals("pending")) {
				System.out.println("\n*****Inside Update logic of controller");
				System.out.println("Delete Value: "+request.getParameter("delete"));
				bloodRequestDao.deleteBloodRequest(Long.parseLong(request.getParameter("bloodRequestId")));;
			}
		}
		
		//************DELETE ENDS HERE*********
		
		//get blood requests corresponding to the hospital
		
		if(bloodRequestDao.getHospitalBloodRequests(hospital.getId())!=null) {
			
			bloodRequests = bloodRequestDao.getHospitalBloodRequests(hospital.getId());
			
			System.out.println("\n********"+bloodRequests);
			//display requests to the console
			for(BloodRequest bloodRequest: bloodRequests) {
				System.out.println("**********");
				System.out.println("\n| "+bloodRequest.getId()+" | "+bloodRequest.getDate()+" | "+bloodRequest.getBloodBank().getName()+" | "+
						bloodRequest.getBloodBank().getEmail()+" | "+bloodRequest.getBloodBank().getPhone()+" | "
						+bloodRequest.getBloodType()+" | "+bloodRequest.getBloodAmount()+" | "+bloodRequest.getConfirmation()+" |");
			}
			
			//send blood requests to the jsp
			request.setAttribute("bloodRequestsList", bloodRequests);
		}
		
		return "hospitalRequests";
		
	}
	//Show Current Stock available
	@RequestMapping(value = "/login/homehospital/bloodbanksstock", method = RequestMethod.GET)
	public String showBloodAvailability(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) throws DonationHistoryException {
				HttpSession session = request.getSession();
				if(session.getAttribute("userName")==null) {
					request.setAttribute("request", request);
					return "ExceptionLoginRequired";
				}
				List<Object[]> currentBloodStocks = hospitalDao.getCurrentStock();
				ArrayList<Map.Entry<String,Object>> entries = new ArrayList<Map.Entry<String,Object>>();
				ArrayList<Object> values  =new ArrayList<Object>();
				
				
				for(Object stock:currentBloodStocks) {
					 System.out.println("||");
					for (Map.Entry<String,Object> entry : ((Map<String, Object>) stock).entrySet()) {
						System.out.println("Key = " + entry.getKey() + 
	                             ", Value = " + entry.getValue());
						entries.add(entry);
						values.add(entry.getValue());
					}
			            System.out.println("||");
				}
				for(Object obj: values) {
					System.out.println("*****\n"+obj+"\n");
				}
				request.setAttribute("values", values);
				
		return "currentBloodStock";
	}

}
