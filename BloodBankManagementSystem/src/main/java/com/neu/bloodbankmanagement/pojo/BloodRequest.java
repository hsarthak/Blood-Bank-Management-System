package com.neu.bloodbankmanagement.pojo;


import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="blood_request")
public class BloodRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id", unique = true, nullable = false)
	private long id;
	
	@Column(name="date_requested")
	private Date date;
	
	@Column(name="blood_type")
	private String bloodType;
	
	@Column(name="amount_requested")
	private int bloodAmount;
	
	@Column(name="confirmation")
	private String confirmation;
	
	@ManyToOne
	@JoinColumn(name="Blood_Bank_Id")
	private BloodBank bloodBank;
	
	@ManyToOne
	@JoinColumn(name="Hospital_Id")
	private Hospital hospital;

	public BloodRequest() {
		
		
	}

	public BloodRequest(long id, Date date, String bloodType, int bloodAmount, String confirmation, BloodBank bloodBank,
			Hospital hospital) {
		super();
		this.id = id;
		this.date = date;
		this.bloodType = bloodType;
		this.bloodAmount = bloodAmount;
		this.confirmation = confirmation;
		this.bloodBank = bloodBank;
		this.hospital = hospital;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date){
		this.date = date;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public int getBloodAmount() {
		return bloodAmount;
	}

	public void setBloodAmount(int bloodAmount) {
		this.bloodAmount = bloodAmount;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public BloodBank getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "BloodRequest [id=" + id + ", date=" + date + ", bloodType=" + bloodType + ", bloodAmount=" + bloodAmount
				+ ", confirmation=" + confirmation + ", bloodBank=" + bloodBank + ", hospital=" + hospital + "]";
	}

}
