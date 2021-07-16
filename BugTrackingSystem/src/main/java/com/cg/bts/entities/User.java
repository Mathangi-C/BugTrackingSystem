package com.cg.bts.entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name = "Users")

public class User {
	@Id
	private long userId;
	@NotEmpty(message = "Password name is required")
	private String password;
	@NotEmpty(message = "Role name is required")
	@NotNull
	@Size
private String role;
@OneToOne(cascade=CascadeType.PERSIST)
@JsonIgnore
@JoinColumn(name="Admin")
private Admin admin;
@OneToOne(cascade=CascadeType.PERSIST)
@JoinColumn(name="employee")
private Employee employee;
	
    public User() {
        super();
    }
 
    public User(Long userId, String password, String role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }
 
    public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    @Override
    public String toString() {
       // return this.userId + "/" + this.password;
        return "User [userId=" + userId + ", password=" + password + ", role=" + role + "]";
    }
 
}



