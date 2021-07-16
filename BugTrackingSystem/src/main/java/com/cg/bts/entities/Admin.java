package com.cg.bts.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

	@Component
	@Entity
	@Table(name = "admin")
	public class Admin {

		@Id
		private long adminId;
		private String adminName;
		private String adminContact;
		
		public Admin() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Admin(long adminId, String adminName, String adminContact) {
		this.adminId= adminId;
		this.adminName = adminName;
		this.adminContact = adminContact;
	}
	    public long getAdminId() {
			return adminId;
		}

		public void setUserId(long adminId) {
			this.adminId = adminId;
		}

		public String getAdminName() {
	        return adminName;
	    }
	 
	    public void setAdminName(String adminName) {
	        this.adminName = adminName;
	    }
	 
	    public String getAdminContact() {
	        return adminContact;
	    }
	 
	    public void setAdminContact(String adminContact) {
	        this.adminContact = adminContact;
	    }
	 
	    @Override
	    public String toString() {
	       // return this.userId + "/" + this.password;
	        return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminContact=" + adminContact + "]";
	    }
	}

