package io.mzlnk.identitybroker.server.application.security.auth.authz;

public class AuthZException extends RuntimeException {

    public AuthZException(String message) {
        super(message);
    }

    public AuthZException(String message, Throwable cause) {
        super(message, cause);
    }

}
