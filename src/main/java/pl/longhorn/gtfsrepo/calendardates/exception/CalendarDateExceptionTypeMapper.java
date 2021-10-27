package pl.longhorn.gtfsrepo.calendardates.exception;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CalendarDateExceptionTypeMapper {

    private final Map<Byte, CalendarDateExceptionType> typesByBaseValue;

    public CalendarDateExceptionTypeMapper() {
        typesByBaseValue = CalendarDateExceptionType.getAll().stream()
                .collect(Collectors.toMap(CalendarDateExceptionType::getBaseValue, t -> t));
    }

    public CalendarDateExceptionType map(byte baseValue) {
        var result = typesByBaseValue.get(baseValue);
        if (result == null) {
            throw new IllegalArgumentException(baseValue + " is invalid");
        } else {
            return result;
        }
    }
}
