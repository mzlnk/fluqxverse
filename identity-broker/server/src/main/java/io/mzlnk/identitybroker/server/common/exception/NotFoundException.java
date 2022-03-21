package io.mzlnk.identitybroker.server.common.exception;

public abstract class NotFoundException extends RuntimeException {

    protected NotFoundException(String message) {
        super(message);
    }

}
