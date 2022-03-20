package io.mzlnk.identitybroker.server.domain.user;

import io.mzlnk.identitybroker.server.common.exception.NotFoundException;

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
