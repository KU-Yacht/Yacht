package site.yacht.backend.domain.template.exception;

import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.exception.NotFoundException;

public class TemplateNotFoundException extends NotFoundException {

    public TemplateNotFoundException() {
        super(ErrorCode.TEMPLATE_NOT_FOUND);
    }
}
