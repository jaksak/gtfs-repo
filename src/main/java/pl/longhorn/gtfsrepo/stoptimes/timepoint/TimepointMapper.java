package pl.longhorn.gtfsrepo.stoptimes.timepoint;

import java.util.Map;
import java.util.stream.Collectors;

public class TimepointMapper {

    private final Map<Byte, Timepoint> timepointByBaseValue;

    public TimepointMapper() {
        timepointByBaseValue = Timepoint.getAll().stream()
                .collect(Collectors.toMap(Timepoint::getBaseValue, t -> t));
    }

    public Timepoint map(Byte value) {
        if (value == null) {
            return Timepoint.defaultValue();
        }
        var result = timepointByBaseValue.get(value);
        if (result == null) {
            throw new IllegalArgumentException(value + " is invalid");
        }
        return result;
    }
}
