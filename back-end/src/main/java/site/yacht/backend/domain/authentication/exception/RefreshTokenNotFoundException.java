package site.yacht.backend.domain.authentication.exception;


import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.exception.NotFoundException;

public class RefreshTokenNotFoundException extends NotFoundException {

    public RefreshTokenNotFoundException() {
        super(ErrorCode.INVALID_TOKEN);
    }

}
