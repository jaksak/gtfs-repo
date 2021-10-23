package pl.longhorn.gtfsrepo.trips.bikesallowed;

import lombok.*;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum BikesAllowed {

    //  No bike information for the trip.
    NO_INFORMATION(ByteUtils.asByte(0)),
    // Vehicle being used on this particular trip can accommodate at least one bicycle
    RIDER(ByteUtils.asByte(1)),
    // No bicycles are allowed on this trip
    IMPOSSIBLE(ByteUtils.asByte(2));
    private final byte baseValue;

    public static BikesAllowed defaultValue() {
        return NO_INFORMATION;
    }

    public static List<BikesAllowed> getAll() {
        return List.of(
                NO_INFORMATION,
                RIDER,
                IMPOSSIBLE
        );
    }
}
