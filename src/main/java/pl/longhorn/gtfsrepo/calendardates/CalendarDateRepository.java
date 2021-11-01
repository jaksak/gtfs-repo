package pl.longhorn.gtfsrepo.calendardates;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CalendarDateRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public CalendarDateRepository(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws SQLException {
        this.simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_calendar_date")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
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

    public void batchSave(List<CalendarDate> dates) {
        var size = dates.size();
        jdbcTemplate.batchUpdate(
                "INSERT INTO `gtfs_calendar_date` (`schema_id`, `service_id`, `date`, `exception_type`) VALUES (?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        var calendarDate = dates.get(i);
                        preparedStatement.setInt(1, calendarDate.getSchemaId());
                        preparedStatement.setInt(2, calendarDate.getServiceId());
                        preparedStatement.setDate(3, Date.valueOf(calendarDate.getDate()));
                        preparedStatement.setByte(4, calendarDate.getExceptionType().getBaseValue());
                    }

                    @Override
                    public int getBatchSize() {
                        return size;
                    }
                }
        );
    }
}
