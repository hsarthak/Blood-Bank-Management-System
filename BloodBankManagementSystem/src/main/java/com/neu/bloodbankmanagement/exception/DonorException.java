package com.neu.bloodbankmanagement.exception;

public class DonorException extends Exception {


	public DonorException(String message) {
		super(message);
	}

	public DonorException(String message,Throwable cause) {
		super(cause);
	}

}
