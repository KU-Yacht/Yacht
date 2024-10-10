package site.yacht.backend.domain.project.exception;

import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.exception.NotFoundException;

public class ProjectNotFoundException extends NotFoundException {

    public ProjectNotFoundException() {
        super(ErrorCode.PROJECT_NOT_FOUND);
    }
}
