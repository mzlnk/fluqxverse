package io.mzlnk.identitybroker.server.domain.callback.exchange;

public class AuthCallbackException extends RuntimeException {

    public AuthCallbackException() {
        super();
    }

    public AuthCallbackException(String message) {
        super(message);
    }

}
