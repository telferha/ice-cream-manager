/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import javax.batch.api.chunk.AbstractItemReader;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.pbremer.icecreammanager.service.InputFileMetaDataService;

/**
 * @author Patrick Bremer
 */
public class ProcessInputFileReader<InputFileMetaData>
        extends AbstractItemReader {

    @Autowired
    private InputFileMetaDataService service;

    /*
     * (non-Javadoc)
     * @see javax.batch.api.chunk.AbstractItemReader#readItem()
     */
    @Override
    public InputFileMetaData readItem() throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

}
