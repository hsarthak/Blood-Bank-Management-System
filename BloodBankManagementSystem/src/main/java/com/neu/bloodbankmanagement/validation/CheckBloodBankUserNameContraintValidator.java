package com.neu.bloodbankmanagement.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.neu.bloodbankmanagement.dao.BloodBankDao;
import com.neu.bloodbankmanagement.pojo.BloodBank;



public class CheckBloodBankUserNameContraintValidator implements ConstraintValidator<CheckBloodBankUserName, String>{
	
	
	@Autowired
	private BloodBankDao bloodBankDao;

	@Override
	public boolean isValid(String userEnteredValue, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		List<BloodBank> bloodBanks= new ArrayList<BloodBank>();
		System.out.println("*****"+userEnteredValue);
		boolean result= true;
		try {
			 bloodBanks = bloodBankDao.getAllBloodBanks();
			
			for(BloodBank bloodBank: bloodBanks) {
				if(bloodBank.getUserName().equals(userEnteredValue)) {
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
