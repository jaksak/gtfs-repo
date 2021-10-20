package pl.longhorn.gtfsrepo.routes.pickuptype;

import lombok.*;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum PickupType {

    // Continuous stopping pickup
    CONTINUOUS(ByteUtils.asByte(0)),
    //  No continuous stopping pickup (DEFAULT)
    NO(ByteUtils.asByte(1)),
    // Must phone an agency to arrange continuous
    PHONE(ByteUtils.asByte(2)),
    // Must coordinate with a driver to arrange continuous
    DRIVER(ByteUtils.asByte(3));

    private final byte baseValue;

    public static List<PickupType> getAll() {
        return List.of(
                CONTINUOUS,
                NO,
                PHONE,
                DRIVER
        );
    }

    public static PickupType defaultValue() {
        return NO;
    }
}
