package com.revature.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "reimbursement_status")
public class ERSReimbursementStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private int id;
	
	@Column(name = "status")
	private String status;// "pending", "approved", "denied"

	public ERSReimbursementStatus() {
		super();
	}

	public ERSReimbursementStatus( String status) {
		super();
		
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ERSReimbursementStatus other = (ERSReimbursementStatus) obj;
		return id == other.id && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "ERSReimbursementStatus [id=" + id + ", status=" + status + "]";
	}

}
