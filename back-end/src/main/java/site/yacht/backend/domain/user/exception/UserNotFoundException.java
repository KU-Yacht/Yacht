package site.yacht.backend.domain.user.exception;

import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
