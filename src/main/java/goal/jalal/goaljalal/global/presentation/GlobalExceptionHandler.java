package goal.jalal.goaljalal.global.presentation;

import goal.jalal.goaljalal.auth.exception.AuthenticationException;
import goal.jalal.goaljalal.common.exception.CustomBadRequestException;
import goal.jalal.goaljalal.common.exception.CustomForbiddenException;
import goal.jalal.goaljalal.common.exception.CustomNotFoundException;
import goal.jalal.goaljalal.common.exception.CustomUnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import java.time.DateTimeException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestController
public class GlobalExceptionHandler {

    private static final String DEFAULT_ERROR_MESSAGE = "관리자에게 문의하세요.";
    private static final String DEFAULT_FORMAT_ERROR_MESSAGE = "잘못된 형식입니다.";
    private static final String EXCEPTION_CLASS_TYPE_MESSAGE_FORMANT = "%n class type : %s";


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
        final MethodArgumentNotValidException exception) {
        final String defaultErrorMessage = exception.getBindingResult().getAllErrors().get(0)
            .getDefaultMessage();
        log.warn(defaultErrorMessage);

        return ResponseEntity.badRequest()
            .body(new ErrorResponse(defaultErrorMessage));
    }

    @ExceptionHandler(value = {
        HttpMessageNotReadableException.class,
        DateTimeException.class
    })
    public ResponseEntity<ErrorResponse> handleDateTimeParseException(
        final DateTimeException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.badRequest()
            .body(new ErrorResponse("DateTime 형식이 잘못되었습니다. 서버 관리자에게 문의해주세요."));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(
        final MethodArgumentTypeMismatchException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.badRequest()
            .body(new ErrorResponse(DEFAULT_FORMAT_ERROR_MESSAGE));
    }

    @ExceptionHandler(value = CustomNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final RuntimeException exception) {
        final String message = exception.getMessage();
        log.warn(message);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(message));
    }

    @ExceptionHandler(value = {
        ExpiredJwtException.class,
        CustomUnauthorizedException.class
    })
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
        final RuntimeException exception) {
        final String message = exception.getMessage();
        log.warn(message);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse(message));
    }

    @ExceptionHandler(
        value = AuthenticationException.FailAuthenticationException.class
    )
    public ResponseEntity<ErrorResponse> handleVariousCaseAuthenticationException(
        final AuthenticationException exception) {
        final String logMessage = exception.getMessage();
        log.warn(logMessage);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse("인증이 실패했습니다."));
    }

    @ExceptionHandler(value = CustomForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleCustomForbiddenException(
        final RuntimeException exception) {
        final String message = exception.getMessage();
        log.warn(message);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new ErrorResponse(message));
    }

    @ExceptionHandler(value = {
        MaxUploadSizeExceededException.class,
        CustomBadRequestException.class
    })
    public ResponseEntity<ErrorResponse> handleCustomBadRequestException(
        final RuntimeException exception) {
        final String message = exception.getMessage();
        log.warn(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(message));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(final RuntimeException exception) {
        final String message = exception.getMessage();
        final String errorId = UUID.randomUUID().toString();
        final String exceptionClassInfo = String.format(EXCEPTION_CLASS_TYPE_MESSAGE_FORMANT,
            exception.getClass());

        log.error(message + errorId + exceptionClassInfo);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse(DEFAULT_ERROR_MESSAGE + errorId));

    }
}
