package com.neu.bloodbankmanagement.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailFunctionality {
	
	public static void bloodRequestSent(String receiver, String bloodbankName) throws EmailException {
	    Email email = new SimpleEmail();
	    email.setHostName("smtp.googlemail.com");
	    email.setSmtpPort(465);
	    email.setAuthenticator(new DefaultAuthenticator("samsamberg2311@gmail.com", "Saurabh1993$"));
	    email.setSSLOnConnect(true);
	    email.setFrom("do-not-reply@gmail.com");
	    email.setSubject("Blood Request Update :: "+bloodbankName);
	    email.setMsg("You have successfully sent a blood request to "+bloodbankName );
	    email.addTo(receiver);
	    email.send();
	}

}
