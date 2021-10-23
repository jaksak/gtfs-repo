package pl.longhorn.gtfsrepo.trips;

import pl.longhorn.gtfsrepo.trips.bikesallowed.BikesAllowed;
import pl.longhorn.gtfsrepo.trips.directionid.DirectionId;
import pl.longhorn.gtfsrepo.trips.wheelchairaccessible.WheelchairAccessible;

public class Trip {
    private int id;
    private int schemaId;

    private String routeId;
    private String serviceId;
    private String tripId;
    private String tripHeadsign;
    private String tripShortName;
    private DirectionId directionId;
    private String blockId;
    private String shapeId;
    private WheelchairAccessible wheelchairAccessible;
    private BikesAllowed bikesAllowed;
}
