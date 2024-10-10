package site.yacht.backend.domain.application.exception;

import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.exception.NotFoundException;

public class ApplicationNotFoundException extends NotFoundException {

    public ApplicationNotFoundException() {
        super(ErrorCode.APPLICATION_NOT_FOUND);
    }

}
