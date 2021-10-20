package pl.longhorn.gtfsrepo.routes.type;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class RouteType {
    private String name;
    private short baseValue;

    public RouteType(short baseValue) {
        this.baseValue = baseValue;
    }
}
