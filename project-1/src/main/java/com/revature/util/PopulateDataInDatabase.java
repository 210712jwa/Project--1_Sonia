package com.revature.util;

import java.sql.Blob;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.model.ERSReimbursement;
import com.revature.model.ERSReimbursementStatus;
import com.revature.model.ERSReimbursementType;
import com.revature.model.ERSReimbursementUserRole;
import com.revature.model.ERSUsers;

public class PopulateDataInDatabase {

	public static void main(String[] args) {
		populateERSReimbursementStatus_ERSReimbursementType_ERSReimbursementUserRole();
		addSampleUsers();
		
		addReimbursement();
	}

	

	private static void populateERSReimbursementStatus_ERSReimbursementType_ERSReimbursementUserRole() {
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		ERSReimbursementUserRole admin = new ERSReimbursementUserRole("admin");
		ERSReimbursementUserRole user = new ERSReimbursementUserRole("user");
		session.persist(admin);
		session.persist(user);
		

		ERSReimbursementStatus pending = new ERSReimbursementStatus("pending");
		ERSReimbursementStatus approved = new ERSReimbursementStatus("approved");
		ERSReimbursementStatus denied = new ERSReimbursementStatus("denied");
		session.persist(pending);
		session.persist(approved);
		session.persist(denied);
		
		ERSReimbursementType food = new ERSReimbursementType("food");
		ERSReimbursementType travel = new ERSReimbursementType("travel");
		ERSReimbursementType lodging = new ERSReimbursementType("lodging");
		session.persist(food);
		session.persist(travel);
		session.persist(lodging);
		
		tx.commit();
		session.close();
	}
	
	private static void addSampleUsers() {
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		ERSUsers adminUser1 = new ERSUsers ("Sonia","Sam","sonia.sam@revature.net","sonia1234","1234");
		ERSReimbursementUserRole admin = (ERSReimbursementUserRole) session.createQuery("FROM ERSReimbursementUserRole ur WHERE ur.role = 'admin'").getSingleResult();
		adminUser1.setUserRole(admin);
		session.persist(adminUser1);
		
		ERSReimbursementUserRole  user = (ERSReimbursementUserRole) session.createQuery("FROM ERSReimbursementUserRole ur WHERE ur.role = 'user'").getSingleResult();
		ERSUsers regularUser1 = new ERSUsers("Philips", "Cyril", "cyril@test.com", "Philips12345", "12345");
		regularUser1.setUserRole(user);
		ERSUsers regularUser2 = new ERSUsers("John", "Doe", "johndoe@email.com", "johnny123", "password123");
		regularUser2.setUserRole(user);

		session.persist(regularUser1);
		session.persist(regularUser2);
		
		tx.commit();
		session.close();
	}
	private static void addReimbursement() {
		
		Timestamp submitted = new Timestamp(2021, 8, 16, 12, 30, 52, 12);
		Timestamp resolved = new Timestamp(2021, 8, 17, 3, 32, 36, 37);
		Blob receipt = null;
		 
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
	
		ERSUsers sonia1234 = (ERSUsers) session.createQuery("FROM ERSUsers u WHERE u. username = 'sonia1234'").getSingleResult();
//		ERSUsers Philips12345 = (ERSUsers) session.createQuery("FROM ERSUsers u WHERE u. username = 'Philips12345'").getSingleResult();
//		ERSUsers johnny123 = (ERSUsers) session.createQuery("FROM ERSUsers u WHERE u. username = 'johnny123'").getSingleResult();
		
		ERSReimbursementStatus  pending = (ERSReimbursementStatus) session .createQuery("FROM ERSReimbursementStatus s WHERE s.status = 'pending'").getSingleResult();
		ERSReimbursementStatus  approved = (ERSReimbursementStatus) session .createQuery("FROM ERSReimbursementStatus s WHERE s.status = 'approved'").getSingleResult();
		ERSReimbursementStatus  denied = (ERSReimbursementStatus) session .createQuery("FROM ERSReimbursementStatus s WHERE s.status = 'denied'").getSingleResult();
		
		
		ERSReimbursementType lodging = (ERSReimbursementType) session.createQuery("FROM ERSReimbursementType s WHERE s.type = 'lodging'").getSingleResult();
		ERSReimbursementType travel = (ERSReimbursementType) session.createQuery("FROM ERSReimbursementType s WHERE s.type = 'travel'").getSingleResult();
		ERSReimbursementType food = (ERSReimbursementType) session.createQuery("FROM ERSReimbursementType s WHERE s.type = 'food'").getSingleResult();
		
		
		ERSReimbursement reimbursement1 = new ERSReimbursement(150,null,null,"for Lodging", sonia1234);
		reimbursement1.setAuthor(sonia1234);
		reimbursement1.setStatus(approved);
		reimbursement1.setType(lodging);
		
		ERSReimbursement reimbursement2 = new ERSReimbursement(500,null,null,"for travel", sonia1234);
		reimbursement2.setAuthor(sonia1234);
		reimbursement2.setStatus(denied);
		reimbursement2.setType(travel);
		
		ERSReimbursement reimbursement3 = new ERSReimbursement(100,null,null,"for food", sonia1234);
		reimbursement3.setAuthor(sonia1234);
		reimbursement3.setStatus(pending);
		reimbursement3.setType(food);
		
		session.persist(reimbursement1);
		session.persist(reimbursement2);
		session.persist(reimbursement3);
		
		tx.commit();
		session.close();
	}

}
