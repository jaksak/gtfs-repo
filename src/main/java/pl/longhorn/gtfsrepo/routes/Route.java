package pl.longhorn.gtfsrepo.routes;

import lombok.Builder;
import lombok.Data;
import pl.longhorn.gtfsrepo.routes.pickuptype.PickupType;
import pl.longhorn.gtfsrepo.routes.type.RouteType;

@Data
@Builder
public class Route {
    private int id;
    private int schemaId;

    private String routeExternalId;
    private Integer agencyId;
    private String routeShortName;
    private String routeLongName;
    private String routeDesc;
    private RouteType routeType;
    private String routeUrl;
    private String routeColor;
    private String routeTextColor;
    private Integer routeSortOrder;
    private PickupType continuousPickup;
    private PickupType continuousDropOff;
}
