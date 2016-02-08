package io.github.pbremer.icecreammanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleGreetingController {
    
    public static final String HELLO = "Hello, Group 6!";
    
    @RequestMapping("/")
    public String hello() {
        return HELLO;
    }
}
