package io.mzlnk.fluqxverse.springboot.authsecurity.authz;

public class AuthZException extends RuntimeException {

    public AuthZException(String message) {
        super(message);
    }

    public AuthZException(String message, Throwable cause) {
        super(message, cause);
    }

}
