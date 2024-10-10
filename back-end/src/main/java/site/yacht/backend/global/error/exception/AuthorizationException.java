package site.yacht.backend.global.error.exception;

import site.yacht.backend.global.error.ErrorCode;

public class AuthorizationException extends BusinessException {

    public AuthorizationException() {
        super(ErrorCode.NO_AUTHORIZATION);
    }

    public AuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthorizationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
