package com.revature.model;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ERSReimbursement")

public class ERSReimbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REIMB_ID")
	private int id;

	@Column(name = "REIMB_AMOUNT")
	private int amount;

	@Column(name = "REIMB_SUBMITTED")
	private Timestamp submitted;

	@Column(name = "REIMB_RESOLVED")
	private Timestamp resolved;

	@Column(name = "REIMB_DESCRIPTION")
	private String description;

	@Column(name = "REIMB_RECEIPT")
	private Blob receipt;

	@ManyToOne
	@JoinColumn(name = "REIMB_AUTHOR")
	private ERSUsers author;

	@ManyToOne
	@JoinColumn(name = "REIMB_RESOLVER")
	private ERSUsers resolver;

	@ManyToOne
	@JoinColumn(name = "REIMB_STATUS_ID")
	private ERSReimbursementStatus status;

	@ManyToOne
	@JoinColumn(name = "REIMB_TYPE_ID")
	private ERSReimbursementType type;

	public ERSReimbursement() {
		super();
	}

	public ERSReimbursement( int amount, Timestamp submitted, Timestamp resolved, String description, 
			ERSUsers author) {
		super();
//		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
//		this.receipt = receipt;
		this.author = author;
//		this.resolver = resolver;
//		this.status = status;
//		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public ERSUsers getAuthor() {
		return author;
	}

	public void setAuthor(ERSUsers author) {
		this.author = author;
	}

	public ERSUsers getResolver() {
		return resolver;
	}

	public void setResolver(ERSUsers resolver) {
		this.resolver = resolver;
	}

	public ERSReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ERSReimbursementStatus status) {
		this.status = status;
	}

	public ERSReimbursementType getType() {
		return type;
	}

	public void setType(ERSReimbursementType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, author, description, id, receipt, resolved, resolver, status, submitted, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ERSReimbursement other = (ERSReimbursement) obj;
		return amount == other.amount && Objects.equals(author, other.author)
				&& Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(receipt, other.receipt) && Objects.equals(resolved, other.resolved)
				&& Objects.equals(resolver, other.resolver) && Objects.equals(status, other.status)
				&& Objects.equals(submitted, other.submitted) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "ERSReimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", receipt=" + receipt + ", author=" + author
				+ ", resolver=" + resolver + ", status=" + status + ", type=" + type + "]";
	}


}
