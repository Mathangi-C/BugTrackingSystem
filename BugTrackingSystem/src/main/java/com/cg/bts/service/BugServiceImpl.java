package com.cg.bts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bts.entities.Bug;
import com.cg.bts.repositories.BugRepository;


@Service
public class BugServiceImpl implements BugService {
	
	@Autowired
	private BugRepository bugRepo;

	@Override
	public Bug createBug(Bug bug) {
		// TODO Auto-generated method stub
		return bugRepo.save(bug);
	}

	@Override
	public Bug updateBug(Bug bug) {
		// TODO Auto-generated method stub
		return bugRepo.save(bug);
	}

	@Override
	public Optional<Bug> getBug(long id) {
		// TODO Auto-generated method stub
		return bugRepo.findById(id);
	}

	@Override
	public List<Bug> getAllBugs() {
		// TODO Auto-generated method stub
		return bugRepo.findAll();
	}

	@Override
	public List<Bug> getAllBugsByStatus(String status) {
		// TODO Auto-generated method stub
		return bugRepo.findByStatus(status);
	}

	@Override
	public void deleteBug(long id) {  							//Of type Bug changed to void
		// TODO Auto-generated method stub
		bugRepo.deleteById(id);
	}

	
	

}
