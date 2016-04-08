/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.flatfilecontents.HeaderTrailerContainer;

/**
 * @author Patrick Bremer
 */
public class InputFileHeaderAndTrailerItemReader
        extends MultilineFlatFileItemReader<HeaderTrailerContainer> {

    private static final Logger log =
            LoggerFactory.getLogger(InputFileHeaderAndTrailerItemReader.class);

    private HeaderTrailerContainer returnContainer;

    private String countRowRegex;

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public HeaderTrailerContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	HeaderTrailerContainer headerTrailerContainer = null;

	for (FieldSet line; (line = this.delegate.read()) != null;) {

	    Assert.isNull(returnContainer,
	            "Trailer is not at the end of the file");

	    String prefix = line.readString(0);

	    if ("HD".equals(prefix)) {
		log.trace(line.toString());
		Assert.isNull(headerTrailerContainer,
		        "Encountered header record in the middle of the file");
		headerTrailerContainer = new HeaderTrailerContainer();
		headerTrailerContainer
		        .setSequenceNumber(line.readShort("Sequence Number"));
		headerTrailerContainer.setDay(line.readDate("Date"));
	    } else if ("T".equalsIgnoreCase(prefix)) {
		log.trace(line.toString());
		Assert.notNull(headerTrailerContainer,
		        "Encountered trailer record before the header record");
		headerTrailerContainer
		        .setFooterNumber(line.readInt("Record Count"));
		returnContainer = headerTrailerContainer;
		return returnContainer;
	    } else if (prefix.matches(countRowRegex)) {
		headerTrailerContainer.incrimentCount();
		log.trace("Current actual count: {}",
		        headerTrailerContainer.getActualCount());
	    }

	}
	Assert.isNull(headerTrailerContainer,
	        "No ending trailer row was found");
	return null;
    }

    /**
     * @param countRowRegex
     *            the countRowRegex to set
     */
    public void setCountRowRegex(String countRowRegex) {
	this.countRowRegex = countRowRegex;
    }

}
