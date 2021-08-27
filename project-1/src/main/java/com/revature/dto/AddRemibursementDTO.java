package com.revature.dto;
import com.revature.model.ERSReimbursementType;
import com.revature.model.ERSUsers;
public class AddRemibursementDTO {

	
	private String reimbDescription;
	private int type;
	private int amount;
	


	@Override
	public String toString() {
		return "AddRemibursementDTO [reimbDescription=" + reimbDescription + ", type=" + type + ", Amount=" + amount
				+ "]";
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public AddRemibursementDTO() {
		super();
	}
	
	
}
