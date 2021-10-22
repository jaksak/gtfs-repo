package pl.longhorn.gtfsrepo.stoptimes.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class StopTimeLoader {

    public List<StopTimeCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<StopTimeCsvModel>(reader)
                .withType(StopTimeCsvModel.class)
                .build()
                .parse();
    }
}
