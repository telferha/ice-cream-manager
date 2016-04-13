/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Patrick Bremer
 */
@ContextConfiguration(locations = { "classpath:batch/batch.xml",
        "classpath:test-batch-configuration.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TruckSalesItemReaderTest {

    @Autowired
    private JobLauncherTestUtils jobLuancherTestUtils;

    // @Test
    public void exampleTruckSalesItemReaderTest() {
	Map<String, JobParameter> parameters =
	        new HashMap<String, JobParameter>();
	parameters.put("input.file.name", new JobParameter(
	        "classpath:input-files/truck-sales/example-truck-sales.txt"));
	JobParameters jobParams = new JobParameters(parameters);

	JobExecution execution = jobLuancherTestUtils
	        .launchStep("truckSalesItemReaderTestJobStep", jobParams);

	assertThat("Exit status is not COMEPLETE", execution.getExitStatus(),
	        equalTo(ExitStatus.COMPLETED));
    }
}
