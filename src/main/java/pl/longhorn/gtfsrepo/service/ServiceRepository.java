package pl.longhorn.gtfsrepo.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ServiceRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ServiceRowMapper serviceRowMapper;

    public ServiceRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_service")
                .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        serviceRowMapper = new ServiceRowMapper();
    }

    public Service save(Service service) {
        var id = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource()
                .addValue("schema_id", service.getSchemaId())
                .addValue("name", service.getName())
                .addValue("external_service_id", service.getExternalServiceId()));
        service.setId(id.intValue());
        return service;
    }

    public Service findBySchemaIdAndExternalServiceId(int schemaId, String externalServiceId) {
        var params = new MapSqlParameterSource()
                .addValue("schema_id", schemaId)
                .addValue("external_service_id", externalServiceId);
        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "SELECT * FROM `gtfs_service` WHERE schema_id = :schema_id AND external_service_id = :external_service_id",
                    params,
                    serviceRowMapper
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    class ServiceRowMapper implements RowMapper<Service> {

        @Override
        public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Service.builder()
                    .id(rs.getInt("id"))
                    .schemaId(rs.getInt("schema_id"))
                    .name(rs.getString("name"))
                    .externalServiceId(rs.getString("external_service_id"))
                    .build();
        }
    }
}
