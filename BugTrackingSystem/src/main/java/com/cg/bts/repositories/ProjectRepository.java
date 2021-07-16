package com.cg.bts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bts.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
	

}
