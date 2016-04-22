package io.github.pbremer.icecreammanager.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionController {

    public final static String DEFAULT_ERROR_VIEW = "public/error";

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String defaultErrorHandler(Throwable throwable, Model model) {
	model.addAttribute("stackTrace",
	        ExceptionUtils.getStackTrace(throwable));
	return DEFAULT_ERROR_VIEW;
    }

}
