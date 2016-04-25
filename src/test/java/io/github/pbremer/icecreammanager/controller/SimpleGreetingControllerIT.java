package io.github.pbremer.icecreammanager.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
 * @RunWith(SpringJUnit4ClassRunner.class)
 * @SpringApplicationConfiguration(Application.class)
 * @WebIntegrationTest(randomPort = true)
 * @ActiveProfiles("local")
 * @TestPropertySource(properties = {
 * "spring.datasource.url = jdbc:h2:~/local;DB_CLOSE_ON_EXIT=FALSE" })
 */
public class SimpleGreetingControllerIT {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    // @Before
    public void setUp() {
	mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    // @Test
    public void helloIT() {
	try {
	    mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(
	            content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
	} catch (final Exception e) {
	    fail();
	}
    }

}
