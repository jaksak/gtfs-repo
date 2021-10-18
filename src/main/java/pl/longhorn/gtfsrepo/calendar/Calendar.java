package pl.longhorn.gtfsrepo.calendar;

import java.time.LocalDate;

public class Calendar {
    private int id;
    private int schemaId;

    private String serviceId;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;
    private LocalDate startDate;
    private LocalDate endDate;
}
