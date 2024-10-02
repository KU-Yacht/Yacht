package site.yacht.backend.global.error.exception;

import site.yacht.backend.global.error.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorCode e) {
        super(e);
    }
}
