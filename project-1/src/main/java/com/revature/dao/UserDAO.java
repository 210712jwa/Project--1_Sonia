package com.revature.dao;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.model.ERSUsers;
import com.revature.util.SessionFactorySingleton;

public class UserDAO {

	public ERSUsers getUserByUsernameAndPassword(String username, String password) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		
		try {
			ERSUsers user = (ERSUsers) session.createQuery("FROM ERSUsers u WHERE u.username = :username AND u.password = :password")
					.setParameter("username", username)
					.setParameter("password", password)
					.getSingleResult();
			
			return user;
		} catch(NoResultException e) {			
			return null;
		} finally {
			session.close();
		}

	}
	
}
