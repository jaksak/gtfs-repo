package pl.longhorn.gtfsrepo.calendardates.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

@Component
public class CalendarDateLoader {

    public List<CalendarDateCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<CalendarDateCsvModel>(reader)
                .withType(CalendarDateCsvModel.class)
                .build()
                .parse();
    }
}
