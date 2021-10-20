package pl.longhorn.gtfsrepo.routes.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class RouteLoader {

    public List<RouteCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<RouteCsvModel>(reader)
                .withType(RouteCsvModel.class)
                .build()
                .parse();
    }
}
