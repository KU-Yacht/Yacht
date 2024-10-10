package site.yacht.backend.domain.application.exception;

import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.exception.IllegalException;

public class ApplicationNameDuplicationException extends IllegalException {

    public ApplicationNameDuplicationException() {
        super(ErrorCode.APPLICATION_NAME_DUPLICATION);
    }

}
