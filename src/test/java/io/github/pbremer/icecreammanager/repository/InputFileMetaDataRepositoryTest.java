/**
 * 
 */
package io.github.pbremer.icecreammanager.repository;

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

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.testconfig.TestEntityConfiguration;

/**
 * @author Patrick Bremer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestEntityConfiguration.class)
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "spring.datasource.url = jdbc:h2:~/local;DB_CLOSE_ON_EXIT=FALSE" })
@Transactional
public class InputFileMetaDataRepositoryTest {

    private static final Logger log =
            LoggerFactory.getLogger(InputFileMetaDataRepositoryTest.class);

    @Autowired
    private InputFileMetaDataRepository repository;

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
	    data.setStatus(statuses.get(nextInt(0, statuses.size())));
	    repository.save(data);
	}
    }

    @Test
    public void findByStatusOrderByLastModifiedDateTest() {
	int count = 0;

	for (InputFileMetaData.Status status : statuses) {
	    log.debug("Searching for: {}", status);

	    List<InputFileMetaData> statusList =
	            repository.findByStatusOrderByLastModifiedDateDesc(status);
	    count += statusList.size();
	    log.debug("Recieved {} classes", statusList.size());
	    for (InputFileMetaData inputFile : statusList) {
		// log.debug("\t{}", inputFile);
		assertThat(inputFile.getStatus(), equalTo(status));
	    }
	}
	assertThat("Did not return correct number of rows", count,
	        equalTo(fileTypes.size()));

    }

}
