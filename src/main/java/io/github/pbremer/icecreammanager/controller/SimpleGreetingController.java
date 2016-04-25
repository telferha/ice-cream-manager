package io.github.pbremer.icecreammanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleGreetingController {
    
    public static final String HELLO = "Hello, team formally known as 'Team 6'!";
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(SimpleGreetingController.class);
            
    //@RequestMapping("/")
    public String hello() {
        LOGGER.debug("At index");
        // return HELLO;
        return "public/index";
    }
    
    @RequestMapping(path={"/dashboard", "/"})
    public String dashboard() {
        LOGGER.debug("At dashboard");
        // return HELLO;
        return "public/dashboard";
    }
    
    @RequestMapping("/alter")
    public String alter() {
        LOGGER.debug("At alter");
        // return HELLO;
        return "public/alter";
    }
    
    @RequestMapping("/inventory")
    public String inventory() {
        LOGGER.debug("At inventory");
        // return HELLO;
        return "public/inventory";
    }
    @RequestMapping("/uploadfile")
    public String uploadfile() {
        LOGGER.debug("At uploadfile");
        // return HELLO;
        return "public/uploadfile";
    }
}
