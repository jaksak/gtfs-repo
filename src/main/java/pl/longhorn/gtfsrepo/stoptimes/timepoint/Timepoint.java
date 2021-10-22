package pl.longhorn.gtfsrepo.stoptimes.timepoint;

import lombok.*;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Timepoint {

    APPROXIMATE(ByteUtils.asByte(0)),
    EXACT(ByteUtils.asByte(1));

    private final Byte baseValue;

    public static Timepoint defaultValue() {
        return EXACT;
    }

    public static List<Timepoint> getAll() {
        return List.of(
                APPROXIMATE,
                EXACT
        );
    }
}
