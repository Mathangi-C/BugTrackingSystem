package com.cg.bts.entities;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import com.cg.bts.entities.Employee;
import javax.validation.constraints.NotEmpty;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "projects"})
public class Project {
	
	@Id
	private long projId;
	
	@NotEmpty(message = "Project Manager name is required")
	private String projManager;
	
	@NotEmpty(message = "Project Name is required")
	private String projName;
	
	@NotEmpty(message = "Project Status is required")
	private String projStatus;
	
	@ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
    @JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "empId"), inverseJoinColumns = @JoinColumn(name = "projId"))
	private Set<Employee> employees=new HashSet<>();

	
	
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(long projId, String projManager, String projName, String projStatus /*Set<Employee> employees*/) {
		super();
		this.projId = projId;
		this.projManager = projManager;
		this.projName = projName;
		this.projStatus = projStatus;
		//this.employees = employees;
	}

	public long getProjId() {
		return projId;
	}

	public void setProjId(long projId) {
		this.projId = projId;
	}

	public String getProjManager() {
		return projManager;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	
    
	
}
