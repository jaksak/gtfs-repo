package pl.longhorn.gtfsrepo.stops.wheelchairboarding;

import java.util.Map;
import java.util.stream.Collectors;

public class WheelchairBoardingMapper {

    private final Map<Byte, WheelchairBoarding> boardingByBaseValue;

    public WheelchairBoardingMapper() {
        boardingByBaseValue = WheelchairBoarding.getAll().stream()
                .collect(Collectors.toMap(WheelchairBoarding::getBaseValue, b -> b));
    }

    public WheelchairBoarding map(Byte value) {
        if (value == null) {
            return WheelchairBoarding.defaultValue();
        }
        var result = boardingByBaseValue.get(value);
        if (result == null) {
            throw new IllegalArgumentException(value + " is invalid");
        }
        return result;
    }
}
