package pl.longhorn.gtfsrepo.calendardates;

import lombok.*;
import pl.longhorn.gtfsrepo.calendardates.exception.CalendarDateExceptionType;

import java.time.LocalDate;

@Data
@Builder
public class CalendarDate {
    private int id;
    private int schemaId;
    private int serviceId;

    private LocalDate date;
    private CalendarDateExceptionType exceptionType;
}
