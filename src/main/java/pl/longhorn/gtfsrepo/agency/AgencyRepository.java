package pl.longhorn.gtfsrepo.agency;

import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AgencyRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public AgencyRepository(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_agency")
                .usingGeneratedKeyColumns("id");
    }

    public Agency save(Agency agency) {
        var id = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource()
                .addValue("schema_id", agency.getSchemaId())
                .addValue("agency_external_id", agency.getAgencyExternalId())
                .addValue("agency_name", agency.getAgencyName())
                .addValue("agency_url", agency.getAgencyUrl())
                .addValue("agency_timezone", agency.getAgencyTimezone())
                .addValue("agency_lang", agency.getAgencyLang())
                .addValue("agency_phone", agency.getAgencyPhone())
                .addValue("agency_fare_url", agency.getAgencyFareUrl())
                .addValue("agency_email", agency.getAgencyEmail())
        );
        agency.setId(id.intValue());
        return agency;
    }
}
