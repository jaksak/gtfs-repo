package pl.longhorn.gtfsrepo.routes.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.agency.Agency;
import pl.longhorn.gtfsrepo.bundle.GtfsBundleWorkingData;
import pl.longhorn.gtfsrepo.routes.Route;
import pl.longhorn.gtfsrepo.routes.RoutesRepository;
import pl.longhorn.gtfsrepo.routes.pickuptype.PickupMapper;
import pl.longhorn.gtfsrepo.routes.type.RouteTypeMapper;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RouteTranslator {

    private final RouteTypeMapper routeTypeMapper;
    private final PickupMapper pickupMapper;
    private final RoutesRepository routesRepository;

    public GtfsBundleWorkingData translate(GtfsBundleWorkingData data) {
        List<Route> routes = new ArrayList<>(data.getRoutes().size());
        for (RouteCsvModel csvModel : data.getRoutes()) {
            Integer agencyId = findAgencyId(csvModel.getAgencyId(), data.getSavedAgencies());
            Route route = map(csvModel, data.getSchemaVersion(), agencyId);
            routes.add(routesRepository.save(route));
        }
        data.setSavedRoutes(routes);
        return data;
    }

    private Integer findAgencyId(String agencyId, Map<String, Agency> savedAgencies) {
        if (agencyId == null) {
            return null;
        }
        var agency = savedAgencies.get(agencyId);
        return agency == null ? null : agency.getId();
    }

    private Route map(RouteCsvModel csvModel, SchemaVersion schemaVersion, Integer agencyId) {
        return Route.builder()
                .schemaId(schemaVersion.getId())
                .routeExternalId(csvModel.getRouteExternalId())
                .agencyId(agencyId)
                .routeShortName(csvModel.getRouteShortName())
                .routeLongName(csvModel.getRouteLongName())
                .routeDesc(csvModel.getRouteDesc())
                .routeType(routeTypeMapper.map(csvModel.getRouteTypeValue()))
                .routeUrl(csvModel.getRouteUrl())
                .routeColor(csvModel.getRouteColor())
                .routeTextColor(csvModel.getRouteTextColor())
                .routeSortOrder(csvModel.getRouteSortOrder())
                .continuousPickup(pickupMapper.map(csvModel.getContinuousPickupValue()))
                .continuousDropOff(pickupMapper.map(csvModel.getContinuousDropOffValue()))
                .build();
    }
}
