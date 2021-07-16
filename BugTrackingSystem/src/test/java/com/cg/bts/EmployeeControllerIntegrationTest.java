
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

import com.cg.bts.entities.Employee;

@SpringBootTest(classes = BugTrackingSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
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
    public void testcreateEmployee() {
        Employee employee = new Employee();
        employee.setEmpId(115);
        employee.setEmpName("jake");
        employee.setEmail("jake@gmail.com");
        employee.setEmpContact("896532895");
        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/employee/create", employee, Employee.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }*/
    
    
    @Test
    public void testGetAllEmployees() {
       HttpHeaders headers = new HttpHeaders();	//Represents an HTTP request or response entity, consisting of headers and body.
       HttpEntity<String> entity = new HttpEntity<String>(null, headers);
       ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employee/all",
       HttpMethod.GET, entity, String.class);  
       assertNotNull(response.getBody());
   }
    

    @Test
    public void testGetEmployeeById() {
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employee/101", Employee.class);
        assertNotNull(employee);
    }

    @Test
    public void testUpdateEmployee() {
        int id = 105;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employee/" + id, Employee.class);
        employee.setEmpName("James");
        restTemplate.put(getRootUrl() + "/employee/update/" + id, employee);
        Employee updatedEmployee = restTemplate.getForObject(getRootUrl() + "/employee/" + id, Employee.class);
       //  assertEquals(employee.getEmpName(), updatedEmployee.getEmpName());
    }
    

    @Test
    public void testDeleteEmployee() {
         int id = 104;
         Employee employee = restTemplate.getForObject(getRootUrl() + "/employee/" + id, Employee.class);
         restTemplate.delete(getRootUrl() + "/employee/delete/" + id);
         try {
              employee = restTemplate.getForObject(getRootUrl() + "/employee/" + id, Employee.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }

}

