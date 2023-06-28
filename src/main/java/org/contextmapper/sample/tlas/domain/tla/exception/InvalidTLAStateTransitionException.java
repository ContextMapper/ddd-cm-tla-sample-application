package org.contextmapper.sample.tlas.domain.tla.exception;

public class InvalidTLAStateTransitionException extends RuntimeException {

    public InvalidTLAStateTransitionException(final String message) {
        super(message);
    }

}
