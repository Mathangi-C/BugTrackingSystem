package com.cg.bts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.bts.entities.Project;

@SpringBootTest(classes = BugTrackingSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerIntegrationTest {
	
	 @Autowired
     private TestRestTemplate restTemplate;
	 
	 @LocalServerPort
     private int port;
	 
	 private String getRootUrl() {
         return "http://localhost:" + port;
     }
	 
	 @Test
     public void contextLoads() {

     }
	 
	 
	/* @Test
	 public void testaddProject() {
	        Project project=new Project();
	        project.setProjId(2);
	        project.setProjName("RestCrud");
	        project.setProjManager("Jake");
	        project.setProjStatus("Now Started");
	        ResponseEntity<Project> postResponse = restTemplate.postForEntity(getRootUrl() + "/project/add", project, Project.class);
	        assertNotNull(postResponse);
	        assertNotNull(postResponse.getBody());
	  }*/
	 
	 @Test
     public void testGetAllProjects() {
        HttpHeaders headers = new HttpHeaders();	//Represents an HTTP request or response entity, consisting of headers and body.
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/project/all",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
    }
	 
	 @Test
	 public void testGetProjectById() {
	        Project project = restTemplate.getForObject(getRootUrl() + "/project/1", Project.class);
	        assertNotNull(project);
	 }
	 
	 @Test
	 public void testUpdateProject() {
	        int id = 1;
	        Project project = restTemplate.getForObject(getRootUrl() + "/project/" + id, Project.class);
	        project.setProjName("Hello");
	        restTemplate.put(getRootUrl() + "/project/update/" + id, project);
	        Project updatedProject = restTemplate.getForObject(getRootUrl() + "/project/" + id, Project.class);
	//	assertEquals(project.getProjName(), updatedProject.getProjName());
	    }
	 
	 @Test
	 public void testDeleteProject() {
	         int id = 1;
	         Project project = restTemplate.getForObject(getRootUrl() + "/project/" + id, Project.class);
	         restTemplate.delete(getRootUrl() + "/project/delete/" + id);
	         try {
	              project = restTemplate.getForObject(getRootUrl() + "/project/" + id, Project.class);
	         } catch (final HttpClientErrorException e) {
	              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
	         }
	    }

}

