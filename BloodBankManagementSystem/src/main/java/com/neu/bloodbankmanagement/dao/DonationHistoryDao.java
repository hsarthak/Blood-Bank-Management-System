package com.neu.bloodbankmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.neu.bloodbankmanagement.exception.DonationHistoryException;
import com.neu.bloodbankmanagement.exception.DonorException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.DonationHistory;
import com.neu.bloodbankmanagement.pojo.Hospital;

public class DonationHistoryDao extends DAO {
	
	
	public void save(DonationHistory donationHistory) throws DonationHistoryException {
		try {
			begin();
			System.out.println("\n\nAfter Begin");
			getSession().save(donationHistory);
			System.out.println("\n\nAfter getSession().save(hospital)");
			commit();
			System.out.println("\n\nAfter commit");
		}catch(Exception e) {
			System.out.println("\n\nBefore rollback");
			rollback();
			System.out.println("*********************\n\n"+e.getLocalizedMessage()+" "+e.getMessage()+"\n");
			throw new DonationHistoryException("Exception while registering the DonationHistory: "+e.getMessage());	
		}finally {
			close();
		}
		
	}
	
	//Get all the History of the blood bank
	public List<DonationHistory> getBloodBankDonationHistory(long bloodBankId) throws DonationHistoryException {
		List<DonationHistory> bloodBankHistory= new ArrayList<DonationHistory>();
		try {
			begin();
			Criteria donationHistoryCrit = getSession().createCriteria(DonationHistory.class);
			Criteria bloodBankCrit = donationHistoryCrit.createCriteria("bloodBank");
			bloodBankCrit.add(Restrictions.eq("id",bloodBankId));
			bloodBankHistory=donationHistoryCrit.list();
			commit();
			return bloodBankHistory;
		}catch(HibernateException e) {
			rollback();
			throw new DonationHistoryException("Could not get Donation History");
		}finally {
			close();
		}

	}
	
	//get blood bank stock form beginning
	public List<Object[]> getBloodBankStock(long bloodBankId) throws DonationHistoryException{
		try {
			begin();
			Criteria donationHistoryCrit = getSession().createCriteria(DonationHistory.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.groupProperty("bloodType"));
			projList.add(Projections.sum("bloodAmount"));
			donationHistoryCrit.setProjection(projList);
			donationHistoryCrit.addOrder(Order.asc("bloodType"));
			System.out.println("*****\n\nInside try::DonationHistoryDao.getBloodBankStock() ");
			List<Object[]> results = donationHistoryCrit.list();//list contains two references one is String and other is int
			commit();
			return results;
			
		}catch(HibernateException e){
			System.out.println("*****\n\nInside catch::DonationHistoryDao.getBloodBankStock() ");
			rollback();
			throw new DonationHistoryException("Could not get blood bank stock");
			
		}finally {
			close();
		}
	}
	
	//get donor history
	
	public List<DonationHistory> getDonorHistory(long donorId) throws DonationHistoryException{
		List<DonationHistory> donorHistory = new ArrayList<DonationHistory>();
		try {
			begin();
			Criteria donationHistoryCrit = getSession().createCriteria(DonationHistory.class);
			Criteria donorCrit = donationHistoryCrit.createCriteria("donor");
			donorCrit.add(Restrictions.eq("id",donorId));
			donorHistory=donationHistoryCrit.list();
			System.out.println("\n\n*************1");
			commit();
			return donorHistory;
		}catch(HibernateException e) {
			rollback();
			System.out.println("\n\n*************2");
			throw new DonationHistoryException("Could not get Donation History");
		}finally {
			close();
		}
	
	}
	
	
	//Get blood stock from beginning group by BloodBank Name and Blood_Type
	public List<Object[]> getTotalStocks() throws DonationHistoryException{
		try {
			begin();
			Criteria donationHistoryCrit = getSession().createCriteria(DonationHistory.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.groupProperty("bloodBank"));
			projList.add(Projections.groupProperty("bloodType"));
			projList.add(Projections.sum("bloodAmount"));
			donationHistoryCrit.setProjection(projList);
			donationHistoryCrit.addOrder(Order.asc("bloodBank"));
			donationHistoryCrit.addOrder(Order.asc("bloodType"));
			System.out.println("*****\n\nInside try::DonationHistoryDao.getBloodBankStock() ");
			List<Object[]> results = donationHistoryCrit.list();//list contains three references one is String and other is int
			commit();
			return results;
			
		}catch(HibernateException e){
			System.out.println("*****\n\nInside catch::DonationHistoryDao.getBloodBankStock() ");
			rollback();
			throw new DonationHistoryException("Could not get blood bank stock");
			
		}finally {
			close();
		}
	}

}
