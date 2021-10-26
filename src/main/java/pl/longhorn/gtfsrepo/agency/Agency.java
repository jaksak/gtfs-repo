package pl.longhorn.gtfsrepo.agency;

import lombok.*;

@Data
@Builder
public class Agency {
    private int id;
    private int schemaId;

    private String agencyExternalId;
    private String agencyName;
    private String agencyUrl;
    private String agencyTimezone;
    private String agencyLang;
    private String agencyPhone;
    private String agencyFareUrl;
    private String agencyEmail;
}
