package pl.longhorn.gtfsrepo.stoptimes.pickuptype;

import java.util.Map;
import java.util.stream.Collectors;

public class StopTimePickupTypeMapper {

    private final Map<Byte, StopTimePickupType> pickupTypeByBaseValue;

    public StopTimePickupTypeMapper() {
        pickupTypeByBaseValue = StopTimePickupType.getAll().stream()
                .collect(Collectors.toMap(StopTimePickupType::getBaseValue, t -> t));
    }

    public StopTimePickupType map(Byte value) {
        if (value == null) {
            return StopTimePickupType.defaultValue();
        }
        var result = pickupTypeByBaseValue.get(value);
        if (result == null) {
            throw new IllegalArgumentException(value + " is invalid");
        }
        return result;
    }
}
