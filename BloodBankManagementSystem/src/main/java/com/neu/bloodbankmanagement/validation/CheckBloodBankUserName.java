package com.neu.bloodbankmanagement.validation;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = CheckBloodBankUserNameContraintValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckBloodBankUserName {
	
	//public String value();
	
	public String message() default " UserName Already Exists";
	
	public Class<?>[] groups() default{};
	
	public Class<? extends Payload>[] payload() default{};

}
