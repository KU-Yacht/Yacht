package site.yacht.backend.global.error.exception;

import site.yacht.backend.global.error.ErrorCode;

public class IllegalException extends BusinessException {

    public IllegalException() {
        super(ErrorCode.BAD_REQUEST);
    }

    public IllegalException(ErrorCode e) {
        super(e);
    }

    public IllegalException(String message) {
        super(message, ErrorCode.BAD_REQUEST);
    }
}
