package com.cg.bts.service;

import java.util.List;
import java.util.Optional;

import com.cg.bts.entities.Bug;

public interface BugService {

	public Bug createBug(Bug bug);
	public Bug updateBug(Bug bug);
	public Optional<Bug> getBug(long id);                       //optional added
	public List<Bug> getAllBugs();
	public List<Bug> getAllBugsByStatus(String status);
	public void deleteBug(long id);                                 //of type bug changed to void
}
