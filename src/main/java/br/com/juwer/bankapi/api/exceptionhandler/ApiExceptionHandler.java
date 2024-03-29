package br.com.juwer.bankapi.api.exceptionhandler;

import br.com.juwer.bankapi.domain.exceptions.*;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String GENERIC_END_USER_ERROR_MSG =
            "An unexpected internal system error has occurred. "
                    + "Try again and if the problem persists, please contact "
                    + "the system's administrator";

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalExceptions(
            Exception ex,
            WebRequest request){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        String detail = GENERIC_END_USER_ERROR_MSG;

        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        Problem problem = createProblemBuilder(
                status, problemType, detail, detail, OffsetDateTime.now())
                .build();

        log.error(ex.getMessage(), ex);

        return handleExceptionInternal
                (ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(EntityNotFoundException ex, WebRequest request) {
        String detail = ex.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;

        Problem problem = createProblemBuilder(
                status, problemType, detail, detail, OffsetDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ExistingEntityException.class)
    public ResponseEntity<?> handleExistingEntityException(ExistingEntityException ex, WebRequest request) {
        String detail = ex.getMessage();
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_ALREADY_EXISTS;

        Problem problem = createProblemBuilder(
                status, problemType, detail, detail, OffsetDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<?> handleInvalidTransactionException(InvalidTransactionException ex, WebRequest request) {
        String detail = ex.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.INVALID_DATA;

        Problem problem = createProblemBuilder(
                status, problemType, detail, detail, OffsetDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        String detail = ex.getMessage();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ProblemType problemType = ProblemType.ACCESS_DENIED;

        Problem problem = createProblemBuilder(
                status, problemType, detail, detail, OffsetDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(SecurityValidationAccountException.class)
    public ResponseEntity<?> handleSecurityValidationAccountException(
            SecurityValidationAccountException ex, WebRequest request
    ) {
        String detail = ex.getMessage();
        HttpStatus status = HttpStatus.FORBIDDEN;
        ProblemType problemType = ProblemType.ACCESS_DENIED;

        Problem problem = createProblemBuilder(
                status, problemType, detail, detail, OffsetDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CurrentPasswordDoesNotMatchException.class    )
    public ResponseEntity<?> handleCurrentPasswordDoesNotMatchException(
            CurrentPasswordDoesNotMatchException ex, WebRequest request
    ) {
        String detail = ex.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.INVALID_DATA;

        Problem problem = createProblemBuilder(
                status, problemType, detail, detail, OffsetDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> handleInsufficientBalanceException(
            InsufficientBalanceException ex, WebRequest request
    ) {
        String detail = ex.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.INVALID_DATA;

        Problem problem = createProblemBuilder(
                status, problemType, detail, detail, OffsetDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal
            (Exception ex, @Nullable  Object body, HttpHeaders headers,
             HttpStatusCode status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .title(HttpStatus.valueOf(status.value()).getReasonPhrase())
                    .status(status.value())
                    .userMessage(GENERIC_END_USER_ERROR_MSG)
                    .timestamp(OffsetDateTime.now())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .userMessage(GENERIC_END_USER_ERROR_MSG)
                    .timestamp(OffsetDateTime.now())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(
            HttpStatusCode status,
            ProblemType problem,
            String detail,
            String userMessage,
            OffsetDateTime timestamp){

        return Problem.builder()
                .timestamp(timestamp)
                .status(status.value())
                .type(problem.getUrl())
                .title(problem.getTitle())
                .detail(detail)
                .userMessage(userMessage);
    }
}
