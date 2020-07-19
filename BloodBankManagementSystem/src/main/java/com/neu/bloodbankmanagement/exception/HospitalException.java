package com.neu.bloodbankmanagement.exception;

public class HospitalException extends Exception {


	public HospitalException(String message) {
		super(message);
	}

	public HospitalException(String message,Throwable cause) {
		super(cause);
	}

}
