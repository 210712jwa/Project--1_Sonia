package com.revature.service;

import com.revature.exception.NotAuthorizedException;
import com.revature.model.ERSUsers;

import io.javalin.http.Context;

public class AuthorizationService {

	public void guard(Context ctx) throws NotAuthorizedException {
		ERSUsers user = (ERSUsers) ctx.sessionAttribute("currentUser");
		
		if (user.getUserRole().getId() != 1) {
			throw new NotAuthorizedException("Permission Denied: User is not Admin");
		}
	}
	
}
