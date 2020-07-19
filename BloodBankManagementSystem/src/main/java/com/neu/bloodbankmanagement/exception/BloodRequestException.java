package com.neu.bloodbankmanagement.exception;

public class BloodRequestException extends Exception {


	public BloodRequestException(String message) {
		super(message);
	}

	public BloodRequestException(String message,Throwable cause) {
		super(cause);
	}

}
