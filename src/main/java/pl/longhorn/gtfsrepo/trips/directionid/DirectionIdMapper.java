package pl.longhorn.gtfsrepo.trips.directionid;

import java.util.Map;
import java.util.stream.Collectors;

public class DirectionIdMapper {

    private final Map<Byte, DirectionId> directionByBaseValue;

    public DirectionIdMapper() {
        directionByBaseValue = DirectionId.getAll().stream()
                .collect(Collectors.toMap(DirectionId::getBaseValue, d -> d));
    }

    public DirectionId map(Byte value) {
        if (value == null) {
            return null;
        }
        var result = directionByBaseValue.get(value);
        if (result == null) {
            throw new IllegalArgumentException(value + " is invalid");
        }
        return result;
    }
}
