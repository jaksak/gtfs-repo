package pl.longhorn.gtfsrepo.calendardates;

import pl.longhorn.gtfsrepo.calendardates.exception.CalendarDateExceptionType;

import java.time.LocalDate;

public class CalendarDate {
    private int id;
    private int schemaId;

    private String serviceId;
    private LocalDate date;
    private CalendarDateExceptionType exceptionType;
}
