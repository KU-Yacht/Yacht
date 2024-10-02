package site.yacht.backend.domain.user.exception;

import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.exception.IllegalException;

public class UserNicknameDuplicationException extends IllegalException {

    public UserNicknameDuplicationException() {
        super(ErrorCode.NICKNAME_DUPLICATION);
    }

}
