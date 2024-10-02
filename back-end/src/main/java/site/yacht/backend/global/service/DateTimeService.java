package site.yacht.backend.global.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DateTimeService {

    public LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now();
    }

    public LocalDate getLocalDateNow() {
        return LocalDate.now();
    }

}
