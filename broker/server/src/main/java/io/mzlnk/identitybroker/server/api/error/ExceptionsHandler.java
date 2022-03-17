package io.mzlnk.identitybroker.server.api.error;

import io.mzlnk.identitybroker.server.domain.callback.exchange.AuthCallbackException;
import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderNotSupportedException;
import org.slf4j.MDC;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

import java.net.URI;

@RestControllerAdvice(basePackages = "io.mzlnk.identitybroker.server.api")
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    /* TODO: implement:
             - handleTypeMismatch
             - handleHttpMessageNotReadable
             - handleMethodArgumentNotValid
             - handleBindException
             when more complex API provided (handling request body, etc.)
     */

    @ExceptionHandler(AuthCallbackException.class)
    public ResponseEntity<Object> handleException(AuthCallbackException ex, WebRequest request) {
        Problem problem = Problem.builder()
                .withTitle("Authentication callback failed")
                .withDetail(ex.getMessage())
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .withType(URI.create("api/v1/errors/callback-failed"))
                .build();

        return null;
    }

    @ExceptionHandler(IdentityProviderNotSupportedException.class)
    public ResponseEntity<Object> handleException(IdentityProviderNotSupportedException ex, WebRequest request) {
        return null;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Throwable ex, WebRequest request) {
        return null;
    }

}
