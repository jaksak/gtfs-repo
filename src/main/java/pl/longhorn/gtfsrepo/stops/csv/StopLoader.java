package pl.longhorn.gtfsrepo.stops.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

@Component
public class StopLoader {

    public List<StopCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<StopCsvModel>(reader)
                .withType(StopCsvModel.class)
                .build()
                .parse();
    }
}
