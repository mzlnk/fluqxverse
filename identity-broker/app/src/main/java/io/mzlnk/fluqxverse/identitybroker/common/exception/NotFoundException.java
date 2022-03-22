package io.mzlnk.fluqxverse.identitybroker.common.exception;

public abstract class NotFoundException extends RuntimeException {

    protected NotFoundException(String message) {
        super(message);
    }

}
