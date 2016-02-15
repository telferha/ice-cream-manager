package io.github.pbremer.icecreammanager.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.pbremer.icecreammanager.testconfig.TestControllerConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestControllerConfiguration.class)
public class SimpleGreetingControllerTest {
    
    @Autowired
    private SimpleGreetingController controller;
    
    @Test
    public void helloTest() {
        assertThat("String was not the same", controller.hello(),
                equalTo("public/index"));
    }
    
}
