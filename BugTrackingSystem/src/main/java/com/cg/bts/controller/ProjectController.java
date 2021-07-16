package com.cg.bts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.cg.bts.entities.Project;
import com.cg.bts.exception.ResourceNotFoundException;
import com.cg.bts.repositories.ProjectRepository;
import com.cg.bts.service.ProjectService;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

	@Autowired
	private ProjectService projService;
	
	//@Autowired
  //  private ProjectRepository projRepository;
	
	/*@RequestMapping(value = "/project/all", method=RequestMethod.GET)
	public List<Project>getAllProjects(){
		return projService.getAllProjects();
	}*/
	
	 /*  @RequestMapping("/")
	    public String home() {
	        return "Hello Guest, Welcome to this page";
	    } */
	
	@GetMapping("/project/all")
	public List<Project> getAllProjects() {
		return projService.getAllProjects();
	       // return projRepository.findAll();
	}
	
	
	/*
	@RequestMapping(value= "/project/{id}", method= RequestMethod.GET)
    public Project getProject(@PathVariable long id) {
        Optional<Project> proj =  projService.getProject(id);
        if(!proj.isPresent()) {
             return new Project();         // returns empty Project object
        }
        else
            return proj.get();        // returns Project object with data
    }
   */
	
	@GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long projectId)
        throws ResourceNotFoundException {
        Project project = projService.getProject(projectId)
          .orElseThrow(() -> new ResourceNotFoundException("Project not found for this id : " + projectId));
        return ResponseEntity.ok().body(project);
    }
	
	/*
	@RequestMapping(value= "/project/delete/{id}", method= RequestMethod.DELETE)
	public void deleteProjectById(@PathVariable long id) {

	        Optional<Project> proj =  projService.getProject(id);
	        if(!proj.isPresent())
	                System.out.println("Could not find project with id - " + id);
	        else   
	                projService.deleteProjectById(id);
	}*/
	
	
	@DeleteMapping("/project/delete/{id}")
    public Map<String, Boolean> deleteProject(@PathVariable(value = "id") Long projectId)
         throws ResourceNotFoundException {
        Project project= projService.getProject(projectId)
       .orElseThrow(() -> new ResourceNotFoundException("Project not found for this id : " + projectId));
        
        projService.deleteProjectById(projectId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
	
	/*
	@RequestMapping(value= "/project/add", method= RequestMethod.POST)
	public Project addProject(@RequestBody Project newproj) {       
	        return projService.addProject(newproj);
	}*/
	
	@PostMapping("/project/add")
    public Project addProject(@Valid @RequestBody Project project ) {    	
		return projService.addProject(project);
      //  return projRepository.save(project);
    }

	/*
	@RequestMapping(value= "/project/update/{id}", method= RequestMethod.PUT)
    public Project updateProject(@RequestBody Project updproj, @PathVariable long id) {
        Optional<Project> proj =  projService.getProject(id);
        if (!proj.isPresent()) {
                     System.out.println("Could not find project with id - " + id);
                     return new Project();
        }
        else  {
                     updproj.setProjId(id);
                     return projService.updateProject(updproj);
        }
    }*/
	
	 @PutMapping("/project/update/{id}")
	 public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Long projectId,
			@Valid @RequestBody Project projectDetails) throws ResourceNotFoundException {
	        Project project = projService.getProject(projectId)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : " + projectId));

	        project.setProjId(projectDetails.getProjId());
	        project.setProjManager(projectDetails.getProjManager());
	        project.setProjName(projectDetails.getProjName());
	        project.setProjStatus(projectDetails.getProjStatus());
	        final Project updatedProject = projService.updateProject(project);
	        return ResponseEntity.ok(updatedProject);
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
