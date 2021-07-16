package com.cg.bts.service;


import java.util.List;
import java.util.Optional;

import com.cg.bts.entities.User;

public interface UserService {
	
	//public User createUser(User user);
	//public User login(User user);
	//public User logout(User user);
	
	public List<User> getAllUsers();
	public User addUser(User user);
    public Optional<User> getUser(long id);
	public User updateUser(User upduser);
	
	
}

