package io.mzlnk.fluqxverse.authn.domain.user;

import io.mzlnk.fluqxverse.authn.common.exception.NotFoundException;

import java.util.function.Supplier;

public class UserNotFoundException extends NotFoundException {

    private static final String MSG_BY_ID_TEMPLATE = "User with id `%s` not found.";
    private static final String MSG_BY_EMAIL_TEMPLATE = "User with email `%s` not found.";

    public UserNotFoundException(Long userId) {
        super(MSG_BY_ID_TEMPLATE.formatted(userId));
    }

    public UserNotFoundException(String email) {
        super(MSG_BY_EMAIL_TEMPLATE.formatted(email));
    }

    public static Supplier<UserNotFoundException> userNotFound(Long userId) {
        return () -> new UserNotFoundException(userId);
    }

    public static Supplier<UserNotFoundException> userNotFound(String email) {
        return () -> new UserNotFoundException(email);
    }

}
