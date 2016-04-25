/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.processor;

import java.io.File;
import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.entity.InputFileMetaData.FileType;
import io.github.pbremer.icecreammanager.flatfilecontents.HeaderTrailerContainer;
import io.github.pbremer.icecreammanager.service.InputFileMetaDataService;

/**
 * @author Patrick Bremer
 */
public class IncrimentCountStaging implements
        ItemProcessor<HeaderTrailerContainer, HeaderTrailerContainer> {

    private static final Logger logger =
            LoggerFactory.getLogger(IncrimentCountStaging.class);

    @Autowired
    private InputFileMetaDataService service;

    private String filePath;

    public IncrimentCountStaging(String filePath) {
	this.filePath = filePath;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    @Override
    public HeaderTrailerContainer process(HeaderTrailerContainer item)
            throws Exception {
	for (FileType fileName : EnumSet.allOf(FileType.class)) {
	    String[] arr = filePath.split(File.separator);
	    if (filePath.toUpperCase()
	            .endsWith(fileName.getFileName().toUpperCase())) {
		InputFileMetaData file = service.getOne(fileName);
		file.setSequenceNumber((file.getSequenceNumber() + 1 < 10000
		        ? file.getSequenceNumber() + 1 : 0));
		logger.info("{} now at: {}", fileName.getFileName(),
		        file.getSequenceNumber());
		service.save(file);
		return item;
	    }
	}
	return item;
    }

}
