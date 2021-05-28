package com.cg.bts.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Employee {

	@Id
	private long empId;
	private String empName;
	private String email;
	private String empContact;
	private long projectId;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(long empId, String empName, String email, String empContact, long projectId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.email = email;
		this.empContact = empContact;
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", email=" + email + ", empContact=" + empContact
				+ ", projectId=" + projectId + "]";
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpContact() {
		return empContact;
	}

	public void setEmpContact(String empContact) {
		this.empContact = empContact;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	
	

}
