package pl.longhorn.gtfsrepo.stoptimes;

import pl.longhorn.gtfsrepo.stoptimes.pickuptype.StopTimePickupType;
import pl.longhorn.gtfsrepo.stoptimes.timepoint.Timepoint;

import java.time.Duration;

public class StopTime {
    private int id;
    private int schemaId;

    private String tripId;
    private Duration arrivalTime;
    private Duration departureTime;
    private String stopId;
    private int stopSequence;
    private String stopHeadsign;
    private StopTimePickupType pickupType;
    private StopTimePickupType dropOffType;
    private StopTimePickupType continuousPickup;
    private StopTimePickupType continuousDropOff;
    private String shapeDistTraveled;
    private Timepoint timepoint;
}
