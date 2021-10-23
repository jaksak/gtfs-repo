package pl.longhorn.gtfsrepo.stops.locationtype;

import java.util.Map;
import java.util.stream.Collectors;

public class LocationTypeMapper {

    private final Map<Byte, LocationType> locationsByBaseValueMap;

    public LocationTypeMapper() {
        locationsByBaseValueMap = LocationType.getAll().stream()
                .collect(Collectors.toMap(LocationType::getBaseValue, t -> t));
    }

    public LocationType map(Byte value) {
        if (value == null) {
            return LocationType.defaultValue();
        }
        var result = locationsByBaseValueMap.get(value);
        if (result == null) {
            throw new IllegalArgumentException(value + " is invalid");
        }
        return result;
    }
}
