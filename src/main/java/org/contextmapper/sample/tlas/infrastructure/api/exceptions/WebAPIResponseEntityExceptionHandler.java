package org.contextmapper.sample.tlas.infrastructure.api.exceptions;

import org.contextmapper.sample.tlas.application.exception.TLAShortNameDoesNotExist;
import org.contextmapper.sample.tlas.application.exception.TLAShortNameNotValid;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.net.URI.create;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@ControllerAdvice
public class WebAPIResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String BASIC_TYPE_URL = "https://tla-example.contextmapper.org/common-problems/";

    @ExceptionHandler(value = {TLAShortNameNotValid.class})
    protected ProblemDetail handleTLAShortNameNotValid(final TLAShortNameNotValid exception) {
        var error = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        error.setType(create(BASIC_TYPE_URL + "invalid-input"));
        return error;
    }

    @ExceptionHandler(value = {TLAShortNameDoesNotExist.class})
    protected ProblemDetail handleTLANotFound(final RuntimeException exception) {
        var error = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        error.setType(create(BASIC_TYPE_URL + "not-found"));
        return error;
    }

}
