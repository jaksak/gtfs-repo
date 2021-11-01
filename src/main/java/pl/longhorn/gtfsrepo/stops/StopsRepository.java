package pl.longhorn.gtfsrepo.stops;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class StopsRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StopsRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_stops")
                .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Stop save(Stop stop) {
        var params = new MapSqlParameterSource()
                .addValue("schema_id", stop.getSchemaId())
                .addValue("stop_external_id", stop.getStopExternalId())
                .addValue("stop_code", stop.getStopCode())
                .addValue("stop_name", stop.getStopName())
                .addValue("stop_desc", stop.getStopDesc())
                .addValue("stop_lat", stop.getStopLat())
                .addValue("stop_lon", stop.getStopLon())
                .addValue("zone_id", stop.getZoneId())
                .addValue("stop_url", stop.getStopUrl())
                .addValue("location_type", stop.getLocationType().getBaseValue())
                .addValue("parent_station", stop.getParentStation())
                .addValue("stop_timezone", stop.getStopTimezone())
                .addValue("wheelchair_boarding", stop.getWheelchairBoarding().getBaseValue())
                .addValue("level_id", stop.getLevelId())
                .addValue("platform_code", stop.getPlatformCode());
        var id = simpleJdbcInsert.executeAndReturnKey(params);
        stop.setId(id.intValue());
        return stop;
    }

    public void updateParentStation(int stationId, Integer parentStationId) {
        namedParameterJdbcTemplate.update(
                "UPDATE gtfs_stops SET parent_station = :parentStation WHERE id = :id",
                new MapSqlParameterSource()
                        .addValue("id", stationId)
                        .addValue("parentStation", parentStationId)
        );
    }
}
