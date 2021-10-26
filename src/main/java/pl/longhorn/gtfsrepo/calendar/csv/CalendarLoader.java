package pl.longhorn.gtfsrepo.calendar.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

@Component
public class CalendarLoader {

    public List<CalendarCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<CalendarCsvModel>(reader)
                .withType(CalendarCsvModel.class)
                .build()
                .parse();
    }
}
