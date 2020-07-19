package com.neu.bloodbankmanagement.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.neu.bloodbankmanagement.utils.UtilsSecure;
import com.neu.bloodbankmanagement.validation.CheckDonorEmail;
import com.neu.bloodbankmanagement.validation.CheckDonorUserName;

@Entity
@Table(name="donor")
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id", unique = true, nullable = false)
	private long id;
	
	@Column(name="first_name")
	@NotNull(message="is required")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull(message="is required")
	private String lastName;
	
	@Column(name="dob")
	//@NotNull(message="is required")
	private Date dateOfBirth;
	
	@Column(name="gender")
	@NotNull(message="is required")
	private String gender;
	
	@Column(name="blood_type")
	@NotNull(message="is required")
	private String bloodType;
	
	@CheckDonorEmail
	@Column(name="Email")
	@Pattern(regexp="[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+", message="Invalid Email id")
	@NotNull(message="is required")
	private String email;
	
	@Column(name="Phone")
	@Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="Invalid Phone number (xxx-xxx-xxxx)")
	@NotNull(message="is required")
	private String phone;
	
	@CheckDonorUserName
	@Column(name="Username")
	@NotNull(message="is required")
	@Size(min=5, max=15, message="Minimum 5 and Maximum 15 characters required")
	private String userName;
	
	@Column(name="Password")
	@NotNull(message="is required")
	@Size(min=5, message="Minimum 5 characters required")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Role_Fk")
	private Role role;
	
	@OneToMany(mappedBy="donor")
	private List<DonationHistory> donationHistory;
	
	public Donor() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<DonationHistory> getDonationHistory() {
		return donationHistory;
	}

	public void setDonationHistory(List<DonationHistory> donationHistory) {
		this.donationHistory = donationHistory;
	}

	@Override
	public String toString() {
		return "Donor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", bloodType=" + bloodType +", email=" + email + ", phone=" + phone + ", userName="
				+ userName + ", password=" + password + ", role=" + role + "]";
	}
	
	
}
