package pl.longhorn.gtfsrepo.agency.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class AgencyLoader {

    public List<AgencyCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<AgencyCsvModel>(reader)
                .withType(AgencyCsvModel.class)
                .build()
                .parse();
    }
}
