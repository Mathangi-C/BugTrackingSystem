package com.cg.bts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bts.entities.Employee;
import com.cg.bts.exception.ResourceNotFoundException;
import com.cg.bts.repositories.EmployeeRepository;
import com.cg.bts.service.EmployeeService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	//@Autowired
	//private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService empService;
	
	/*@RequestMapping(value= "/employee/create", method= RequestMethod.POST)
	public Employee addEmployee(@RequestBody Employee newemp) {       
	        return empService.createEmployee(newemp);
	}*/
	
	/*   @RequestMapping("/")
	    public String home() {
	        return "Hello Guest, Welcome to this page";
	    } */
	
	 @PostMapping("/employee/create")
	    public Employee createEmployee(@Valid @RequestBody Employee employee) {    	
		 return empService.createEmployee(employee);
	       // return employeeRepository.save(employee);
	    }


	/*@RequestMapping(value="/employee/all", method=RequestMethod.GET)
	public List<Employee> getAllEmployees(){
		return empService.getAllEmployees();
	}*/
	
	 @GetMapping("/employee/all")
	    public List<Employee> getAllEmployees() {
		 return empService.getAllEmployees();
	        //return employeeRepository.findAll();
	    }
	
	/*@RequestMapping(value= "/employee/{id}", method= RequestMethod.GET)
    public Employee getEmployee(@PathVariable int id) {
        Optional<Employee> emp =  empService.getEmployee(id);
        if(!emp.isPresent()) {
             return new Employee();         // returns empty Employee object
        }
        else
            return emp.get();        // returns Employee object with data
    }*/
	
	 @GetMapping("/employee/id/{id}")
	    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
	        throws ResourceNotFoundException {
	        Employee employee = empService.getEmployee(employeeId)
	          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : " + employeeId));
	        return ResponseEntity.ok().body(employee);
	    }
	 
	
	/*@RequestMapping(value= "/employee/update/{id}", method= RequestMethod.PUT)
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
}*/

	    @PutMapping("/employee/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
         @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = empService.getEmployee(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : " + employeeId));

        employee.setEmpId(employeeDetails.getEmpId());
        employee.setEmpName(employeeDetails.getEmpName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setEmpContact(employeeDetails.getEmpContact());
       // employee.setProjectId(employeeDetails.getProjectId());
        
        final Employee updatedEmployee = empService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

	/*@RequestMapping(value= "/employee/delete/{id}", method= RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable int id) {

	        Optional<Employee> emp =  empService.getEmployee(id);
	        if(!emp.isPresent())
	                System.out.println("Could not find employee with id - " + id);
	        else   
	                empService.deleteEmployee(id);
	}*/
	
	 @DeleteMapping("/employee/delete/{id}")
	    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
	         throws ResourceNotFoundException {
	        Employee employee = empService.getEmployee(employeeId)
	       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : " + employeeId));
	        
	        empService.deleteEmployee(employeeId);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("Deleted", Boolean.TRUE);
	        return response;
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
