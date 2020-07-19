package com.neu.bloodbankmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.bloodbankmanagement.exception.HospitalException;
import com.neu.bloodbankmanagement.pojo.Hospital;
import com.neu.bloodbankmanagement.pojo.Role;


public class HospitalDao extends DAO {
	
	
	private RoleDao roleDao;
	
	public HospitalDao() {
		roleDao = new RoleDao();
		
	}
	
	public void save(Hospital hospital) throws HospitalException {
		try {
			begin();
			System.out.println("\n\nAfter Begin");
			getSession().save(hospital);
			System.out.println("\n\nAfter getSession().save(hospital)");
			commit();
			System.out.println("\n\nAfter commit");
		}catch(Exception e) {
			System.out.println("\n\nBefore rollback");
			rollback();
			System.out.println("*********************\n\n"+e.getLocalizedMessage()+" "+e.getMessage()+"\n");
			throw new HospitalException("Exception while registering the hospital: "+e.getMessage());	
		}finally {
			close();
		}
		
	}
	
	//get All the Hospitals
	public List<Hospital> getAllHospitals() throws HospitalException {
		List<Hospital> hospitals = new ArrayList<Hospital>();
		try {
			begin();
			Query q = getSession().createQuery("from Hospital");
			hospitals = q.list();
			commit();
			return hospitals;
		} catch (HibernateException e) {
			rollback();
			throw new HospitalException("Could not get hospital " + hospitals, e);
		}finally {
			close();
		}
	}
	//get Hospital by Id
	public Hospital getHospital(long id) throws HospitalException {
		try {
			begin();
			Query q = getSession().createQuery("from Hospital where Id = :id");
			q.setLong("id", id);
			Hospital hospital = (Hospital) q.uniqueResult();
			commit();
			return hospital;
		} catch (HibernateException e) {
			rollback();
			throw new HospitalException("Could not get hospital " + id, e);
		}finally {
			close();
		}
	}
	
	//get Hospital from userName and password
	public Hospital getHospital(String userName, String password) throws HospitalException {
		try {
			begin();
			Query q = getSession().createQuery("from Hospital where userName = :userName AND password = :password");
			q.setString("userName", userName);
			q.setString("password", password);
			Hospital hospital = (Hospital) q.uniqueResult();
			commit();
			return hospital;
		} catch (HibernateException e) {
			rollback();
			throw new HospitalException("Could not get hospital " + userName, e);
		}finally {
			close();
		}
	}
	
	//Authenticates whether email or username is already registered or not
	public boolean AuthenticateHospitalRegistration(Hospital hospital) {
		for(Role role: roleDao.getRoles()) {
			if( role.getEmail().equals(hospital.getEmail())) {
				return true;
			}else if(role.getUserName().equals(hospital.getUserName())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public List<Object[]> getCurrentStock(){
		try {
			begin();
		String sql = "SELECT bb.Name, dh.blood_type, SUM(dh.amount)-(SELECT IFNULL(SUM(br.amount_requested),0) FROM BloodBankManagement.blood_request as br WHERE br.confirmation=\"approve\" AND dh.blood_type=br.blood_type )\n" + 
				"FROM Donation_History as dh\n" + 
				"RIGHT JOIN blood_bank as bb\n" + 
				"ON bb.Id = dh.Blood_Bank_Id \n" + 
				"GROUP BY bb.Name, dh.blood_type;";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List results = query.list();//returns an list of Maps and one single map contains [keys:{Query,Name, BloodType}, Values:{Query Result,Name, BloodType}]
		System.out.println("**********CurrentStock\n"+results.get(0));
		commit();
		return results;
		}catch(Exception e) {
			System.out.println("Exception in current stock");
			rollback();
			System.out.println("Can not fetch current stocks: "+e.getMessage());
			return null;
		}finally {
			close();
		}
		
	}
	
	

}
