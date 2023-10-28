package com.example.dashboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DashBoardControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllPlans() {
        ResponseEntity<String> response = restTemplate.getForEntity(createURL("/api/plans"), String.class);
        assertResponse(response, HttpStatus.OK);
        // Add more assertions as needed
    }

    @Test
    public void testGetAllSubscribers() {
        ResponseEntity<String> response = restTemplate.getForEntity(createURL("/api/all"), String.class);
        assertResponse(response, HttpStatus.OK);
        // Add more assertions as needed
    }

    @Test
    public void testGetTotalSubscribers() {
        ResponseEntity<String> response = restTemplate.getForEntity(createURL("/api/total"), String.class);
        assertResponse(response, HttpStatus.OK);
        // Add more assertions as needed
    }

  

    @Test
    public void testGetTotalRevenue() {
        ResponseEntity<String> response = restTemplate.getForEntity(createURL("/api/revenue"), String.class);
        assertResponse(response, HttpStatus.OK);
        // Add more assertions as needed
    }

    private String createURL(String uri) {
        return "http://localhost:" + port + uri;
    }
    private void assertResponse(ResponseEntity<String> response, HttpStatus expectedStatus) {
        // Correct usage of assertEquals
        assertEquals(expectedStatus, response.getStatusCode());
        // Add more assertions as needed
    }

}
