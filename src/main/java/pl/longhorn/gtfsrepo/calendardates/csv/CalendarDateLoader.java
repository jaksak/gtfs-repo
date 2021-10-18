package pl.longhorn.gtfsrepo.calendardates.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class CalendarDateLoader {

    public List<CalendarDateCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<CalendarDateCsvModel>(reader)
                .withType(CalendarDateCsvModel.class)
                .build()
                .parse();
    }
}
