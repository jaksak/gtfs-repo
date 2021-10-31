package pl.longhorn.gtfsrepo.routes;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class RoutesRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public RoutesRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_routes")
                .usingGeneratedKeyColumns("id");
    }


    public Route save(Route route) {
        var params = new MapSqlParameterSource()
                .addValue("route_external_id", route.getRouteExternalId())
                .addValue("schema_id", route.getSchemaId())
                .addValue("agency_id", route.getAgencyId())
                .addValue("route_short_name", route.getRouteShortName())
                .addValue("route_long_name", route.getRouteLongName())
                .addValue("route_desc", route.getRouteDesc())
                .addValue("route_type", route.getRouteType().getBaseValue())
                .addValue("route_url", route.getRouteUrl())
                .addValue("route_color", route.getRouteColor())
                .addValue("route_text_color", route.getRouteTextColor())
                .addValue("route_sort_order", route.getRouteSortOrder())
                .addValue("continuous_pickup", route.getContinuousPickup().getBaseValue())
                .addValue("continuous_drop_off", route.getContinuousDropOff().getBaseValue());
        var id = simpleJdbcInsert.executeAndReturnKey(params);
        route.setId(id.intValue());
        return route;
    }
}
