package pl.longhorn.gtfsrepo.routes;

import pl.longhorn.gtfsrepo.routes.pickuptype.PickupType;
import pl.longhorn.gtfsrepo.routes.type.RouteType;

public class Route {
    private int id;
    private int schemaId;

    private String routeId;
    private String agencyId;
    private String routeShortName;
    private String routeLongName;
    private String routeDesc;
    private RouteType routeType;
    private String routeUrl;
    private String routeColor;
    private String routeTextColor;
    private int routeSortOrder;
    private PickupType continuousPickup;
    private PickupType continuousDropOff;
}
