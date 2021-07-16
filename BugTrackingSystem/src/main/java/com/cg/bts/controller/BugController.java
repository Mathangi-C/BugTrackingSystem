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

import com.cg.bts.entities.Bug;
import com.cg.bts.exception.ResourceNotFoundException;
import com.cg.bts.repositories.BugRepository;
import com.cg.bts.service.BugService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BugController {

	@Autowired
	private BugService bugService;
	
//	@Autowired
  //  private BugRepository bugRepository;
	
/*	@RequestMapping(value= "/bug/create", method= RequestMethod.POST)
	public Bug createBug(@RequestBody Bug newbug) {       
	        return bugService.createBug(newbug);
	}*/
	
	/*   @RequestMapping("/")
	    public String home() {
	        return "Hello Guest, Welcome to this page";
	    }*/
	
	 @PostMapping("/bug/add")
	    public Bug createBug(@Valid @RequestBody Bug bug) {    	
	    return bugService.createBug(bug);
		 //return bugRepository.save(bug);
	 }
	
/*	@RequestMapping(value="/bug/all", method=RequestMethod.GET)
	public List<Bug> getAllBugs(){
		return bugService.getAllBugs();
	}*/

	    @GetMapping("/bug/all")
	    public List<Bug> getAllBugs() {
	    	return bugService.getAllBugs();
	      //  return bugRepository.findAll();
	    }
	 
	
/*	@RequestMapping(value= "/bug/update/{id}", method= RequestMethod.PUT)
    public Bug updateBug(@RequestBody Bug updbug, @PathVariable long id) {
        Optional<Bug> bug =  bugService.getBug(id);
        if (!bug.isPresent()) {
                     System.out.println("Could not find any bug with id - " + id);
                     return new Bug();
        }
        else  {
                     updbug.setBugId(id);
                     return bugService.updateBug(updbug);
        }
	
	}*/
	
	 @PutMapping("/bug/update/{id}")
	    public ResponseEntity<Bug> updateBug(@PathVariable(value = "id") Long bugId,
	         @Valid @RequestBody Bug bugDetails) throws ResourceNotFoundException {
	     //   Bug bug = bugRepository.findById(bugId)
		 Bug bug =  bugService.getBug(bugId)
	        .orElseThrow(() -> new ResourceNotFoundException("Bug not found for this id : " + bugId));

	        bug.setBugId(bugDetails.getBugId());
	        bug.setTitle(bugDetails.getTitle());
	        bug.setBugDesc(bugDetails.getBugDesc());
	        bug.setAssignee(bugDetails.getAssignee());
	        bug.setStartDate(bugDetails.getStartDate());
	        bug.setEndDate(bugDetails.getEndDate());
	        bug.setPriority(bugDetails.getPriority());
	        bug.setProgress(bugDetails.getProgress());
	        bug.setStatus(bugDetails.getStatus());
	        bug.setType(bugDetails.getType());
	    //    bug.setProjectId(bugDetails.getProjectId());
	     //   final Bug updatedBug = bugRepository.save(bug);
	        final Bug updatedBug = bugService.updateBug(bug);
	        return ResponseEntity.ok(updatedBug);
	    }
	
/*	@RequestMapping(value= "/bug/{id}", method= RequestMethod.GET)
    public Bug getBug(@PathVariable long id) {
        Optional<Bug> bug =  bugService.getBug(id);
        if(!bug.isPresent()) {
             return new Bug();         // returns empty Bug object
        }
        else
            return bug.get();        // returns Employee Bug with data
    }
*/	
	 
	  @GetMapping("/bug/id/{id}")
	    public ResponseEntity<Bug> getBugById(@PathVariable(value = "id") Long bugId)
	        throws ResourceNotFoundException {
	       // Bug bug = bugRepository.findById(bugId)
		  Bug bug = bugService.getBug(bugId)
	          .orElseThrow(() -> new ResourceNotFoundException("Bug not found for this id : " + bugId));
	        return ResponseEntity.ok().body(bug);
	    }
	 
	 @GetMapping("/bug/all/status{status}")
	//@RequestMapping(value="/bug/all/status/{status}", method=RequestMethod.GET)
	public List<Bug> getAllBugsByStatus(@PathVariable(value="status") String status) {
		return bugService.getAllBugsByStatus(status);
		
	}
	
	
	
	/* @RequestMapping(value= "/bug/delete/{id}", method= RequestMethod.DELETE)
	public void deleteBug(@PathVariable long id) {

	        Optional<Bug> bug =  bugService.getBug(id);
	        if(!bug.isPresent())
	                System.out.println("Could not find bug with id - " + id);
	        else   
	                bugService.deleteBug(id);
	}*/
	
	@DeleteMapping("/bug/delete/{id}")
    public Map<String, Boolean> deleteBug(@PathVariable(value = "id") Long bugId)
         throws ResourceNotFoundException {
      //  Bug bug = bugRepository.findById(bugId)
		   Bug bug =  bugService.getBug(bugId)
       .orElseThrow(() -> new ResourceNotFoundException("Bug not found for this id : " + bugId));
        
      //  bugRepository.delete(bug);
        bugService.deleteBug(bugId);
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
