/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.listener;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.service.InputFileMetaDataService;

/**
 * @author Patrick Bremer
 */
public class ProcessFileStepListener extends StepExecutionListenerSupport {

    private static final Logger log =
            LoggerFactory.getLogger(ProcessFileStepListener.class);

    @Autowired
    private InputFileMetaDataService service;

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.ItemReadListener#beforeRead()
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {

	InputFileMetaData entity = service
	        .getMostRecentInputFileByStatus(InputFileMetaData.Status.READY);
	String filePath =
	        FileUtils.getTempDirectoryPath() + entity.getFileName();

	try {
	    FileUtils.writeByteArrayToFile(new File(filePath),
	            entity.getContents(), false);
	    stepExecution.getExecutionContext().putString("input.file.name",
	            filePath);
	} catch (IOException e) {
	    log.error("Unable to create file for parsing", e);
	    stepExecution.setExitStatus(ExitStatus.FAILED
	            .addExitDescription("Unable to create file for parsing"));
	}

    }
}
