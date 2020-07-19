package com.neu.bloodbankmanagement.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.neu.bloodbankmanagement.dao.BloodBankDao;
import com.neu.bloodbankmanagement.dao.DonorDao;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.Donor;



public class CheckDonorUserNameContraintValidator implements ConstraintValidator<CheckDonorUserName, String>{
	
	
	@Autowired
	private DonorDao donorDao;

	@Override
	public boolean isValid(String userEnteredValue, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		List<Donor> donors= new ArrayList<Donor>();
		System.out.println("*****"+userEnteredValue);
		boolean result= true;
		try {
			donors = donorDao.getAllDonors();
			
			for(Donor donor: donors) {
				if(donor.getUserName().equalsIgnoreCase(userEnteredValue)) {
					result = false ;
					break;
				}else {
					result = true;
				}
				
			}
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			
		}
		
		
		return result;
	}

}
