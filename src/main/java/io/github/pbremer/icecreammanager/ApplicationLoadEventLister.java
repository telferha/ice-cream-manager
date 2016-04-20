/**
 * 
 */
package io.github.pbremer.icecreammanager;

import java.util.Date;
import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.entity.InputFileMetaData.FileType;
import io.github.pbremer.icecreammanager.entity.InputFileMetaData.Status;
import io.github.pbremer.icecreammanager.repository.InputFileMetaDataRepository;

/**
 * @author Patrick Bremer
 */
public class ApplicationLoadEventLister
        implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger =
            LoggerFactory.getLogger(ApplicationLoadEventLister.class);

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.context.ApplicationListener#onApplicationEvent(org.
     * springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
	InputFileMetaDataRepository repository = event.getApplicationContext()
	        .getBean(InputFileMetaDataRepository.class);
	for (FileType type : EnumSet.allOf(FileType.class)) {
	    if (!repository.exists(type)) {
		logger.debug("Adding {} entry to database", type.getFileName());
		InputFileMetaData data = new InputFileMetaData();
		data.setDay(new Date(0L));
		data.setSequenceNumber(0);
		data.setStatus(Status.WAITING);
		data.setFileType(type);
		repository.save(data);
	    }
	}

    }

}
