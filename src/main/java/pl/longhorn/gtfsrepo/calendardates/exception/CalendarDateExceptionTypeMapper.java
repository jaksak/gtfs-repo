package pl.longhorn.gtfsrepo.calendardates.exception;

import java.util.Map;
import java.util.stream.Collectors;

public class CalendarDateExceptionTypeMapper {

    private final Map<Byte, CalendarDateExceptionType> typesByBaseValue;

    public CalendarDateExceptionTypeMapper() {
        typesByBaseValue = CalendarDateExceptionType.getAll().stream()
                .collect(Collectors.toMap(CalendarDateExceptionType::getBaseValue, t -> t));
    }

    public CalendarDateExceptionType map(byte baseValue) {
        return typesByBaseValue.get(baseValue);
    }
}
