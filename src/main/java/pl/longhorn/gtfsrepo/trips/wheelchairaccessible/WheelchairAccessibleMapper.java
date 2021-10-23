package pl.longhorn.gtfsrepo.trips.wheelchairaccessible;

import java.util.Map;
import java.util.stream.Collectors;

public class WheelchairAccessibleMapper {

    private final Map<Byte, WheelchairAccessible> boardingByBaseValue;

    public WheelchairAccessibleMapper() {
        boardingByBaseValue = WheelchairAccessible.getAll().stream()
                .collect(Collectors.toMap(WheelchairAccessible::getBaseValue, b -> b));
    }

    public WheelchairAccessible map(Byte value) {
        if (value == null) {
            return WheelchairAccessible.defaultValue();
        }
        var result = boardingByBaseValue.get(value);
        if (result == null) {
            throw new IllegalArgumentException(value + " is invalid");
        }
        return result;
    }
}
