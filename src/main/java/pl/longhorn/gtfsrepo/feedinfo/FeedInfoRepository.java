package pl.longhorn.gtfsrepo.feedinfo;

import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class FeedInfoRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public FeedInfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_feed_info")
                .usingGeneratedKeyColumns("id");
    }

    public FeedInfo save(FeedInfo feedInfo) {
        var params = new MapSqlParameterSource()
                .addValue("schema_id", feedInfo.getSchemaId())
                .addValue("feed_publisher_name", feedInfo.getFeedPublisherName())
                .addValue("feed_publisher_url", feedInfo.getFeedPublisherUrl())
                .addValue("default_lang", feedInfo.getDefaultLang())
                .addValue("feed_contact_email", feedInfo.getFeedContactEmail())
                .addValue("feed_contact_url", feedInfo.getFeedContactUrl())
                .addValue("feed_lang", feedInfo.getFeedLang())
                .addValue("feed_version", feedInfo.getFeedVersion())
                .addValue("feed_start_date", feedInfo.getFeedStartDate())
                .addValue("feed_end_date", feedInfo.getFeedEndDate());
        var id = simpleJdbcInsert.executeAndReturnKey(params);
        feedInfo.setId(id.intValue());
        return feedInfo;
    }
}
