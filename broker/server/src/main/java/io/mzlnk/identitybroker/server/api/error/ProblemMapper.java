package io.mzlnk.identitybroker.server.api.error;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import io.mzlnk.identitybroker.server.common.exception.InvalidEnumException;
import io.mzlnk.identitybroker.server.common.utils.PathUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.zalando.problem.Problem;

import java.net.URI;
import java.util.List;

import static io.mzlnk.identitybroker.server.api.error.ErrorType.BAD_REQUEST;
import static io.mzlnk.identitybroker.server.application.logging.RequestInterceptor.REQUEST_ID;

@Component
public class ProblemMapper {

    private static final String TYPE_URI_PREFIX = "idp/api/v1/errors/";
    private static final String INVALID_PARAM_MSG_TEMPLATE = "Value of %s %s.";

    public Problem toProblem(Exception exception, ErrorType errorType) {
        return toProblem(errorType, exception.getMessage());
    }

    public Problem toProblem(InvalidEnumException enumException, ValueInstantiationException valueException) {
        String detail = "Value of %s %s.".formatted(PathUtils.getPath(valueException.getPath()), enumException.getMessage());
        return toProblem(BAD_REQUEST, detail);
    }

    public Problem toProblem(ErrorType errorType, String detail) {
        return Problem.builder()
                .withTitle(errorType.getDefaultTitle())
                .withDetail(detail)
                .withStatus(errorType.getStatus())
                .withType(toTypeURI(errorType))
                .withInstance(toInstanceURI())
                .build();
    }

    public Problem toProblem(MethodArgumentNotValidException exception) {
        List<InvalidParameter> invalidParameters = exception.getFieldErrors().stream()
                .map(error -> new InvalidParameter(
                        error.getField(),
                        error.getRejectedValue(),
                        INVALID_PARAM_MSG_TEMPLATE.formatted(error.getField(), error.getDefaultMessage()))
                )
                .toList();

        return toProblem("Request body is invalid.", BAD_REQUEST, invalidParameters);
    }

    private Problem toProblem(String detail, ErrorType errorType, List<InvalidParameter> invalidParameters) {
        return Problem.builder()
                .withTitle(errorType.getDefaultTitle())
                .withDetail(detail)
                .withStatus(errorType.getStatus())
                .withType(toTypeURI(errorType))
                .withInstance(toInstanceURI())
                .with("invalidParams", invalidParameters)
                .build();
    }

    private static URI toTypeURI(ErrorType errorType) {
        return URI.create(TYPE_URI_PREFIX + errorType.getType());
    }

    private static URI toInstanceURI() {
        return URI.create(MDC.get(REQUEST_ID));
    }

    private static record InvalidParameter(String invalidField,
                                           Object invalidValue,
                                           String detail) { }

}
