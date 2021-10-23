package pl.longhorn.gtfsrepo.stops.locationtype;

import lombok.*;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum LocationType {
    //  (or Platform). A location where passengers board or disembark from a transit vehicle. Is called a platform when defined within a parent_station
    STOP(ByteUtils.asByte(0)),
    // A physical structure or area that contains one or more platform
    STATION(ByteUtils.asByte(1)),
    //  A location where passengers can enter or exit a station from the street. If an entrance/exit belongs to multiple stations, it can be linked by pathways to both, but the data provider must pick one of them as parent
    ENTRANCE(ByteUtils.asByte(2)),
    // A location within a station, not matching any other location_type, which can be used to link together pathways define in pathways.txt
    GENERIC_NODE(ByteUtils.asByte(3)),
    // A specific location on a platform, where passengers can board and/or alight vehicles
    BOARDING_AREA(ByteUtils.asByte(4));

    private final byte baseValue;

    public static LocationType defaultValue() {
        return STOP;
    }

    public static List<LocationType> getAll() {
        return List.of(
                STOP,
                STATION,
                ENTRANCE,
                GENERIC_NODE,
                BOARDING_AREA
        );
    }
}
