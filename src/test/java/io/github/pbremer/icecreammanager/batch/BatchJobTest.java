/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Date;
import java.util.EnumSet;

import org.junit.Before;
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
import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.entity.InputFileMetaData.FileType;
import io.github.pbremer.icecreammanager.entity.InputFileMetaData.Status;
import io.github.pbremer.icecreammanager.repository.InputFileMetaDataRepository;
import io.github.pbremer.icecreammanager.service.CityService;
import io.github.pbremer.icecreammanager.service.DriverService;
import io.github.pbremer.icecreammanager.service.RouteService;
import io.github.pbremer.icecreammanager.service.TruckInstanceService;
import io.github.pbremer.icecreammanager.service.TruckService;
import io.github.pbremer.icecreammanager.service.WarehouseInventoryService;
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

    private static final Logger log =
            LoggerFactory.getLogger(BatchJobTest.class);

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job job;

    @Autowired
    private InputFileMetaDataRepository inputFileMetaDataRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private WarehouseInventoryService warehouseInventoryService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private TruckInstanceService truckInstanceService;

    @Before
    public void setup() {
	for (FileType type : EnumSet.allOf(FileType.class)) {
	    log.debug("Adding {} entry to database", type.getFileName());
	    InputFileMetaData data = new InputFileMetaData();
	    data.setDay(new Date(0L));
	    data.setSequenceNumber(0);
	    data.setStatus(Status.WAITING);
	    data.setFileType(type);
	    inputFileMetaDataRepository.save(data);
	}
    }

    @Test
    public void testJobFlow() throws JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException, IOException {
	log.info("Starting city job");
	JobExecution jobExecution = launcher.run(job,
	        new JobParametersBuilder()
	                .addLong("time", System.currentTimeMillis())
	                .addString("input.file.name",
	                        "classpath:input-files/city/cityUpload.txt")
	                .addString("input.file.countablerow.regex",
	                        "^(?!T\\s)(?!HD\\s).*")
	                .toJobParameters());
	log.info("Starting city job validation");
	assertThat("Exit status is not COMEPLETE",
	        jobExecution.getExitStatus().getExitCode(),
	        equalTo(ExitStatus.COMPLETED.getExitCode()));
	assertThat("City data is not stored",
	        cityService.existsAndIsActive("Dearborn"), equalTo(true));
	assertThat("Zone data is not stored",
	        zoneService.existsAndIsActive("Dearborn 1"), equalTo(true));
	assertThat("Zone data is not stored",
	        zoneService.existsAndIsActive("Dearborn 2"), equalTo(true));
	assertThat("Zone data is not stored",
	        zoneService.existsAndIsActive("Dearborn 3"), equalTo(true));

	log.info("Starting route job");
	jobExecution = launcher.run(job,
	        new JobParametersBuilder()
	                .addLong("time", System.currentTimeMillis())
	                .addString("input.file.name",
	                        "classpath:input-files/route/routeUpload.txt")
	                .addString("input.file.countablerow.regex", "^[ACD].*")
	                .toJobParameters());
	log.info("Starting route job validation");
	assertThat("Exit status is not COMEPLETE",
	        jobExecution.getExitStatus().getExitCode(),
	        equalTo(ExitStatus.COMPLETED.getExitCode()));
	assertThat("Route data is not stored",
	        routeService.existsAndIsActive("0001"), equalTo(true));

	log.info("Starting truck job");
	jobExecution = launcher.run(job,
	        new JobParametersBuilder()
	                .addLong("time", System.currentTimeMillis())
	                .addString("input.file.name",
	                        "classpath:input-files/truck/truckUpload.txt")
	                .addString("input.file.countablerow.regex", "^[0-9].*")
	                .toJobParameters());
	log.info("Starting truck job validation");
	assertThat("Exit status is not COMEPLETE",
	        jobExecution.getExitStatus().getExitCode(),
	        equalTo(ExitStatus.COMPLETED.getExitCode()));
	assertThat("Truck data is not stored",
	        truckService.existsAndIsActive("0001"), equalTo(true));
	assertThat("Truck data is not stored",
	        truckService.existsAndIsActive("0010"), equalTo(true));
	assertThat("Truck data is not stored",
	        truckService.existsAndIsActive("0110"), equalTo(true));

	log.info("Starting driver job");
	jobExecution = launcher.run(job,
	        new JobParametersBuilder()
	                .addLong("time", System.currentTimeMillis())
	                .addString("input.file.name",
	                        "classpath:input-files/driver/driverUpload.txt")
	                .addString("input.file.countablerow.regex", "^[0-9].*")
	                .toJobParameters());
	log.info("Starting driver job validation");
	assertThat("Exit status is not COMEPLETE",
	        jobExecution.getExitStatus().getExitCode(),
	        equalTo(ExitStatus.COMPLETED.getExitCode()));
	assertThat("Driver data not stored",
	        driverService.existsAndIsActive("0023"), equalTo(true));
	assertThat("Driver data not stored",
	        driverService.existsAndIsActive("0345"), equalTo(true));

	log.info("Starting warehouse inventory job");
	jobExecution = launcher.run(job,
	        new JobParametersBuilder()
	                .addLong("time", System.currentTimeMillis())
	                .addString("input.file.name",
	                        "classpath:input-files/inventory/dailyInventory.txt")
	                .addString("input.file.countablerow.regex", "^[0-9].*")
	                .toJobParameters());
	log.info("Starting warehouse inventory job validation");
	assertThat("Exit status is not COMEPLETE",
	        jobExecution.getExitStatus().getExitCode(),
	        equalTo(ExitStatus.COMPLETED.getExitCode()));
	assertThat("Warehouse inventory data is not stored",
	        warehouseInventoryService.findWhereIsActiveEquals(true).size(),
	        equalTo(1));

	log.info("Starting truck-route mapping job");
	jobExecution = launcher.run(job,
	        new JobParametersBuilder()
	                .addLong("time", System.currentTimeMillis())
	                .addString("input.file.name",
	                        "classpath:input-files/truck-route/truckRouteUpload.txt")
	                .addString("input.file.countablerow.regex", "^[0-9].*")
	                .toJobParameters());
	log.info("Starting driver-truck mapping job validation");
	assertThat("Exit status is not COMEPLETE",
	        jobExecution.getExitStatus().getExitCode(),
	        equalTo(ExitStatus.COMPLETED.getExitCode()));
	assertThat("Truck-route data is not stored",
	        truckInstanceService.findAll().size(), equalTo(1));

	// log.info("Starting driver-truck mapping job");
	// jobExecution = launcher.run(job,
	// new JobParametersBuilder()
	// .addLong("time", System.currentTimeMillis())
	// .addString("input.file.name",
	// "classpath:input-files/driver-truck/driverTruck.txt")
	// .addString("input.file.countablerow.regex", "^[0-9].*")
	// .toJobParameters());
	// log.info("Starting driver-truck mapping job validation");
	// assertThat("Exit status is not COMEPLETE",
	// jobExecution.getExitStatus().getExitCode(),
	// equalTo(ExitStatus.COMPLETED.getExitCode()));
	// assertThat("Driver-truck data is not stored",
	// truckInstanceService.findAll().size(), equalTo(1));

    }

}
