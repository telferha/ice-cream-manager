package io.github.pbremer.icecreammanager.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;

public class InputFileMetaDataDataValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
	return clazz.isInstance(InputFileMetaData.class);
    }

    @Override
    public void validate(Object arg, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "", "", "");
    }

}
