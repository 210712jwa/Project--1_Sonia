package com.revature.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.model.ERSUsers;
import com.revature.service.ReimbursementService;
import com.revature.service.AuthorizationService;
import com.revature.dto.EditReimbursementDTO;
import com.revature.model.ERSReimbursement;

public class ManagerActionsController implements Controller {

	private AuthorizationService authService;
	private ReimbursementService ReimbursementService;

	public ManagerActionsController() {
		this.authService = new AuthorizationService();
		this.ReimbursementService = new ReimbursementService();
	}

	private Handler filterRequestsByStatus = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		authService.guard(ctx);
		ERSUsers currentUser = (ERSUsers) session.getAttribute("currentUser");
		
		String status = ctx.pathParam("status");

		List<ERSReimbursement> reimbursements = ReimbursementService.getAllReimbursementsFromStatus(status);

		ctx.json(reimbursements);

	};
	
	private Handler processRequest = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		authService.guard(ctx);
		
	String status = ctx.pathParam("status");
		String reimbId = ctx.pathParam("reimbid"); 
		ERSUsers currentUser = (ERSUsers) session.getAttribute("currentUser");
		
//		EditReimbursementDTO reimbursementEditInfo = ctx.bodyAsClass(EditReimbursementDTO.class);
		ERSReimbursement editedReimbursement = ReimbursementService.editReimbursement(currentUser, reimbId, status);
		ctx.json(editedReimbursement);
		
	};
	
	private Handler getAllRequests = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		authService.guard(ctx);
		
		
		List<ERSReimbursement> reimbursements = ReimbursementService.getAllReimbursements();
		
		ctx.json(reimbursements);
	};
	@Override
	public void mapEndpoints(Javalin app) {
		
		app.get("/getAllRequests", getAllRequests);
		app.get("/filterRequestsByStatus/:status", filterRequestsByStatus);
//		app.put("/user/:userid/processRequest/:reimbid", processRequest);
		app.put("processRequest/:reimbid/:status", processRequest);
	}
}
