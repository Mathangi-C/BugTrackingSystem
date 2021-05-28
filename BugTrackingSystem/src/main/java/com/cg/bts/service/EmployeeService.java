package com.cg.bts.service;

import java.util.List;
import java.util.Optional;

import com.cg.bts.entities.Employee;

public interface EmployeeService {

	public Employee createEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public void deleteEmployee(long id);							//of type Employee 
	public Optional<Employee> getEmployee(long id);
	public  List<Employee> getAllEmployees();
}
