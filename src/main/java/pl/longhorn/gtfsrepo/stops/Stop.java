package pl.longhorn.gtfsrepo.stops;

import pl.longhorn.gtfsrepo.stops.locationtype.LocationType;
import pl.longhorn.gtfsrepo.stops.wheelchairboarding.WheelchairBoarding;

public class Stop {
    private String stopId;
    private String stopCode;
    private String stopName;
    private String stopDesc;
    private String stopLat;
    private String stopLon;
    private String zoneId;
    private String stopUrl;
    private LocationType locationType;
    private String parentStation;
    private String stopTimezone;
    private WheelchairBoarding wheelchairBoarding;
    private String levelId;
    private String platformCode;
}
