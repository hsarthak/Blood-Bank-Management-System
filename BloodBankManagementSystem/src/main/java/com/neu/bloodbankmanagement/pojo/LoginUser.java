package com.neu.bloodbankmanagement.pojo;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.neu.bloodbankmanagement.validation.CheckLoginUserName;

public class LoginUser {
	
	@CheckLoginUserName
	@Column(name="Username")
	@NotNull(message="is required")
	@Size(min=5, max=15, message="Minimum 5 and Maximum 15 characters required")
	private String userName;
	
	@Column(name="Password")
	@NotNull(message="is required")
	@Size(min=5, message="Minimum 5 characters required")
	private String password;
	


	public LoginUser() {
		
	}

	public LoginUser(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginUser [userName=" + userName + ", password=" + password + "]";
	}

}
