package site.yacht.backend.domain.project.exception;

import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.exception.IllegalException;

public class ProjectNameDuplicationException extends IllegalException {

    public ProjectNameDuplicationException() {
        super(ErrorCode.PROJECT_NAME_DUPLICATION);
    }

}
