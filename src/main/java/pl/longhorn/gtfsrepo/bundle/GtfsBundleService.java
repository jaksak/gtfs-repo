package pl.longhorn.gtfsrepo.bundle;

import lombok.RequiredArgsConstructor;
import pl.longhorn.gtfsrepo.agency.csv.AgencyCsvLoader;
import pl.longhorn.gtfsrepo.agency.csv.AgencyCsvTranslator;
import pl.longhorn.gtfsrepo.calendar.csv.CalendarCsvTranslator;
import pl.longhorn.gtfsrepo.calendar.csv.CalendarLoader;
import pl.longhorn.gtfsrepo.calendardates.csv.CalendarDateCsvTranslator;
import pl.longhorn.gtfsrepo.calendardates.csv.CalendarDateLoader;
import pl.longhorn.gtfsrepo.feedinfo.csv.FeedInfoCsvTranslator;
import pl.longhorn.gtfsrepo.feedinfo.csv.FeedInfoLoader;
import pl.longhorn.gtfsrepo.routes.csv.RouteLoader;
import pl.longhorn.gtfsrepo.routes.csv.RouteTranslator;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersionRepository;
import pl.longhorn.gtfsrepo.stops.csv.StopLoader;
import pl.longhorn.gtfsrepo.stoptimes.csv.StopTimeLoader;
import pl.longhorn.gtfsrepo.trips.csv.TripLoader;
import pl.longhorn.gtfsrepo.zip.ZipEntryCaller;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class GtfsBundleService {

    private final SchemaVersionRepository schemaVersionRepository;

    private final AgencyCsvLoader agencyLoader;
    private final CalendarLoader calendarLoader;
    private final CalendarDateLoader calendarDateLoader;
    private final FeedInfoLoader feedInfoLoader;
    private final RouteLoader routeLoader;
    private final StopTimeLoader stopTimeLoader;
    private final StopLoader stopLoader;
    private final TripLoader tripLoader;

    private final AgencyCsvTranslator agencyCsvTranslator;
    private final CalendarCsvTranslator calendarCsvTranslator;
    private final CalendarDateCsvTranslator calendarDateCsvTranslator;
    private final FeedInfoCsvTranslator feedInfoCsvTranslator;
    private final RouteTranslator routeTranslator;

    public void run(InputStream inputStream, int customerId) throws IOException {
        var schemaVersion = prepareSchemaVersion(customerId);
        var csvData = GtfsBundleWorkingData.builder();
        new ZipEntryCaller(inputStream).call(f -> prepare(f, csvData));
        save(csvData.build(), schemaVersion);
    }

    private void save(GtfsBundleWorkingData data, SchemaVersion schemaVersion) {
        if (data.getAgencyCsv() != null) {
            data = agencyCsvTranslator.translate(data, schemaVersion);
        }

        if (data.getCalendar() != null) {
            data = calendarCsvTranslator.translate(data, schemaVersion);
        }

        if (data.getCalendarDates() != null) {
            data = calendarDateCsvTranslator.translate(data, schemaVersion);
        }

        if (data.getFeedInfos() != null) {
            data = feedInfoCsvTranslator.translate(data, schemaVersion);
        }

        if (data.getRoutes() != null) {
            data = routeTranslator.translate(data, schemaVersion);
        }
    }

    private void prepare(ZipEntryCaller.SimpleZippedFile f, GtfsBundleWorkingData.GtfsBundleWorkingDataBuilder csvData) {
        try (FileReader fileReader = new FileReader(f.getPathToFile().toFile())) {
            switch (f.getOriginalName()) {
                case "agency.txt" -> csvData.agencyCsv(agencyLoader.load(fileReader));
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
}
