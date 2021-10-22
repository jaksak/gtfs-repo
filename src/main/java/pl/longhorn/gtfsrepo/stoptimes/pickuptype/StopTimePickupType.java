package pl.longhorn.gtfsrepo.stoptimes.pickuptype;

import lombok.*;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum StopTimePickupType {

    REGULAR(ByteUtils.asByte(0)),
    NO(ByteUtils.asByte(1)),
    PHONE(ByteUtils.asByte(2)),
    DRIVER(ByteUtils.asByte(3));

    private final byte baseValue;

    public static StopTimePickupType defaultValue() {
        return REGULAR;
    }

    public static List<StopTimePickupType> getAll() {
        return List.of(
                REGULAR,
                NO,
                PHONE,
                DRIVER
        );
    }
}
