package io.github.pbremer.icecreammanager.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;

/**
 * Validator to validate the contents of an {@link InputFileMetaData} object
 * 
 * @author Patrick Bremer
 */
public class InputFileMetaDataDataValidator implements Validator {

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(InputFileMetaData.class);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object arg, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "", "", "");
    }

}
