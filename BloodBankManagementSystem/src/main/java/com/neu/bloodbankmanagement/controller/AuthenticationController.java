package com.neu.bloodbankmanagement.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
//import org.springframework.http.HttpRequest;
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
import com.neu.bloodbankmanagement.dao.DonorDao;
import com.neu.bloodbankmanagement.dao.HospitalDao;
import com.neu.bloodbankmanagement.exception.BloodBankException;
import com.neu.bloodbankmanagement.exception.DonorException;
import com.neu.bloodbankmanagement.exception.HospitalException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.Donor;
import com.neu.bloodbankmanagement.pojo.Hospital;
import com.neu.bloodbankmanagement.pojo.LoginUser;
import com.neu.bloodbankmanagement.pojo.Role;

//@RequestMapping("/*")
@Controller
public class AuthenticationController {
	
	@Autowired
	private HospitalDao hospitalDao;
	
	@Autowired
	private BloodBankDao bloodBankDao;
	
	@Autowired
	private DonorDao donorDao;
	
	@RequestMapping("/")
	public String showHome(HttpServletRequest request){
		request.getSession().invalidate();
		return "home";
	}
	
	//InitBinder is a preprocessor used here to remove white spaces
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
//  HOSPITAL REGISTRATION 
	
	@RequestMapping(value = "/registerhospital", method = RequestMethod.GET)
	public ModelAndView showHospitalRegister(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) {
		//request.setAttribute("hospital", new Hospital());
		request.getSession().invalidate();
		model.addAttribute("hospital", new Hospital());
		return new ModelAndView("registerHospital");
	}
	
	@RequestMapping(value= "/registerhospital" , method = RequestMethod.POST)
	public String processHospitalRegister(@Valid @ModelAttribute("hospital") Hospital hospital, BindingResult bindingResult) throws HospitalException {
		
		if(bindingResult.hasErrors()) {
			System.out.println("\n\nbindingResult.hasErrors() is TRUE");
			return "registerHospital";
		}else {

				System.out.println("\n\nbindingResult.hasErrors() is FALSE");
				System.out.println("\nHospital is "+ hospital);
				Role role = new Role();
				role.setUserName(hospital.getUserName());
				role.setPassword(hospital.getPassword());
				role.setEmail(hospital.getEmail());
				role.setRole("Hospital");
				hospital.setRole(role);//This will also save the role because of the CascadeType.All
				if(hospitalDao.AuthenticateHospitalRegistration(hospital)) {
					return "registerHospital";
				}
				
				hospitalDao.save(hospital);
				
			
			
			return "redirect:/";
		}
	}
	
//  HOSPITAL REGISTRATION ENDS HERE

	
//  BLOOD BANK REGISTRATION
	
	@RequestMapping(value = "/registerbloodbank", method = RequestMethod.GET)
	public ModelAndView showBloodBankRegister(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) {
		//request.setAttribute("hospital", new Hospital());
		request.getSession().invalidate();
		model.addAttribute("bloodBank", new BloodBank());
		return new ModelAndView("registerBloodBank");
	}
	
	@RequestMapping(value= "/registerbloodbank" , method = RequestMethod.POST)
	public String processBloodBankRegister(@Valid @ModelAttribute("bloodBank") BloodBank bloodBank, BindingResult bindingResult) throws BloodBankException {
		
		if(bindingResult.hasErrors()) {
			System.out.println("\n\nbindingResult.hasErrors() is TRUE");
			return "registerBloodBank";
		}else {
			System.out.println("\n\nbindingResult.hasErrors() is FALSE");
			System.out.println("\nBloodBank is "+ bloodBank);
			Role role = new Role();
			role.setUserName(bloodBank.getUserName());
			role.setPassword(bloodBank.getPassword());
			role.setEmail(bloodBank.getEmail());
			role.setRole("BloodBank");
			bloodBank.setRole(role);//This will also save the role because of the CascadeType.All
			if(bloodBankDao.AuthenticateBloodBankRegistration(bloodBank)) {
				return "registerBloodBank";
			}
			bloodBankDao.save(bloodBank);
			return "redirect:/";
		}
	}
	
//  BLOOD BANK REGISTRATION ENDS HERE
	
//    DONOR REGISTRATION
	
	@RequestMapping(value = "/registerdonor", method = RequestMethod.GET)
	public ModelAndView showdonorRegister(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) {
		//request.setAttribute("hospital", new Hospital());
		request.getSession().invalidate();
		model.addAttribute("donor", new Donor());
		return new ModelAndView("registerDonor");
	}
	
	@RequestMapping(value= "/registerdonor" , method = RequestMethod.POST)
	public String processDonorRegister(@Valid @ModelAttribute("donor") Donor donor, BindingResult bindingResult, HttpServletRequest request) throws DonorException, ParseException {
		
		if(bindingResult.hasErrors()) {
			System.out.println("\n\nbindingResult.hasErrors() is TRUE");
			return "registerDonor";
		}else {
			System.out.println("\n\nbindingResult.hasErrors() is FALSE");
			System.out.println("\nDonor is "+ donor);
			Role role = new Role();
			role.setUserName(donor.getUserName());
			role.setPassword(donor.getPassword());
			role.setEmail(donor.getEmail());
			role.setRole("Donor");
			
			System.out.println("*********\n"+request.getParameter("dob"));
			Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob"));
			donor.setDateOfBirth(dateOfBirth);
			
			donor.setRole(role);//This will also save the role because of the CascadeType.All
			if(donorDao.AuthenticateDonorRegistration(donor)) {
				return "registerDonor";
			}
			donorDao.save(donor);
			return "redirect:/";
		}
	}
//  DONOR REGISTRATION END HERE	
		
}
