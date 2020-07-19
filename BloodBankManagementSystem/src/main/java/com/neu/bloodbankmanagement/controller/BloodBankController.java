package com.neu.bloodbankmanagement.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.bloodbankmanagement.dao.BloodBankDao;
import com.neu.bloodbankmanagement.dao.BloodRequestDao;
import com.neu.bloodbankmanagement.dao.DonationHistoryDao;
import com.neu.bloodbankmanagement.dao.DonorDao;
import com.neu.bloodbankmanagement.exception.BloodBankException;
import com.neu.bloodbankmanagement.exception.BloodRequestException;
import com.neu.bloodbankmanagement.exception.DonationHistoryException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.BloodRequest;
import com.neu.bloodbankmanagement.pojo.DonationHistory;


@Controller
public class BloodBankController {
	
	@Autowired
	private BloodBankDao bloodBankDao;

	@Autowired
	private DonorDao donorDao;

	@Autowired
	private DonationHistoryDao donationHistoryDao;
	
	@Autowired
	private BloodRequestDao bloodRequestDao;
	
	//InitBinder is a preprocessor used here to remove white spaces
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping(value = "/login/homebloodbank/searchdonor", method = RequestMethod.GET)
	public String showBloodBankRespectiveDonors(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) throws BloodBankException, DonationHistoryException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userName")==null) {
			System.out.println("\n\n******"+session+"******\n\n");
			request.setAttribute("request", request);
			return "ExceptionLoginRequired";
		}
		System.out.println("\n\n\n***Blood Bank username"+(String)session.getAttribute("userName")+"\n\n");
		//get Bloodbank id from username and password
		BloodBank bloodBank = bloodBankDao.getBloodBankSession((String)session.getAttribute("userName"),(String) session.getAttribute("password"));
		System.out.println(bloodBank);
		
		//get donation history of the blood bank
		for(DonationHistory dh: donationHistoryDao.getBloodBankDonationHistory(bloodBank.getId())) {
			System.out.println("****\nDonationDate: "+dh.getDate()+"\nBloodBankId: "+dh.getBloodBank().getName()+"\nDonorName: "+dh.getDonor().getFirstName());
		}
		//send donationhistory list to the jsp to display
		request.setAttribute("donationHistoryList",donationHistoryDao.getBloodBankDonationHistory(bloodBank.getId()) );
		
		//get donor of the blood bank
		
		return "bloodBankDonors";
	}
	
	@RequestMapping(value = "/login/homebloodbank/stocks", method = RequestMethod.GET)
	public String showBloodBankStocks(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) throws BloodBankException, DonationHistoryException {
		
		HttpSession session = request.getSession();

		if(session.getAttribute("userName")==null) {
			System.out.println("\n\n******"+session+"******\n\n");
			request.setAttribute("request", request);
			return "ExceptionLoginRequired";
		}
		System.out.println("\n\n\n***Blood Bank username"+(String)session.getAttribute("userName")+"\n\n");
		
		//get Bloodbank id from username and password
		BloodBank bloodBank = bloodBankDao.getBloodBankSession((String)session.getAttribute("userName"),(String) session.getAttribute("password"));
		System.out.println(bloodBank);
		
		//get blood bank stocks
		List<Object[]> bloodBankStocks =  donationHistoryDao.getBloodBankStock(bloodBank.getId());

		
		System.out.println("*****\n\nType||Amount\n");
		
		for(int i=0; i<bloodBankStocks.size();i++) {
			System.out.println(bloodBankStocks.get(i)[0]+"  ||"+bloodBankStocks.get(i)[1]+"  |");
			
		}
		//send donationhistory list to the jsp to display
		request.setAttribute("stockList", bloodBankStocks);
		
		return "bloodBankStocks";
	}
	
	@RequestMapping(value = "/login/homebloodbank/bloodrequest", method = RequestMethod.GET)
	public String showBloodBankRequests(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) throws BloodBankException, DonationHistoryException, NumberFormatException, BloodRequestException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userName")==null) {
			System.out.println("\n\n******"+session+"******\n\n");
			request.setAttribute("request", request);
			return "ExceptionLoginRequired";
		}
		//create a list to store the blood requests
		List<BloodRequest> bloodRequests = new ArrayList<BloodRequest>();
		
		//Get session
		System.out.println("\n\n\n***Blood Bank username"+(String)session.getAttribute("userName")+"\n\n");
		
		//Get blood bank id from the username and password stored in session
		BloodBank bloodBank = bloodBankDao.getBloodBankSession((String)session.getAttribute("userName"),(String) session.getAttribute("password"));
		System.out.println(bloodBank);
		
		//************UPDATE*********
		//check whether confirmationValue is null or not
		//if null then display original request
		//else update the confirmation value only when request is pending
		
		System.out.println("\n*****Above Update logic of controller");
		System.out.println("Confirmation Value: "+request.getParameter("confirmationValue"));
		
		if(request.getParameter("confirmationValue")!=null) {
			System.out.println("****\nconfirmation Value is not null");
			if(bloodRequestDao.getBloodRequestById(Long.parseLong(request.getParameter("bloodRequestId"))).getConfirmation().equals("pending")) {
				System.out.println("\n*****Inside Update logic of controller");
				System.out.println("Confirmation Value: "+request.getParameter("confirmationValue"));
				bloodRequestDao.updateConfirmationStatus(Long.parseLong(request.getParameter("bloodRequestId")), request.getParameter("confirmationValue"));
			}
			
		}
		//************UPDATE ENDS HERE*********
		
		//get blood requests
		bloodRequests = bloodRequestDao.getBloodRequests(bloodBank.getId());
		
		
		//display requests to the console
		for(BloodRequest bloodRequest: bloodRequests) {
			System.out.println("\n| "+bloodRequest.getId()+" | "+bloodRequest.getDate()+" | "+bloodRequest.getHospital().getName()+" | "+
					bloodRequest.getHospital().getEmail()+" | "+bloodRequest.getHospital().getPhone()+" | "
					+bloodRequest.getBloodType()+" | "+bloodRequest.getBloodAmount()+" | "+bloodRequest.getConfirmation()+" |");
		}
		
		//send blood requests to the jsp
		request.setAttribute("bloodRequestsList", bloodRequests);
		
		return "bloodBankRequests";
	}

}
