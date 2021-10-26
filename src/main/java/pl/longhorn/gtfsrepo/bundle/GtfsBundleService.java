package pl.longhorn.gtfsrepo.bundle;

import lombok.*;
import org.springframework.stereotype.Service;
import pl.longhorn.gtfsrepo.agency.csv.*;
import pl.longhorn.gtfsrepo.calendar.csv.*;
import pl.longhorn.gtfsrepo.calendardates.csv.*;
import pl.longhorn.gtfsrepo.feedinfo.csv.*;
import pl.longhorn.gtfsrepo.routes.csv.*;
import pl.longhorn.gtfsrepo.schemaversion.*;
import pl.longhorn.gtfsrepo.stops.csv.*;
import pl.longhorn.gtfsrepo.stoptimes.csv.*;
import pl.longhorn.gtfsrepo.trips.csv.*;
import pl.longhorn.gtfsrepo.zip.ZipEntryCaller;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GtfsBundleService {

    private final SchemaVersionRepository schemaVersionRepository;

    private final AgencyLoader agencyLoader;
    private final CalendarLoader calendarLoader;
    private final CalendarDateLoader calendarDateLoader;
    private final FeedInfoLoader feedInfoLoader;
    private final RouteLoader routeLoader;
    private final StopTimeLoader stopTimeLoader;
    private final StopLoader stopLoader;
    private final TripLoader tripLoader;

    public void run(InputStream inputStream, int customerId) throws IOException {
        var schemaVersion = prepareSchemaVersion(customerId);
        var csvData = GtfsCsvBundleData.builder();
        new ZipEntryCaller(inputStream).call(f -> prepare(f, csvData));
        save(csvData.build(), schemaVersion);
    }

    private void save(GtfsCsvBundleData csvData, SchemaVersion schemaVersion) {
        System.out.println(csvData);
    }

    private void prepare(ZipEntryCaller.SimpleZippedFile f, GtfsCsvBundleData.GtfsCsvBundleDataBuilder csvData) {
        try (FileReader fileReader = new FileReader(f.getPathToFile().toFile())) {
            switch (f.getOriginalName()) {
                case "agency.txt" -> csvData.agencies(agencyLoader.load(fileReader));
                case "calendar.txt" -> csvData.calendar(calendarLoader.load(fileReader));
                case "calendar_dates.txt" -> csvData.calendarDates(calendarDateLoader.load(fileReader));
                case "feed_info.txt" -> csvData.feedInfos(feedInfoLoader.load(fileReader));
                case "routes.txt" -> csvData.routes(routeLoader.load(fileReader));
                case "stop_times.txt" -> csvData.stopTimes(stopTimeLoader.load(fileReader));
                case "stops.txt" -> csvData.stops(stopLoader.load(fileReader));
                case "trips.txt" -> csvData.trips(tripLoader.load(fileReader));
                default -> System.out.println("unknow file " + f.getOriginalName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SchemaVersion prepareSchemaVersion(int customerId) {
        var schema = SchemaVersion.builder()
                .customerId(customerId)
                .createdTime(LocalDateTime.now())
                .isActive(Boolean.TRUE)
                .build();
        return schemaVersionRepository.add(schema);
    }

    @Builder
    @ToString
    static
    class GtfsCsvBundleData {
        private final List<AgencyCsvModel> agencies;
        private final List<CalendarCsvModel> calendar;
        private final List<CalendarDateCsvModel> calendarDates;
        private final List<FeedInfoCsvModel> feedInfos;
        private final List<RouteCsvModel> routes;
        private final List<StopTimeCsvModel> stopTimes;
        private final List<StopCsvModel> stops;
        private final List<TripCsvModel> trips;
    }
}
