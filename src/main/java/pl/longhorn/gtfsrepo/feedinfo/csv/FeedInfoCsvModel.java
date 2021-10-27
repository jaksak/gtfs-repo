package pl.longhorn.gtfsrepo.feedinfo.csv;

import com.opencsv.bean.*;
import lombok.Data;
import pl.longhorn.gtfsrepo.primitive.LocalDateConverter;

import java.time.LocalDate;

@Data
public class FeedInfoCsvModel {
    @CsvBindByName(column = "feed_publisher_name")
    private String feedPublisherName;
    @CsvBindByName(column = "feed_publisher_url")
    private String feedPublisherUrl;
    @CsvBindByName(column = "feed_lang")
    private String feedLang;
    @CsvBindByName(column = "feed_version")
    private String feedVersion;
    @CsvCustomBindByName(column = "feed_start_date", converter = LocalDateConverter.class)
    private LocalDate feedStartDate;
    @CsvCustomBindByName(column = "feed_end_date", converter = LocalDateConverter.class)
    private LocalDate feedEndDate;
    @CsvBindByName(column = "default_lang")
    private String defaultLang;
    @CsvBindByName(column = "feed_contact_email")
    private String feedContactEmail;
    @CsvBindByName(column = "feed_contact_url")
    private String feedContactUrl;
}
