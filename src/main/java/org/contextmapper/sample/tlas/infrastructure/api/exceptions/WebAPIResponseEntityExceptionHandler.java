/*
 * Copyright 2023 The Context Mapper project team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
