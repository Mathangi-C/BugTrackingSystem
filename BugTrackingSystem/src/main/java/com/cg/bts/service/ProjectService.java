package com.cg.bts.service;

import java.util.List;
import java.util.Optional;

import com.cg.bts.entities.Project;




public interface ProjectService {
	
	public List<Project> getAllProjects();
	public Optional<Project> getProject(long projid);
	public void deleteProjectById(long projid);
	public Project addProject(Project proj);
	public Project updateProject(Project proj);

}