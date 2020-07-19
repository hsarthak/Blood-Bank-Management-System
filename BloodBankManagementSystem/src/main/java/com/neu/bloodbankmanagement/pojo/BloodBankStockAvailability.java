package com.neu.bloodbankmanagement.pojo;

import java.util.HashMap;
import java.util.Map;

public class BloodBankStockAvailability {
	private String bloodBankName;
	Map<String,Integer> totalBlood;
	Map<String,Integer> approvedBlood;
	static final String[] BLOODTYPES = {"A+","A-","B+","B-","AB+","AB-","O+","O-",};
	
	public BloodBankStockAvailability() {
		this.totalBlood = new HashMap<String,Integer>();
		this.approvedBlood = new HashMap<String,Integer>();
		for(String bloodType: BLOODTYPES) {
			totalBlood.put(bloodType, 0);
			approvedBlood.put(bloodType, 0);
		}
		
	}

	public String getBloodBankName() {
		return bloodBankName;
	}

	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}

	public Map<String, Integer> getTotalBlood() {
		return totalBlood;
	}

	public void setTotalBlood(Map<String, Integer> totalBlood) {
		this.totalBlood = totalBlood;
	}

	public Map<String, Integer> getApprovedBlood() {
		return approvedBlood;
	}

	public void setApprovedBlood(Map<String, Integer> approvedBlood) {
		this.approvedBlood = approvedBlood;
	}
	

}
