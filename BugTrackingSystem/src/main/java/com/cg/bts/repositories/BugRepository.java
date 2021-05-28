package com.cg.bts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bts.entities.Bug;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {

	List<Bug> findByStatus(String status);
	

}
