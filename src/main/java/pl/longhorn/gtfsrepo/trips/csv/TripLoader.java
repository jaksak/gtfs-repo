package pl.longhorn.gtfsrepo.trips.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

@Component
public class TripLoader {

    public List<TripCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<TripCsvModel>(reader)
                .withType(TripCsvModel.class)
                .build()
                .parse();
    }
}
