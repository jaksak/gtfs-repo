package pl.longhorn.gtfsrepo.feedinfo;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class FeedInfo {
    private int id;
    private int schemaId;

    private String feedPublisherName;
    private String feedPublisherUrl;
    private String defaultLang;
    private String feedContactEmail;
    private String feedContactUrl;
    private String feedLang;
    private String feedVersion;
    private LocalDate feedStartDate;
    private LocalDate feedEndDate;
}
