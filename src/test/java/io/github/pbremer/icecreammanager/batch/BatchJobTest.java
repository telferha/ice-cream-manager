/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.pbremer.icecreammanager.Application;
import io.github.pbremer.icecreammanager.service.CityService;
import io.github.pbremer.icecreammanager.service.ZoneService;

/**
 * @author Patrick Bremer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest(randomPort = true)
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "spring.datasource.url = jdbc:h2:~/local;DB_CLOSE_ON_EXIT=FALSE" })
public class BatchJobTest {

    private static final Logger log = LoggerFactory.getLogger(BatchJobIT.class);

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job job;

    @Autowired
    private CityService service;

    @Autowired
    private ZoneService zoneService;

    @Test
    public void testCityFlow() throws JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException, IOException {

	JobExecution jobExecution = launcher.run(job, new JobParametersBuilder()
	        .addLong("time", System.currentTimeMillis(), true)
	        .addString("input.file.name",
	                "classpath:input-files/city/cityUpload.txt", false)
	        .toJobParameters());

	assertThat("Exit status is not COMEPLETE", jobExecution.getExitStatus(),
	        equalTo(ExitStatus.COMPLETED));
	assertThat("City data is not stored",
	        service.existsAndIsActive("Dearborn"), equalTo(true));
	assertThat("Zone data is not stored",
	        zoneService.existsAndIsActive("Dearborn 1"), equalTo(true));
	assertThat("Zone data is not stored",
	        zoneService.existsAndIsActive("Dearborn 2"), equalTo(true));
	assertThat("Zone data is not stored",
	        zoneService.existsAndIsActive("Dearborn 3"), equalTo(true));
    }

}
