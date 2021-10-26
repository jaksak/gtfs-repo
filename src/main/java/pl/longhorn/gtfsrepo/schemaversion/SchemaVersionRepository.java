package pl.longhorn.gtfsrepo.schemaversion;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class SchemaVersionRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public SchemaVersionRepository(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_schema_version")
                .usingGeneratedKeyColumns("id");
    }

    public SchemaVersion add(SchemaVersion schemaVersion) {
        var id = simpleJdbcInsert.executeAndReturnKey(Map.of(
                "customer_id", schemaVersion.getCustomerId(),
                "created_time", schemaVersion.getCreatedTime(),
                "is_active", schemaVersion.isActive()
        ));
        schemaVersion.setId(id.intValue());
        return schemaVersion;
    }
}
