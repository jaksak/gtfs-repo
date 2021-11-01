package pl.longhorn.gtfsrepo.stoptimes.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

@Component
public class StopTimeLoader {

    public List<StopTimeCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<StopTimeCsvModel>(reader)
                .withType(StopTimeCsvModel.class)
                .withIgnoreEmptyLine(Boolean.TRUE)
                .build()
                .parse();
    }
}
