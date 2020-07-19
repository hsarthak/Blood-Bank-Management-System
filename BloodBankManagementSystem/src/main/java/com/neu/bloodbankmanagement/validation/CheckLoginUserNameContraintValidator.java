package com.neu.bloodbankmanagement.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.neu.bloodbankmanagement.dao.BloodBankDao;
import com.neu.bloodbankmanagement.dao.RoleDao;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.Role;



public class CheckLoginUserNameContraintValidator implements ConstraintValidator<CheckLoginUserName, String>{
	
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public boolean isValid(String userEnteredValue, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		List<Role> roles= new ArrayList<Role>();
		System.out.println("*****"+userEnteredValue);
		boolean result= true;
		try {
			 roles = roleDao.getRoles();
			
			for(Role role: roles) {
				if(!role.getUserName().equals(userEnteredValue)) {
					result = false ;
					System.out.println("TRUE");
				}else {
					result = true;
					System.out.println("FALSE");
					break;
				}
				
			}
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			
		}
		
		
		return result;
	}

}
