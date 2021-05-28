package com.cg.bts.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Bug {

	@Id
	private long bugId;
	private String title;
	private int progress;
	private String bugDesc;
	private String status;
	private Date startDate;
	private Date endDate;
	private String assignee;
	private String type;
	private String priority;
	private long projectId;
	
	@Override
	public String toString() {
		return "Bug [bugId=" + bugId + ", title=" + title + ", progress=" + progress + ", bugDesc=" + bugDesc
				+ ", status=" + status + ", startDate=" + startDate + ", endDate=" + endDate + ", assignee=" + assignee
				+ ", type=" + type + ", priority=" + priority + ", projectId=" + projectId + "]";
	}
	public Bug() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Bug(long bugId, String title, int progress, String bugDesc, String status, Date startDate, Date endDate,
			String assignee, String type, String priority, long projectId) {
		super();
		this.bugId = bugId;
		this.title = title;
		this.progress = progress;
		this.bugDesc = bugDesc;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.assignee = assignee;
		this.type = type;
		this.priority = priority;
		this.projectId = projectId;
	}
	public long getBugId() {
		return bugId;
	}
	public void setBugId(long bugId) {
		this.bugId = bugId;
	}
	public String getBugDesc() {
		return bugDesc;
	}
	public void setBugDesc(String bugDesc) {
		this.bugDesc = bugDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
}
