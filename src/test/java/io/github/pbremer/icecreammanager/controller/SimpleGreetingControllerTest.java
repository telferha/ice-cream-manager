package io.github.pbremer.icecreammanager.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import io.github.pbremer.icecreammanager.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest(randomPort = true)
public class SimpleGreetingControllerTest {
    
    @Value("${local.server.port}")
    private int port;
    
    private RestTemplate restTemplate;
    
    @Before
    public void setup() {
        restTemplate = new TestRestTemplate();
    }
    
    @Test
    public void helloTest() {
        
        @SuppressWarnings("unchecked")
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port, String.class, Collections.EMPTY_MAP);
        
        assertThat("Response body was not the same", response.getBody(), containsString(SimpleGreetingController.HELLO));
        assertThat("HTTP response code was not 200 - OK", response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    
}
