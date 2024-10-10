package site.yacht.backend.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    NOT_FOUND(HttpStatus.BAD_REQUEST, "C001", "Not found resource"),
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "C002", "Invalid input value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C003", "Not allowed HTTP method"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "C004", "Access is denied"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C005", "Internal server error"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "C006", "Bad Request"),

    // Auth
    EMPTY_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A001", "Authorization header is empty"),
    NOT_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A002", "Grant type is not Bearer"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A003", "Invalid Token"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "A004", "Expired Token"),
    FAIL_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "A007", "Fail Authentication"),
    NO_AUTHORIZATION(HttpStatus.FORBIDDEN, "A008", "No authorization"),

    // User
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "U001", "Not found user"),
    EMAIL_DUPLICATION(HttpStatus.BAD_REQUEST, "U002", "Unavailable email"),
    NICKNAME_DUPLICATION(HttpStatus.BAD_REQUEST, "U003", "Unavailable nickname"),

    // Project
    PROJECT_NOT_FOUND(HttpStatus.BAD_REQUEST, "P001", "Not found project"),
    PROJECT_NAME_DUPLICATION(HttpStatus.BAD_REQUEST, "P002", "Unavailable project name"),

    // Template
    TEMPLATE_NOT_FOUND(HttpStatus.BAD_REQUEST, "T001", "Not found template"),

    // Application
    APPLICATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "AA01", "Not found application"),
    APPLICATION_NAME_DUPLICATION(HttpStatus.BAD_REQUEST, "AA02", "Unavailable application name"),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
