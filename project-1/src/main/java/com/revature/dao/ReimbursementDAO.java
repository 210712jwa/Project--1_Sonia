package com.revature.dao;

import java.util.Date;
import java.sql.*;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.dto.AddRemibursementDTO;
import com.revature.dto.EditReimbursementDTO;
import com.revature.model.ERSReimbursement;
import com.revature.model.ERSReimbursementStatus;
import com.revature.model.ERSReimbursementType;
import com.revature.model.ERSUsers;
import com.revature.util.SessionFactorySingleton;

public class ReimbursementDAO {

	public List<ERSReimbursement> getAllERSReimbursementFromERSUsersId(int id) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		List<ERSReimbursement> reimbursement = session
				.createQuery("SELECT e FROM ERSReimbursement e JOIN e.author  WHERE id = :userid")
				.setParameter("userid", id).getResultList();

		tx.commit();
		session.close();
		return reimbursement;
	}

	public ERSReimbursement addERSReimbursementToERSUsers(AddRemibursementDTO reimbursementToAdd, int id) {
		Date date = new Date();
		Timestamp submitted = new Timestamp(date.getTime());
		Blob receipt = null;
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		ERSReimbursement addedReimbursement = new ERSReimbursement();
		
		addedReimbursement.setAuthor(session.get(ERSUsers.class, id));
		addedReimbursement.setType(session.get(ERSReimbursementType.class, reimbursementToAdd.getType()));
		addedReimbursement.setDescription(reimbursementToAdd.getReimbDescription());
		addedReimbursement.setAmount(reimbursementToAdd.getAmount());
		addedReimbursement.setStatus(session.get(ERSReimbursementStatus.class, 1));
		addedReimbursement.setSubmitted(submitted);

		System.out.println(reimbursementToAdd.getReimbDescription());
		System.out.println(reimbursementToAdd.getAmount());
		
		addedReimbursement.setResolved(null);
		addedReimbursement.setResolver(null);
		addedReimbursement.setReceipt(receipt);
		session.persist(addedReimbursement);
		tx.commit();
		session.close();

		return addedReimbursement;
	}

	public List<ERSReimbursement> getAllReimbursementsFromStatus(int status) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		List<ERSReimbursement> reimbursements = session
				.createQuery("SELECT s FROM ERSReimbursement s JOIN s.status u WHERE u.id = :statusid")
				.setParameter("statusid", status).getResultList();
		tx.commit();

		session.close();

		return reimbursements;

	}

	public ERSReimbursement editReimbursement(ERSUsers currentUser, int reimbId, int reimbStatusId) {
		Date date = new Date();
		Timestamp resolved = new Timestamp(date.getTime());

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		ERSReimbursement reimbursementToEdit = session.get(ERSReimbursement.class, reimbId);
		reimbursementToEdit.setResolver(currentUser);
		reimbursementToEdit.setStatus(session.get(ERSReimbursementStatus.class, reimbStatusId));
		reimbursementToEdit.setResolved(resolved);
		session.saveOrUpdate(reimbursementToEdit);

		session.saveOrUpdate(reimbursementToEdit);

		tx.commit();

		session.close();

		return reimbursementToEdit;

	}

	public List<ERSReimbursement> getAllReimbursements() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		List<ERSReimbursement> reimbursements = session.createQuery("SELECT s FROM ERSReimbursement s").getResultList();
		return reimbursements;
	}
}
