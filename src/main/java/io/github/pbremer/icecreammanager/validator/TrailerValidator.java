/**
 * 
 */
package io.github.pbremer.icecreammanager.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.flatfilecontents.HeaderTrailerContainer;

/**
 * @author Patrick Bremer
 */
public class TrailerValidator implements Validator {

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
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
	if (arg.getFooterNumber() != arg.getActualCount()) {
	    errors.reject("trailer.invalidCount",
	            String.format(
	                    "Count in trailer(%d) does not match the actual count(%d)",
	                    arg.getFooterNumber(), arg.getActualCount()));
	}
    }

}
