/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import javax.batch.api.chunk.AbstractItemReader;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.entity.InputFileMetaData.Status;
import io.github.pbremer.icecreammanager.service.InputFileMetaDataService;

/**
 * @author Patrick Bremer
 */
public class ProcessInputFileReader extends AbstractItemReader {

    @Autowired
    private InputFileMetaDataService service;

    private InputFileMetaData returnData;

    /*
     * (non-Javadoc)
     * @see javax.batch.api.chunk.AbstractItemReader#readItem()
     */
    @Override
    public InputFileMetaData readItem() throws Exception {

	if (returnData != null) {
	    return null;
	}
	returnData = service.getMostRecentInputFileByStatus(Status.READY);
	return returnData;
    }

}
