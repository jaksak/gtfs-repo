package pl.longhorn.gtfsrepo.calendardates.exception;

import lombok.*;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum CalendarDateExceptionType {

    ADD(ByteUtils.asByte(1)),
    REMOVE(ByteUtils.asByte(2));

    private final byte baseValue;

    public static List<CalendarDateExceptionType> getAll() {
        return List.of(
                ADD,
                REMOVE
        );
    }
}
