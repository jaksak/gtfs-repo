package pl.longhorn.gtfsrepo.feedinfo;

import java.time.LocalDate;

public class FeedInfo {
    private int id;
    private int schemaId;

    private String feedPublisherName;
    private String feedPublisherUrl;
    private String feedLang;
    private String feedVersion;
    private LocalDate feedStartDate;
    private LocalDate feedEndDate;
}
