package pl.longhorn.gtfsrepo.trips.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class TripLoader {

    public List<TripCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<TripCsvModel>(reader)
                .withType(TripCsvModel.class)
                .build()
                .parse();
    }
}
