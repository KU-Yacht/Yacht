package site.yacht.backend.domain.user.exception;

import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.exception.IllegalException;

public class UserEmailDuplicationException extends IllegalException {

    public UserEmailDuplicationException() {
        super(ErrorCode.EMAIL_DUPLICATION);
    }

}
