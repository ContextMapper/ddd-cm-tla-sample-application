package org.contextmapper.sample.tlas.application.exception;

public class TLAShortNameNotValid extends RuntimeException {

    public TLAShortNameNotValid(final String name) {
        super("'" + name + "' is not a valid TLA short name!");
    }

}
