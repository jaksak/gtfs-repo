package pl.longhorn.gtfsrepo.service;

import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public ServiceRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_service")
                .usingGeneratedKeyColumns("id");
    }

    public Service save(Service service) {
        var id = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource()
                .addValue("schema_id", service.getSchemaId())
                .addValue("name", service.getName())
                .addValue("external_service_id", service.getExternalServiceId()));
        service.setId(id.intValue());
        return service;
    }
}
