package pl.longhorn.gtfsrepo.trips.bikesallowed;

import java.util.Map;
import java.util.stream.Collectors;

public class BikesAllowedMapper {

    private final Map<Byte, BikesAllowed> boardingByBaseValue;

    public BikesAllowedMapper() {
        boardingByBaseValue = BikesAllowed.getAll().stream()
                .collect(Collectors.toMap(BikesAllowed::getBaseValue, b -> b));
    }

    public BikesAllowed map(Byte value) {
        if (value == null) {
            return BikesAllowed.defaultValue();
        }
        var result = boardingByBaseValue.get(value);
        if (result == null) {
            throw new IllegalArgumentException(value + " is invalid");
        }
        return result;
    }
}
