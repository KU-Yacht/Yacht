package site.yacht.backend.domain.authentication.exception;

import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.exception.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }

}
