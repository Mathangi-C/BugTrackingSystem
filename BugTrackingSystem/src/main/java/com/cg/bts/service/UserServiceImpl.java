package com.cg.bts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bts.entities.User;
import com.cg.bts.repositories.UserRepository;


	@Service
	public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository usRepo;

	@Override
	public List<User> getAllUsers(){
		return usRepo.findAll();
	}


	@Override
	public Optional<User> getUser(long userId) {
		
		return usRepo.findById(userId);
	}



	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return usRepo.save(user);
	}


	@Override
	public User updateUser(User upduser) {
		// TODO Auto-generated method stub
		return usRepo.save(upduser);
	}


	/*@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User logout(User user) {
		// TODO Auto-generated method stub
		return null;
	}
*/

	
	}

