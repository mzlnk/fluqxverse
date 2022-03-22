package io.mzlnk.fluqxverse.identitybroker.domain.user;

import io.mzlnk.fluqxverse.identitybroker.common.exception.NotFoundException;

import java.util.function.Supplier;

public class UserNotFoundException extends NotFoundException {

    private static final String MSG_TEMPLATE = "User with id `%s` not found.";

    public UserNotFoundException(Long userId) {
        super(MSG_TEMPLATE.formatted(userId));
    }

    public static Supplier<UserNotFoundException> userNotFound(Long userId) {
        return () -> new UserNotFoundException(userId);
    }

}
