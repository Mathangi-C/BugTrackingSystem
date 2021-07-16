package com.cg.bts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.cg.bts.entities.User;
import com.cg.bts.exception.ResourceNotFoundException;
import com.cg.bts.repositories.UserRepository;
import com.cg.bts.service.UserService;


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

@Autowired
private UserService userservice;
@Autowired
private UserRepository userrepository;

/*@RequestMapping("/")
public String home() {
    return "Hello Guest, Welcome to this page";
}*/

@RequestMapping(value="/user/all", method=RequestMethod.GET)
public List<User>getAllUsers(){
	return userservice.getAllUsers();
}
@RequestMapping(value="/user/adduser", method=RequestMethod.POST)
public User addUser(@RequestBody User newuser) {
	System.out.println("user = "+newuser);
	return userservice.addUser(newuser);
}		

@PostMapping("/users")
public User createUser(@Valid @RequestBody User user) {    	
    return userrepository.save(user);
}

@PutMapping("/user/{id}")
public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, 
		@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
    User user = userrepository.findById(userId)
    .orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + userId));

    user.setUserId(userDetails.getUserId());
    user.setPassword(userDetails.getPassword());
    user.setRole(userDetails.getRole());
     User updatedUser = userrepository.save(user);
    return ResponseEntity.ok(updatedUser);
}


@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
 
    ex.getBindingResult().getFieldErrors().forEach(error -> 
        errors.put(error.getField(), error.getDefaultMessage()));
     
    return errors;
}


}
