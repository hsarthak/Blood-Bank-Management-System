package com.neu.bloodbankmanagement.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.neu.bloodbankmanagement.dao.HospitalDao;
import com.neu.bloodbankmanagement.dao.RoleDao;
import com.neu.bloodbankmanagement.pojo.Hospital;
import com.neu.bloodbankmanagement.pojo.Role;

public class CheckEmailContraintValidator implements ConstraintValidator<CheckEmail, String>{
	
	
	@Autowired
	private HospitalDao hospitalDao;

	@Override
	public boolean isValid(String userEnteredValue, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		List<Hospital> hospitals= new ArrayList<Hospital>();
		System.out.println("*****"+userEnteredValue);
		boolean result= true;
		try {
			 hospitals = hospitalDao.getAllHospitals();
			
			for(Hospital hospital: hospitals) {
				if(hospital.getEmail().equals(userEnteredValue)) {
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
