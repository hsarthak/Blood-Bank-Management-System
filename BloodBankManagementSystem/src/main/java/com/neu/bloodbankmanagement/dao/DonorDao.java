package com.neu.bloodbankmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.bloodbankmanagement.exception.DonorException;
import com.neu.bloodbankmanagement.pojo.Donor;
import com.neu.bloodbankmanagement.pojo.Role;


public class DonorDao extends DAO {
	
	
	private RoleDao roleDao;
	
	public DonorDao() {
		roleDao = new RoleDao();
		
	}
	
	public void save(Donor donor) throws DonorException {
		try {
			begin();
			System.out.println("\n\nAfter Begin");
			getSession().save(donor);
			System.out.println("\n\nAfter getSession().save(donor)");
			commit();
			System.out.println("\n\nAfter commit");
		}catch(Exception e) {
			System.out.println("\n\nBefore rollback");
			rollback();
			System.out.println("*********************\n\n"+e.getLocalizedMessage()+" "+e.getMessage()+"\n");
			throw new DonorException("Exception while registering the donor: "+e.getMessage());	
		}finally {
			close();
		}
		
	}
	
	public Donor getDonor(long id) throws DonorException {
		try {
			begin();
			Query q = getSession().createQuery("from Donor where Id = :id");
			q.setLong("id", id);
			Donor donor = (Donor) q.uniqueResult();
			commit();
			return donor;
		} catch (HibernateException e) {
			rollback();
			throw new DonorException("Could not get donor " + id, e);
		}finally {
			close();
		}
	}
	
	//get All donors
	public List<Donor> getAllDonors() throws DonorException {
		List<Donor> donors =new ArrayList<Donor>();
		try {
			begin();
			Query q = getSession().createQuery("from Donor");
			 donors = q.list();
			commit();
			return donors;
		} catch (HibernateException e) {
			rollback();
			throw new DonorException("Could not get donor " +donors, e);
		}finally {
			close();
		}
	}
	
	//get Donor from the email entered
	public Donor getDonor(String email) throws DonorException {
		try {
			begin();
			Query q = getSession().createQuery("from Donor where email = :email");
			q.setString("email", email);
			Donor donor = (Donor) q.uniqueResult();
			commit();
			return donor;
		} catch (HibernateException e) {
			rollback();
			throw new DonorException("Could not get donor " + email, e);
		}finally {
			close();
		}
	}
	
	//get Donor from the userName and password entered
	public Donor getDonor(String userName, String password) throws DonorException {
		try {
			begin();
			Query q = getSession().createQuery("from Donor where userName = :userName AND password= :password");
			q.setString("userName", userName);
			q.setString("password", password);
			Donor donor = (Donor) q.uniqueResult();
			commit();
			return donor;
		} catch (HibernateException e) {
			rollback();
			throw new DonorException("Could not get donor " + userName, e);
		}finally {
			close();
		}
	}
	
	//get All donor Emails 
	public List<String> getAllDonorEmails() throws DonorException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(Donor.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("email"));
			crit.setProjection(projList);
			List donorEmails = crit.list();
			System.out.println("******Inside DonorDao.getAllDonorEmails");
			commit();
			return donorEmails;
		} catch (HibernateException e) {
			rollback();
			throw new DonorException("Could not get donor email ");
		}finally {
			close();
		}
	}
	
	//Authenticates whether email or username is already registered or not
	public boolean AuthenticateDonorRegistration(Donor donor) {
		for(Role role: roleDao.getRoles()) {
			if( role.getEmail().equals(donor.getEmail())) {
				return true;
			}else if(role.getUserName().equals(donor.getUserName())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	

}
