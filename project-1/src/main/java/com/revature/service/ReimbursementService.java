package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dto.AddRemibursementDTO;
import com.revature.dto.EditReimbursementDTO;
import com.revature.model.ERSReimbursement;
import com.revature.model.ERSUsers;

public class ReimbursementService {

	private ReimbursementDAO reimbursementDao;
	
	public ReimbursementService() {
		this.reimbursementDao = new ReimbursementDAO();
	}
	public List<ERSReimbursement>getAllERSReimbursementFromERSUsersId(String userId){
		int id = Integer.parseInt(userId);
		
		
		List<ERSReimbursement> reimbursement = reimbursementDao.getAllERSReimbursementFromERSUsersId(id);
		return reimbursement;
	}
	public ERSReimbursement addERSReimbursementToERSUsers(AddRemibursementDTO reimbursementToAdd, String userId) {
		int id = Integer.parseInt(userId);
		
		ERSReimbursement reimbursement = reimbursementDao. addERSReimbursementToERSUsers(reimbursementToAdd,id);
		return reimbursement;
	}
	public List<ERSReimbursement> getAllReimbursementsFromStatus(String status) {
		int statusid = Integer.parseInt(status);

		List<ERSReimbursement> reimbursements = reimbursementDao.getAllReimbursementsFromStatus(statusid);
		
		return reimbursements;
	}
	
	
	public ERSReimbursement editReimbursement(ERSUsers currentUser, String reimbIdString, String reimbStatusIdString ) {
		int statusId = Integer.parseInt(reimbStatusIdString);
		int reimbId = Integer.parseInt(reimbIdString);
		
		ERSReimbursement editedReimbursement = reimbursementDao.editReimbursement(currentUser, reimbId, statusId);
		return editedReimbursement;
	}
	
	public List<ERSReimbursement> getAllReimbursements() {
		
		List<ERSReimbursement> reimbursements = reimbursementDao.getAllReimbursements();
		
		return reimbursements;
	}
	
}
