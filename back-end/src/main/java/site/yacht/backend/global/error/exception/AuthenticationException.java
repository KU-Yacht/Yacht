package site.yacht.backend.global.error.exception;

import site.yacht.backend.global.error.ErrorCode;

public class AuthenticationException extends BusinessException {

    public AuthenticationException() {
        super(ErrorCode.FAIL_AUTHENTICATION);
    }

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
