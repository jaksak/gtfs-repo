package pl.longhorn.gtfsrepo.stops;

import lombok.Builder;
import lombok.Data;
import pl.longhorn.gtfsrepo.stops.locationtype.LocationType;
import pl.longhorn.gtfsrepo.stops.wheelchairboarding.WheelchairBoarding;

@Data
@Builder
public class Stop {
    private int id;
    private int schemaId;
    private String stopExternalId;
    private String stopCode;
    private String stopName;
    private String stopDesc;
    private String stopLat;
    private String stopLon;
    // until no requirement: used as string
    private String zoneId;
    private String stopUrl;
    private LocationType locationType;
    private String parentStation;
    private String stopTimezone;
    private WheelchairBoarding wheelchairBoarding;
    // until no requirement: used as string
    private String levelId;
    private String platformCode;
}
