package pl.longhorn.gtfsrepo.agency.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class AgencyCsvModel {
    @CsvBindByName(column = "agency_id")
    private String agencyId;
    @CsvBindByName(column = "agency_name")
    private String agencyName;
    @CsvBindByName(column = "agency_url")
    private String agencyUrl;
    @CsvBindByName(column = "agency_timezone")
    private String agencyTimezone;
    @CsvBindByName(column = "agency_lang")
    private String agencyLang;
    @CsvBindByName(column = "agency_phone")
    private String agencyPhone;
    @CsvBindByName(column = "agency_fare_url")
    private String agencyFareUrl;
    @CsvBindByName(column = "agency_email")
    private String agencyEmail;
}
