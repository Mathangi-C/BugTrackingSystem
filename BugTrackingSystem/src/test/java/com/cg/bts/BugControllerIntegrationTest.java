package com.cg.bts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

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

import com.cg.bts.entities.Bug;



//@RunWith(SpringRunner.class)
@SpringBootTest(classes = BugTrackingSystemApplication
.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BugControllerIntegrationTest {
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

 /*   @Test
    public void testCreateBug() {
        Bug bug = new Bug();
        bug.setBugId(103);
        bug.setTitle("cannot find symbol");
        bug.setBugDesc("insert appropriate symbol");
        bug.setAssignee("Peter");
       // bug.setStartDate();
       // bug.setEndDate();
        bug.setPriority("high");
        bug.setProgress(60);
        bug.setStatus("active");
        bug.setType("logical error");
        ResponseEntity<Bug> postResponse = restTemplate.postForEntity(getRootUrl() + "/bug/create", bug, Bug.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }*/

     @Test
     public void testGetAllBug() {
        HttpHeaders headers = new HttpHeaders();	//Represents an HTTP request or response entity, consisting of headers and body.
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/bug/all",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetEmplById() {
        Bug bug = restTemplate.getForObject(getRootUrl() + "/bug/byId/101", Bug.class);
        System.out.println("Bug Title : "+bug.getTitle());
        assertNotNull(bug);
    }

    @Test
    public void testupdateBug() {
        int id = 103;
        Bug bug = restTemplate.getForObject(getRootUrl() + "/bug/update/" + id, Bug.class);
        bug.setPriority("medium");
       // bug.setProgress(85);
        restTemplate.put(getRootUrl() + "/bug/update/" + id, bug);
        Bug updatedBug = restTemplate.getForObject(getRootUrl() + "/bug/update/" + id, Bug.class);
      assertNotNull(updatedBug);
	//assertEquals(bug.getPriority(), updatedBug.getPriority());
    }

    @Test
    public void testDeleteBug() {
         int id = 102;
         Bug bug = restTemplate.getForObject(getRootUrl() + "/bug/byId/" + id, Bug.class);
//         assertNotNull(bug);
         restTemplate.delete(getRootUrl() + "/bug/deleteBug/" + id);
         try {
              bug = restTemplate.getForObject(getRootUrl() + "/bug/byId/" + id, Bug.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }
}