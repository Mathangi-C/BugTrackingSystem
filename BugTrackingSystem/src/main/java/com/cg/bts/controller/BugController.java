package com.cg.bts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bts.entities.Bug;
import com.cg.bts.service.BugService;


@RestController
public class BugController {

	@Autowired
	private BugService bugService;
	
	@RequestMapping(value= "/bug/create", method= RequestMethod.POST)
	public Bug createBug(@RequestBody Bug newbug) {       
	        return bugService.createBug(newbug);
	}
	
	@RequestMapping(value="/bug/all", method=RequestMethod.GET)
	public List<Bug> getAllBugs(){
		return bugService.getAllBugs();
	}
	
	@RequestMapping(value= "/bug/update/{id}", method= RequestMethod.PUT)
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
	
	}
	
	@RequestMapping(value= "/bug/{id}", method= RequestMethod.GET)
    public Bug getBug(@PathVariable long id) {
        Optional<Bug> bug =  bugService.getBug(id);
        if(!bug.isPresent()) {
             return new Bug();         // returns empty Bug object
        }
        else
            return bug.get();        // returns Employee Bug with data
    }
	
	@RequestMapping(value="/bug/all/status/{status}", method=RequestMethod.GET)
	public List<Bug> getAllBugsByStatus(@PathVariable(value="status") String status){
		return bugService.getAllBugsByStatus(status);
	}
	
	@RequestMapping(value= "/bug/delete/{id}", method= RequestMethod.DELETE)
	public void deleteBug(@PathVariable long id) {

	        Optional<Bug> bug =  bugService.getBug(id);
	        if(!bug.isPresent())
	                System.out.println("Could not find employee with id - " + id);
	        else   
	                bugService.deleteBug(id);
	}
}
