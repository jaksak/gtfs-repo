package pl.longhorn.gtfsrepo.calendar;

import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public CalendarRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_calendar")
                .usingGeneratedKeyColumns("id");
    }

    public Calendar save(Calendar calendar) {
        var id = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource()
                .addValue("schema_id", calendar.getSchemaId())
                .addValue("service_id", calendar.getServiceId())
                .addValue("monday", calendar.isMonday())
                .addValue("tuesday", calendar.isTuesday())
                .addValue("wednesday", calendar.isWednesday())
                .addValue("thursday", calendar.isThursday())
                .addValue("friday", calendar.isFriday())
                .addValue("saturday", calendar.isSaturday())
                .addValue("sunday", calendar.isSunday())
                .addValue("start_date", calendar.getStartDate())
                .addValue("end_date", calendar.getEndDate())
        );
        calendar.setId(id.intValue());
        return calendar;
    }
}
