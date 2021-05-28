package com.cg.bts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bts.entities.Employee;
import com.cg.bts.service.EmployeeService;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@RequestMapping(value= "/employee/create", method= RequestMethod.POST)
	public Employee addEmployee(@RequestBody Employee newemp) {       
	        return empService.createEmployee(newemp);
	}
	

	@RequestMapping(value="/employee/all", method=RequestMethod.GET)
	public List<Employee> getAllEmployees(){
		return empService.getAllEmployees();
	}
	
	@RequestMapping(value= "/employee/{id}", method= RequestMethod.GET)
    public Employee getEmployee(@PathVariable int id) {
        Optional<Employee> emp =  empService.getEmployee(id);
        if(!emp.isPresent()) {
             return new Employee();         // returns empty Employee object
        }
        else
            return emp.get();        // returns Employee object with data
    }
	
	@RequestMapping(value= "/employee/update/{id}", method= RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee updemp, @PathVariable int id) {
        Optional<Employee> emp =  empService.getEmployee(id);
        if (!emp.isPresent()) {
                     System.out.println("Could not find employee with id - " + id);
                     return new Employee();
        }
        else  {
                     updemp.setEmpId(id);
                     return empService.updateEmployee(updemp);
        }
}
	@RequestMapping(value= "/employee/delete/{id}", method= RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable int id) {

	        Optional<Employee> emp =  empService.getEmployee(id);
	        if(!emp.isPresent())
	                System.out.println("Could not find employee with id - " + id);
	        else   
	                empService.deleteEmployee(id);
	}
	
}
