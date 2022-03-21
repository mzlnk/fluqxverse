package io.mzlnk.identitybroker.server.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.zalando.problem.Status;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    BAD_REQUEST(
            Status.BAD_REQUEST,
            "Bad request",
            "bad-request"
    ),
    CALLBACK_FAILED(
            Status.INTERNAL_SERVER_ERROR,
            "Authentication callback failed",
            "auth-callback-failed"
    ),
    FORBIDDEN(
            Status.FORBIDDEN,
            "Forbidden",
            "forbidden"
    ),
    INTERNAL_SERVER_ERROR(
            Status.INTERNAL_SERVER_ERROR,
            "Internal server error",
            "internal-server-error"
    ),
    INVALID_JSON(
            Status.BAD_REQUEST,
            "Bad request",
            "invalid-json"
    ),
    NOT_FOUND(
            Status.NOT_FOUND,
            "Not found",
            "not-found"
    ),
    PROVIDER_NOT_SUPPORTED(
            Status.BAD_REQUEST,
            "Identity provider not supported",
            "identity-provider-not-supported"
    ),
    UNAUTHORIZED(
            Status.UNAUTHORIZED,
            "Unauthorized",
            "unauthorized"
    );

    private final Status status;
    private final String defaultTitle;
    private final String type;

}
