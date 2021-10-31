package pl.longhorn.gtfsrepo.routes.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

@Component
public class RouteLoader {

    public List<RouteCsvModel> load(Reader reader) {
        return new CsvToBeanBuilder<RouteCsvModel>(reader)
                .withType(RouteCsvModel.class)
                .withIgnoreEmptyLine(Boolean.TRUE)
                .build()
                .parse();
    }
}
