package io.mzlnk.fluqxverse.authn.api.error;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import io.mzlnk.fluqxverse.authn.common.exception.InvalidEnumException;
import io.mzlnk.fluqxverse.authn.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "io.mzlnk.fluqxverse.authn.api")
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

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

        log.error("Error occurred: ", ex);

        return handleExceptionInternal((Exception) ex, problem, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
