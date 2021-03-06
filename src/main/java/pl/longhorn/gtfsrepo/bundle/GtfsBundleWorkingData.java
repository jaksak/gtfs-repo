package pl.longhorn.gtfsrepo.bundle;

import lombok.Builder;
import lombok.Data;
import pl.longhorn.gtfsrepo.agency.Agency;
import pl.longhorn.gtfsrepo.agency.csv.AgencyCsvModel;
import pl.longhorn.gtfsrepo.calendar.Calendar;
import pl.longhorn.gtfsrepo.calendar.csv.CalendarCsvModel;
import pl.longhorn.gtfsrepo.calendardates.csv.CalendarDateCsvModel;
import pl.longhorn.gtfsrepo.feedinfo.FeedInfo;
import pl.longhorn.gtfsrepo.feedinfo.csv.FeedInfoCsvModel;
import pl.longhorn.gtfsrepo.routes.Route;
import pl.longhorn.gtfsrepo.routes.csv.RouteCsvModel;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;
import pl.longhorn.gtfsrepo.service.Service;
import pl.longhorn.gtfsrepo.stops.Stop;
import pl.longhorn.gtfsrepo.stops.csv.StopCsvModel;
import pl.longhorn.gtfsrepo.stoptimes.csv.StopTimeCsvModel;
import pl.longhorn.gtfsrepo.trips.csv.TripCsvModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class GtfsBundleWorkingData {
    private final List<AgencyCsvModel> agencyCsv;
    private final List<CalendarCsvModel> calendar;
    private final List<CalendarDateCsvModel> calendarDates;
    private final List<FeedInfoCsvModel> feedInfos;
    private final List<RouteCsvModel> routes;
    private final List<StopTimeCsvModel> stopTimes;
    private final List<StopCsvModel> stops;
    private final List<TripCsvModel> trips;

    private final SchemaVersion schemaVersion;

    @Builder.Default
    private Map<String, Agency> savedAgencies = new HashMap<>();
    @Builder.Default
    private List<Calendar> savedCalendar = new ArrayList<>();
    @Builder.Default
    private List<FeedInfo> savedFeedInfos = new ArrayList<>();
    @Builder.Default
    private List<Route> savedRoutes = new ArrayList<>();
    @Builder.Default
    private List<Stop> savedStops = new ArrayList<>();
    @Builder.Default
    private Map<String, Service> savedServices = new HashMap<>();
}
