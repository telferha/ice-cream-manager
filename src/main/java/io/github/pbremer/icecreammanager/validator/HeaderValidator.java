/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.flatfilecontents.HeaderTrailerContainer;
import io.github.pbremer.icecreammanager.service.InputFileMetaDataService;

/**
 * @author Patrick Bremer
 */
public class HeaderValidator implements Validator {

    @Autowired
    private InputFileMetaDataService service;

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(HeaderTrailerContainer.class);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	HeaderTrailerContainer arg = (HeaderTrailerContainer) target;

    }

}
