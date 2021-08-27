package com.revature.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement_type")
public class ERSReimbursementType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "type")
	private String type;

	public ERSReimbursementType() {
		super();
	}

	public ERSReimbursementType( String type) {
		super();
		
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ERSReimbursementType other = (ERSReimbursementType) obj;
		return id == other.id && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "ERSReimbursementType [id=" + id + ", type=" + type + "]";
	}

}
