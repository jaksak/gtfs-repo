package pl.longhorn.gtfsrepo.trips.directionid;

import lombok.*;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum DirectionId {

    ONE(ByteUtils.asByte(0)),
    OPPOSITE(ByteUtils.asByte(1));

    private final byte baseValue;

    public static List<DirectionId> getAll() {
        return List.of(
                ONE,
                OPPOSITE
        );
    }
}
