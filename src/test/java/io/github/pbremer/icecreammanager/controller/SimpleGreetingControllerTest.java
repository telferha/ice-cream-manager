package io.github.pbremer.icecreammanager.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * @RunWith(SpringJUnit4ClassRunner.class)
 * @SpringApplicationConfiguration(TestControllerConfiguration.class)
 */
public class SimpleGreetingControllerTest {

    @Autowired
    private SimpleGreetingController controller;

    // @Test
    public void helloTest() {
	assertThat("String was not the same", controller.hello(),
	        equalTo("public/index"));
    }

}
