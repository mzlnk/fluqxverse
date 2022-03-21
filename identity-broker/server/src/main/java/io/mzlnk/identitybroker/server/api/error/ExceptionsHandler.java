package io.mzlnk.identitybroker.server.api.error;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import io.mzlnk.identitybroker.server.common.exception.InvalidEnumException;
import io.mzlnk.identitybroker.server.common.exception.NotFoundException;
import io.mzlnk.identitybroker.server.domain.callback.exchange.AuthCallbackException;
import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderNotSupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.zalando.problem.Problem;

@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "io.mzlnk.identitybroker.server.api")
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    private static final String TYPE_URI_PREFIX = "auth/api/v1/errors/";

    private final ProblemMapper problemMapper;

    private static HttpHeaders problemHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);

        return headers;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        if (ex.getCause() instanceof ValueInstantiationException valueException
                && ex.getMostSpecificCause() instanceof InvalidEnumException enumException) {

            Problem problem = problemMapper.toProblem(enumException, valueException);
            return handleExceptionInternal(ex, problem, headers, HttpStatus.BAD_REQUEST, request);
        }

        Problem problem = problemMapper.toProblem(ErrorType.INVALID_JSON, "Invalid JSON body");
        return handleExceptionInternal(ex, problem, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        Problem problem = problemMapper.toProblem(ex);
        return handleExceptionInternal(ex, problem, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(AuthCallbackException.class)
    public ResponseEntity<Object> handleException(AuthCallbackException ex, WebRequest request) {
        Problem problem = problemMapper.toProblem(ex, ErrorType.INTERNAL_SERVER_ERROR);
        HttpHeaders headers = problemHeaders();

        return handleExceptionInternal(ex, problem, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(IdentityProviderNotSupportedException.class)
    public ResponseEntity<Object> handleException(IdentityProviderNotSupportedException ex, WebRequest request) {
        Problem problem = problemMapper.toProblem(ex, ErrorType.PROVIDER_NOT_SUPPORTED);
        HttpHeaders headers = problemHeaders();

        return handleExceptionInternal(ex, problem, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleException(NotFoundException ex, WebRequest request) {
        Problem problem = problemMapper.toProblem(ex, ErrorType.NOT_FOUND);
        HttpHeaders headers = problemHeaders();

        return handleExceptionInternal(ex, problem, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Throwable ex, WebRequest request) {
        Problem problem = problemMapper.toProblem(ErrorType.INTERNAL_SERVER_ERROR, "Please contact administrator.");
        HttpHeaders headers = problemHeaders();

        return handleExceptionInternal((Exception) ex, problem, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
