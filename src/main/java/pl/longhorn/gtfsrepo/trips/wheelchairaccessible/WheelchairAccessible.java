package pl.longhorn.gtfsrepo.trips.wheelchairaccessible;

import lombok.*;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum WheelchairAccessible {

    // No accessibility information for the stop
    NO_INFORMATION(ByteUtils.asByte(0)),
    // Vehicle being used on this particular trip can accommodate at least one rider in a wheelchair.
    RIDER(ByteUtils.asByte(1)),
    // No riders in wheelchairs can be accommodated on this trip
    IMPOSSIBLE(ByteUtils.asByte(2));
    private final byte baseValue;

    public static WheelchairAccessible defaultValue() {
        return NO_INFORMATION;
    }

    public static List<WheelchairAccessible> getAll() {
        return List.of(
                NO_INFORMATION,
                RIDER,
                IMPOSSIBLE
        );
    }
}
