package pl.longhorn.gtfsrepo.routes.type;

import pl.longhorn.gtfsrepo.primitive.ShortUtils;

import java.util.*;
import java.util.stream.Collectors;

public class RouteTypeMapper {

    // Tram, Streetcar, Light rail. Any light rail or street level system within a metropolitan area
    private static final RouteType TRAM = new RouteType("tram", ShortUtils.asShort(0));
    //  Subway, Metro. Any underground rail system within a metropolitan area
    private static final RouteType METRO = new RouteType("metro", ShortUtils.asShort(1));
    // Rail. Used for intercity or long-distance travel
    private static final RouteType RAIL = new RouteType("rail", ShortUtils.asShort(2));
    // Bus. Used for short- and long-distance bus routes
    private static final RouteType BUS = new RouteType("bus", ShortUtils.asShort(3));
    //Ferry. Used for short- and long-distance boat service
    private static final RouteType FERRY = new RouteType("ferry", ShortUtils.asShort(4));
    // Cable tram. Used for street-level rail cars where the cable runs beneath the vehicle, e.g., cable car in San Francisco
    private static final RouteType CABLE_TRAM = new RouteType("cable tram", ShortUtils.asShort(5));
    // Aerial lift, suspended cable car (e.g., gondola lift, aerial tramway). Cable transport where cabins, cars, gondolas or open chairs are suspended by means of one or more cables
    private static final RouteType AERIAL_LIFT = new RouteType("aerial lift", ShortUtils.asShort(6));
    // Funicular. Any rail system designed for steep inclines.
    private static final RouteType FUNICULAR = new RouteType("funicular", ShortUtils.asShort(7));
    // Trolleybus. Electric buses that draw power from overhead wires using poles
    private static final RouteType TROLLEYBUS = new RouteType("trolleybus", ShortUtils.asShort(11));
    // Monorail. Railway in which the track consists of a single rail or a beam
    private static final RouteType MONORAIL = new RouteType("monorail", ShortUtils.asShort(12));

    private final Map<Short, RouteType> typesByBaseValue;

    public RouteTypeMapper() {
        List<RouteType> allGtfsTypes = List.of(
                TRAM,
                METRO,
                RAIL,
                BUS,
                FERRY,
                CABLE_TRAM,
                AERIAL_LIFT,
                FUNICULAR,
                TROLLEYBUS,
                MONORAIL
        );
        typesByBaseValue = allGtfsTypes.stream()
                .collect(Collectors.toMap(RouteType::getBaseValue, t -> t));
    }

    public RouteType map(short baseValue) {
        var result = typesByBaseValue.get(baseValue);
        if (result == null) {
            return new RouteType(baseValue);
        } else {
            return result;
        }
    }
}
