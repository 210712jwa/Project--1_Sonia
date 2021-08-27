package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;


import com.revature.model.ERSReimbursement;

import com.revature.dto.AddRemibursementDTO;
import com.revature.dto.MessageDTO;
import com.revature.model.ERSUsers;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController  implements Controller{

	private  ReimbursementService reimbursementSerivce;
	
	public ReimbursementController() {
		this.reimbursementSerivce = new ReimbursementService();
				
	}
	
	private Handler getAllERSReimbursementBelongingToSpecificERSUsers=(ctx)->{
		HttpSession session = ctx.req.getSession();
		
		if (session.getAttribute("currentUser") == null){
			ctx.json(new MessageDTO("You need to be logged in to perform this action"));
		} else {
			ERSUsers currentUser = (ERSUsers) session.getAttribute("currentUser");
			
			String userId = ctx.pathParam("userid");
			
			if (currentUser.getId() == Integer.parseInt(userId)){
				List<ERSReimbursement> reimbursement = reimbursementSerivce.getAllERSReimbursementFromERSUsersId(userId);
				
				ctx.json(reimbursement);
				ctx.status(200);
			}else {
				ctx.json(new MessageDTO("You are not the user that you want to retrieve all reimbursement from"));
				ctx.status(401);
			}
		
	}

};

private Handler addERSReimbursementToERSUsers=(ctx)->{
	HttpSession session = ctx.req.getSession();
	
	if (session.getAttribute("currentUser") == null){
		ctx.json(new MessageDTO("You need to be logged in to perform this action"));
	} else {
		ERSUsers currentUser = (ERSUsers) session.getAttribute("currentUser");
		
		String userId = ctx.pathParam("userid");
		
		if (currentUser.getId() == Integer.parseInt(userId)){
			AddRemibursementDTO reimbursementToAdd = ctx.bodyAsClass(AddRemibursementDTO.class);
			
			ERSReimbursement addedReimbursement = reimbursementSerivce.addERSReimbursementToERSUsers(reimbursementToAdd,userId);
			ctx.status(200);
			ctx.json(addedReimbursement);
		}else {
			ctx.json(new MessageDTO("You are not the user that you want to retrieve all reimbursement from"));
			ctx.status(401);
		}
	
}

};

@Override
public void mapEndpoints(Javalin app) {
	app.get("/user/:userid/reimbursement", getAllERSReimbursementBelongingToSpecificERSUsers);
	app.post("/user/:userid/reimbursement", addERSReimbursementToERSUsers);
}
}
