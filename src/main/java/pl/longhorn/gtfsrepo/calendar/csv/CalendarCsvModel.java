package pl.longhorn.gtfsrepo.calendar.csv;

import com.opencsv.bean.*;
import lombok.Data;
import pl.longhorn.gtfsrepo.primitive.LocalDateConverter;

import java.time.LocalDate;

@Data
public class CalendarCsvModel {

    @CsvBindByName(column = "service_id")
    private String serviceId;

    @CsvBindByName(column = "monday")
    private boolean monday;

    @CsvBindByName(column = "tuesday")
    private boolean tuesday;

    @CsvBindByName(column = "wednesday")
    private boolean wednesday;

    @CsvBindByName(column = "thursday")
    private boolean thursday;

    @CsvBindByName(column = "friday")
    private boolean friday;

    @CsvBindByName(column = "saturday")
    private boolean saturday;

    @CsvBindByName(column = "sunday")
    private boolean sunday;

    @CsvCustomBindByName(column = "start_date", converter = LocalDateConverter.class)
    private LocalDate startDate;

    @CsvCustomBindByName(column = "end_date", converter = LocalDateConverter.class)
    private LocalDate endDate;
}
