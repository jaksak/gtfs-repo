package pl.longhorn.gtfsrepo.primitive;

import com.opencsv.bean.AbstractBeanField;

import java.time.Duration;

public class DurationConverter extends AbstractBeanField<String, Duration> {

    @Override
    protected Duration convert(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        var splittedValues = value.split(":");
        if (splittedValues.length != 3) {
            throw new IllegalArgumentException(value + "is invalid");
        }
        var duration = Duration.ofHours(Long.parseLong(splittedValues[0]));
        duration = duration.plusMinutes(Long.parseLong(splittedValues[1]));
        duration = duration.plusSeconds(Long.parseLong(splittedValues[2]));
        return duration;
    }
}
