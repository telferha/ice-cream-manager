package io.github.pbremer.icecreammanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleGreetingController {
    
    public static final String HELLO = "Hello, team formally known as 'Team 6'!";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleGreetingController.class);
    
    @RequestMapping("/")
    public String hello() {
    	LOGGER.info("There was a request to show the greeting");
        return HELLO;
    }
}
