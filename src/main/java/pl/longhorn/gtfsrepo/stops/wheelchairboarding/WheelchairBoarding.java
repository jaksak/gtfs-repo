package pl.longhorn.gtfsrepo.stops.wheelchairboarding;

import lombok.*;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum WheelchairBoarding {

    // No accessibility information for the stop
    NO_INFORMATION(ByteUtils.asByte(0)),
    // Some vehicles at this stop can be boarded by a rider in a wheelchair
    RIDER(ByteUtils.asByte(1)),
    // Wheelchair boarding is not possible at this stop
    IMPOSSIBLE(ByteUtils.asByte(2));
    private final byte baseValue;

    public static WheelchairBoarding defaultValue() {
        return NO_INFORMATION;
    }

    public static List<WheelchairBoarding> getAll() {
        return List.of(
                NO_INFORMATION,
                RIDER,
                IMPOSSIBLE
        );
    }
}
