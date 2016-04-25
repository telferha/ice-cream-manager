/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import java.text.SimpleDateFormat;
import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.entity.InputFileMetaData.FileType;
import io.github.pbremer.icecreammanager.flatfilecontents.HeaderTrailerContainer;
import io.github.pbremer.icecreammanager.service.InputFileMetaDataService;

/**
 * @author Patrick Bremer
 */
public class HeaderValidator implements Validator {

    private static final Logger logger =
            LoggerFactory.getLogger(HeaderValidator.class);

    @Autowired
    private InputFileMetaDataService service;
    private String fileName;

    public HeaderValidator(String fileName) {
	this.fileName = fileName;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	logger.debug("Got class: {}", clazz.getName());
	return clazz.isInstance(new HeaderTrailerContainer());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	HeaderTrailerContainer arg = (HeaderTrailerContainer) target;
	InputFileMetaData metaData = null;
	for (FileType type : EnumSet.allOf(FileType.class)) {
	    if (fileName.endsWith(type.getFileName())) {
		logger.info("Using: {}", type.toString());
		metaData = service.getOne(type);
		int expecedSequenceNumber =
		        metaData.getSequenceNumber() + 1 > 9999 ? 1
		                : metaData.getSequenceNumber() + 1;
		if (arg.getSequenceNumber() != expecedSequenceNumber) {
		    errors.reject("header.sequenceNumber.notExpected",
		            String.format(
		                    "Expecting sequence number %d, got sequence number %d",
		                    expecedSequenceNumber,
		                    arg.getSequenceNumber()));
		}
		if (arg.getDay().before(metaData.getDay())) {
		    errors.reject("header.day.before",
		            String.format(
		                    "Header date %s is before last run date of %s",
		                    new SimpleDateFormat("yyyy-MM-dd")
		                            .format(arg.getDay()),
		                    new SimpleDateFormat("yyyy-MM-dd")
		                            .format(metaData.getDay())));
		}
		break;
	    }
	}

    }

}
