package com.cg.bts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bts.entities.Project;
import com.cg.bts.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectRepository projRepo;
	
	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return projRepo.findAll();
	}

	@Override
	public Optional<Project> getProject(long projid) {
		// TODO Auto-generated method stub
		return projRepo.findById(projid);
	}

	@Override
	public void deleteProjectById(long projid) {
		// TODO Auto-generated method stub
		projRepo.deleteById(projid);
		
	}

	@Override
	public Project addProject(Project proj) {
		// TODO Auto-generated method stub
		return projRepo.save(proj);
	}

	@Override
	public Project updateProject(Project proj) {
		// TODO Auto-generated method stub
		return projRepo.save(proj);
	}

	
	
	

}
