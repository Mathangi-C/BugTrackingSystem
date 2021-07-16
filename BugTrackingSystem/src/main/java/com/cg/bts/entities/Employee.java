package com.cg.bts.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "projects"})
public class Employee {

	@Id
	private long empId;
	@NotEmpty(message = "Name is required")
	private String empName;
	@Email(message = "Not a valid Email")
	@NotEmpty(message = "Email is required")
	private String email;
	@Size(max=10)
	@NotEmpty(message = "Contact Number is required")
	private String empContact;
	//private long projectId;
	
	//@OneToMany
	//private List<Bug> bugs;
	

	@ManyToMany(mappedBy = "employees", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
    private Set<Project> projects=new HashSet<>();
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(long empId, String empName, String email, String empContact/*, long projectId*/) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.email = email;
		this.empContact = empContact;
		//this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", email=" + email + ", empContact=" + empContact
				+ ", projectId="  + "]";
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

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	/*public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}*/

/*	public List<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}*/
	
	

}
