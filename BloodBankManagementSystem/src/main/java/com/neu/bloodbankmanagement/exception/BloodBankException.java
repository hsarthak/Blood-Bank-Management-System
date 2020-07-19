package com.neu.bloodbankmanagement.exception;

public class BloodBankException extends Exception {


	public BloodBankException(String message) {
		super(message);
	}

	public BloodBankException(String message,Throwable cause) {
		super(cause);
	}

}
