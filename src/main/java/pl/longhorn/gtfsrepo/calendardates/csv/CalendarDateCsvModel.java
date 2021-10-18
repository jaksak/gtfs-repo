package pl.longhorn.gtfsrepo.calendardates.csv;

import com.opencsv.bean.*;
import lombok.Data;
import pl.longhorn.gtfsrepo.primitive.LocalDateConverter;

import java.time.LocalDate;

@Data
public class CalendarDateCsvModel {

    @CsvBindByName(column = "service_id")
    private String serviceId;
    @CsvCustomBindByName(column = "date", converter = LocalDateConverter.class)
    private LocalDate date;
    @CsvBindByName(column = "exception_type")
    private byte exceptionTypeValue;
}
