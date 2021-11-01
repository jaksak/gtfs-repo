package pl.longhorn.gtfsrepo.stops.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class StopCsvModel {
    @CsvBindByName(column = "stop_id")
    private String stopExternalId;
    @CsvBindByName(column = "stop_code")
    private String stopCode;
    @CsvBindByName(column = "stop_name")
    private String stopName;
    @CsvBindByName(column = "stop_desc")
    private String stopDesc;
    @CsvBindByName(column = "stop_lat")
    private String stopLat;
    @CsvBindByName(column = "stop_lon")
    private String stopLon;
    @CsvBindByName(column = "zone_id")
    private String zoneId;
    @CsvBindByName(column = "stop_url")
    private String stopUrl;
    @CsvBindByName(column = "location_type")
    private Byte locationType;
    @CsvBindByName(column = "parent_station")
    private String parentStation;
    @CsvBindByName(column = "stop_timezone")
    private String stopTimezone;
    @CsvBindByName(column = "wheelchair_boarding")
    private Byte wheelchairBoarding;
    @CsvBindByName(column = "level_id")
    private String levelId;
    @CsvBindByName(column = "platform_code")
    private String platformCode;
}
