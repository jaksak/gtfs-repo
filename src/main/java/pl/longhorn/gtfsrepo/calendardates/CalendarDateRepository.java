package pl.longhorn.gtfsrepo.calendardates;

import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarDateRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public CalendarDateRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_calendar_date")
                .usingGeneratedKeyColumns("id");
    }

    public CalendarDate save(CalendarDate calendarDate) {
        var id = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource()
                .addValue("schema_id", calendarDate.getSchemaId())
                .addValue("service_id", calendarDate.getServiceId())
                .addValue("date", calendarDate.getDate())
                .addValue("exception_type", calendarDate.getExceptionType().getBaseValue()));
        calendarDate.setId(id.intValue());
        return calendarDate;
    }
}
