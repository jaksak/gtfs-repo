package pl.longhorn.gtfsrepo.feedinfo.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class FeedInfoLoader {

    public List<FeedInfoCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<FeedInfoCsvModel>(reader)
                .withType(FeedInfoCsvModel.class)
                .build()
                .parse();
    }
}
