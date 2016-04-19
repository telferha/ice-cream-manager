/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import static org.apache.commons.lang3.RandomUtils.nextBytes;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import io.github.pbremer.icecreammanager.configuration.ServiceConfiguration;
import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.testconfig.TestEntityConfiguration;

/**
 * @author Patrick Bremer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
        classes = { TestEntityConfiguration.class, ServiceConfiguration.class })
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "spring.datasource.url = jdbc:h2:~/local;DB_CLOSE_ON_EXIT=FALSE" })
@Transactional
public class InputFileMetaDataServiceTest {

    @SuppressWarnings("unused")
    private static final Logger log =
            LoggerFactory.getLogger(InputFileMetaDataServiceTest.class);

    @Autowired
    private InputFileMetaDataService service;

    private List<InputFileMetaData.Status> statuses;
    private List<InputFileMetaData.FileType> fileTypes;

    @Before
    public void populate() {
	fileTypes = new ArrayList<InputFileMetaData.FileType>(
	        EnumSet.allOf(InputFileMetaData.FileType.class));
	statuses = new ArrayList<InputFileMetaData.Status>(
	        EnumSet.allOf(InputFileMetaData.Status.class));
	InputFileMetaData data = new InputFileMetaData();
	for (InputFileMetaData.FileType fileType : fileTypes) {
	    data.setFileType(fileType);
	    data.setContents(nextBytes(nextInt(10, 20)));
	    data.setStatus(statuses.get(0));
	    service.save(data);
	}
    }

    @Test
    public void findAllTest() {
	List<InputFileMetaData> inputFiles = service.findAll();
	assertThat(fileTypes.size(), equalTo(inputFiles.size()));
	for (InputFileMetaData inputFile : inputFiles) {
	    // log.debug("\t{}", inputFile);
	    assertThat(inputFile.getStatus(), equalTo(statuses.get(0)));
	}
    }

    @Test
    public void batchUpdateTest() throws InterruptedException {
	InputFileMetaData data = new InputFileMetaData();
	data.setFileType(fileTypes.get(0));
	data.setStatus(statuses.get(0));
	service.save(data);
	/*
	 * log.debug("After update:"); for (InputFileMetaData inputFile :
	 * service.findAll()) { log.debug("\t{}", inputFile); }
	 */
	assertThat(data.getFileType(),
	        equalTo(service.getOne(fileTypes.get(0)).getFileType()));
	assertThat(fileTypes.get(0),
	        equalTo(service.getMostRecentInputFileByStatus(statuses.get(0))
	                .getFileType()));
    }
}
