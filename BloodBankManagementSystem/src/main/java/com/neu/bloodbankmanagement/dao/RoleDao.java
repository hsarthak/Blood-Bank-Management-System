package com.neu.bloodbankmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.neu.bloodbankmanagement.exception.RoleException;
import com.neu.bloodbankmanagement.pojo.Hospital;
import com.neu.bloodbankmanagement.pojo.Role;


public class RoleDao extends DAO {
	
	public RoleDao() {
		
	}
	
	public void save(Role role) throws RoleException {
		try {
			begin();
			getSession().save(role);
			commit();
		}catch(Exception e) {
			rollback();
			throw new RoleException("Exception while registering the hospital: "+e.getMessage());	
		}finally {
			close();
		}
		
	}
	
	public ArrayList<Role> getRoles(){
		ArrayList<Role> Roles = new ArrayList<Role>();
		try {
		begin();
		Query q = getSession().createQuery("FROM Role");
		Roles = (ArrayList) q.list();
		}catch(Exception e) {
			rollback();
		}finally {
			close();
		}
		
		return Roles;
		
	}
	
//To get LoggedIn User	
	public Role getLoggedInUser(String userName, String password) {
		Role loggedInUser = new Role();
		
		try {
			begin();
			Query q = getSession().createQuery("FROM Role WHERE userName=:userName AND password=:password");
			//It return only one role instance
			loggedInUser = (Role)q.uniqueResult();
			commit();
		}catch(HibernateException e) {
			rollback();
			System.out.println("\n********Exception in checking the user "+e.getMessage());
			
		}finally {
			close();
			
		}
		return loggedInUser;
		
	}
	
	//to check whether loginuser is present in ROLE table or not
	public boolean checkLoginUser(String userName, String password, String role) {
		Role loginUser = new Role();
		
		try {
			begin();
			Query q = getSession().createQuery("FROM Role WHERE userName=:userName AND password=:password AND role=:role");
			q.setString("userName", userName);
			q.setString("password", password);
			q.setString("role", role);
			//It return only one role instance
			loginUser = (Role)q.uniqueResult();
			System.out.println("Inside RoleDao.checkLoginUser"+loginUser);
			commit();
		}catch(NonUniqueResultException e) {
			//System.out.println("User["+userName+" "+ password+"] does not exist");
			return false;
		}catch(HibernateException e) {
			rollback();
			System.out.println("\n********Exception in checking the user "+e.getMessage());
			
		}finally {
			close();	
		}
		
		return loginUser!=null?true:false;
		
	}
	
	

}
