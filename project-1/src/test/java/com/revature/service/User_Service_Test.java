package com.revature.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import com.revature.dao.ReimbursementDAO;
import com.revature.dao.UserDAO;
import com.revature.dto.AddRemibursementDTO;
import com.revature.dto.EditReimbursementDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.InvalidLoginException;
import com.revature.exception.NotAuthorizedException;
import com.revature.model.ERSUsers;
import com.revature.model.ERSReimbursement;

public class User_Service_Test {
	private ReimbursementService reimbursementService;
	private UserDAO userDao;
	private ReimbursementDAO reimbursementDao;
	
	 

	    @Before
	    public void setup() {

	       reimbursementService = mock(ReimbursementService.class);
	    }

	    @Test
	    public void testGetAllERSUsers() {
	        Object users = ( (Object) reimbursementService);

	        Assert.assertTrue((boolean) ((Object) users));

	        Object users1 = ( (Object) verify(reimbursementService));
	    }

		

	

}
