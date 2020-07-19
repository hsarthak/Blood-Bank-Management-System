package com.neu.bloodbankmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.neu.bloodbankmanagement.exception.BloodBankException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.Role;


public class BloodBankDao extends DAO {
	
	
	private RoleDao roleDao;
	
	public BloodBankDao() {
		roleDao = new RoleDao();
		
	}
	
	public void save(BloodBank bloodBank) throws BloodBankException {
		try {
			begin();
			System.out.println("\n\nAfter Begin");
			getSession().save(bloodBank);
			System.out.println("\n\nAfter getSession().save(bloodBank)");
			commit();
			System.out.println("\n\nAfter commit");
		}catch(Exception e) {
			System.out.println("\n\nBefore rollback");
			rollback();
			System.out.println("*********************\n\n"+e.getLocalizedMessage()+" "+e.getMessage()+"\n");
			throw new BloodBankException("Exception while registering the BloodBank: "+e.getMessage());	
		}finally {
			close();
		}
		
	}
	
	public List<BloodBank> getAllBloodBanks() throws BloodBankException {
		List<BloodBank> bloodBanks = new ArrayList<BloodBank>();
		try {
			begin();
			Query q = getSession().createQuery("FROM BloodBank");
			bloodBanks = q.list();
			commit();
			return bloodBanks;
		} catch (HibernateException e) {
			rollback();
			throw new BloodBankException("Could not get bloodbanks " + e);
		}finally {
			close();
		}
	}
	
	public BloodBank getBloodBank(long id) throws BloodBankException {
		try {
			begin();
			Query q = getSession().createQuery("from BloodBank where id = :id");
			q.setLong("id", id);
			BloodBank bloodBank = (BloodBank) q.uniqueResult();
			commit();
			return bloodBank;
		} catch (HibernateException e) {
			rollback();
			throw new BloodBankException("Could not get hospital " + id, e);
		}finally {
			close();
		}
	}
	
	//get Bloodbank from the username and password
	public BloodBank getBloodBankSession(String userName, String password) throws BloodBankException {
		try {
			begin();
			Query q = getSession().createQuery("from BloodBank where userName = :userName AND password= :password");
			q.setString("userName", userName);
			q.setString("password", password);
			BloodBank bloodBank = (BloodBank) q.uniqueResult();
			
			System.out.println("********Inside BloodBankDao.getBloodBankSession\n ");
			System.out.println("userName: "+userName+"\npassword:"+password+"\nBloodBank"+bloodBank+"\n");
			System.out.println("********Outside BloodBankDao.getBloodBankSession\n ");
			
			commit();
			return bloodBank;
		} catch (HibernateException e) {
			rollback();
			throw new BloodBankException("Could not get hospital " + userName, e);
		}finally {
			close();
		}
	}
	
	//get first blood bank from name
	public BloodBank getBloodBank(String bloodBankName) throws BloodBankException {
		List<BloodBank> bloodBanks = new ArrayList<BloodBank>();
		try {
			begin();
			Query q = getSession().createQuery("from BloodBank where name = :name");
			q.setString("name", bloodBankName);
			bloodBanks = q.list();
			commit();
			return bloodBanks.get(0);
		} catch (HibernateException e) {
			rollback();
			throw new BloodBankException("Could not get hospital " + bloodBankName, e);
		}finally {
			close();
		}
	}
	
	//Authenticates whether email or username are already registered or not
	public boolean AuthenticateBloodBankRegistration(BloodBank bloodBank) {
		for(Role role: roleDao.getRoles()) {
			if( role.getEmail().equals(bloodBank.getEmail())) {
				return true;
			}else if(role.getUserName().equals(bloodBank.getUserName())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	

}
