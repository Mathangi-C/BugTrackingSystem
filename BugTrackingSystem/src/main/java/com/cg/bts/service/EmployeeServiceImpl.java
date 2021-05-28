package com.cg.bts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bts.entities.Employee;
import com.cg.bts.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public Employee createEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return empRepo.save(emp);
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return empRepo.save(emp);
	}

	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		empRepo.deleteById(id);
	}

	@Override
	public Optional<Employee> getEmployee(long id) {
		// TODO Auto-generated method stub
		return empRepo.findById(id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

}
