package com.poc.accountLogin.model;

import java.io.Serializable;
import java.util.Date;

public class AuditLog implements Serializable{
	String service;
	String operation;
	String info;
	Date date;
	
	public AuditLog(String service, String operation, String info, Date date) {
		super();
		this.service = service;
		this.operation = operation;
		this.info = info;
		this.date = date;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}