package org.contextmapper.sample.tlas.application.exception;

public class TLAShortNameDoesNotExist extends RuntimeException {

    public TLAShortNameDoesNotExist(final String name) {
        super("A TLA '" + name + "' does not exist!");
    }

}
